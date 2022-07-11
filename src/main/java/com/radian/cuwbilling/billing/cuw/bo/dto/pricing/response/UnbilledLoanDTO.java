/**
 * @(#) UnbilledLoanDTO.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.radian.foundation.bo.dto.BaseDTO;

/**
 * DTO for result holds info for an unbilled loan from MIOnline
 */
public class UnbilledLoanDTO extends BaseDTO
{
	private String orderID;
	private String orderItemID;
	private String lenderLoanNumber;
	private Date decisionDate;
	private Boolean piggyback;
	private Boolean miOrdered;
	private String miAppNumber;
	private String lienPosition;
	private String serviceType;
	private String ausCode;
	private String duCaseID;
	private String loanProgram;
	private String ausRecommendationCode;
	private String loanPurposeDesc;
	private String loanAmount;
	private String loanLtv;
	private String loanPropStreet;
	private String loanPropCity;
	private String loanPropState;
	private String loanPropZip;
	private String loanStatusDesc;
	private String businessChannel;
	private String brokerLoanAppNum;
	private String lpKeyNumber;
	private Date fulfillmentDate;
	private String decisionBy;	
	private LenderDTO clientLender;
	private LenderDTO submittedByLender;
	private LenderDTO originatingLender;
	private BorrowerDTO borrower;
	private BorrowerDTO coborrower;
	private BorrowerDTO extraBorrower;
	
	private static String LEGACYINFO_SEP = "\\|";
	private static String WS_TRUE = "Y";
	private static String LIEN_FIRST = "1ST";
	private static String LIEN_SECOND = "2ND";
	private static String LIEN_NODAT = "NODAT";
	private static String LIEN_1 = "1";
	private static String LIEN_2 = "2";
	private static String DECISION_DATE_FMT_SHORT = "MM/dd/yyyy";
	private static String DECISION_DATE_FMT_LONG = "MM/dd/yyyy hh:mm:ss a";
	
	/**
	 * @return the orderID
	 */
	public String getOrderID()
	{
		return orderID;
	}
	
	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(String orderID)
	{
		this.orderID = orderID;
	}
	
	/**
	 * @return the orderItemID
	 */
	public String getOrderItemID()
	{
		return orderItemID;
	}
	
	/**
	 * @param orderItemID the orderItemID to set
	 */
	public void setOrderItemID(String orderItemID)
	{
		this.orderItemID = orderItemID;
	}
	
	/**
	 * @return the lenderLoanNumber
	 */
	public String getLenderLoanNumber()
	{
		return lenderLoanNumber;
	}
	
	/**
	 * @param lenderLoanNumber the lenderLoanNumber to set
	 */
	public void setLenderLoanNumber(String lenderLoanNumber)
	{
		this.lenderLoanNumber = lenderLoanNumber;
	}
	
	/**
	 * @return the decisionDate
	 */
	public Date getDecisionDate()
	{
		return decisionDate;
	}
	
	/**
	 * @param decisionDate the decisionDate to set
	 */
	public void setDecisionDate(String decisionDate)
	{
		this.decisionDate = convertDate(decisionDate);
	}
	
	/**
	 * @return the piggyback
	 */
	public Boolean getPiggyback()
	{
		return piggyback;
	}
	
	/**
	 * @param piggyback the piggyback to set
	 */
	public void setPiggyback(String piggyback)
	{
		if (piggyback.equalsIgnoreCase(WS_TRUE))
		{	//Y for true
			this.piggyback = Boolean.TRUE;
		}
		else
		{	//all other values are false
			this.piggyback = Boolean.FALSE;
		}
	}
	
	/**
	 * @return the miOrdered
	 */
	public Boolean getMiOrdered()
	{
		return miOrdered;
	}
	
	/**
	 * @param miOrdered the miOrdered to set
	 */
	public void setMiOrdered(String miOrdered)
	{
		if (miOrdered.equalsIgnoreCase(WS_TRUE))
		{	//Y for true
			this.miOrdered = Boolean.TRUE;
		}
		else
		{	//all other values are false
			this.miOrdered = Boolean.FALSE;
		}
	}
	
