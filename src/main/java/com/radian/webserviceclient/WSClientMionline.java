/**
 * @(#) WSClientMionline.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.webserviceclient;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import MionlineCUW.radianmi.WMCUWGetAllUnbilledLoans;
import MionlineCUW.radianmi.WMCUWGetAllUnbilledLoansForDates;
import MionlineCUW.radianmi.WMCUWGetAllUnbilledLoansForDatesResponse;
import MionlineCUW.radianmi.WMCUWGetAllUnbilledLoansResponse;
import MionlineCUW.radianmi.WMCUWGetRepInfo;
import MionlineCUW.radianmi.WMCUWGetRepInfoResponse;
import MionlineCUW.radianmi.WMCUWListUnderwritersWithUnbilledLoansForDates;
import MionlineCUW.radianmi.WMCUWListUnderwritersWithUnbilledLoansForDatesResponse;
import MionlineCUW.radianmi.WMCUWUpdateLoanBillingStatus;
import MionlineCUW.radianmi.WMCUWUpdateLoanBillingStatusResponse;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.BillingStatusUpdateDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.RepInfoDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.UnbilledLoanDTO;
import com.radian.foundation.common.logging.LogManager;
import com.radian.foundation.common.logging.Logger;
import com.radian.ws.client.CUWServiceSoap;

/**
 * Webservice client to call various MIOnline webservice methods for CUW Billing
 */
public class WSClientMionline implements WSClient
{
	private CUWServiceSoap webservice;
	
    private static Logger logger = LogManager.getLogger(WSClientMionline.class);
    private static String WS_XMLHDR = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private static String EMPTY_STRING = "";
    private static String LOANID_SEP = ",";
    private static String WSERR_NOLOANIDS = "WSClientMionline.getLoanList: No loanIDs provided";
	private static String WS_DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
	private static String WS_DEFAULT_FROM_DATE = "01/01/1990";
	private static String WS_DEFAULT_TO_DATE = "12/31/2030";
    private static String WS_CLEANXML_AMPSYM = "\\&";
    private static String WS_CLEANXML_AMPSYM_REPL = " ";
	
	/**
	 * @param webservice
	 */
	protected void setWebservice(CUWServiceSoap webservice)
	{
		this.webservice = webservice;
	}
	
