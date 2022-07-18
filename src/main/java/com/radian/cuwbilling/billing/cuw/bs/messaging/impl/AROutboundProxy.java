/*
 * @(#)AROutboundProxy.java
 */
package com.radian.cuwbilling.billing.cuw.bs.messaging.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import com.radian.cuwbilling.billing.common.bs.BillingException;
import com.radian.cuwbilling.billing.cuw.bo.dto.ARTransactionSummaryDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.BillingProfileSummaryDTO;
import com.radian.cuwbilling.billing.cuw.bs.CUWBillingException;
import com.radian.cuwbilling.billing.cuw.bs.messaging.AROutboundSender;
import com.radian.cuwbilling.common.bs.util.DTOtoXML.DTOtoXML;
import com.radian.foundation.os.content.composition.DocGenException;
import com.radian.foundation.os.content.composition.DocumentGenerator;
import com.radian.foundation.os.content.composition.DocumentGeneratorFactory;
import com.radian.foundation.os.content.composition.DocumentTypeEnum;
import org.apache.commons.net.ftp.FTPClient;
import com.radian.foundation.common.config.Configuration;


/**
 * Implementation class for export transactions
 *
 * @author James Carpenter
 */

public class AROutboundProxy extends AROutboundSender
{

	private String exportType;

    public static final String EXPORT_CUSTOMER_PROFILE_XSLT_KEY = "ExportCustomerProfile";
    public static final String EXPORT_BILLING_INVOICE_XSLT_KEY = "ExportInvoice";
    public static final String DOC_VERSION = "1";

    public static final String FTP_HOST = "export.peoplesoft.ftp.host";
    public static final String FTP_USER_NAME = "export.peoplesoft.ftp.username";
    public static final String FTP_PASSWORD = "export.peoplesoft.ftp.password";

    public static final String FTP_LENDER_SUB_FOLDER = "export.customerprofile.peoplesoft.ftp.subfolder";
    public static final String FTP_LENDER_BACKUP_FOLDER = "export.customerprofile.peoplesoft.ftp.backup.folder";
    public static final String FTP_INVOICE_SUB_FOLDER = "export.invoice.peoplesoft.ftp.subfolder";
    public static final String FTP_INVOICE_BACKUP_FOLDER = "export.invoice.peoplesoft.ftp.backup.folder";
    public static final String FTP_INVOICE_ADJUSTMENT_SUB_FOLDER = "export.invoiceadjustment.peoplesoft.ftp.subfolder";
    public static final String FTP_INVOICE_ADJUSTMENT_BACKUP_FOLDER = "export.invoiceadjustment.peoplesoft.ftp.backup.folder";

    public static final String AR_CUSTOMER_EXPORT_INFO_TAG = "billingProfileSummaryDTOs";
    public static final String AR_INVOICE_EXPORT_INFO_TAG = "arTransactionSummary";
    public static final String AR_INVOICE_ADJ_EXPORT_INFO_TAG = "arTransactionSummary";

    public static final String CUSTOMER_PROFILE = "ExportCustomerProfile";
    public static final String INVOICE = "ExportInvoice";
    public static final String INVOICE_ADJUSTMENTS = "ExportInvoiceAdjustments";

    public static final String CUSTOMER_PROFILE_FILE_NAME = "Customer";
    public static final String INVOICE_FILE_NAME = "re_ar_cus_billing";
    public static final String INVOICE_ADJ_FILE_NAME = "re_ar_cus_adjbill";