	/**
	 * @return the miAppNumber
	 */
	public String getMiAppNumber()
	{
		return miAppNumber;
	}
	
	/**
	 * @param miAppNumber the miAppNumber to set
	 */
	public void setMiAppNumber(String miAppNumber)
	{
		this.miAppNumber = miAppNumber;
	}
	
	/**
	 * @return the lienPosition
	 */
	public String getLienPosition()
	{
		return lienPosition;
	}
	
	/**
	 * @param lienPosition the lienPosition to set
	 */
	public void setLienPosition(String lienPosition)
	{
		if (lienPosition.equalsIgnoreCase(LIEN_FIRST))
		{	//specified as first lien
			this.lienPosition = LIEN_1;
		}
		else if (lienPosition.equalsIgnoreCase(LIEN_SECOND))
		{	
			this.lienPosition = LIEN_2;
		}
		else
		{
			this.lienPosition = LIEN_NODAT;
		}
	}
	
	/**
	 * @return the serviceType
	 */
	public String getServiceType()
	{
		return serviceType;
	}
	
	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(String serviceType)
	{
		this.serviceType = serviceType;
	}
	
	/**
	 * @return the ausCode
	 */
	public String getAusCode()
	{
		return ausCode;
	}
	
	/**
	 * @param ausCode the ausCode to set
	 */
	public void setAusCode(String ausCode)
	{
		this.ausCode = ausCode;
	}

	/**
	 * @return the clientLender
	 */
	public LenderDTO getClientLender()
	{
		if (clientLender == null)
		{	//verrry unlikely and not coding for sync issues
			clientLender = new LenderDTO(); 
		}
		return clientLender;
	}

	/**
	 * @param theElement contains vertical-bar (pipe) delimited fields, parsed and stored internally
	 */
	public void setClientLender(String theElement)
	{
		this.clientLender = setLegacyInfo(theElement);		
	}

	/**
	 * @return the submittedByLender
	 */
	public LenderDTO getSubmittedByLender()
	{
		return submittedByLender;
	}

	/**
	 * @param theElement contains vertical-bar (pipe) delimited fields, parsed and stored internally
	 */
	public void setSubmittedByLender(String theElement)
	{
		if (submittedByLender == null)
		{	//verrry unlikely and not coding for sync issues
			submittedByLender = new LenderDTO(); 
		}
		this.submittedByLender = setLegacyInfo(theElement);		
	}

	/**
	 * @return the originatingLender
	 */
	public LenderDTO getOriginatingLender()
	{
		if (originatingLender == null)
		{	//verrry unlikely and not coding for sync issues
			originatingLender = new LenderDTO(); 
		}
		return originatingLender;
	}

	/**
	 * @param theElement contains vertical-bar (pipe) delimited fields, parsed and stored internally
	 */
	public void setOriginatingLender(String theElement)
	{
		this.originatingLender = setLegacyInfo(theElement);		
	}

	/**
	 * @param borrower number (1=borrwer, 2=co-borrower, else ignore)
	 * @return the borrower based on borrower number
	 */
	public BorrowerDTO getBorrower(int numBorrower)
	{
		if (numBorrower == 1)
		{
			return getBorrower();
		}
		else if (numBorrower == 2)
		{
			return getCoborrower();
		}
		//if not borrower or co-borrower, return DTO that never gets used
		return getExtraBorrower();
	}

	/**
	 * @return the borrower
	 */
	public BorrowerDTO getBorrower()
	{
		if (borrower == null)
		{	//verrry unlikely and not coding for sync issues
			borrower = new BorrowerDTO(); 
		}
		return borrower;
	}

	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(BorrowerDTO borrower)
	{
		this.borrower = borrower;
	}

	/**
	 * @return the coborrower
	 */
	public BorrowerDTO getCoborrower()
	{
		if (coborrower == null)
		{	//verrry unlikely and not coding for sync issues
			coborrower = new BorrowerDTO(); 
		}
		return coborrower;
	}

