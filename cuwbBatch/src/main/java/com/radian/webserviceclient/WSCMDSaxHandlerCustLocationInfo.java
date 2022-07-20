package com.radian.webserviceclient;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustLocationDTO;

public class WSCMDSaxHandlerCustLocationInfo extends WSCMDClientSaxHandler
{
	private CustLocationDTO custLocInfo;
	
	private static String WSMIO_CUST_LOC_INFO = "CustomerLocation";
	private static String WSMIO_CUST_NUM = "CustomerNum";
	private static String WSMIO_LOC_NAME = "LocationNam";
	private static String WSMIO_LOC_ALT_NAME = "LocationAlternateNam";
	private static String WSMIO_ATTN_NAME = "AttentionNam";
	private static String WSMIO_LOC_NUM = "LocationNum";
	private static String WSMIO_ADDR_1 = "LocationStreetAddr1";
	private static String WSMIO_ADDR_2 = "LocationStreetAddr2";
	private static String WSMIO_ADDR_3 = "LocationStreetAddr3";
	private static String WSMIO_LOC_CITY = "LocationCty";
	private static String WSMIO_LOC_STATE_CD = "LocationStateCd";
	private static String WSMIO_LOC_ZIP = "LocationPhoneExtNum";
	private static String WSMIO_ACTIVATION_STATUS_CD = "ActivationStatusCd";
	private static String WSMIO_LOC_PHONE = "LocationPhoneNum";
	private static String WSMIO_LOC_PHONE_EXT = "LocationPhoneExtNum";
	private static String WSMIO_LOC_FAX = "LocationFaxNum";
	private static String WSMIO_LOC_FAX_EXT = "LocationFaxExtNum";
	
	
	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		if (custLocInfo != null)
		{	//fill in rep info
			if (elementName.equals(WSMIO_CUST_NUM))
			{
				custLocInfo.setCustomerNumber(theElement);
			}
			else if (elementName.equals(WSMIO_LOC_NAME))
			{
				custLocInfo.setLocName(theElement);
			}
			else if (elementName.equals(WSMIO_LOC_ALT_NAME))
			{
				custLocInfo.setLocAltName(theElement);
			}	
			else if (elementName.equals(WSMIO_ATTN_NAME))
			{
				custLocInfo.setAttentionName(theElement);
			}	
			else if (elementName.equals(WSMIO_LOC_NUM))
			{
				custLocInfo.setLocNumber(theElement);
			}	
			else if (elementName.equals(WSMIO_ADDR_1))
			{
				custLocInfo.setAddress1(theElement);
			}	
			else if (elementName.equals(WSMIO_ADDR_2))
			{
				custLocInfo.setAddress2(theElement);
			}	
			else if (elementName.equals(WSMIO_ADDR_3))
			{
				custLocInfo.setAddress3(theElement);
			}	
			else if (elementName.equals(WSMIO_LOC_CITY))
			{
				custLocInfo.setCity(theElement);
			}
			else if (elementName.equals(WSMIO_LOC_STATE_CD))
			{
				custLocInfo.setState(theElement);
			}
			else if (elementName.equals(WSMIO_LOC_ZIP))
			{
				custLocInfo.setZip(theElement);
			}
			else if (elementName.equals(WSMIO_ACTIVATION_STATUS_CD))
			{
				custLocInfo.setActivationStatus(theElement);
			}
			else if (elementName.equals(WSMIO_LOC_PHONE))
			{
				custLocInfo.setPhoneNumber(theElement);
			}
			else if (elementName.equals(WSMIO_LOC_PHONE))
			{
				custLocInfo.setPhoneNumber(theElement);
			}
			else if (elementName.equals(WSMIO_LOC_PHONE))
			{
				custLocInfo.setPhoneNumber(theElement);
			}
			else if (elementName.equals(WSMIO_LOC_PHONE_EXT))
			{
				custLocInfo.setPhoneExt(theElement);
			}
			else if (elementName.equals(WSMIO_LOC_FAX))
			{
				custLocInfo.setFaxNumber(theElement);
			}
			else if (elementName.equals(WSMIO_LOC_FAX_EXT))
			{
				custLocInfo.setFaxExt(theElement);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
		if (elementName.equals(WSMIO_CUST_LOC_INFO))
		{	//have rep info
			custLocInfo = new CustLocationDTO();
		}
	}

	/**
	 * @return the custInfo
	 */
	public CustLocationDTO getCustLocInfo()
	{
		return custLocInfo;
	}
}
