package com.radian.webserviceclient;

import java.util.ArrayList;
import java.util.Collection;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustomerDTO;

public class WSCMDSaxHandlerThirdPartySubmitterBilledInvestors extends WSCMDClientSaxHandler
{
	private Collection customers = null;
	private CustomerDTO cust = null;
	
	private static String WSCMD_BP_THIRD_PARTIES_SUBMITTER_BILLED_INVESTOR = "ArrayOfCustomer";
	private static String WSCMD_BP_THIRD_PARTY_INFO = "Customer";
		
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
		try
		{
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
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
		if (elementName.equals(WSCMD_BP_THIRD_PARTIES_SUBMITTER_BILLED_INVESTOR))
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
	public Collection getInvestors()
	{
		return customers;
	}
}
