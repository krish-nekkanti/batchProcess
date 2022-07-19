/*
 * BillingMapper.java
 *
 * Created on September 5, 2003, 12:34 PM
 */

package com.radian.cuwbilling.billing.common.os.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;

import com.radian.cuwbilling.billing.common.bo.codes.ARTransmissionStatus;
import com.radian.cuwbilling.billing.common.bo.codes.BillingExportType;
import com.radian.cuwbilling.billing.common.bo.codes.BillingStatus;
import com.radian.cuwbilling.billing.common.bo.codes.ExportEntryType;
import com.radian.cuwbilling.billing.common.bo.codes.InvoiceAdjustmentType;
import com.radian.cuwbilling.billing.common.bo.codes.InvoiceStatus;
import com.radian.cuwbilling.billing.cuw.bo.domain.AROutboundTrx;
import com.radian.cuwbilling.billing.cuw.bo.domain.BDRCostCenterAllocation;
import com.radian.cuwbilling.billing.cuw.bo.domain.BillingPeriod;
import com.radian.cuwbilling.billing.cuw.bo.domain.CUWBillingCalendar;
import com.radian.cuwbilling.billing.cuw.bo.domain.CUWBillingPeriod;
import com.radian.cuwbilling.billing.cuw.bo.domain.Invoice;
import com.radian.cuwbilling.billing.cuw.bo.domain.InvoiceAdjustmentBDR;
import com.radian.cuwbilling.billing.cuw.bo.domain.LLNOrderItem;
import com.radian.cuwbilling.billing.cuw.bo.domain.PerDiemOrder;
import com.radian.cuwbilling.billing.cuw.bo.domain.impl.BillingPeriodImpl;
import com.radian.cuwbilling.billing.cuw.bo.domain.impl.CUWBillingPeriodImpl;
import com.radian.cuwbilling.billing.cuw.bo.domain.impl.LLNOrderItemImpl;
import com.radian.cuwbilling.billing.cuw.bo.dto.ARTransactionSummaryDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.PendingDistSummaryDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.PendingInvoiceSummaryDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.TrialResultsDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.UnbilledOrderDTO;
import com.radian.cuwbilling.common.ScrollWindow;
import com.radian.cuwbilling.common.ScrolledQueryResult;
import com.radian.cuwbilling.common.bo.domain.CUWOrderItem;
import com.radian.cuwbilling.common.bo.domain.CustBranch;
import com.radian.cuwbilling.common.bo.domain.MyRadOrderItem;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.cuwbilling.common.bo.dto.MyRadOrderReplicateDTO;
import com.radian.cuwbilling.common.bs.util.CustomerUtils;
import com.radian.cuwbilling.common.os.persistence.QueryMapper;
import com.radian.cuwbilling.system.batch.BatchHistoryItem;
import com.radian.cuwbilling.system.batch.MyRadianBatchHistoryItem;
import com.radian.cuwbilling.system.document.bo.domain.DeliveryItemProfileTransmission;
import com.radian.cuwbilling.system.document.bo.domain.impl.DeliveryItemProfileTransmissionImpl;
import com.radian.foundation.bs.SearchCriteria;
import com.radian.foundation.os.persistence.mapper.BaseRDBMSMapper;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;
import com.radian.foundation.os.persistence.spi.Query;
import com.radian.foundation.os.persistence.util.JDBCUtil;
import com.radian.foundation.os.persistence.util.ProviderHelper;

/**
 *
 * @author tzhou
 */
public class BillingMapper extends BaseRDBMSMapper
{
    private static final String SEARCH_AROUTBOUNDTRX_BY_TRANSMISSION_STATUS = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getOutboundTrxByTransmission";

    private static final String SEARCH_AROUTBOUNDTRX_BY_GUID = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getOutboundTrxByGUID";

    private static final String GET_ADJUSTABLE_INVOICES = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getAdjustableInvoices";

    private static final String GET_LAST_BILLING_CALENDER = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getLastBillingCalender";

    private static final String GET_LAST_BILLING_PERIOD = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getLastBillingPeriod";
    
    private static final String GET_UNFINISHED_INVOICE_RUN = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getUnfinishedInvoiceRun";

    private static final String GET_BILLING_PERIOD_BY_END_DATE = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getBillingPeriodByEndDate";
    
    private static final String GET_ORDER_IEMS_BY_RADIAN_NUMBERS = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getOrderItemsByRadianNumbers";

    private static final String GET_UNPRICED_MYRAD_ORDER_ITEMS = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getUnpricedOrderItems";

    private static final String GET_LAST_BATCH_HISTORY_ITEM_BY_END_DATE = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getLastBatchHistoryItemByEndDate";
    
    private static final String GET_LAST_EXECUTION_DATE_BY_CUSTOMER_EXPORT_TASK = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getLastExecutionDateByCustomerExportTask";
       
    private static final String GET_MYRAD_LAST_BATCH_HISTORY_ITEM_BY_END_DATE = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getMyRadLastBatchHistoryItemByEndDate";

    private static final String GET_TRIAL_RUN_RESULTS = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getTrialRunResults";

    private static final String FIND_ORDER_ITEMS_BY_PERIOD_START_DATE = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getOrderItemsByPeriodStartDate";
    
    private static final String FIND_SERVICE_CENTER_LOANS_BY_PERIOD_START_DATE = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getServiceCenterLoansByPeriodStartDate";
    
    private static final String GET_SERVICE_CENTER_BDRS_BY_BILLING_PERIOD = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getServiceCenterBDRsByBillingPeriod";
    
    private static final String GET_REP_ID_BY_BDR_ID = "{ call cb_billing_pkg.GET_UW_CODE(?, ?)}";
    
    private static final String GET_MYRADIAN_SERVICE_CENTER_ORDER_ITEMS = "{ call cb_billing_pkg.get_myrad_sc_priced_ois(?, ?, ?)}";
    
