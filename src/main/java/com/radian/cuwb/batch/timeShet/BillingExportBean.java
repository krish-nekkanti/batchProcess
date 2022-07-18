package com.radian.cuwb.batch.timeShet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.radian.cuwbilling.billing.common.bo.codes.ARTransmissionStatus;
import com.radian.cuwbilling.billing.common.bo.codes.BillingExportType;
import com.radian.cuwbilling.billing.common.bs.BillingException;
import com.radian.cuwbilling.billing.common.os.persistence.ARTrxMapper;
import com.radian.cuwbilling.billing.common.os.persistence.BillingMapper;
import com.radian.cuwbilling.billing.cuw.bo.domain.AROutboundTrx;
import com.radian.cuwbilling.billing.cuw.bo.dto.ARTransactionSummaryDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.BillingProfileSummaryDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.PendingInvoiceSummaryDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.request.BillingProfileRequestDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.BillingProfileDTO;
import com.radian.cuwbilling.billing.cuw.bs.BillingExportAdministration;
import com.radian.cuwbilling.billing.cuw.bs.CUWBillingException;
import com.radian.cuwbilling.billing.cuw.bs.messaging.AROutboundSender;
import com.radian.cuwbilling.common.bo.codes.AxiomEntityType;
import com.radian.cuwbilling.common.bo.domain.impl.NullEntityImpl;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.cuwbilling.system.batch.bo.dto.ImportExportMsgDTO;
import com.radian.cuwbilling.system.batch.bs.ImportExportMsgAdminService;
import com.radian.cuwbilling.system.batch.bs.ImportExportMsgException;
import com.radian.cuwbilling.system.common.bo.code.ImportExportMsgType;
import com.radian.cuwbilling.system.messaging.bo.dto.ResponseInfo;
import com.radian.cuwbilling.system.notification.bo.code.AxiomEventCategory;
import com.radian.cuwbilling.system.notification.bs.eventrouter.EventRouterDelegate;
import com.radian.foundation.common.exception.ApplicationException;
import com.radian.foundation.os.messaging.util.UUIDUtil;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;
import com.radian.webserviceclient.WSClientCMD;
import com.radian.webserviceclient.WSClientException;
import com.radian.webserviceclient.WSClientExceptionStatus;
import com.radian.webserviceclient.WSClientFactory;

/**
 * Exporting CUW Billing Transactions
 * 
 * BillingExportBean is designed for exporting Billing Transactions to
 * peoplesoft system. Methods of this class will be invoked by a batch job
 * everyday. There are four public methods and two private methods defined in
 * this class PUBLIC Methods exportBillingProfiles() exportBillingInvoices()
 * updateProfileStatus() updateInvoiceStatus() These methods are invoked for
 * exporting billing profile,invoices and updating myRadian Database with
 * changed status.
 * 
 * PRIVATE methods getBillingProfileSummary() getBillingInvoiceSummary() This
 * methods are internally used by public methods.
 * 
 * @author Shekhar Guddimath
 * @date 02/04/2004
 * @version 1.0
 */

public class BillingExportBean 
{

    private static final String EXPORT_SUCCESS = "0";
    
    private static WSClientCMD wsCMD = null;

    private static final String ACTIVE_CODE = "ACTV";
    
    private static final String INACTIVE_CODE = "INAC";
    
    private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    
    public BillingExportBean()
    {
        super();
    }

    /*
     * Method : getBillingProfileSummary() Purpose : Fetch all Billing profile
     * which are created or updated on or after date on which batch job runs.
     * Input Parameters : Date Return Type : Collection Exception :
     * CUWBillingException
     * 
     */