	/**
	 * @param coborrower the coborrower to set
	 */
	public void setCoborrower(BorrowerDTO coborrower)
	{
		this.coborrower = coborrower;
	}

	/**
	 * @return the extraBorrower
	 */
	public BorrowerDTO getExtraBorrower()
	{
		if (extraBorrower == null)
		{	//verrry unlikely and not coding for sync issues
			extraBorrower = new BorrowerDTO(); 
		}
		return extraBorrower;
	}

	/**
	 * If more than two borrower, this has the last of the 3-N borrowers
	 * @param extraBorrower the extraBorrower to set
	 */
	public void setExtraBorrower(BorrowerDTO extraBorrower)
	{
		this.extraBorrower = extraBorrower;
	}

	/**
	 * @return the duCaseID
	 */
	public String getDuCaseID()
	{
		return duCaseID;
	}

	/**
	 * @param duCaseID the duCaseID to set
	 */
	public void setDuCaseID(String duCaseID)
	{
		this.duCaseID = duCaseID;
	}

	/**
	 * @return the loanProgram
	 */
	public String getLoanProgram()
	{
		return loanProgram;
	}

	/**
	 * @param loanProgram the loanProgram to set
	 */
	public void setLoanProgram(String loanProgram)
	{
		this.loanProgram = loanProgram;
	}

	/**
	 * @return the ausRecommendationCode
	 */
	public String getAusRecommendationCode()
	{
		return ausRecommendationCode;
	}

	/**
	 * @param ausRecommendationCode the ausRecommendationCode to set
	 */
	public void setAusRecommendationCode(String ausRecommendationCode)
	{
		this.ausRecommendationCode = ausRecommendationCode;
	}

	/**
	 * @return the loanPurposeDesc
	 */
	public String getLoanPurposeDesc()
	{
		return loanPurposeDesc;
	}

	/**
	 * @param loanPurposeDesc the loanPurposeDesc to set
	 */
	public void setLoanPurposeDesc(String loanPurposeDesc)
	{
		this.loanPurposeDesc = loanPurposeDesc;
	}

	/**
	 * @return the loanAmount
	 */
	public String getLoanAmount()
	{
		return loanAmount;
	}

	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(String loanAmount)
	{
		this.loanAmount = loanAmount;
	}

	/**
	 * @return the loanLtv
	 */
	public String getLoanLtv()
	{
		return loanLtv;
	}

	/**
	 * @param loanLtv the loanLtv to set
	 */
	public void setLoanLtv(String loanLtv)
	{
		this.loanLtv = loanLtv;
	}

	/**
	 * @return the loanPropStreet
	 */
	public String getLoanPropStreet()
	{
		return loanPropStreet;
	}

	/**
	 * @param loanPropStreet the loanPropStreet to set
	 */
	public void setLoanPropStreet(String loanPropStreet)
	{
		this.loanPropStreet = loanPropStreet;
	}

	/**
	 * @return the loanPropCity
	 */
	public String getLoanPropCity()
	{
		return loanPropCity;
	}

	/**
	 * @param loanPropCity the loanPropCity to set
	 */
	public void setLoanPropCity(String loanPropCity)
	{
		this.loanPropCity = loanPropCity;
	}

	/**
	 * @return the loanPropState
	 */
	public String getLoanPropState()
	{
		return loanPropState;
	}

	/**
	 * @param loanPropState the loanPropState to set
	 */
	public void setLoanPropState(String loanPropState)
	{
		this.loanPropState = loanPropState;
	}

	/**
	 * @return the loanPropZip
	 */
	public String getLoanPropZip()
	{
		return loanPropZip;
	}

	/**
	 * @param loanPropZip the loanPropZip to set
	 */
	public void setLoanPropZip(String loanPropZip)
	{
		this.loanPropZip = loanPropZip;
	}

