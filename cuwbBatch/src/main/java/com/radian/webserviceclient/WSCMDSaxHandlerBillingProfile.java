package com.radian.webserviceclient;

import java.util.ArrayList;
import java.util.Collection;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.BillingProfileDTO;
import com.radian.cuwbilling.common.bo.codes.USState;
import com.radian.cuwbilling.common.bo.dto.AddressDTO;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.cuwbilling.common.bo.dto.PhoneDTO;

public class WSCMDSaxHandlerBillingProfile extends WSCMDClientSaxHandler
{
	private Collection billingProfiles = null;
	private BillingProfileDTO profileDTO;
	
	private static String WSCMD_BP_INFO = "BillingProfile";
	private static String WSCMD_BP_INFOS = "ArrayOfBillingProfile";
	private static String WSCMD_PROF_ID = "BillingProfileId";
	private static String WSCMD_PROF_NUM = "BillingProfileNum";
	private static String WSCMD_CUST_ID = "CustomerId";
	private static String WSCMD_INV_NAME = "InvoiceNam";
	private static String WSCMD_ATTN_NAME = "AttentionNam";
	private static String WSCMD_BILLING_ADDR_1 = "BillingStreetAddr1";
	private static String WSCMD_BILLING_ADDR_2 = "BillingStreetAddr2";
	private static String WSCMD_BILLING_ADDR_3 = "BillingStreetAddr3";
	private static String WSCMD_BILLING_CITY = "BillingCty";
	private static String WSCMD_BILLING_STATE_CD = "BillingStateCd";
	private static String WSCMD_BILLING_ZIP = "BillingPostalCd";
	private static String WSCMD_BILLING_PHONE = "BillingPhoneNum";
	private static String WSCMD_BILLING_PHONE_EXT = "BillingPhoneExtNum";
	private static String WSCMD_BILLING_FAX = "BillingFaxNum";
	private static String WSCMD_BILLING_FAX_EXT = "BillingFaxExtNum";
	private static String WSCMD_CREATE_DATE = "CreatedDtm";
	private static String WSCMD_CREATE_BY_NAME = "CreatedByNam";
	private static String WSCMD_UPDATED_BY_NAME = "UpdatedByNam";
	private static String WSCMD_UPDATED_BY_DATE = "UpdatedDtm";
	
	private static String WSCMD_BILLING_EMAIL = "BillingEmailAddr";
	private static String WSCMD_ACTIVATION_STATUS_CD = "ActivationStatusCd";
	private static String WSCMD_ACTIVATION_STAT_DESC = "ActivationStatusDesc";
	private static String WSCMD_BILLING_LAG_DAY = "InvoiceGenerationLagDay";
	private static String WSCMD_BILLING_CSV_IND = "CsvFileInd";
	private static String WSCMD_DELIVERY_METHOD = "InvoiceDeliveryMethodCd";

	private static String WSCMD_INV_GEN_DETAIL_CD = "InvoiceGenerationDetailCd";
	private static String WSCMD_DEFAULT_IND = "DefaultBillingProfileInd";
	private static String WSCMD_THIRT_PARTY_BP_IND = "ThirdPrtyBillingProfileInd";
	private static String WSCMD_SVC_CENTER_USAGE_IND = "ServiceCenterUsageInd";
	private static String WSCMD_BILL_BY_LOC_IND = "BillByLocationInd";
	
	private static String WSCMD_BILLING_ADDRESS = "BillingAddress";
	private static String WSCMD_BILLING_CITY_STATE = "BillingCityState";
	private static String WSCMD_BRANCH_COUNT = "BranchCount";
	private static String WSCMD_CUST_NUM = "CustomerNum";
	private static String WSCMD_CUST_NAME = "CustomerNam";
	