    private Collection getBillingProfileSummary() throws CUWBillingException, WSClientException, WSClientExceptionStatus
    {

        getLogger().entering(this.getClass(), "getBillingProfileSummary");
        Collection profiles = null;
        Collection profileList = new ArrayList();
        Collection summaryDTOs = new ArrayList();
        
        try
        {
            wsCMD = this.getCMDClient();
            BillingProfileRequestDTO request = new BillingProfileRequestDTO();
            
            //get all active billing profiles
            request.setActivationStatus(ACTIVE_CODE);
            profiles = wsCMD.getBillingProfiles(request);
            profileList.addAll(profiles);
            
          //get all inactive billing profiles
            request.setActivationStatus(INACTIVE_CODE);
            profiles = wsCMD.getBillingProfiles(request);
            profileList.addAll(profiles);
            
            //map from BillingProfileDTO to BillingProfileSummaryDTO
            summaryDTOs = this.mapProfileDTOs(profileList);
            
                        
        } catch (ApplicationException e)
        {
            getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            throw new CUWBillingException("PersistenceProviderException in " + "BillingExportBean.getBillingProfileSummary()", e);
        }
        catch(ParseException e)
        {
        	getLogger().error(e.getMessage(), e);
        	throw new CUWBillingException("parse exception in " + "BillingExportBean.getBillingProfileSummary()", e);
        	
        }
        finally
        {
            //forceProviderClose(provider);
        }
        getLogger().exiting(this.getClass(), "getBillingProfileSummary");

        return summaryDTOs;
    }// Eof getBillingProfileSummary()

    /*
     * Method : getBillingInvoiceSummary() Purpose : Fetch all invoices whose
     * ARTransmission status is Ready. Input Parameters : None Return Type :
     * ARTransactionSummaryDTO Exception : CUWBillingException
     * 
     */
    private ARTransactionSummaryDTO getBillingInvoiceSummary(BillingExportType invoiceType) throws CUWBillingException
    {
    	getLogger().entering(this.getClass(), "getBillingInvoicesSummary");
    	
    	System.out.println("Entering getBillingInvoiceSummary method");
        PersistenceProvider provider = null;

        Collection resultList = null;

        ARTransactionSummaryDTO arTransactionSummary = null;

        getLogger().entering(this.getClass(), "getBillingInvoicesSummary");
        try
        {
            provider = this.getProvider();
            BillingMapper mapper = new BillingMapper(provider);
            arTransactionSummary = mapper.getBillingInvoiceSummary(invoiceType);
        } catch (PersistenceProviderException e)
        {
            getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            throw new CUWBillingException("PersistenceProviderException in " + "BillingExportBean.getBillingInvoiceSummary()", e);
        } catch (ParseException e)
        {
            getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            throw new CUWBillingException("ParseException in " + "BillingExportBean.getBillingInvoiceSummary()", e);
        } finally
        {
            forceProviderClose(provider);
        }
        
        getLogger().exiting(this.getClass(), "getBillingInvoicesSummary");
        
        System.out.println("Leaving getBillingInvoiceSummary method");
        return arTransactionSummary;
    }// Eof getBillingProfileSummary()

    /*
     * Method : exportBillingProfiles() Purpose : export the collection of
     * billing profiles to peoplesoft. Input Parameters : Date Return Type :
     * Void Exception : CUWBillingException,BillingException
     * 
     */

