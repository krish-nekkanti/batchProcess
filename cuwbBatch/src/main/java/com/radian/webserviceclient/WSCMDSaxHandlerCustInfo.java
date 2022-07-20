package com.radian.webserviceclient;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustomerDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.RepInfoDTO;

public class WSCMDSaxHandlerCustInfo extends WSCMDClientSaxHandler
{
	private CustomerDTO custInfo;
	
	private static String WSMIO_CUSTINFO = "Customer";
	private static String WSMIO_CUST_NUM = "CustomerNum";
	private static String WSMIO_CUST_NAME = "CustomerNam";
	private static String WSMIO_CUST_ID = "CustomerId";
	
	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		if (custInfo != null)
		{	//fill in rep info
			if (elementName.equals(WSMIO_CUST_ID))
			{
				custInfo.setCustomerID(new Long(theElement));
			}
			else if (elementName.equals(WSMIO_CUST_NAME))
			{
				custInfo.setCustomerName(theElement);
			}
			else if (elementName.equals(WSMIO_CUST_NUM))
			{
				custInfo.setCustomerNumber(theElement);
			}	
		}
	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
		if (elementName.equals(WSMIO_CUSTINFO))
		{	//have rep info
			custInfo = new CustomerDTO();
		}
	}

	/**
	 * @return the custInfo
	 */
	public CustomerDTO getCustInfo()
	{
		return custInfo;
	}
}