	private static String WSCMD_STATE_DESC = "StateDesc";
	private static String WSCMD_INV_DELIVERY_METHOD_DESC = "InvoiceDeliveryMethodDesc";
	private static String WSCMD_INV_GEN_DETAIL_DESC = "InvoiceGenerationDetailDesc";
	private static String WSCMD_DELIVERY_EMAIL_ADDR = "InvoiceDeliveryEmailAddr";
	
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
			else if (elementName.equals(WSCMD_BILL_BY_LOC_IND))
			{
				profileDTO.setIsBillByLocation(super.getBooleanFromString(theElement, "Y"));
			}
		    else if (elementName.equals( WSCMD_CREATE_DATE))
			{
		    	DateDTO date = super.getFilterDate(theElement);
		    	if( date != null) {
		    		profileDTO.setCreationDate(date);
		    	}
			}
		    else if (elementName.equals( WSCMD_CREATE_BY_NAME))
			{
				profileDTO.setCreatedByUser(theElement);
			}
		    else if (elementName.equals( WSCMD_CUST_NUM))
			{
				profileDTO.setCustomerDisplayID(theElement);
			}
		    else if (elementName.equals( WSCMD_CUST_NAME))
			{
				profileDTO.setCustomerName(theElement);
			}
		    else if (elementName.equals( WSCMD_UPDATED_BY_DATE))
			{
		    	DateDTO date = super.getFilterDate(theElement);
		    	if( date != null) {
		    		profileDTO.setModifiedDate(date);
		    	}
			}
		    else if (elementName.equals( WSCMD_UPDATED_BY_NAME))
			{
				profileDTO.setLastModifiedByUser(theElement);
			}
		    else if (elementName.equals( WSCMD_INV_GEN_DETAIL_DESC))
			{
				profileDTO.setShowDetailOnBill(super.getBooleanFromString(theElement, "Detail"));
			}
		    else if (elementName.equals( WSCMD_BILLING_CSV_IND))
			{
				profileDTO.setIncludeCSV(super.getBooleanFromString(theElement, "Y"));
			}
		    else if (elementName.equals( WSCMD_ACTIVATION_STATUS_CD))
			{
				profileDTO.setActivationStatusCd(theElement);
			}
		    else if (elementName.equals( WSCMD_ACTIVATION_STAT_DESC))
		    {
		    	profileDTO.setActivationStatus(theElement);
		    }
//		    else if (elementName.equals( WSCMD_CUST_CHASE))
//			{
//				profileDTO.setCustomerChase(theElement);
//			}
//		    else if (elementName.equals( WSCMD_BILLING_CYCLE_CODE))
//			{
//				profileDTO.setBillingCycleCode(theElement);
//			}
		    else if (elementName.equals( WSCMD_BILLING_LAG_DAY))
			{
				profileDTO.setLagDays(theElement);
			}	
		    else if (elementName.equals( WSCMD_ATTN_NAME))
			{
				profileDTO.setBillToAttention(theElement);
			}		    
		    else if (elementName.equals( WSCMD_INV_NAME))
			{
				profileDTO.setBillToInvoiceName(theElement);
			}
		    else if (elementName.equals( WSCMD_BILLING_PHONE))
			{
				profileDTO.getBillToPhone().setNumber(getFormattedPhoneNumber(theElement));
			}
		    else if (elementName.equals( WSCMD_BILLING_PHONE_EXT))
			{
				profileDTO.getBillToPhone().setExtension(theElement);
			}
		    else if (elementName.equals( WSCMD_BILLING_FAX))
			{
		    	profileDTO.getBillToFax().setNumber(getFormattedPhoneNumber(theElement));
			}
		    else if (elementName.equals( WSCMD_BILLING_FAX_EXT))
			{
		    	profileDTO.getBillToFax().setExtension(theElement);
			}
		    else if (elementName.equals(WSCMD_BILLING_EMAIL))
			{
		    	Collection methods = profileDTO.getBillToEmails();
		    	if( methods == null )
		    		methods = new ArrayList();
		    	if (theElement != null && theElement.indexOf("@") > 0)
		    	{
			    	methods.add(theElement);
			    	profileDTO.setBillToEmails(methods);
		    	}
			}
		    else if (elementName.equals(WSCMD_DELIVERY_EMAIL_ADDR))
			{
		    	Collection methods = profileDTO.getOverrideEmails();
		    	if( methods == null )
		    		methods = new ArrayList();
		    	if (theElement != null && theElement.indexOf("@") > 0)
		    	{
			    	methods.add(theElement);
			    	profileDTO.setOverrideEmails(methods);
		    	}
			}
		    else if (elementName.equals(WSCMD_DELIVERY_METHOD))
			{
				profileDTO.setDeliveryServiceTypeCd(theElement);
			} 
		    else if (elementName.equals(WSCMD_INV_DELIVERY_METHOD_DESC ))
			{
				profileDTO.setDeliveryServiceType(theElement);
			}
		    else if (elementName.equals(WSCMD_BILLING_ADDR_1))
			{
				profileDTO.getBillToLocation().setStreet1(theElement);
			} 
		    else if (elementName.equals(WSCMD_BILLING_ADDR_2))
			{
				profileDTO.getBillToLocation().setStreet2(theElement);
			} 
		    else if (elementName.equals(WSCMD_BILLING_ADDR_3))
			{
				profileDTO.getBillToLocation().setStreet3(theElement);
			} 
		    else if (elementName.equals(WSCMD_BILLING_CITY))
			{
				profileDTO.getBillToLocation().setCity(theElement);
			} 
		    else if (elementName.equals(WSCMD_BILLING_STATE_CD))
			{
				profileDTO.getBillToLocation().setState(USState.decodeAbbreviation(theElement));
			} 
		    else if (elementName.equals(WSCMD_BILLING_ZIP))
			{
				profileDTO.getBillToLocation().setZipCode(getFormattedZipcode(theElement));
			} 
//		    else if (elementName.equals(WSCMD_BILLING_ADDRESS))
//			{
//				profileDTO.setBillingAddressString(theElement);
//			}
//		    else if (elementName.equals(WSCMD_BILLING_CITY_STATE))
//			{
//				profileDTO.setBillingCityState(theElement);
//			} 
		    else if (elementName.equals(WSCMD_BRANCH_COUNT))
			{
				profileDTO.setBranchCount(Integer.valueOf(theElement).intValue());
			}
		    else if (elementName.equals(WSCMD_SVC_CENTER_USAGE_IND))
			{
				profileDTO.setIsUsedForServiceCenter(super.getBooleanFromString(theElement, "Y"));
			}
		    else if (elementName.equals(WSCMD_THIRT_PARTY_BP_IND))
			{
				profileDTO.setIsThirdParty(super.getBooleanFromString(theElement, "Y"));
			}
		    else if (elementName.equals(WSCMD_DEFAULT_IND))
			{
				profileDTO.setDefaultBillingProfile(super.getBooleanFromString(theElement, "Y"));
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
		{	
			billingProfiles = new ArrayList();
		}
		if (elementName.equals(WSCMD_BP_INFO))
		{	//have rep info
			profileDTO = new BillingProfileDTO();
			profileDTO.setBillToLocation(new AddressDTO());
			profileDTO.setBillToPhone(new PhoneDTO());
			profileDTO.setBillToFax(new PhoneDTO());
			if( billingProfiles != null){
				billingProfiles.add(profileDTO);
			}
		}
	}

	/**
	 * @return the billingProfileDTO
	 */
	public BillingProfileDTO getBillingProfile()
	{
		return profileDTO;
	}
	
	/**
	 * @return the list of billingProfileDTO
	 */
	public Collection getBillingProfiles()
	{
		return billingProfiles;
	}
	

}
