package com.radian.webserviceclient;

import java.util.Collection;
import java.util.ArrayList;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustomerDTO;
import com.radian.cuwbilling.common.bo.dto.DateDTO;

public class WSCMDSaxHandlerBillingProfileThirdParties extends WSCMDClientSaxHandler
{
	private Collection customers = null;
	private CustomerDTO cust = null;
	
	private static String WSCMD_BP_THIRD_PARTIES = "ArrayOfBillingProfileSubmitter";
	private static String WSCMD_BP_THIRD_PARTY_INFO = "BillingProfileSubmitter";
	private static String WSCMD_BP_SUBMITTER_ID = "BillingProfileSubmitterId";
	private static String WSCMD_BP_ID = "BillingProfileId";
	
	private static String WSCMD_CUST_ID = "CustomerId";
	private static String WSCMD_CUST_NUM = "CustomerNum";
	private static String WSCMD_CUST_NAME = "CustomerNam";
	private static String WSCMD_CREATED_BY_NAME = "CreatedByNam";
	private static String WSCMD_CREATED_DATE = "CreatedDtm";
	private static String WSCMD_UPDATED_BY_NAME = "UpdatedByNam";
	private static String WSCMD_UPDATED_DATE = "UpdatedDtm";
			
	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		try{
		if ( customers != null)
		{
			if (elementName.equals(WSCMD_CUST_ID))
			{
				cust.setCustomerID(new Long(theElement));
			}
			else if (elementName.equals(WSCMD_CUST_NUM))
			{
				cust.setCustomerNumber(theElement);
			}
			else if (elementName.equals(WSCMD_CUST_NAME))
			{
				cust.setCustomerName(theElement);
			}
			else if (elementName.equals(WSCMD_CUST_NAME))
			{
				cust.setCustomerName(theElement);
			}
			else if (elementName.equals(WSCMD_CREATED_BY_NAME))
			{
				cust.setCreatedByName(theElement);
			}
			else if (elementName.equals(WSCMD_CREATED_DATE))
			{
				cust.setCreatedDate(super.getFilterDate(theElement));
			}
			else if (elementName.equals(WSCMD_UPDATED_BY_NAME))
			{
				cust.setLastModifiedByName(theElement);
			}
			else if (elementName.equals(WSCMD_UPDATED_DATE))
			{
				cust.setModifiedDate(super.getFilterDate(theElement));
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
		if (elementName.equals(WSCMD_BP_THIRD_PARTIES))
		{	
			 customers = new ArrayList();
		}
		if (elementName.equals(WSCMD_BP_THIRD_PARTY_INFO))
		{	
			cust = new CustomerDTO();
			customers.add(cust);
		}
	}

	/**
	 * @return the custInfo
	 */
	public Collection getBillingProfileThirdParties()
	{
		return customers;
	}
}