	/**
	 * @param repID
	 * @return rep info DTO with info about underwriter code (rep ID)
	 * @throws WSClientException
	 * @throws WSClientExceptionStatus
	 */
	public RepInfoDTO getRepInfo(String repID) throws WSClientException, WSClientExceptionStatus
	{
		WMCUWGetRepInfo mioParamsRepInfo = new WMCUWGetRepInfo();
		mioParamsRepInfo.setStrRepID(getRepID(repID));
		WMCUWGetRepInfoResponse mioResponse = null;
		try
		{	//call web service
			mioResponse = getWebservice().wMCUWGetRepInfo(mioParamsRepInfo);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSMionlineSaxHandlerRepInfo repinfoHandler = new WSMionlineSaxHandlerRepInfo();
		parseResponse(mioResponse.getWMCUWGetRepInfoResult(), repinfoHandler);
//TEMP TEMP log rep info
printRepInfo(repinfoHandler);
		return repinfoHandler.getRepInfo();
	}

	/**
	 * @return Collection (ArrayList) of underwriter codes (repIDs) as Strings
	 * @throws WSClientException
	 * @throws WSClientExceptionStatus
	 */
	public Collection getUnderwritersWithUnbilledLoans() throws WSClientException, WSClientExceptionStatus
	{
		String mioResponse;
		try
		{	//call web service
			mioResponse = getWebservice().wMCUWListUnderwritersWithUnbilledLoans();
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSMionlineSaxHandlerListUnderwriters listUwHandler = new WSMionlineSaxHandlerListUnderwriters();
		parseResponse(mioResponse, listUwHandler);
//TEMP TEMP log list underwriters
printListUnderwriters(listUwHandler);
		return listUwHandler.getUwList();
	}

	/**
	 * @return Collection (ArrayList) of underwriter codes (repIDs) as Strings
	 * @throws WSClientException
	 * @throws WSClientExceptionStatus
	 */
	public Collection getAllUnbilledLoans(String repID) throws WSClientException, WSClientExceptionStatus
	{
		WMCUWGetAllUnbilledLoans params = new WMCUWGetAllUnbilledLoans();
		params.setStrRepID(getRepID(repID));
		WMCUWGetAllUnbilledLoansResponse mioResponse;
		try
		{	//call web service
			mioResponse = getWebservice().wMCUWGetAllUnbilledLoans(params);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSMionlineClientSaxHandlerUnbilledLoans unbilledLoansHandler = new WSMionlineClientSaxHandlerUnbilledLoans();
		parseResponse(mioResponse.getWMCUWGetAllUnbilledLoansResult(), unbilledLoansHandler);
//TEMP TEMP log unbilledloans
printUnbilledLoans(unbilledLoansHandler);
		return unbilledLoansHandler.getUnbilledLoans();
	}

	/**
	 * @return Collection (ArrayList) of underwriter codes (repIDs) as Strings
	 * @throws WSClientException
	 * @throws WSClientExceptionStatus
	 */
	public BillingStatusUpdateDTO updateLoanBillingStatus(String repID, Collection loanIDs) throws WSClientException, WSClientExceptionStatus
	{
		WMCUWUpdateLoanBillingStatus params = new WMCUWUpdateLoanBillingStatus();
		params.setStrRepID(getRepID(repID));
		params.setLoanIdsList(getLoanList(loanIDs));
		WMCUWUpdateLoanBillingStatusResponse mioResponse;
		try
		{	//call web service
			mioResponse = getWebservice().wMCUWUpdateLoanBillingStatus(params);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSMionlineClientSaxHandlerBillingStatus billingStatusHandler = new WSMionlineClientSaxHandlerBillingStatus();
		parseResponse(mioResponse.getWMCUWUpdateLoanBillingStatusResult(), billingStatusHandler);
//TEMP TEMP log billing status update
printBillingStatusUpdate(billingStatusHandler);
		return billingStatusHandler.getBillingStatusUpdate();
	}

	/**
	 * Filter response by date range
	 * @param fromDate start date of range
	 * @param toDate end date of range
	 * @return Collection (ArrayList) of underwriter codes (repIDs) as Strings
	 * @throws WSClientException
	 * @throws WSClientExceptionStatus
	 */
	public Collection getUnderwritersWithUnbilledLoansForDates(Date fromDate, Date toDate) throws WSClientException, WSClientExceptionStatus
	{
		WMCUWListUnderwritersWithUnbilledLoansForDates dateParams = new WMCUWListUnderwritersWithUnbilledLoansForDates();
		dateParams.setStartDate(getDateString(fromDate, WS_DEFAULT_FROM_DATE));
		dateParams.setEndDate(getDateString(toDate, WS_DEFAULT_TO_DATE));
		WMCUWListUnderwritersWithUnbilledLoansForDatesResponse mioResponse;
		try
		{	//call web service
			mioResponse = getWebservice().wMCUWListUnderwritersWithUnbilledLoansForDates(dateParams);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSMionlineSaxHandlerListUnderwriters listUwHandler = new WSMionlineSaxHandlerListUnderwriters();
		parseResponse(mioResponse.getWMCUWListUnderwritersWithUnbilledLoansForDatesResult(), listUwHandler);
//TEMP TEMP log list underwriters
printListUnderwriters(listUwHandler);
		return listUwHandler.getUwList();
	}

	/**
	 * Filter response by date range
	 * @param fromDate start date of range
	 * @param toDate end date of range
	 * @return Collection (ArrayList) of underwriter codes (repIDs) as Strings
	 * @throws WSClientException
	 * @throws WSClientExceptionStatus
	 */
	public Collection getAllUnbilledLoansForDates(String repID, Date fromDate, Date toDate) throws WSClientException, WSClientExceptionStatus
	{
		WMCUWGetAllUnbilledLoansForDates params = new WMCUWGetAllUnbilledLoansForDates();
		params.setStrRepID(getRepID(repID));
		params.setStartDate(getDateString(fromDate, WS_DEFAULT_FROM_DATE));
		params.setEndDate(getDateString(toDate, WS_DEFAULT_TO_DATE));
		WMCUWGetAllUnbilledLoansForDatesResponse mioResponse;
		try
		{	//call web service
			mioResponse = getWebservice().wMCUWGetAllUnbilledLoansForDates(params);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSMionlineClientSaxHandlerUnbilledLoans unbilledLoansHandler = new WSMionlineClientSaxHandlerUnbilledLoans();
		parseResponse(mioResponse.getWMCUWGetAllUnbilledLoansForDatesResult(), unbilledLoansHandler);
//TEMP TEMP log unbilledloans
printUnbilledLoans(unbilledLoansHandler);
		return unbilledLoansHandler.getUnbilledLoans();
	}

	/**
	 * @param response is the XML response, without the <xml> hdr
	 * @param handler is the Sax parser handler and will contain the results of the parse
	 * @throws WSClientException
	 */
	private void parseResponse(String response, WSMionlineClientSaxHandler handler) throws WSClientException, WSClientExceptionStatus
	{
		try
		{
			//get JAXP parser factory, config for no validation, get parser, parse using appropriate handler
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setValidating(false);
			SAXParser sp = spf.newSAXParser();
			String xmlhdr = new String(WS_XMLHDR);
			InputSource input = new InputSource(new StringReader(xmlhdr + cleanResponse(response)));
			sp.parse(input, handler);
			WSMionlineStatus status = handler.getStatus();
			if (status != null && handler.getStatus().getErrorcode().length() > 0)
			{	//have error condition
				throw new WSClientExceptionStatus(status);
			}
			//else successfully parsed response and response is not an error response
		}
		catch (FactoryConfigurationError e)
		{
			throw new WSClientException(e);
		}
		catch (ParserConfigurationException e)
		{
			throw new WSClientException(e);
		}
		catch (SAXException e)
		{
			throw new WSClientException(e);
		}
		catch (IOException e)
		{
			throw new WSClientException(e);
		}
		catch (RuntimeException e)
		{
			throw new WSClientException(e);
		}
	}
	
	/**
	 * @param response
	 * @return response without ampersands
	 */
	private String cleanResponse(String response)
	{
		try
		{
			return response.replaceAll(WS_CLEANXML_AMPSYM, WS_CLEANXML_AMPSYM_REPL);
		}
		catch (Exception e)
		{	//possible to have PatternSyntaxException (though highly unlikely)--return orig response instead
			return response;
		}
	}
	
	/**
	 * @param loanIDs
	 * @return comma-separated list of loanIDs
	 */
	private String getLoanList(Collection loanIDs) throws WSClientException
	{
		if (loanIDs == null || loanIDs.size() == 0)
		{	//no loanIDs
			throw new WSClientException(WSERR_NOLOANIDS);
		}
		StringBuffer sb = new StringBuffer();
		for (Iterator iter = loanIDs.iterator(); iter.hasNext();)
		{
			if (sb.length() > 0)
			{	//separate loanIDs with comma
				sb.append(LOANID_SEP);
			}
			sb.append((String)iter.next());		
		}
		return sb.toString();
	}

	/**
	 * TEMP methods to print responses
	 */
	private void printRepInfo(WSMionlineSaxHandlerRepInfo repinfoHandler)
	{
		//have rep info from response
		logger.debug("repInfo-from-obj repID=" + repinfoHandler.getRepInfo().getRepID() +
				" firstname=" + repinfoHandler.getRepInfo().getFirstName() +
				" lastname=" + repinfoHandler.getRepInfo().getLastName() +
				" name=" + repinfoHandler.getRepInfo().getName() +
				" jobfunction=" + repinfoHandler.getRepInfo().getJobFunction() +
				" location=" + repinfoHandler.getRepInfo().getLocation());
	}

	private void printListUnderwriters(WSMionlineSaxHandlerListUnderwriters listUwHandler)
	{
		//have list of underwriters from response
		StringBuffer sb = new StringBuffer();
		Iterator iter = listUwHandler.getUwList().iterator();
		while (iter.hasNext())
		{
			sb.append((String)iter.next());
			sb.append(",");
		}
		logger.debug("list-underwiters-from-obj=" + sb.toString());
	}

	private void printUnbilledLoans(WSMionlineClientSaxHandlerUnbilledLoans unbilledLoansHandler)
	{
		//have list of unbilled loans from response
		Iterator iter = unbilledLoansHandler.getUnbilledLoans().iterator();
		while (iter.hasNext())
		{
			UnbilledLoanDTO unbilledLoan = (UnbilledLoanDTO)iter.next();
			logger.debug("unbilled-loans-from-obj orderid=" + unbilledLoan.getOrderID() +
					" orderitemid(loanid)=" + unbilledLoan.getOrderItemID() +
					" lenderloanid=" + unbilledLoan.getLenderLoanNumber() +
					" decisiondate=" + unbilledLoan.getDecisionDate() +
					" miordered=" + unbilledLoan.getMiOrdered() +
					" miappno=" + unbilledLoan.getMiAppNumber() +
					" lienpos=" + unbilledLoan.getLienPosition() +
					" piggyback=" + unbilledLoan.getPiggyback() +
					" svctype=" + unbilledLoan.getServiceType() +
					" auscode=" + unbilledLoan.getAusCode() +
					" ausrecommend=" + unbilledLoan.getAusRecommendationCode() +
					" ducaseid=" + unbilledLoan.getDuCaseID() +
					" loanamt=" + unbilledLoan.getLoanAmount() +
					" loanltv=" + unbilledLoan.getLoanLtv() +
					" loanpurpose=" + unbilledLoan.getLoanPurposeDesc() +
					" loanstatus=" + unbilledLoan.getLoanStatusDesc() +
					" loanprogram=" + unbilledLoan.getLoanProgram() +
					" bizchannel=" + unbilledLoan.getBusinessChannel() +
					" brokerloanappnum=" + unbilledLoan.getBrokerLoanAppNum() +
					" lpkeynum=" + unbilledLoan.getLpKeyNumber() +
					" fulfillmentdate=" + unbilledLoan.getFulfillmentDate() +
					" decisionby=" + unbilledLoan.getDecisionBy() +
					" loanpropstreet=" + unbilledLoan.getLoanPropStreet() +
					" loanpropcity=" + unbilledLoan.getLoanPropCity() +
					" loanpropstate=" + unbilledLoan.getLoanPropState() +
					" loanpropzip=" + unbilledLoan.getLoanPropZip() +
					" ClientLender Lmp#=" + unbilledLoan.getClientLender().getLenderMasterPolicyNumber() +
					" Lname=" + unbilledLoan.getClientLender().getLenderName() +
					" Ladr1=" + unbilledLoan.getClientLender().getLenderAddr1() +
					" Ladr2=" + unbilledLoan.getClientLender().getLenderAddr2() +
					" Ladr3=" + unbilledLoan.getClientLender().getLenderAddr3() +
					" Ladr1=" + unbilledLoan.getClientLender().getLenderAddr4() +
					" Lcity=" + unbilledLoan.getClientLender().getLenderCity() +
					" Lstate=" + unbilledLoan.getClientLender().getLenderState() +
					" Lzip=" + unbilledLoan.getClientLender().getLenderZipcode() +
					" SubmittedByLender Lmp#=" + unbilledLoan.getSubmittedByLender().getLenderMasterPolicyNumber() +
					" Lname=" + unbilledLoan.getSubmittedByLender().getLenderName() +
					" Ladr1=" + unbilledLoan.getSubmittedByLender().getLenderAddr1() +
					" Ladr2=" + unbilledLoan.getSubmittedByLender().getLenderAddr2() +
					" Ladr3=" + unbilledLoan.getSubmittedByLender().getLenderAddr3() +
					" Ladr1=" + unbilledLoan.getSubmittedByLender().getLenderAddr4() +
					" Lcity=" + unbilledLoan.getSubmittedByLender().getLenderCity() +
					" Lstate=" + unbilledLoan.getSubmittedByLender().getLenderState() +
					" Lzip=" + unbilledLoan.getSubmittedByLender().getLenderZipcode() +
					" OriginatingLender Lmp#=" + unbilledLoan.getOriginatingLender().getLenderMasterPolicyNumber() +
					" Lname=" + unbilledLoan.getOriginatingLender().getLenderName() +
					" Ladr1=" + unbilledLoan.getOriginatingLender().getLenderAddr1() +
					" Ladr2=" + unbilledLoan.getOriginatingLender().getLenderAddr2() +
					" Ladr3=" + unbilledLoan.getOriginatingLender().getLenderAddr3() +
					" Ladr1=" + unbilledLoan.getOriginatingLender().getLenderAddr4() +
					" Lcity=" + unbilledLoan.getOriginatingLender().getLenderCity() +
					" Lstate=" + unbilledLoan.getOriginatingLender().getLenderState() +
					" Lzip=" + unbilledLoan.getOriginatingLender().getLenderZipcode() +
					" Borrower Bssn=" + unbilledLoan.getBorrower().getSsn() +
					" Bname=" + unbilledLoan.getBorrower().getName() +
					" Bfico=" + unbilledLoan.getBorrower().getFico() +
					" CoBorrower Bssn=" + unbilledLoan.getCoborrower().getSsn() +
					" Bname=" + unbilledLoan.getCoborrower().getName() +
					" Bfico=" + unbilledLoan.getCoborrower().getFico()
					);
		}
	}
	
	private void printBillingStatusUpdate(WSMionlineClientSaxHandlerBillingStatus billingStatusHandler)
	{
		//have billing status update info from response
		logger.debug("billing-status-update-from-obj #loans=" + billingStatusHandler.getBillingStatusUpdate().getTotLoansUpdated() +
				" #billed=" + billingStatusHandler.getBillingStatusUpdate().getTotBilled() +
				" #alreadyBilled=" + billingStatusHandler.getBillingStatusUpdate().getTotAlreadyBilled() +
				" #notFound=" + billingStatusHandler.getBillingStatusUpdate().getTotNotFound());
	}
	
	/**
	 * @param theDate
	 * @return date formatted for webserivce MM/dd/yyyy e.g. 01/31/2008
	 */
	private String getDateString(Date theDate, String defaultDate)
	{
		if (theDate == null)
		{	//date not provided, use default date
			return defaultDate;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try
		{	//return formatted date
			return sdf.format(theDate);
		}
		catch (RuntimeException e)
		{	//unlikely
			return defaultDate;
		}
	}

	/**
	 * @param repID
	 * @return repID uppercased or as empty string if null
	 */
	private String getRepID(String repID)
	{
		if (repID == null)
		{	//use empty string if null (very unlikely)
			return EMPTY_STRING;
		}
		//uppercase to insure match with source system
		return repID.trim().toUpperCase();
	}
	
	/**
	 * @return the webservice
	 */
	private CUWServiceSoap getWebservice()
	{
		return webservice;
	}
}