    /**
	 * @param billingProfileSummaryDTOs
	 * @return
	 * @throws Exception
	 */
    public boolean sendCustomerData(Collection billingProfileSummaryDTOs) throws BillingException
    {
    	//getLogger().entering(this.getClass(), "sendCustomerData");
		byte[] textDocument = null;
        boolean ftpStatus = false;
		setExportType(CUSTOMER_PROFILE);

		try
		{
			ByteArrayInputStream xmlDocument = convertCustomerProfilesToXML(billingProfileSummaryDTOs);

			textDocument = convertXMLtoTextDocument(xmlDocument, EXPORT_CUSTOMER_PROFILE_XSLT_KEY, DOC_VERSION);

			ftpStatus = ftpTextFiletoPeopleSoft(textDocument, CUSTOMER_PROFILE_FILE_NAME, FTP_LENDER_SUB_FOLDER, FTP_LENDER_BACKUP_FOLDER);
        }
        catch(CUWBillingException e)
        {
        	throw new BillingException(e.getMessage());
        }
        //getLogger().exiting(this.getClass(), "sendCustomerData");

        return ftpStatus;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.radian.cuwbilling.billing.cuw.bs.messaging.AROutboundSender#sendARInvoiceData(java.util.Collection)
     */
    public boolean sendARInvoiceData(ARTransactionSummaryDTO arTransactionSummary) throws BillingException
    {
    	//getLogger().entering(this.getClass(), "sendARInvoiceData");
		byte[] textDocument = null;
        boolean ftpStatus = false;
		setExportType(INVOICE);

		try
		{
			ByteArrayInputStream xmlDocument = convertInvoiceToXML(arTransactionSummary);

			textDocument = convertXMLtoTextDocument(xmlDocument, EXPORT_BILLING_INVOICE_XSLT_KEY , DOC_VERSION);

			ftpStatus = ftpTextFiletoPeopleSoft(textDocument, INVOICE_FILE_NAME, FTP_INVOICE_SUB_FOLDER, FTP_INVOICE_BACKUP_FOLDER);
        }
        catch(CUWBillingException e)
        {
        	throw new BillingException(e.getMessage());
        }
        //getLogger().exiting(this.getClass(), "sendARInvoiceData");
        return ftpStatus;
    }

    /* (non-Javadoc)
     * @see com.radian.cuwbilling.billing.cuw.bs.messaging.AROutboundSender#sendARInvoiceAdjData(com.radian.cuwbilling.billing.cuw.bo.dto.ARTransactionSummaryDTO)
     */
    public boolean sendARInvoiceAdjData(ARTransactionSummaryDTO arTransactionSummary) throws BillingException
    {
    	//getLogger().entering(this.getClass(), "sendARInvoiceAdjData");
    	System.out.println("Entering sendARInvoiceAdjData method");
		byte[] textDocument = null;
        boolean ftpStatus = false;
		setExportType(INVOICE_ADJUSTMENTS);
        try
		{
			ByteArrayInputStream xmlDocument = convertInvoiceToXML(arTransactionSummary);

			textDocument = convertXMLtoTextDocument(xmlDocument, EXPORT_BILLING_INVOICE_XSLT_KEY , DOC_VERSION);

			ftpStatus = ftpTextFiletoPeopleSoft(textDocument, INVOICE_ADJ_FILE_NAME, FTP_INVOICE_ADJUSTMENT_SUB_FOLDER, FTP_INVOICE_ADJUSTMENT_BACKUP_FOLDER);
        }
        catch(CUWBillingException e)
        {
        	System.out.println("Error in sendARInvoiceAdjData function ");
			e.printStackTrace();
        	throw new BillingException(e.getMessage());
        }
        //getLogger().exiting(this.getClass(), "sendARInvoiceAdjData");
        System.out.println("Leaving sendARInvoiceAdjData method");

        return ftpStatus;
    }

	/**
	 * @param exportCustomerData
	 * @return
	 * @return
	 * @throws Exception
	 */
	private ByteArrayInputStream convertCustomerProfilesToXML(Collection exportCustomerData) throws CUWBillingException
	{
		//getLogger().entering(this.getClass(), "convertCustomerProfilesToXML");
		String headerString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<" + AR_CUSTOMER_EXPORT_INFO_TAG + ">";
        String footerString = "</" + AR_CUSTOMER_EXPORT_INFO_TAG + ">";

    	ByteArrayOutputStream xmlStream = new ByteArrayOutputStream();
    	ByteArrayInputStream inStream = null;
		DTOtoXML xml = new DTOtoXML(xmlStream);

		try
		{
			xml.writeString(headerString);
			Iterator itr = exportCustomerData.iterator();
			while (itr.hasNext())
			{
				BillingProfileSummaryDTO dto = (BillingProfileSummaryDTO) itr
						.next();

				xml.getXML("item", dto);
			}
			xml.writeString(footerString);

			inStream = new ByteArrayInputStream(xmlStream.toByteArray());
		}
		catch (IOException ie)
		{
			//getLogger().error("Error while converting Customer Profile to XML document ", ie);
			throw new CUWBillingException("errors.export.xmlfileconversion.customerprofile", new Object[] { (ie.getMessage()) });
		}
		//getLogger().exiting(this.getClass(), "convertCustomerProfilesToXML");
		return inStream;
	}

	/**
	 * @param exportCustomerData
	 * @return
	 * @return
	 * @throws Exception
	 */
	private ByteArrayInputStream convertInvoiceToXML(ARTransactionSummaryDTO exportInvoiceData) throws CUWBillingException
	{
		//getLogger().entering(this.getClass(), "convertInvoiceToXML");

		System.out.println("Entering convertInvoiceToXML method");

    	ByteArrayOutputStream xmlStream = new ByteArrayOutputStream();
    	ByteArrayInputStream inStream = null;
		DTOtoXML xml = new DTOtoXML(xmlStream, "UTF-8");

		try
		{
			if(exportInvoiceData != null)
			{
				xml.getXML(AR_INVOICE_EXPORT_INFO_TAG, exportInvoiceData, false);
			}
			else
			{
				xml.writeString("<?xml version=\"1.0\" encoding=\"UTF-8\"?><arTransactionSummary></arTransactionSummary>");
			}

			inStream = new ByteArrayInputStream(xmlStream.toByteArray());
		}
		catch (Exception ie)
		{
			String errorMsg = null;
			String errorKey = null;
			if(INVOICE.equalsIgnoreCase(getExportType()))
			{
				errorMsg = "Error while converting Invoice to XML document ";
				errorKey = "errors.export.xmlfileconversion.invoice";
			}
			else if(INVOICE_ADJUSTMENTS.equalsIgnoreCase(getExportType()))
			{
				errorMsg = "Error while converting Invoice Adjustment to XML document ";
				errorKey = "errors.export.xmlfileconversion.adj";
			}

			//getLogger().error(errorMsg, ie);

			System.out.println("Error while converting Invoice/Invoice Adjustment to XML document ");
			ie.printStackTrace();

			throw new CUWBillingException(errorKey, new Object [] { (ie.getMessage()) });
		}
		System.out.println("Leaving convertInvoiceToXML method");
		//getLogger().exiting(this.getClass(), "convertInvoiceToXML");
		return inStream;
	}


	/**
	 * @param xmlDocument
	 * @param xsltKey
	 * @param docVersion
	 * @return
	 * @return
	 * @throws Exception
	 */
	private byte[] convertXMLtoTextDocument(ByteArrayInputStream xmlDocument,
			String xsltKey, String docVersion) throws CUWBillingException
	    {
			//getLogger().entering(this.getClass(), "convertXMLtoTextDocument");
			System.out.println("Entering convertXMLtoTextDocument method");
			byte[] textDocument = null;

	    	try
	    	{
	    		DocumentGeneratorFactory factory = DocumentGeneratorFactory.getInstance();
		        DocumentGenerator documentGenerator = factory.getDocumentGenerator();
		        com.radian.foundation.os.content.composition.Document document = documentGenerator.generate(xmlDocument, xsltKey, docVersion, DocumentTypeEnum.TEXT);
		        if (document != null && document.getContent() != null)
		        {
		            OutputStream docContent = document.getContent();
		            ByteArrayOutputStream res = (ByteArrayOutputStream) docContent;
		            textDocument = res.toByteArray();
		        }
	    	}
	    	catch (Exception de)
	    	{
	    		String errorMsg = null;
	    		String errorKey = null;

	    		if(CUSTOMER_PROFILE.equalsIgnoreCase(getExportType()))
	    		{
	    			errorMsg = "Error while converting Customer Profile XML document to Flat file ";
	    			errorKey = "errors.export.textfileconversion.customerprofile";
	    		}
	    		else if(INVOICE.equalsIgnoreCase(getExportType()))
	    		{
	    			errorMsg = "Error while converting Invoice XML document to Flat file ";
	    			errorKey = "errors.export.textfileconversion.invoice";
	    		}
	    		else if(INVOICE_ADJUSTMENTS.equalsIgnoreCase(getExportType()))
	    		{
	    			errorMsg = "Error while converting Invoice Adjustment XML document to Flat file ";
	    			errorKey = "errors.export.textfileconversion.adj";
	    		}

	    		//getLogger().error(errorMsg, de);

				System.out.println("Error while converting Invoice/Invoice Adjustment XML document to Flat file ");
				de.printStackTrace();

				throw new CUWBillingException(errorKey, new Object [] {(de.getMessage())});
	    	}
	    	System.out.println("Leaving convertXMLtoTextDocument method");
	    	//getLogger().exiting(this.getClass(), "convertXMLtoTextDocument");
			return textDocument;
		}


	/**
     * @param doc
     * @param filename
	 * @param ftpBackupFolder
     * @param ftp_sub_folder
     * @return
     * @throws Exception
     */
    private boolean ftpTextFiletoPeopleSoft(byte[] doc, String filename, String ftpSubfolder, String ftpBackupFolder) throws CUWBillingException
	{
		//getLogger().entering(getClass(), "ftpTextFiletoPeopleSoft()");
		System.out.println("Entering ftpTextFiletoPeopleSoft method");

		boolean retVal = false;

    	FTPClient client = new FTPClient();
    	FTPClient clientBk = new FTPClient();
    	OutputStream os = null;
    	OutputStream osBk = null;
    	boolean loginSuccessful;
    	Properties prop = new Properties(); //Repositioned code - 09APR2013
        try {

        	Configuration ftpConfig =null;// ServiceLocator.getInstance().getConfiguration("ftp-config");

        	//Properties prop = new Properties();
        	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(ftpConfig.getString("configFile"));
        	prop.load(is);
        	is.close();

        	client.connect(prop.getProperty(FTP_HOST));
        	loginSuccessful = client.login(prop.getProperty(FTP_USER_NAME), prop.getProperty(FTP_PASSWORD));
        	Thread.sleep(1000);  //New 17Apr2013
        	if (!loginSuccessful)
        	{
        		//getLogger().error("Failed to logon to FTP server for sending file to main folder: "+ prop.getProperty(FTP_HOST));
        		throw new Exception("Failed to logon to FTP server: "+ prop.getProperty(FTP_HOST));
        	}

        	clientBk.connect(prop.getProperty(FTP_HOST));
        	loginSuccessful = clientBk.login(prop.getProperty(FTP_USER_NAME), prop.getProperty(FTP_PASSWORD));
        	Thread.sleep(1000);  //New 17Apr2013
        	if (!loginSuccessful)
        	{
        		//getLogger().error("Failed to logon to FTP server for sending file to backup folder: "+ prop.getProperty(FTP_HOST));
        		throw new Exception("Failed to logon to FTP server: "+ prop.getProperty(FTP_HOST));
        	}

        	String fmtFileName = filename;

        	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        	String timestamp = formatter.format(new Date());

        	if(INVOICE_ADJUSTMENTS.equalsIgnoreCase(getExportType()))
        		fmtFileName = filename + "_" + timestamp+".txt";
        	if(INVOICE.equalsIgnoreCase(getExportType()))
        		fmtFileName = filename +".txt";
        	if(CUSTOMER_PROFILE.equalsIgnoreCase(getExportType()))
        		fmtFileName = filename + ".csv";
        	client.enterLocalPassiveMode();
        	//getLogger().entering(getClass(), "Reply String after enter local passive mode for client - " + client.getReplyString());
        	client.setFileType(FTPClient.ASCII_FILE_TYPE);
        	client.changeWorkingDirectory(prop.getProperty(ftpSubfolder));
			Thread.sleep(1000);  //New 17Apr2013
        	os = client.storeFileStream(fmtFileName);
        	Thread.sleep(1000);  //New 17Apr2013
        	os.write(doc);
        	Thread.sleep(1000);  //New 17Apr2013
        	os.flush();
        	Thread.sleep(1000);  //New 17Apr2013
        	os.close();       	
        	client.completePendingCommand();
        	//getLogger().entering(getClass(), "Reply String after complete pending command for client - " + client.getReplyString());

        	formatter = new SimpleDateFormat("yyyyMMddHHmmssSS");
        	timestamp = formatter.format(new Date());
        	if(CUSTOMER_PROFILE.equalsIgnoreCase(getExportType()))
        		filename = filename+"_"+timestamp+".csv";
        	else
        		filename = filename+"_"+timestamp+".txt";	
        	clientBk.enterLocalPassiveMode();
        	//getLogger().entering(getClass(), "Reply String after enter local passive mode - " + clientBk.getReplyString());
        	clientBk.setFileType(FTPClient.ASCII_FILE_TYPE);
        	clientBk.changeWorkingDirectory(prop.getProperty(ftpBackupFolder));
        	osBk = clientBk.storeFileStream(filename);
        	osBk.write(doc);
        	osBk.flush();
        	osBk.close();
        	clientBk.completePendingCommand();
        	//getLogger().entering(getClass(), "Reply String after complete pending command for clientBK - " + clientBk.getReplyString());

	        retVal = true;
        }
        catch (Exception e)
        {
        	String errorMsg = null;
        	String errorKey = null;
        	if(CUSTOMER_PROFILE.equalsIgnoreCase(getExportType()))
        	{
        		errorMsg = "Process failed when FTP Customer Profile ";
        		errorKey = "errors.export.ftptopeoplesoft.customerprofile";
        	}
        	else if(INVOICE.equalsIgnoreCase(getExportType()))
        	{
        		errorMsg = "Process failed when FTP Invoice ";
        		errorKey = "errors.export.ftptopeoplesoft.invoice";
        	}
        	else if(INVOICE_ADJUSTMENTS.equalsIgnoreCase(getExportType()))
        	{
        		errorMsg = "Process failed when FTP Invoice Adjustments ";
        		errorKey = "errors.export.ftptopeoplesoft.adj";
        	}

        	//getLogger().error(errorMsg, e);

        	System.out.println("Error while FTP Invoice/Invoice Adjustment file to P-Soft");
			e.printStackTrace();

        	throw new CUWBillingException(errorKey, new Object [] {(e.getMessage())});
        }
        finally
        {
	        // release resources
        	try
        	{
        		if( os != null )
		        {
		        	os.close();
		        }

		        if( osBk != null )
		        {
		        	osBk.close();
		        }
				Thread.sleep(1000);  //New 17Apr2013
        		if(client.isConnected())
        		{
    		        //client.logout(); // Commenting this out because system is timing out in this process.
        			client.disconnect();
        		}

        		if(clientBk.isConnected())
        		{
    		        //clientBk.logout(); // Commenting this out because system is timing out in this process.
        			clientBk.disconnect();
        		}
        	}
        	catch ( Exception e )
        	{
        		//getLogger().error("Error closing ftp connections.", e);
        	}
        }
        //getLogger().exiting(getClass(), "ftpTextFiletoPeopleSoft()");
        System.out.println("Leaving ftpTextFiletoPeopleSoft method");
        return retVal;
	}



public String getExportType() {
	return exportType;
}

public void setExportType(String exportType) {
	this.exportType = exportType;
}
}