    private static final String GET_PAYMENTS = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getPayments";
    
    private static final String GET_BILLING_PERIOD_BY_MY_RAD_ID = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getBillingPeriodByMyRadID";
    
    private static final String GET_UW_LIST_FOR_UNBILLED_MYRADIAN_ORDERS = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getUWListForUnbilledMyRadianOrders";

    private static final String GET_BILLING_PERIOD_BY_DATES = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getBillingPeriodByDates";
    
    private static final String GET_EXISTING_ORDER_ITEM = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getExistingOrderItem";
    
    private static final String GET_PER_DIEM_ORDERS_BY_PROFILE = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getPerDiemOrdersByProfile";
    
    private static final String GET_COST_CENTER_NUMBER = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getCostCenterNumber";
    
    private static final String GET_REP_ID_FOR_BDR_ID = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getRepIDByBDRid";
    
    private static final String GET_MYRAD_ORDER_ITEM = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getMyRadOrderItemByNum";
    
    private static final String GET_PER_DIEM_ORDER_ITEM_NUMBERS_BY_INVOICE_ID = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getPerDiemOrderNumbersByInvoiceID";
    
    private static final String GET_PER_LOAN_ORDER_ITEMS_BY_INVOICE_ID = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getPerLoanOrdersByInvoiceID";
    
    private static final String GET_UNBILLED_ORDER_COUNT = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getUnbilledOrderItemCount";

    private static final String GET_UNBILLED_HOURS_COUNT = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getUnbilledHourCount";
    
    private static final String GET_MYRAD_ORDER_ITEM_REPLICATE = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getMyRadOrderItemByBDRid";
    
    private static final String GET_ORDER_ITEM = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getOrderItemByNum";
    
    private static final String GET_CUST_INFO = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getCustInfoByBranchID";
    
    public static final String GET_BILLED_PROFILES_FOR_PERIOD = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getBilledProfileForPeriod";
    
    public static final String GET_LOANS_FOR_THIRD_PARTIES = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getLoansForThirdParties";
    
    public static final String GET_LOANS_FOR_CUSTOMER = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getLoansForCustomer";
    
    public static final String GET_LOANS_FOR_BRANCHES = "com.radian.cuwbilling.billing.common.os.persistence.BillingMapper.getLoansForBranches";
    
    private static final int DEBIT_ACCOUNT = 105400;

    private static final int CREDIT_ACCOUNT = 503040;
    
    private static final String CUSTOMER_EXPORT_FLOW_NAME = "CUWBillingExportWorkflow";
    
    private static final String CUSTOMER_EXPORT_TASK_NAME = "CUWCustomerExportTask";

    /**
     * Creates a new instance of BillingMapper
     */
    public BillingMapper(PersistenceProvider provider)
    {
        super(provider);
    }

    public BillingMapper()
    {
        super();
    }

    public void create(DeliveryItemProfileTransmission obj) throws PersistenceProviderException
    {
        save(obj);
    }
    
    public void create(CUWBillingPeriod obj) throws PersistenceProviderException
    {
        save(obj);
    }
    
    public void create(BatchHistoryItem obj) throws PersistenceProviderException
    {
        save(obj);
    }
    
    public void create(CUWOrderItem obj) throws PersistenceProviderException
    {
        save(obj);
    }
    
    public void create(PerDiemOrder obj) throws PersistenceProviderException
    {
        save(obj);
    }
    
    public DeliveryItemProfileTransmission readDeliveryItemProfileTransmission(Long id) throws PersistenceProviderException
    {
        return (DeliveryItemProfileTransmission) get(DeliveryItemProfileTransmissionImpl.class, id);
    }

    /**
     * Gets all the outbound transactions by GUID
     *
     * @return a collection of AROutboundTrx objects
     */
    public Collection getBillingTransactionsByGUID(String GUID) throws PersistenceProviderException
    {
        Query outboundTrxQuery = getProvider().newQuery(SEARCH_AROUTBOUNDTRX_BY_GUID);
        outboundTrxQuery.setNamedParameter("guid", GUID);
        return outboundTrxQuery.execute();
    }
    
