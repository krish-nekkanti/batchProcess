/**
 * @(#) WSMionlineSaxHandlerListUnderwriters.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.webserviceclient;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Handle creation of java objects to hold mionline web service client response
 * This one is for ListUnderwritersWithUnbilledLoans response (ArrayList of Strings (underwriter codes, aka repIDs))
 */
public class WSMionlineSaxHandlerListUnderwriters extends WSMionlineClientSaxHandler
{
	//ArrayList of Strings (underwriter codes, aka repIDs)
	private Collection uwList;
	
	private static String WSMIO_UWS = "underwriters";
	private static String WSMIO_ITEM = "listitem";
	
	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		if (uwList != null)
		{
			if (elementName.equals(WSMIO_ITEM))
			{	//save underwriter codes
				uwList.add(theElement);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
		if (elementName.equals(WSMIO_UWS))
		{	//have underwriters
			uwList = new ArrayList();
		}
	}

	/**
	 * @return the uwList
	 */
	public Collection getUwList()
	{
		return uwList;
	}
}
