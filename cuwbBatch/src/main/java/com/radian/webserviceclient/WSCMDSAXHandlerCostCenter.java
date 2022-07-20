package com.radian.webserviceclient;

import java.util.ArrayList;
import java.util.Collection;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustomerDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.UnderwriterRespDTO;

public class WSCMDSAXHandlerCostCenter extends WSCMDClientSaxHandler
{
	private String costCenterNum;	
	private static String WSMIO_COST_CENTER = "ostCenterCustomized";
	private static String WSMIO_COST_CENTER_NUMBER = "CUWCostCenterNum";
		
	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		if (elementName.equals(WSMIO_COST_CENTER_NUMBER))
		{
			costCenterNum = theElement;
		}
	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
		if (elementName.equals(WSMIO_COST_CENTER))
		{	
			costCenterNum = new String();
		}
	}

	/**
	 * @return the custInfo
	 */
	public String getCostCenterNum()
	{
		return costCenterNum;
	}
}