    public Collection getUnpricedMyRadOrderItems(String repID) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_UNPRICED_MYRAD_ORDER_ITEMS);
        query.setNamedParameter("repID", repID);
        return query.execute();
    }
    
    public CUWOrderItem getExistingOrderItem(String orderItemNum, String sourceSystem, Long periodID) throws PersistenceProviderException
    {
    	Collection results = new ArrayList();
        Query query = getProvider().newQuery(GET_EXISTING_ORDER_ITEM);
        query.setNamedParameter("orderItemNum", orderItemNum);
        query.setNamedParameter("sourceSystem", sourceSystem);
        query.setNamedParameter("periodID", periodID);
        results = query.execute();
        
        if (results != null && results.size() > 0)
        {
        	return (CUWOrderItem)results.iterator().next();
        }
        
        return null;
    }
    
    public Collection getUWListForUnbilledMyRadianOrders() throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_UW_LIST_FOR_UNBILLED_MYRADIAN_ORDERS);
        
        return query.execute();
    }
    
    public String getRepIDByBDRID(Long bdrID) throws PersistenceProviderException
    {
    	getLogger().entering(this.getClass(), "execGetRepIDByBDRID(bdrID)");
        OracleCallableStatement stmt = null;
        Collection col = new ArrayList();
        ResultSet results = null;
        try
        {
            Connection conn = getProvider().getConnection();
            OracleConnection ocon = (OracleConnection) JDBCUtil.getPhysicalConnection(conn);

            stmt = (OracleCallableStatement) ocon.prepareCall(GET_REP_ID_BY_BDR_ID);
            stmt.setLong(1, bdrID.longValue());
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.execute();
            
            getLogger().debug("Results:");
            results = (ResultSet) stmt.getObject(2);
            
            if (results != null)
            {
	            while (results.next())
	            {
	            	if (results.getString("UNDERWRITER_CODE") != null)
	            	{
		                String id = new String(results.getString("UNDERWRITER_CODE"));
		                if (getLogger().isDebugEnabled())
		                {
		                    getLogger().debug("Result from proc:" + String.valueOf(id));
		                }
		                col.add(id);
	            	}
	            }
            }
        } catch (SQLException se)
        {
            throw new PersistenceProviderException(se);
        } finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            } catch (SQLException se)
            {
                throw new PersistenceProviderException(se);
            }
        }
        getLogger().exiting(this.getClass(), "execGetRepIDByBDRID(bdrID)");
        if (col != null && col.size() > 0)
        {
        	return (String)col.iterator().next();
        }
        else
        {
        	return null;
        }
    }
    
    public void getMyRadianServiceCenterOrderItems(Long periodID, Long myRadPeriodID) throws PersistenceProviderException
    {
    	getLogger().entering(this.getClass(), "execGetMyRadianServiceCenterOrderItems(periodID, myRadPeriodID)");
        OracleCallableStatement stmt = null;
        Collection col = null;
        try
        {
            Connection conn = getProvider().getConnection();
            OracleConnection ocon = (OracleConnection) JDBCUtil.getPhysicalConnection(conn);

            stmt = (OracleCallableStatement) ocon.prepareCall(GET_MYRADIAN_SERVICE_CENTER_ORDER_ITEMS);
            stmt.setLong(1, periodID.longValue());
            stmt.setLong(2, myRadPeriodID.longValue());
            stmt.registerOutParameter(3, OracleTypes.CURSOR);
            stmt.execute();
        } catch (SQLException se)
        {
            throw new PersistenceProviderException(se);
        } finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            } catch (SQLException se)
            {
                throw new PersistenceProviderException(se);
            }
        }
        getLogger().exiting(this.getClass(), "execGetMyRadianServiceCenterOrderItems(periodID, myRadPeriodID)");
    }

    public Collection getServiceCenterBDRs(Long periodID, Long bpID) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_SERVICE_CENTER_BDRS_BY_BILLING_PERIOD);
        query.setNamedParameter("periodID", periodID);
        query.setNamedParameter("bpID", bpID);
        return query.execute();
    }
    
    /**
     * Gets all the invoices and their outbound transaction info for transaction
     * status "READY" export type can be of InvoiceExport or AdjustmentExport
     *
     * @return BillingARTransactionSummaryDTO record
     */
    public ARTransactionSummaryDTO getBillingInvoiceSummary(BillingExportType exportType) throws PersistenceProviderException, ParseException
    {
        ARTransactionSummaryDTO transactionSummaryDTO = null;

        if (exportType != null)
        {
            Query outboundTrxQuery = getProvider().newQuery(SEARCH_AROUTBOUNDTRX_BY_TRANSMISSION_STATUS);
            outboundTrxQuery.setNamedParameter("transmissionStatus", ARTransmissionStatus.READY.getID());

            if (exportType.equals(BillingExportType.INVOICE_EXPORT))
            {
                outboundTrxQuery.setNamedParameter("adjustmentRequiredFlag", Boolean.FALSE);
            } else if (exportType.equals(BillingExportType.ADJUSTMENT_EXPORT))
            {
                outboundTrxQuery.setNamedParameter("adjustmentRequiredFlag", Boolean.TRUE);
            }
            Collection outboundTrxResults = outboundTrxQuery.execute();

            if (outboundTrxResults.size() > 0)
            {

                transactionSummaryDTO = new ARTransactionSummaryDTO();

                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
                String todayStr = dateFormat.format(new Date());

                transactionSummaryDTO.setTransactionDate(DateDTO.dateToDateDTO(dateFormat.parse(todayStr)));

                if (exportType.equals(BillingExportType.INVOICE_EXPORT))
                {
                    transactionSummaryDTO.setGroupId((new StringBuffer().append("CUW").append(todayStr.substring(0, 2)).append(todayStr.substring(3, 5))
                            .append(todayStr.substring(6, 8))).toString());
                } else if (exportType.equals(BillingExportType.ADJUSTMENT_EXPORT))
                {
                    transactionSummaryDTO.setGroupId((new StringBuffer().append("ADJ").append(todayStr.substring(0, 2)).append(todayStr.substring(3, 5))
                            .append(todayStr.substring(6, 8))).toString());
                }

                double totalAmount = 0;

                DateDTO accountingDate = null;

                /*
                 * loop through all the results and construct pending invoice
                 * summary dtos
                 */
                Iterator outboundTrxItr = outboundTrxResults.iterator();
                while (outboundTrxItr.hasNext())
                {
                    AROutboundTrx outboundTrx = (AROutboundTrx) outboundTrxItr.next();
                    Invoice invoiceDO = null;
                    if (exportType.equals(BillingExportType.INVOICE_EXPORT))
                    {
                        invoiceDO = outboundTrx.getInvoice();
                    } else if (exportType.equals(BillingExportType.ADJUSTMENT_EXPORT) && outboundTrx.getAdjustment() != null)
                    {
                        // for adjustments we need only invoice adjustments
                        Class c = ProviderHelper.getInstanceClass(outboundTrx.getAdjustment());
                        if (c.getName().indexOf("InvoiceAdjustmentBDRImpl") != -1 || c.getName().indexOf("ExpenseAdjustmentBDRImpl") != -1)
                        {
                            invoiceDO = ((InvoiceAdjustmentBDR) outboundTrx.getAdjustment()).getAdjustedInvoice();
                        }
                    }

                    if (invoiceDO == null)
                        continue;

                    // skip the invoice if the invoice amount is 0.0 and it is
                    // not a Chase
                    // preloaded invoice for adjustment export
                    if ((invoiceDO.getInvoiceAmount() == null || invoiceDO.getInvoiceAmount().getValue().doubleValue() == 0.0)
                            && !(BillingExportType.ADJUSTMENT_EXPORT.equals(exportType) && isChaseInvoice(invoiceDO) && invoiceDO.getPreloadedIndicator()
                                    .booleanValue()))
                        continue;

                    int distributionsSize = 0;
                    Iterator costCentersItr = null;

                    /* populate pending invoice dto */
                    PendingInvoiceSummaryDTO exportDTO = new PendingInvoiceSummaryDTO();
                    if (exportType.equals(BillingExportType.INVOICE_EXPORT))
                    {
                        exportDTO.setInvoiceAmt(invoiceDO.getInvoiceAmount().getValue());
                        exportDTO.setInvoiceDate(DateDTO.dateToDateDTO(invoiceDO.getInvoiceDate()));
                        exportDTO.setInvoiceDueDate(DateDTO.dateToDateDTO(invoiceDO.getInvoiceDueDate()));
                        if (accountingDate == null)
                        {
                            accountingDate = DateDTO.dateToDateDTO(outboundTrx.getInvoice().getCreatedDate());
                        }

                    } else if (exportType.equals(BillingExportType.ADJUSTMENT_EXPORT))
                    {
                        exportDTO.setInvoiceAmt(outboundTrx.getAdjustment().getItemTotalPrice().getValue());
                        exportDTO.setInvoiceDate(DateDTO.dateToDateDTO(outboundTrx.getAdjustment().getCreatedDate()));
                        exportDTO.setInvoiceDueDate(DateDTO.dateToDateDTO(outboundTrx.getAdjustment().getCreatedDate()));

                        if (accountingDate == null)
                        {
                            accountingDate = DateDTO.dateToDateDTO(outboundTrx.getAdjustment().getCreatedDate());
                        }
                    }
                    exportDTO.setBillingProfileNum(invoiceDO.getBillingProfile());
                    exportDTO.setInvoiceDueDate(DateDTO.dateToDateDTO(invoiceDO.getInvoiceDueDate()));
                    exportDTO.setInvoiceNo(invoiceDO.getInvoiceNo());
                    exportDTO.setTransactionID(outboundTrx.getID());

                    // set entry types
                    if (exportType.equals(BillingExportType.INVOICE_EXPORT))
                    {

                        exportDTO.setEntryType(ExportEntryType.INVOICE_EXPORT_ENTRY_TYPE.getAbbreviation());
                        distributionsSize = outboundTrx.getSummaryCCAllocations().size();
                        costCentersItr = outboundTrx.getSummaryCCAllocations().iterator();

                    } else if (exportType.equals(BillingExportType.ADJUSTMENT_EXPORT))
                    {

                        distributionsSize = ((InvoiceAdjustmentBDR) outboundTrx.getAdjustment()).getBDRCostCenterAllocations().size();
                        costCentersItr = ((InvoiceAdjustmentBDR) outboundTrx.getAdjustment()).getBDRCostCenterAllocations().iterator();

                        /*
                         * If the adjustment reflects a type = write-off, then
                         * entry type value = WO If the adjustment amount is
                         * negative, then entry type value = RADCR. If the
                         * adjustment amount is +ve, then entry type value =
                         * RADDR.
                         */
                        if (((InvoiceAdjustmentBDR) outboundTrx.getAdjustment()).getInvoiceAdjustmentType().equals(InvoiceAdjustmentType.WRITE_OFF))
                        {
                            exportDTO.setEntryType(ExportEntryType.WRITE_OFF_ENTRY_TYPE.getAbbreviation());
                        } else if (((InvoiceAdjustmentBDR) outboundTrx.getAdjustment()).getItemTotalPrice().getValue().doubleValue() < 0)
                        {
                            exportDTO.setEntryType(ExportEntryType.CREDIT_ENTRY_TYPE.getAbbreviation());
                        } else
                        {
                            exportDTO.setEntryType(ExportEntryType.DEBIT_ENTRY_TYPE.getAbbreviation());
                        }
                    }

                    totalAmount = totalAmount + exportDTO.getInvoiceAmt().doubleValue();

                    /*
                     * populate glaccount (hard coded values) and cost center
                     * info in PendingDistSummaryDTO. We need to combine cost
                     * center and glaccount numbers in one dto.
                     */
                    Collection distSummaryList = new HashSet();

                    if (distributionsSize > 0)
                    {
                        double costCenterTotalAmount = 0;

                        while (costCentersItr.hasNext())
                        {
                            /* the following data is for credit account */
                            PendingDistSummaryDTO distSummaryDTO = new PendingDistSummaryDTO();
                            BDRCostCenterAllocation costCenterAllocation = (BDRCostCenterAllocation) costCentersItr.next();
                            distSummaryDTO.setOperatingUnit(costCenterAllocation.getCostCenterState());
                            distSummaryDTO.setCostCenterNo(costCenterAllocation.getCostCenterNumber());
                            distSummaryDTO.setGlAccountNo(String.valueOf(CREDIT_ACCOUNT));

                            if (costCenterAllocation.getAmount() != null && costCenterAllocation.getAmount().getValue().doubleValue() != 0)
                            {
                                if (exportType.equals(BillingExportType.INVOICE_EXPORT))
                                {
                                    // if(costCenterAllocation.getAmount().getValue().doubleValue()
                                    // < 0){
                                    distSummaryDTO.setMonetaryAmt(new Double(-costCenterAllocation.getAmount().getValue().doubleValue()));
                                    // }else{
                                    // distSummaryDTO.setMonetaryAmt(new
                                    // Double("-" +
                                    // costCenterAllocation.getAmount().getValue().toString()));
                                    // }
                                    costCenterTotalAmount += costCenterAllocation.getAmount().getValue().doubleValue();
                                } else if (exportType.equals(BillingExportType.ADJUSTMENT_EXPORT))
                                {
                                    /*
                                     * credit account means system defined value
                                     * is set to U. For RADDR transactions where
                                     * system defined = U, the amount will be
                                     * negative For RADCR or WO transactions
                                     * where system defined = U, the amount will
                                     * be positive
                                     */
                                    if (costCenterAllocation.getAmount().getValue().doubleValue() > 0)
                                    {
                                        distSummaryDTO.setMonetaryAmt(new Double("-" + costCenterAllocation.getAmount().getValue().doubleValue()));
                                    } else
                                    {
                                        distSummaryDTO.setMonetaryAmt(new Double(Math.abs(costCenterAllocation.getAmount().getValue().doubleValue())));
                                    }
                                    costCenterTotalAmount += distSummaryDTO.getMonetaryAmt().doubleValue();
                                }
                                distSummaryList.add(distSummaryDTO);
                            }
                        }

                        /*
                         * create one dto for all cost center amounts. This data
                         * is for debit account
                         */
                        PendingDistSummaryDTO distSummaryPendingDTO = new PendingDistSummaryDTO();
                        distSummaryPendingDTO.setGlAccountNo(String.valueOf(DEBIT_ACCOUNT));
                        if (exportType.equals(BillingExportType.INVOICE_EXPORT))
                        {
                            distSummaryPendingDTO.setMonetaryAmt(new Double(costCenterTotalAmount));
                        } else if (exportType.equals(BillingExportType.ADJUSTMENT_EXPORT))
                        {
                            /*
                             * debit account means system defined value is set
                             * to A. For RADDR transactions where system defined =
                             * A, the amount will be positive For RADCR or WO
                             * transactions where system defined = A, the amount
                             * will be negative
                             */
                            if (costCenterTotalAmount > 0)
                            {
                                costCenterTotalAmount = -costCenterTotalAmount;
                            } else
                            {
                                costCenterTotalAmount = Math.abs(costCenterTotalAmount);
                            }
                            if (exportDTO.getEntryType().equalsIgnoreCase(ExportEntryType.DEBIT_ENTRY_TYPE.getAbbreviation()))
                            {
                                distSummaryPendingDTO.setMonetaryAmt(new Double(Math.abs(costCenterTotalAmount)));
                            } else
                            {
                                distSummaryPendingDTO.setMonetaryAmt(new Double("-" + Math.abs(costCenterTotalAmount)));
                            }
                        }
                        distSummaryList.add(distSummaryPendingDTO);
                    }

                    exportDTO.setPendDistributions(distSummaryList);
                    transactionSummaryDTO.addPendingInvoice(exportDTO);
                }
                transactionSummaryDTO.setControlAmt(new Double(totalAmount));
                transactionSummaryDTO.setAccountingDate(accountingDate);
            }
        }
        return transactionSummaryDTO;
    }

    /**
     * Retrieves a collection of invoices that can be adjusted for the given
     * profile.
     *
     * @param billingProfileId
     *            ID of the profile to retrieve invoices for.
     * @return a collection of invoices that can be adjusted for the given
     *         profile.
     * @throws PersistenceProviderException
     */
    public Collection getAdjustableInvoices(String bpNum) throws PersistenceProviderException
    {
        if (bpNum == null)
        {
            throw new IllegalArgumentException("billingProfileNumber cannot be null.");
        }

        Query query = getProvider().newQuery(GET_ADJUSTABLE_INVOICES);
        Collection statusIds = new ArrayList();
        statusIds.add(InvoiceStatus.OPEN.getID());
        statusIds.add(InvoiceStatus.PAID_IN_FULL.getID());

        query.setNamedParameter("billingProfileNum", bpNum);
        query.setNamedParameterList("statusIds", statusIds);
        query.setNamedParameter("chaseProfileDisplayId", CustomerUtils.CHASE_PROFILE_DISPLAY_ID);

        return query.execute();
    }
    
    public Collection getOrderItemsByRadianNumber(Collection orderItemNums) throws PersistenceProviderException
    {
    	Query query = getProvider().newQuery(GET_ORDER_IEMS_BY_RADIAN_NUMBERS);
        query.setNamedParameterList("orderItemNumbers", orderItemNums);
        
        return query.execute();
    }
    
    public TrialResultsDTO getTrialRunResults() throws PersistenceProviderException
    {
    	TrialResultsDTO dto = new TrialResultsDTO();
    	Query query = getProvider().newQuery(GET_TRIAL_RUN_RESULTS);
        Collection results = query.execute();
        if (results != null)
        {
        	return (TrialResultsDTO)results.iterator().next();
        }
        else
        {
        	return null;
        }	
    }
    
    /**
     * Determines wether the <code>invoice</code> belongs to Chase.
     *
     * @param invoice
     *            the invoice.
     * @return <code>true</code> if the invoice belongs to Chase.
     */
    private boolean isChaseInvoice(Invoice invoice)
    {
        if (invoice == null)
        {
            return false;
        }
        String profNum = invoice.getBillingProfile();
        return CustomerUtils.isChaseDefaultProfile(profNum);
    }
    
    /**
     * Retrieve the last billing cycle invoice run date for the client.
     *
     * @param custID
     *            the custID.
     * @return the last billing cycle invoice run date.
     */
    public Date getLastBillingDate(Long custID) throws PersistenceProviderException
    {
        CUWBillingCalendar calendar = null;
        Date date = null;
        Query query = getProvider().newQuery(GET_LAST_BILLING_CALENDER);
        query.setNamedParameter("custID", custID);
        Collection results = query.execute();
        if (results != null && results.size() > 0)
        {
            calendar = (CUWBillingCalendar) results.iterator().next();
        }
        if (calendar != null)
        {
            date = calendar.getLastBillingDate();
        }

        return date;
    }
    
    public CUWBillingPeriod getBillingPeriodByEndDate(Date endDate) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_BILLING_PERIOD_BY_END_DATE);
        query.setParameters(new Object[] { endDate });
        Collection results = query.execute();
        return (CUWBillingPeriod)results.iterator().next();
    }
    
    public CUWBillingPeriod getBillingPeriod(Long myRadianBillingPeriodID) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_BILLING_PERIOD_BY_MY_RAD_ID);
        query.setNamedParameter("myRadID", myRadianBillingPeriodID);
        Collection results = query.execute();
        if (results != null && results.size() > 0)
        {
        	return (CUWBillingPeriod)results.iterator().next();
        }
        else
        {
        	return null;
        }
    }
    
    public BillingPeriod readMyRadBillingPeriod(Long id) throws PersistenceProviderException
    {
    	return (BillingPeriod) get(BillingPeriodImpl.class, id);
    }

    
    public BatchHistoryItem getLastBatchHistoryItemByEndDate(Date endDate) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_LAST_BATCH_HISTORY_ITEM_BY_END_DATE);
        query.setNamedParameter("endDate", endDate);
        Collection results = query.execute();
        if (results != null && results.size() > 0)
        {
        	return (BatchHistoryItem)results.iterator().next();
        }
        else
        {
        	return null;
        }
    }
    
    public Date getLastExecutionDateByCustomerExportTask() throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_LAST_EXECUTION_DATE_BY_CUSTOMER_EXPORT_TASK);
        
        query.setNamedParameter("flowName", CUSTOMER_EXPORT_FLOW_NAME);
        query.setNamedParameter("taskName", CUSTOMER_EXPORT_TASK_NAME);
        
        Collection results = query.execute();
        if (results != null)
        {
        	return (Date)results.iterator().next();
        }
        else
        {
        	return null;
        }
    }
    
    public MyRadianBatchHistoryItem getMyRadLastBatchHistoryItemByEndDate(Date endDate) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_MYRAD_LAST_BATCH_HISTORY_ITEM_BY_END_DATE);
        query.setNamedParameter("endDate", endDate);
        Collection results = query.execute();
        if (results != null && results.size() > 0)
        {
        	return (MyRadianBatchHistoryItem)results.iterator().next();
        }
        else
        {
        	return null;
        }
    }
    
    public String getCostCenterNumber(Long bdrID) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_COST_CENTER_NUMBER);
        query.setNamedParameter("bdrID", bdrID);
        Collection results = query.execute();
        if (results != null && results.size() > 0)
        {
        	return (String)results.iterator().next();
        }
        else
        {
        	return null;
        }
    }
    
    public Collection getPerDiemOrders(String bpNum) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_PER_DIEM_ORDERS_BY_PROFILE);
        query.setNamedParameter("profileNum", bpNum);
        return query.execute();
    }
    
    public CUWBillingPeriod readBillingPeriod(Long id) throws PersistenceProviderException
    {
    	return (CUWBillingPeriod) get(CUWBillingPeriodImpl.class, id);
    }
    
    /*
     * removes by query
     */
    public void cleanUpOrderItemRecord(Date startDate, String repID) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(FIND_ORDER_ITEMS_BY_PERIOD_START_DATE);
        
        try {
        	String queryString = query.getQueryString().replaceFirst(":repID", "'" + repID + "'");
        	deleteByQuery( queryString );
        	
        }catch (Exception e){
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }
        finally
        {
        	this.closeProvider();
        }
    }
    
    public void cleanUpServiceCenterLoanRecord(Long periodID) throws PersistenceProviderException
    {
    	Query query = getProvider().newQuery(FIND_SERVICE_CENTER_LOANS_BY_PERIOD_START_DATE);
        
        try {
        	if (periodID != null)
        	{
	        	String queryString = query.getQueryString().replaceFirst(":periodID", periodID.toString());
	        	deleteByQuery( queryString );
        	}
        	
        }catch (Exception e){
        	System.out.println("cleanUpServiceCenterLoanRecord - " + e.getMessage());
        	e.printStackTrace();
        }
        finally
        {
        	this.closeProvider();
        }
    }
    
    public Collection getLastUnfinishedBatchRun() throws PersistenceProviderException
    {
    	Query query = getProvider().newQuery(GET_UNFINISHED_INVOICE_RUN);
    	Date now = new Date();
    	long time = now.getTime() - 2 * 60 * 60 * 1000; //2 hours before	
    	//long time = now.getTime() - 5 * 60 * 1000; //3 minutes before
    	Date newDate = new Date();
    	newDate.setTime(time);
    	query.setNamedParameter("date", newDate);
    	return query.execute();
    }
    
    public CUWBillingPeriod getBillingPeriodByDates(Date billingPeriodStartDate, Date billingPeriodEndDate, Boolean isTrialRun) throws PersistenceProviderException
    {
    	CUWBillingPeriod period = null;
    	Query query = getProvider().newQuery(GET_BILLING_PERIOD_BY_DATES);
    	query.setNamedParameter("startDate", billingPeriodStartDate);
    	query.setNamedParameter("endDate", billingPeriodEndDate);
    	if (Boolean.TRUE.equals(isTrialRun))
    	{
    		query.setNamedParameter("flag", Boolean.FALSE);
    	}
    	else if (Boolean.FALSE.equals(isTrialRun))
    	{
    		query.setNamedParameter("flag", Boolean.TRUE);
    	}
    	Collection results = query.execute();
    	if (results != null && results.size() > 0)
    	{
    		period = (CUWBillingPeriod)results.iterator().next();
    	}
    	return period;
    }
    
    private String getStartDateString(Date startDate)
    {
    	Calendar calendar = Calendar.getInstance();   	
    	calendar.setTime(startDate);  	
		return  "'" + (new java.text.SimpleDateFormat("dd-MMM-yyyy")).format(calendar.getTime()) + "'";
    }
    
    public Collection getPayments() throws PersistenceProviderException
    {
    	Query query = getProvider().newQuery(GET_PAYMENTS);
        return query.execute();
    }
        
    private int getDateDifference(Date date1, Date date2)
    {
        GregorianCalendar gc1 = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();
        gc1.setTime(date1);
        gc2.setTime(date2);

        long millies = gc2.getTimeInMillis() - gc1.getTimeInMillis();
        return (int) (millies / 1000 / 24 / 60 / 60);
    }

    private Date getLastInvoiceDate(Date endDate, String strLag)
    {
        Date date = null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(endDate);

        if (endDate != null && strLag != null)
        {
            long m1 = gc.getTimeInMillis();
            long m2 = Long.parseLong(strLag) * 24 * 60 * 60 * 1000;
            date = new Date();
            date.setTime(m1 + m2);
        }

        return date;
    }

    private Collection getLastBillingPeriod(Date date) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_LAST_BILLING_PERIOD);
        query.setParameters(new Object[] { date });
        return query.execute();
    }
    
    public Collection getMyRadOrderItem(String orderItemNum) throws PersistenceProviderException
    {
    	MyRadOrderItem oi = null;
    	Query query = getProvider().newQuery(GET_MYRAD_ORDER_ITEM);
    	query.setNamedParameter("orderItemNum", orderItemNum);
        return query.execute();
    }
    
    public Collection getOrderItem(String orderItemNum) throws PersistenceProviderException
    {
    	CUWOrderItem oi = null;
    	Query query = getProvider().newQuery(GET_ORDER_ITEM);
    	query.setNamedParameter("orderItemNum", orderItemNum);
        return query.execute();
    }
    
    public Collection getOrderItemByInvoiceID(Long invID) throws PersistenceProviderException
    {
    	Collection orders = new ArrayList();
    	Collection packages = new ArrayList();
    	Collection perDiemOrders = new ArrayList();
    	
    	Query query1 = getProvider().newQuery(GET_PER_DIEM_ORDER_ITEM_NUMBERS_BY_INVOICE_ID);
    	query1.setNamedParameter("invoiceID", invID);
        Collection perDiemOrderIDs = query1.execute();
        packages = this.packagingCollections(perDiemOrderIDs);
        
        Iterator pIT = packages.iterator();
        while (pIT.hasNext())
        {
	        Collection perDiemOrderNums = (Collection)pIT.next();
	        if (perDiemOrderNums != null && perDiemOrderNums.size() > 0)
	        {
	        	Collection col = this.getOrderItemsByRadianNumber(perDiemOrderNums);
	        	perDiemOrders.addAll(col);
	        }
        }
        
        Query query2 = getProvider().newQuery(GET_PER_LOAN_ORDER_ITEMS_BY_INVOICE_ID);
        query2.setNamedParameter("invoiceID", invID);
        Collection perLoanOrders = query2.execute();
        
        orders.addAll(perDiemOrders);
        orders.addAll(perLoanOrders);
        
        return orders;
    }
    
    /**
     * 
     * @return
     * @throws PersistenceProviderException
     */
    public Integer getUnbilledOrderItemCount() throws PersistenceProviderException
    {
    	Query query = getProvider().newQuery(GET_UNBILLED_ORDER_COUNT);
    	query.setNamedParameter("statusBilled", BillingStatus.NOT_PRICED.getID());
        Collection col = query.execute();
        Integer count = null;
        
        if (col != null && col.size() > 0)
        {
        	count = (Integer)col.iterator().next();
        }
    	return count;
    }

    /**
     * 
     * @param criteria
     * @param window
     * @return
     * @throws PersistenceProviderException
     */
    public ScrolledQueryResult getUnbilledTimesheetsByCriteria(SearchCriteria criteria, ScrollWindow window) throws PersistenceProviderException
    {
    	try {
        return new QueryMapper(getProvider()).fetch(TimeSheetDTO.class, criteria, window);
    	} catch ( PersistenceProviderException e) {
    		e.printStackTrace();
    		throw e;
    	}
    }
    
    /**
     * 
     * @param criteria
     * @param window
     * @return
     * @throws PersistenceProviderException
     */
    public ScrolledQueryResult getOtherExceptionsByCriteria(SearchCriteria criteria, ScrollWindow window) throws PersistenceProviderException
    {
    	try {
        return new QueryMapper(getProvider()).fetch(TimeSheetDTO.class, criteria, window);
    	} catch ( PersistenceProviderException e) {
    		e.printStackTrace();
    		throw e;
    	}
    }
    
    public ScrolledQueryResult getUnbilledOrderItemsByCriteria(SearchCriteria criteria, ScrollWindow window) throws PersistenceProviderException
    {
    	try {
        return new QueryMapper(getProvider()).fetch(UnbilledOrderDTO.class, criteria, window);
    	} catch ( PersistenceProviderException e) {
    		e.printStackTrace();
    		throw e;
    	}
    }
    
    public Integer getUnbilledTimesheetCount() throws PersistenceProviderException
    {
    	Query query = getProvider().newQuery(GET_UNBILLED_HOURS_COUNT);
    	query.setNamedParameter("statusBilled", BillingStatus.BILLED.getID());
        Collection col = query.execute();
        Integer count = null;
        
        if (col != null && col.size() > 0)
        {
        	count = (Integer)col.iterator().next();
        }
    	return count;
    }
    
    public MyRadOrderReplicateDTO getMyRadReplicateDTO(Long bdrID) throws PersistenceProviderException
    {
    	MyRadOrderReplicateDTO dto = null;
    	
    	Query query = getProvider().newQuery(GET_MYRAD_ORDER_ITEM_REPLICATE);
    	query.setNamedParameter("bdrID", bdrID);
        Collection col = query.execute();
        if (col != null && col.size() > 0)
        {
        	dto = (MyRadOrderReplicateDTO)col.iterator().next();
        }
    	return dto;
    }
    
    public CustBranch getCustInfo(Long branchID) throws PersistenceProviderException
    {
    	CustBranch cust = null;
    	
    	Query query = getProvider().newQuery(GET_CUST_INFO);
    	query.setNamedParameter("branchID", branchID);
        Collection col = query.execute();
        if (col != null && col.size() > 0)
        {
        	cust = (CustBranch)col.iterator().next();
        }
    	return cust;
    }
    

    public Collection getBilledProfiles(Date periodStartDate) throws PersistenceProviderException
    {
    	Query namedQuery = getProvider().newQuery(GET_BILLED_PROFILES_FOR_PERIOD);
    	namedQuery.setNamedParameter("startDate", periodStartDate);
        return namedQuery.execute();
    }

    public Collection getServiceCenterLoansForThirParty(CUWBillingPeriod period, Boolean isTrialRun, String custNum, Collection thirdParties) throws PersistenceProviderException
    {
    	Collection packages = this.packagingCollections(thirdParties);
    	Collection loans = new ArrayList();
        Iterator it = packages.iterator();
        while (it.hasNext())
        {
        	Collection list = (ArrayList) it.next();
        	if (period != null)
	    	{
		    	Query namedQuery = getProvider().newQuery(GET_LOANS_FOR_THIRD_PARTIES);
		    	namedQuery.setNamedParameter("investor", custNum);
		    	namedQuery.setNamedParameter("endDate", period.getEndDate());	
		    	namedQuery.setNamedParameterList("thirdParties", list);
		        Collection packageLoans = namedQuery.execute();
		        if (packageLoans != null && packageLoans.size() > 0)
		        {
		        	loans.addAll(packageLoans);
		        }
	    	}
	    	else
	    	{
	    		return null;
	    	}
        }
        return loans;
    }
    
    public Collection getServiceCenterLoansForCoveredBranches(CUWBillingPeriod period, Boolean isTrialRun, String custNum, Collection branches) throws PersistenceProviderException
    {
    	Collection packages = this.packagingCollections(branches);
    	Collection loans = new ArrayList();
        Iterator it = packages.iterator();
        while (it.hasNext())
        {
        	Collection list = (ArrayList) it.next();
	    	if (period != null)
	    	{
		    	Query namedQuery = getProvider().newQuery(GET_LOANS_FOR_BRANCHES);
		    	namedQuery.setNamedParameter("custNum", custNum);  
		    	namedQuery.setNamedParameter("endDate", period.getEndDate());	 
		    	namedQuery.setNamedParameterList("branches", list);	
		    	Collection packageLoans = namedQuery.execute();
		        if (packageLoans != null && packageLoans.size() > 0)
		        {
		        	loans.addAll(packageLoans);
		        }
	    	}
	    	else
	        {
	        	return null;
	        }
        }
        return loans;
    }
    
    public Collection getServiceCenterLoansForCustomer(CUWBillingPeriod period, Boolean isTrialRun, String custNum) throws PersistenceProviderException
    {
    	if (period != null)
    	{
	    	Query namedQuery = getProvider().newQuery(GET_LOANS_FOR_CUSTOMER);
	    	namedQuery.setNamedParameter("custNum", custNum);
	    	namedQuery.setNamedParameter("endDate", period.getEndDate());	  
	        return namedQuery.execute();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    private Collection getStubbedLoans(int startingNum, CUWBillingPeriod period, Boolean isTrialRun)
    {
    	Collection loans = new ArrayList();
    	for (int i = startingNum; i < startingNum + 10; i++)
		{
			LLNOrderItem oi = new LLNOrderItemImpl();
	    	oi.setOrderItemNumber(new String (new Long(17000000 + i).toString()));
	    	oi.setUnderwriterCode("ABC");
	    	oi.setFulfillmentDate(new Date());
	    	oi.setServiceType("Manual");
			oi.setApplicantFirstName("Jack");
			oi.setApplicantLastName("Homeowner");
			oi.setApplicantMiddleName("A");
			oi.setApplicantSSN("333-44-5555");
			oi.setAuResponseType("DU");
			oi.setBaseLoanAmt(new Double(120000));
			oi.setBaseLTV("0.8");
			oi.setBrokerLoanNumber("12345");
			oi.setChannel("Retail");
			oi.setAusName("DU");
	    	oi.setUnderwriteLienType("1");
	    	oi.setPiggyFlag(Boolean.FALSE);
	    	oi.setMiFlag(Boolean.TRUE);
	    	oi.setMiCommitmentNum("22334455");
	    	oi.setLenderRegID("123456");
	    	oi.setAuResponseType("Approved");
	    	oi.setDecisionDisp("Approved");
	    	oi.setDuCaseNum("123");    	
	    	oi.setLoanProgram("test");   	
	    	oi.setLoanPurposeType("Purchase");
	    	oi.setPropertyAddr1("1 test road");
	    	oi.setPropertyCity("Philadelphia");
	    	oi.setPropertyState("PA");
	    	oi.setPropertyZip("19130");
	    	oi.setClientLenderNumber("13160");
	    	oi.setClientLenderBranchNumber("000");
		    oi.setSubmitByNum("13160");
		    oi.setSubmitByBranchNum("000");
		    oi.setRadianCredential(Boolean.TRUE);
		    oi.setInvestor("13160");	    	
	    	oi.setModifiedDate(new Date());
	    	oi.setCreatedDate(new Date());
	    	loans.add(oi);
	    	
		}
	    return loans;
    }
    
    private Collection packagingCollections(Collection col)
    {
        ArrayList packages = new ArrayList();
        ArrayList list = new ArrayList();
        Iterator it = col.iterator();
        while (it.hasNext())
        {
            String record = (String) it.next();
            list.add(record);
            if (list.size() == 1000)
            {
                packages.add(list);
                list = new ArrayList();
            }
        }
        // add the last list
        if (list.size() != 0)
        {
            packages.add(list);
        }
        return packages;
    }
}
