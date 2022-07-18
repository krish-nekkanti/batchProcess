/**
 * @(#) WSMionlineClientSaxHandlerUnbilledLoans.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.webserviceclient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.UnbilledLoanDTO;

/**
 * Handle creation of java objects to hold mionline web service client response
 * This one is for GetUnbilledLoans response (ArrayList of UnbilledLoanDTOs)
 */
public class WSMionlineClientSaxHandlerUnbilledLoans extends WSMionlineClientSaxHandler
{
	//ArrayList of UnbilledLoanDTOs
	private List unbilledLoans;
	private int numBorrower;
	
	private static String WSMIO_LOANS = "loans";
	private static String WSMIO_LOAN = "loan_info";
	private static String WSMIO_ORDERID = "orderid";
	private static String WSMIO_ORDERITEMID = "loanid";
	private static String WSMIO_LENDER_LOANNO = "lenderloanid";
	private static String WSMIO_DECISION_DATE = "decisiondate";
	private static String WSMIO_MIORDERED = "miordered";
	private static String WSMIO_MIAPPNO = "miappnumber";
	private static String WSMIO_LIENPOS = "lienposition";
	private static String WSMIO_PIGGYBACK = "piggybackflag";
	private static String WSMIO_SVCTYPE = "servicetype";
	private static String WSMIO_AUSCODE = "auscodetext";
	private static String WSMIO_DU_CASEID = "ducaseid";
	private static String WSMIO_LOAN_PROGRAM = "loanprogram";
	private static String WSMIO_AUS_RECOMMENDATION = "ausrecommendation";
	private static String WSMIO_LOAN_PURPOSE = "loanpurposedesc";
	private static String WSMIO_LOAN_AMT = "loanamount";
	private static String WSMIO_LOAN_LTV = "loanltv";
	private static String WSMIO_PROP_STREET = "loanpropstreet";
	private static String WSMIO_PROP_CITY = "loanpropcity";
	private static String WSMIO_PROP_STATE = "loanpropstate";
	private static String WSMIO_PROP_ZIP = "loanpropzip";
	private static String WSMIO_LOAN_STATUS_DESC = "loanstatusdesc";
	private static String WSMIO_BIZ_CHANNEL = "businesschannel";
	private static String WSMIO_BROKER_LOAN_APP_NUM = "brokerloanappnum";
	private static String WSMIO_LP_KEY_NUMBER = "lpkeynumber";
	private static String WSMIO_FULFILLMENT_DATE = "fulfillmentdate";
	private static String WSMIO_DECISION_BY = "decisionby";
	private static String WSMIO_BORROWERS = "borrowers";
	private static String WSMIO_BORROWER = "borrower";
	private static String WSMIO_SSN = "ssn";
	private static String WSMIO_FIRST_NAME = "fname";
	private static String WSMIO_MIDDLE_NAME = "mname";
	private static String WSMIO_LAST_NAME = "lname";
	private static String WSMIO_FICO = "ficoscore";
	private static String WSMIO_CLIENT_LENDER = "clientLenderinfo";
	private static String WSMIO_SUBMITTED_BY_LENDER = "submittedByLenderInfo";
	private static String WSMIO_ORIGINATION_LENDER = "originatorLenderInfo";
	private static String WSMIO_LIENPOS_CODE = "liencode";
	private static String WSMIO_SVCTYPE_CODE = "servicetypecode";
	private static String WSMIO_AUS_RECOMMENDATION_CODE = "ausrecommendationcode";
	private static String WSMIO_BIZ_CHANNEL_CODE = "businesschannelcode";
	private static String WSMIO_LOAN_PURPOSE_CODE = "loanpurposecode";
	private static String WSMIO_AUS_CODE = "auscode";
	private static String WSMIO_LOAN_STATUS_CODE = "loanstatuscode";

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		if (unbilledLoans != null)
		{
			if (elementName.equals(WSMIO_ORDERID))
			{
				getUnbilledLoan().setOrderID(theElement);
			}
			else if (elementName.equals(WSMIO_ORDERITEMID))
			{
				getUnbilledLoan().setOrderItemID(theElement);
			}
			else if (elementName.equals(WSMIO_LENDER_LOANNO))
			{
				getUnbilledLoan().setLenderLoanNumber(theElement);
			}
			else if (elementName.equals(WSMIO_DECISION_DATE))
			{
				getUnbilledLoan().setDecisionDate(theElement);
			}
			else if (elementName.equals(WSMIO_MIORDERED))
			{
				getUnbilledLoan().setMiOrdered(theElement);
			}
			else if (elementName.equals(WSMIO_MIAPPNO))
			{
				getUnbilledLoan().setMiAppNumber(theElement);
			}
			else if (elementName.equals(WSMIO_LIENPOS_CODE))
			{
				getUnbilledLoan().setLienPosition(theElement);
			}
			else if (elementName.equals(WSMIO_PIGGYBACK))
			{
				getUnbilledLoan().setPiggyback(theElement);
			}
			else if (elementName.equals(WSMIO_SVCTYPE_CODE))
			{
				getUnbilledLoan().setServiceType(theElement);
			}
			else if (elementName.equals(WSMIO_AUS_CODE))
			{
				getUnbilledLoan().setAusCode(theElement);
			}
			else if (elementName.equals(WSMIO_DU_CASEID))
			{
				getUnbilledLoan().setDuCaseID(theElement);
			}
			else if (elementName.equals(WSMIO_LOAN_PROGRAM))
			{
				getUnbilledLoan().setLoanProgram(theElement);
			}
			else if (elementName.equals(WSMIO_AUS_RECOMMENDATION_CODE))
			{
				getUnbilledLoan().setAusRecommendationCode(theElement);
			}
			else if (elementName.equals(WSMIO_LOAN_PURPOSE_CODE))
			{
				getUnbilledLoan().setLoanPurposeDesc(theElement);
			}
			else if (elementName.equals(WSMIO_LOAN_AMT))
			{
				getUnbilledLoan().setLoanAmount(theElement);
			}
			else if (elementName.equals(WSMIO_LOAN_LTV))
			{
				getUnbilledLoan().setLoanLtv(theElement);
			}
			else if (elementName.equals(WSMIO_PROP_STREET))
			{
				getUnbilledLoan().setLoanPropStreet(theElement);
			}
			else if (elementName.equals(WSMIO_PROP_CITY))
			{
				getUnbilledLoan().setLoanPropCity(theElement);
			}
			else if (elementName.equals(WSMIO_PROP_STATE))
			{
				getUnbilledLoan().setLoanPropState(theElement);
			}
			else if (elementName.equals(WSMIO_PROP_ZIP))
			{
				getUnbilledLoan().setLoanPropZip(theElement);
			}
			else if (elementName.equals(WSMIO_LOAN_STATUS_CODE))
			{
				getUnbilledLoan().setLoanStatusDesc(theElement);
			}			
			else if (elementName.equals(WSMIO_BIZ_CHANNEL_CODE))
			{
				getUnbilledLoan().setBusinessChannel(theElement);
			}
			else if (elementName.equals(WSMIO_BROKER_LOAN_APP_NUM))
			{
				getUnbilledLoan().setBrokerLoanAppNum(theElement);
			}
			else if (elementName.equals(WSMIO_LP_KEY_NUMBER))
			{
				getUnbilledLoan().setLpKeyNumber(theElement);
			}
			else if (elementName.equals(WSMIO_FULFILLMENT_DATE))
			{
				getUnbilledLoan().setFulfillmentDate(theElement);
			}
			else if (elementName.equals(WSMIO_DECISION_BY))
			{
				getUnbilledLoan().setDecisionBy(theElement);
			}
			else if (elementName.equals(WSMIO_SSN))
			{
				getUnbilledLoan().getBorrower(numBorrower).setSsn(theElement);
			}
			else if (elementName.equals(WSMIO_FIRST_NAME))
			{
				getUnbilledLoan().getBorrower(numBorrower).setFirstName(theElement);
			}
			else if (elementName.equals(WSMIO_MIDDLE_NAME))
			{
				getUnbilledLoan().getBorrower(numBorrower).setMiddleName(theElement);
			}
			else if (elementName.equals(WSMIO_LAST_NAME))
			{
				getUnbilledLoan().getBorrower(numBorrower).setLastName(theElement);
			}
			else if (elementName.equals(WSMIO_FICO))
			{
				getUnbilledLoan().getBorrower(numBorrower).setFico(theElement);
			}
			else if (elementName.equals(WSMIO_CLIENT_LENDER))
			{
				getUnbilledLoan().setClientLender(theElement);
			}
			else if (elementName.equals(WSMIO_SUBMITTED_BY_LENDER))
			{
				getUnbilledLoan().setSubmittedByLender(theElement);
			}
			else if (elementName.equals(WSMIO_ORIGINATION_LENDER))
			{
				getUnbilledLoan().setOriginatingLender(theElement);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
		if (elementName.equals(WSMIO_LOANS))
		{	//have loans
			unbilledLoans = new ArrayList();
		}
		if (elementName.equals(WSMIO_LOAN))
		{	//have next loan
			unbilledLoans.add(new UnbilledLoanDTO());
		}
		if (elementName.equals(WSMIO_BORROWERS))
		{	//start collection of borrowers
			numBorrower = 0;
		}
		if (elementName.equals(WSMIO_BORROWER))
		{	//increment to indicate which borrower
			numBorrower++;
		}
	}

	/**
	 * @return the unbilledLoans
	 */
	public Collection getUnbilledLoans()
	{
		return unbilledLoans;
	}
		
	/**
	 * @return last unbilled loan in list
	 */
	private UnbilledLoanDTO getUnbilledLoan()
	{
		return (UnbilledLoanDTO)unbilledLoans.get(unbilledLoans.size() - 1);
	}
}