    public void exportBillingProfiles(Date executeDate) throws CUWBillingException, BillingException
    {
    	getLogger().entering(this.getClass(), "exportBillingProfiles");
        String trackingNumber = UUIDUtil.getUUID();
        boolean ftpStatus = false;        
        
        try
        {
        	Collection billProfileSummary = getBillingProfileSummary();
        	
            int counter = 0;
            Date todayDate = new Date();

            if (billProfileSummary != null && billProfileSummary.size() > 0)
            {
                counter = billProfileSummary.size();
            }
            AROutboundSender sender = AROutboundSender.getSender();
            ftpStatus = sender.sendCustomerData(billProfileSummary);
            if(ftpStatus)
            {
            	logImportExportNotification(trackingNumber, Boolean.TRUE, "Receipt of Profile export confirmed by recipient", ImportExportMsgType.EXPORT_CUW_CUSTOMERS);
            }
            else
            {
            	logImportExportNotification(trackingNumber, Boolean.FALSE, "Customer I/O Error",
                        ImportExportMsgType.EXPORT_CUW_CUSTOMERS);
                sendNotification("customer.export.io.failed", "Customer I/O Error"); 
            }
        } catch (BillingException be)
        {
            be.printStackTrace();
            sendNotification("customer.export.io.failed", be.getMessage());
            logImportExportNotification(trackingNumber, Boolean.FALSE, be.getMessage(), ImportExportMsgType.EXPORT_CUW_CUSTOMERS);

            throw be;

        } 
        catch(WSClientException e)
        {
        	e.printStackTrace();
            sendNotification("customer.export.io.failed", e.getMessage());
            logImportExportNotification(trackingNumber, Boolean.FALSE, e.getMessage(), ImportExportMsgType.EXPORT_CUW_CUSTOMERS);
        }
        catch(WSClientExceptionStatus e)
        {
        	e.printStackTrace();
            sendNotification("customer.export.io.failed", e.getMessage());
            logImportExportNotification(trackingNumber, Boolean.FALSE, e.getMessage(), ImportExportMsgType.EXPORT_CUW_CUSTOMERS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            sendNotification("customer.export.io.failed", e.getMessage());
            logImportExportNotification(trackingNumber, Boolean.FALSE, e.getMessage(), ImportExportMsgType.EXPORT_CUW_CUSTOMERS);
        }
        getLogger().exiting(this.getClass(), "exportBillingProfiles");
    }// Eof exportBillingProfiles

    public void exportBillingInvoices() throws CUWBillingException, BillingException
    {
        exportBillingInvoices(BillingExportType.INVOICE_EXPORT);
    }

    public void exportBillingAdjustments() throws CUWBillingException, BillingException
    {
        exportBillingInvoices(BillingExportType.ADJUSTMENT_EXPORT);
    }

    /*
     * Method : exportBillingInvoices() Purpose : Input Parameters : None Return
     * Type : Void Exceptions : CUWBillingException,BillingException
     */
    private void exportBillingInvoices(BillingExportType invoiceType) throws CUWBillingException, BillingException
    {
    	getLogger().entering(this.getClass(), "exportBillingInvoices");
    	System.out.println("Entering exportBillingInvoices method");
        PersistenceProvider provider = null;
        String trackingNumber = UUIDUtil.getUUID();
        
        AROutboundTrx arOutboundTrx;
        boolean ftpStatus = false;
        ARTransactionSummaryDTO invoiceSummary = getBillingInvoiceSummary(invoiceType);
        boolean transmission = false;
        try
        {
        	AROutboundSender sender = AROutboundSender.getSender();
        	if(invoiceType.equals(BillingExportType.INVOICE_EXPORT))
        	{	
        		ftpStatus = sender.sendARInvoiceData(invoiceSummary);
        	}
        	else if(invoiceType.equals(BillingExportType.ADJUSTMENT_EXPORT))
        	{
        		ftpStatus = sender.sendARInvoiceAdjData(invoiceSummary);
        	}
            provider = this.getProvider();
            ARTrxMapper mapper = new ARTrxMapper(provider);
            
            if(ftpStatus)
            {
            	if (invoiceSummary != null)
                {
                    Collection readyInvoices = invoiceSummary.getPendingInvoices();
                    if (readyInvoices != null)
                    {
                        Iterator iter = readyInvoices.iterator();
                        
                        while (iter.hasNext())
                        {
                            PendingInvoiceSummaryDTO pendinginvoicesummarydto = (PendingInvoiceSummaryDTO) iter.next();
                            if (pendinginvoicesummarydto != null)
                            {
                                arOutboundTrx = mapper.readAROutboundTrx(pendinginvoicesummarydto.getTransactionID());
                                arOutboundTrx.setARTransmissionStatus(ARTransmissionStatus.ACKNOWLEDGED);
                                arOutboundTrx.setTrackingNumber(trackingNumber);
                            }// Eof of if
                        }// Eof while
                    }
                }
            	
                Date todayDate = new Date();
                if (invoiceType.equals(BillingExportType.INVOICE_EXPORT))
                {
                	logImportExportNotification(trackingNumber, Boolean.TRUE, "Receipt of Invoice export confirmed by recipient", ImportExportMsgType.EXPORT_CUW_INVOICES);

                } 
                else if (invoiceType.equals(BillingExportType.ADJUSTMENT_EXPORT))
                {
                	logImportExportNotification(trackingNumber, Boolean.TRUE, "Receipt of Adjustment export confirmed by recipient",
                            ImportExportMsgType.EXPORT_CUW_ADJUSTMENTS);

                }
            }
            else
            {
            	if (invoiceType.equals(BillingExportType.INVOICE_EXPORT))
                {
    	            sendNotification("invoice.export.io.failed", "Invoice I/O Error");
    	            logImportExportNotification(trackingNumber, Boolean.FALSE, "Invoice I/O Error" ,ImportExportMsgType.EXPORT_CUW_INVOICES);
                }
                else if(invoiceType.equals(BillingExportType.ADJUSTMENT_EXPORT))
                {
                	sendNotification("adjustment.export.io.failed", "Adjustment I/O Error");
    	            logImportExportNotification(trackingNumber, Boolean.FALSE, "Adjustment I/O Error", ImportExportMsgType.EXPORT_CUW_ADJUSTMENTS);
                }
            }
        }
        catch (BillingException be)
        {
            be.printStackTrace();
            if (invoiceType.equals(BillingExportType.INVOICE_EXPORT))
            {
	            sendNotification("invoice.export.io.failed", be.getMessage());
	            logImportExportNotification(trackingNumber, Boolean.FALSE, be.getMessage(), ImportExportMsgType.EXPORT_CUW_INVOICES);
            }
            else if(invoiceType.equals(BillingExportType.ADJUSTMENT_EXPORT))
            {
            	sendNotification("adjustment.export.io.failed", be.getMessage());
	            logImportExportNotification(trackingNumber, Boolean.FALSE, be.getMessage(), ImportExportMsgType.EXPORT_CUW_ADJUSTMENTS);
            }
            throw be;

        }
        catch (PersistenceProviderException e)
        {
        	e.printStackTrace();
            getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            
            if (invoiceType.equals(BillingExportType.INVOICE_EXPORT))
            {
	            sendNotification("invoice.export.io.failed", e.getMessage());
	            logImportExportNotification(trackingNumber, Boolean.FALSE, e.getMessage(), ImportExportMsgType.EXPORT_CUW_INVOICES);
            }
            else if(invoiceType.equals(BillingExportType.ADJUSTMENT_EXPORT))
            {
            	sendNotification("adjustment.export.io.failed", e.getMessage());
	            logImportExportNotification(trackingNumber, Boolean.FALSE, e.getMessage(), ImportExportMsgType.EXPORT_CUW_ADJUSTMENTS);
            }
            throw new CUWBillingException("PersistenceProviderException in" + " BillingExportBean.exportBillingInvoices()", e);
        } 
        catch (Exception e)
        {
        	e.printStackTrace();
        	if (invoiceType.equals(BillingExportType.INVOICE_EXPORT))
            {
	            sendNotification("invoice.export.io.failed", e.getMessage());
	            logImportExportNotification(trackingNumber, Boolean.FALSE, e.getMessage(), ImportExportMsgType.EXPORT_CUW_INVOICES);
            }
            else if(invoiceType.equals(BillingExportType.ADJUSTMENT_EXPORT))
            {
            	sendNotification("adjustment.export.io.failed", e.getMessage());
	            logImportExportNotification(trackingNumber, Boolean.FALSE, e.getMessage(), ImportExportMsgType.EXPORT_CUW_ADJUSTMENTS);
            }
        }
        finally
        {
            forceProviderClose(provider);
        }
        getLogger().exiting(this.getClass(), "exportBillingInvoices");
        System.out.println("Exiting exportBillingInvoices method"); 
    }// Eof exportBillingInvoices
    /*
     * Method : updateInvoiceStatus() Purpose : Input Parameters : ResponseInfo
     * Return Type : void Exceptions : CUWBillingException
     * 
     */

    public void updateInvoiceStatus(ResponseInfo ri) throws CUWBillingException
    {

        PersistenceProvider provider = null;

        AROutboundTrx arOutboundTrx;

        String guid = "";

        try
        {
            guid = ri.getTrackingNumber();
            if (ri.getB2BResponseInfo() != null && ri.getB2BResponseInfo().getCode() != null && ri.getB2BResponseInfo().getCode().equals(EXPORT_SUCCESS))
            {
                // ri.getB2BResponseInfo().getCode().equals("");
                provider = this.getProvider();
                BillingMapper mapper = new BillingMapper(provider);
                Collection sentInvoices = mapper.getBillingTransactionsByGUID(guid);
                if (sentInvoices != null)
                {
                    Iterator iter = sentInvoices.iterator();
                    while (iter.hasNext())
                    {
                        arOutboundTrx = (AROutboundTrx) iter.next();
                        arOutboundTrx.setARTransmissionStatus(ARTransmissionStatus.ACKNOWLEDGED);
                    }// Eof while

                    logImportExportNotification(guid, Boolean.TRUE, "Receipt of Invoice export confirmed by recipient", ImportExportMsgType.EXPORT_CUW_INVOICES);
                }
            } else
            {

                logImportExportNotification(guid, Boolean.FALSE, "Customer I/O Error: " + ri.getB2BResponseInfo().getDesc(),
                        ImportExportMsgType.EXPORT_CUW_INVOICES);
                sendNotification("invoice.export.io.failed", ri.getB2BResponseInfo().getDesc());
            }// Eof if SUCESS FALIURE

        } catch (PersistenceProviderException e)
        {
            getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            sendNotification("invoice.export.io.failed", e.getMessage());
            logImportExportNotification(guid, Boolean.FALSE, e.getMessage(), ImportExportMsgType.EXPORT_CUW_INVOICES);
            throw new CUWBillingException("PersistenceProviderException in" + " BillingExportBean.updateInvoiceSummary()", e);
        } catch (Exception e)
        {
            sendNotification("invoice.export.io.failed", e.getMessage());
            logImportExportNotification(guid, Boolean.FALSE, e.getMessage(), ImportExportMsgType.EXPORT_CUW_INVOICES);
        } finally
        {
            forceProviderClose(provider);
        }
    }// Eof updateInvoiceStatus

    /*
     * Method : updateAdjustmentStatus() Purpose : Input Parameters :
     * ResponseInfo Return Type : void Exceptions : CUWBillingException
     * 
     */

    public void updateAdjustmentStatus(ResponseInfo ri) throws CUWBillingException
    {

        PersistenceProvider provider = null;

        AROutboundTrx arOutboundTrx;
        String guid = "";
        try
        {
            guid = ri.getTrackingNumber();
            if (ri.getB2BResponseInfo() != null && ri.getB2BResponseInfo().getCode() != null && ri.getB2BResponseInfo().getCode().equals(EXPORT_SUCCESS))
            {
                // ri.getB2BResponseInfo().getCode().equals("");
                provider = this.getProvider();
                BillingMapper mapper = new BillingMapper(provider);
                Collection sentInvoices = mapper.getBillingTransactionsByGUID(guid);
                if (sentInvoices != null)
                {
                    Iterator iter = sentInvoices.iterator();
                    while (iter.hasNext())
                    {
                        arOutboundTrx = (AROutboundTrx) iter.next();
                        arOutboundTrx.setARTransmissionStatus(ARTransmissionStatus.ACKNOWLEDGED);
                    }// Eof while

                    logImportExportNotification(guid, Boolean.TRUE, "Receipt of Adjustment export confirmed by recipient",
                            ImportExportMsgType.EXPORT_CUW_ADJUSTMENTS);
                }
            } else
            {
                logImportExportNotification(guid, Boolean.FALSE, "Customer I/O Error: " + ri.getB2BResponseInfo().getDesc(),
                        ImportExportMsgType.EXPORT_CUW_ADJUSTMENTS);
                sendNotification("adjustment.export.io.failed", ri.getB2BResponseInfo().getDesc());
            }// Eof if SUCESS FALIURE

        } catch (PersistenceProviderException e)
        {
            getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            sendNotification("adjustment.export.io.failed", e.getMessage());
            logImportExportNotification(guid, Boolean.FALSE, e.getMessage(), ImportExportMsgType.EXPORT_CUW_ADJUSTMENTS);
            throw new CUWBillingException("PersistenceProviderException in" + " BillingExportBean.updateInvoiceSummary()", e);
        } catch (Exception e)
        {
            sendNotification("adjustment.export.io.failed", e.getMessage());
            logImportExportNotification(guid, Boolean.FALSE, e.getMessage(), ImportExportMsgType.EXPORT_CUW_ADJUSTMENTS);
        } finally
        {
            forceProviderClose(provider);
        }
    }// Eof updateInvoiceStatus

    /*
     * Method : updateProfileStatus() Purpose : Input Parameters : ResponseInfo
     * Return Type : void Exceptions : CUWBillingException
     */
    public void updateProfileStatus(ResponseInfo ri) throws CUWBillingException
    {

        PersistenceProvider provider = null;

        AROutboundTrx arOutboundTrx;

        String guid = "";

        try
        {
            if (ri.getB2BResponseInfo() != null && ri.getB2BResponseInfo().getCode() != null && ri.getB2BResponseInfo().getCode().equals(EXPORT_SUCCESS))
            {
                guid = ri.getTrackingNumber();
                provider = this.getProvider();
                ARTrxMapper mapper = new ARTrxMapper(provider);
                logImportExportNotification(guid, Boolean.TRUE, "Receipt of Profile export confirmed by recipient", ImportExportMsgType.EXPORT_CUW_CUSTOMERS);
            } else
            {
                logImportExportNotification(guid, Boolean.FALSE, "Customer I/O Error: " + ri.getB2BResponseInfo().getDesc(),
                        ImportExportMsgType.EXPORT_CUW_CUSTOMERS);
                sendNotification("customer.export.io.failed", ri.getB2BResponseInfo().getDesc());
            }
        } catch (PersistenceProviderException e)
        {
            sendNotification("customer.export.io.failed", e.getMessage());
            getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            throw new CUWBillingException("PersistenceProviderException in " + "BillingExportBean.updateProfileSummary()", e);
        } finally
        {
            forceProviderClose(provider);
        }
    }// Eof updateProfileStatus()

    /**
     * Creates an import/export log record
     * 
     * @param trackingNumber
     *            Allows correlation between a request and response
     * @param successFlag
     *            Identifies whether the requested action was a success or
     *            failure
     * @param description
     *            Description of the action/error that occurred
     * @param messageType
     *            Enum defining type of message being logged
     * @throws CUWBillingException
     */
    private void logImportExportNotification(String batchNumber, Boolean successFlag, String description, ImportExportMsgType messageType)
            throws CUWBillingException
    {
        getLogger().entering(this.getClass(), "logImportExportNotification");
        // getLogger().debug( "ImportExport Log Entry: " + new Date().toString()
        // + "\n\tLocation: BillingImportAdminBean\n\tBatchNumber: " +
        // batchNumber + "\n\tSuccess: " + successFlag.toString() +
        // "\n\tMessageText: " + description );
        try
        {
            // Create new billing import/export log record with success/failure
            // message
            ImportExportMsgDTO message = new ImportExportMsgDTO();
            message.setBatchNumber(batchNumber);
            message.setMessageText(description);
            message.setSuccessFlag(successFlag);
            message.setImportExportMsgType(messageType);
            ImportExportMsgAdministration service = SystemServiceFactory.instance().getImportExportMsgAdminService();
            service.create(message);
        } catch (ImportExportMsgException e)
        {
            throw new CUWBillingException(e.getMessage(), e);
        }
        getLogger().exiting(this.getClass(), "logImportExportNotification");
    }

    /**
     * Method : sendNotification() Purpose : Send Email notifications to
     * reporting the cause of process failure Input Parameters : String (message
     * Key), String (Error reason) Return Type : void Exceptions : None
     * 
     */

    private void sendNotification(String messageKey, String messageText)
    {
        PersistenceProvider provider = null;
        AxiomEventCategory category = category = AxiomEventCategory.PROCESS_FAILED;
        try
        {
            // create a notification
            Object[] params = new Object[1];
            params[0] = messageText;
            provider = this.getProvider();
            NullEntityImpl nullObj = new NullEntityImpl();
            nullObj.setID(new Long(0));
            EventRouterDelegate.getInstance().send(provider, nullObj, AxiomEntityType.CUW_EXPORT, category, messageKey, "Notifications", params);
        } catch (PersistenceProviderException e)
        {
            // don't throw an error, as this is a batch process that should
            // continue regardless
            getLogger().error("PersistenceProviderException in logTimeSheetNotification:" + e.getMessage(), e);
        } finally
        {
            forceProviderClose(provider);
        }
    }// Eof sendNotification
    
    private WSClientCMD getCMDClient() throws WSClientException
    {
    	if (wsCMD == null)
    	{
    		wsCMD = WSClientFactory.getCMDClient();
    	}
    	return wsCMD;
    }
    
    private Collection mapProfileDTOs(Collection profileDTOs) throws CUWBillingException, ParseException
    {
		Collection summaryDTOs = new ArrayList();
		Date lastExecutionDate = null;

		// Changes made as part of #US35289
		final Calendar cal = Calendar.getInstance();
		// To get the yesterday's date
		cal.add(Calendar.DATE, -1);

		PersistenceProvider provider = null;
		try {
			provider = this.getProvider();
			BillingMapper mapper = new BillingMapper(provider);

			// Get the last successful customer export task execution date
			lastExecutionDate = mapper.getLastExecutionDateByCustomerExportTask();
			System.out.println(" CustomerExportTask last successful ExecutionDate : "
							+ lastExecutionDate);

		} catch (PersistenceProviderException e) {
			getLogger().error("Error while fetching the last successful execution date for CUWCustomerExportTask:"
							+ e.getMessage());
			throw new CUWBillingException(
					"Error while fetching the last successful execution date for CUWCustomerExportTask:",
					e);
		}
        
		if (profileDTOs != null)
    	{
    		Iterator it = profileDTOs.iterator();
    		while (it.hasNext())
    		{
    			BillingProfileDTO dto = (BillingProfileDTO)it.next();
    			
    			if(dto.getModifiedDate()!= null)
    			{
    				
    				Date modifiedDate = formatter.parse(formatter.format(DateDTO.toDate(dto.getModifiedDate())));
    				Date lastSucExecutionDate= formatter.parse( formatter.format(lastExecutionDate));
    				Date yesterdayDate= formatter.parse(formatter.format(cal.getTime()));
    				
    				/*Send the records whose last Modified date is greater than or equals to last successful execution date and 
    				last Modified date is less than or equals to yesturday's date */
	    			if((modifiedDate.after(lastSucExecutionDate)||modifiedDate.equals(lastSucExecutionDate))&&(modifiedDate.before(yesterdayDate)||modifiedDate.equals(yesterdayDate)))
	    			{
	    			BillingProfileSummaryDTO summaryDTO = new BillingProfileSummaryDTO();
	    			summaryDTO.setAttentionToPerson(dto.getBillToAttention());
	    			summaryDTO.setBillingProfileDisplayID(dto.getBillingProfileDisplayID());
	    			summaryDTO.setBillingProfileID(dto.getBillingProfileID());
	    			summaryDTO.setBillToName(dto.getBillToInvoiceName());
	    			summaryDTO.setCustomerAddress(dto.getBillToLocation());
	    			summaryDTO.setPhoneNumber(dto.getBillToPhone());
	    			summaryDTO.setCustomerDisplayID(dto.getCustomerDisplayID());
	    			summaryDTO.setCustomerName(dto.getCustomerName());
	    			summaryDTO.setStatusCodeDesc(dto.getActivationStatus());
	    			summaryDTOs.add(summaryDTO);
	    			}
    		}}
    	}
		
		return summaryDTOs;
    }

}// Eof BillingExportBean class
