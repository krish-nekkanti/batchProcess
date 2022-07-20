package com.radian.webserviceclient;

import java.util.ArrayList;
import java.util.Collection;

import com.radian.cuwbilling.billing.cuw.bo.dto.BillingProfileSummaryDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.BillingProfileDTO;
import com.radian.cuwbilling.common.bo.codes.USState;
import com.radian.cuwbilling.common.bo.dto.AddressDTO;
import com.radian.cuwbilling.common.bo.dto.DateDTO;

public class WSCMDSaxHandlerBillingProfileLite extends WSCMDClientSaxHandler
{
	private Collection billingProfiles = null;
	private BillingProfileSummaryDTO profileDTO;
	
	private static String WSCMD_BP_INFO = "BillingProfileListCustomized";
	private static String WSCMD_BP_INFOS = "ArrayOfBillingProfileListCustomized";
	private static String WSCMD_PROF_ID = "BillingProfileId";
	private static String WSCMD_PROF_NUM = "BillingProfileNum";
	private static String WSCMD_CREATE_DATE = "CreatedDtm";
	private static String WSCMD_UPDATED_BY_DATE = "UpdatedDtm";
	private static String WSCMD_BILLING_ADDR_1 = "BillingStreetAddr1";
	private static String WSCMD_BILLING_ADDR_2 = "BillingStreetAddr2";
	private static String WSCMD_BILLING_ADDR_3 = "BillingStreetAddr3";
	private static String WSCMD_BILLING_CITY = "BillingCty";
	private static String WSCMD_BILLING_STATE_CD = "BillingStateCd";
	private static String WSCMD_BILLING_ZIP = "BillingPostalCd";
	private static String WSCMD_ACTIVATION_STATUS = "ActivationStatusDesc";

	private static String WSCMD_CUST_NAME = "CustomerNam";
	private static String WSCMD_CUST_ID = "CustomerId";
	
	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMIonlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		try {
		if (profileDTO != null)
		{	//fill in rep info

			if (elementName.equals(WSCMD_PROF_ID))
			{
				profileDTO.setBillingProfileID(new Long(theElement));
			}
			else if (elementName.equals(WSCMD_PROF_NUM))
			{
				profileDTO.setBillingProfileDisplayID(theElement);
			}
		    else if (elementName.equals( WSCMD_CREATE_DATE))
			{
				profileDTO.setCreationDate(super.getFilterDate(theElement));
			}
		    else if (elementName.equals( WSCMD_CUST_ID))
			{
				profileDTO.setCustomerID(Long.valueOf(theElement));
			}
		    else if (elementName.equals( WSCMD_CUST_NAME))
			{
				profileDTO.setCustomerName(theElement);
			}
		    else if (elementName.equals( WSCMD_UPDATED_BY_DATE))
			{
				profileDTO.setModifiedDate(super.getFilterDate(theElement));
			}
		    else if (elementName.equals(WSCMD_BILLING_ADDR_1))
			{
				profileDTO.getCustomerAddress().setStreet1(theElement);
			} 
		    else if (elementName.equals(WSCMD_BILLING_ADDR_2))
			{
				profileDTO.getCustomerAddress().setStreet2(theElement);
			} 
		    else if (elementName.equals(WSCMD_BILLING_ADDR_3))
			{
				profileDTO.getCustomerAddress().setStreet3(theElement);
			} 
		    else if (elementName.equals(WSCMD_BILLING_CITY))
			{
				profileDTO.getCustomerAddress().setCity(theElement);
			} 
		    else if (elementName.equals(WSCMD_BILLING_STATE_CD))
			{
				profileDTO.getCustomerAddress().setState(USState.decodeAbbreviation(theElement));
			} 
		    else if (elementName.equals(WSCMD_BILLING_ZIP))
			{
				profileDTO.getCustomerAddress().setZipCode(theElement);
			} 
		    else if (elementName.equals(WSCMD_ACTIVATION_STATUS))
			{
				profileDTO.setStatusCodeDesc(theElement);
			} 

		}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSCMDnlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
		if (elementName.equals(WSCMD_BP_INFOS))
		{	//have rep info
			billingProfiles = new ArrayList();
		}
		if (elementName.equals(WSCMD_BP_INFO))
		{	
			profileDTO = new BillingProfileSummaryDTO();
			profileDTO.setCustomerAddress(new AddressDTO());
			if( billingProfiles != null){
				billingProfiles.add(profileDTO);
			}
		}
		
	}

	/**
	 * @return the billing profile summary
	 */
	public BillingProfileSummaryDTO getProfileSummaryDTO()
	{
		return profileDTO;
	}
	
	/**
	 * @return the billing profile summarys
	 */
	public Collection getProfileSummaryDTOs()
	{
		return this.billingProfiles;
	}
	
}

