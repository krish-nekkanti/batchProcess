/**
 * @(#) WSMionlineSaxHandlerRepInfo.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.webserviceclient;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.RepInfoDTO;

/**
 * Handle creation of java objects to hold mionline web service client response
 * This one is for GetRepInfo response
 */
public class WSMionlineSaxHandlerRepInfo extends WSMionlineClientSaxHandler
{
	private RepInfoDTO repInfo;
	
	private static String WSMIO_REPINFO = "cuw_info";
	private static String WSMIO_REPID = "repid";
	private static String WSMIO_FIRSTNAME = "firstname";
	private static String WSMIO_LASTNAME = "lastname";
	private static String WSMIO_JOBFUNC = "jobfunction";
	private static String WSMIO_LOC = "location";
	
	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		if (repInfo != null)
		{	//fill in rep info
			if (elementName.equals(WSMIO_REPID))
			{
				repInfo.setRepID(theElement);
			}
			else if (elementName.equals(WSMIO_FIRSTNAME))
			{
				repInfo.setFirstName(theElement);
			}
			else if (elementName.equals(WSMIO_LASTNAME))
			{
				repInfo.setLastName(theElement);
			}
			else if (elementName.equals(WSMIO_JOBFUNC))
			{
				repInfo.setJobFunction(theElement);
			}
			else if (elementName.equals(WSMIO_LOC))
			{
				repInfo.setLocation(theElement);
			}			
		}
	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
		if (elementName.equals(WSMIO_REPINFO))
		{	//have rep info
			repInfo = new RepInfoDTO();
		}
	}

	/**
	 * @return the repInfo
	 */
	public RepInfoDTO getRepInfo()
	{
		return repInfo;
	}
}