	/**
	 * @return the loanStatusDesc
	 */
	public String getLoanStatusDesc()
	{
		return loanStatusDesc;
	}

	/**
	 * @param loanStatusDesc the loanStatusDesc to set
	 */
	public void setLoanStatusDesc(String loanStatusDesc)
	{
		this.loanStatusDesc = loanStatusDesc;
	}

	/**
	 * @return the businessChannel
	 */
	public String getBusinessChannel()
	{
		return businessChannel;
	}

	/**
	 * @param businessChannel the businessChannel to set
	 */
	public void setBusinessChannel(String businessChannel)
	{
		this.businessChannel = businessChannel;
	}

	/**
	 * @return the brokerLoanAppNum
	 */
	public String getBrokerLoanAppNum()
	{
		return brokerLoanAppNum;
	}

	/**
	 * @param brokerLoanAppNum the brokerLoanAppNum to set
	 */
	public void setBrokerLoanAppNum(String brokerLoanAppNum)
	{
		this.brokerLoanAppNum = brokerLoanAppNum;
	}

	/**
	 * @return the lpKeyNumber
	 */
	public String getLpKeyNumber()
	{
		return lpKeyNumber;
	}

	/**
	 * @param lpKeyNumber the lpKeyNumber to set
	 */
	public void setLpKeyNumber(String lpKeyNumber)
	{
		this.lpKeyNumber = lpKeyNumber;
	}

	/**
	 * @return the fulfillmentDate
	 */
	public Date getFulfillmentDate()
	{
		return fulfillmentDate;
	}

	/**
	 * @param fulfillmentDate the fulfillmentDate to set
	 */
	public void setFulfillmentDate(String fulfillmentDate)
	{
		this.fulfillmentDate = convertDate(fulfillmentDate);
	}

	/**
	 * @return the decisionBy
	 */
	public String getDecisionBy()
	{
		return decisionBy;
	}

	/**
	 * @param decisionBy the decisionBy to set
	 */
	public void setDecisionBy(String decisionBy)
	{
		this.decisionBy = decisionBy;
	}

	/**
	 * legacyinfo element contains vertical-bar (pipe) delimited fields, parsed and stored internally
	 * this data is for the Client Lender
	 * @param theElement
	 */
	private LenderDTO setLegacyInfo(String theElement)
	{
		LenderDTO lender = new LenderDTO();
		//legacy info is client-masterpolicy-number|name|addr1|addr2|addr3|addr4|city|ST|zip+4
		String[] lenderFields = theElement.split(LEGACYINFO_SEP);
		if (lenderFields.length == 9)
		{	//have correct number of fields; get positional content
			int i = 0;
			lender.setLenderMasterPolicyNumber(lenderFields[i++]);
			lender.setLenderName(lenderFields[i++]);
			lender.setLenderAddr1(lenderFields[i++]);
			lender.setLenderAddr2(lenderFields[i++]);
			lender.setLenderAddr3(lenderFields[i++]);
			lender.setLenderAddr4(lenderFields[i++]);
			lender.setLenderCity(lenderFields[i++]);
			lender.setLenderState(lenderFields[i++]);
			//zip is zip+4 and we use only zip
			lender.setLenderZipcode(lenderFields[i++].substring(0, 5));
		}
		return lender;
	}
	
	/**
	 * @param decisionDate is a date in String format, convert to Date if matching expected formats
	 */
	private Date convertDate(String decisionDate)
	{
		Date theDate = null;
		if (decisionDate != null && decisionDate.length() > 0)
		{
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat(DECISION_DATE_FMT_SHORT);
				theDate = sdf.parse(decisionDate);
			}
			catch (ParseException e)
			{	//try both short and long formats		
				try
				{
					SimpleDateFormat sdf2 = new SimpleDateFormat(DECISION_DATE_FMT_LONG);
					theDate = sdf2.parse(decisionDate);
				}
				catch (ParseException e2)
				{	//TODO: date in invalid format--now what?
					e.printStackTrace();
				}
			}
		}
		return theDate;
	}
}
