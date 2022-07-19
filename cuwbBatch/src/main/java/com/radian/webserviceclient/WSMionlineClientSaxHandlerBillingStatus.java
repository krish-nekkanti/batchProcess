/**
 * @(#) WSMionlineClientSaxHandlerBillingStatus.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.webserviceclient;

import java.util.ArrayList;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.BillingStatusUpdateDTO;

/**
 * Handle creation of java objects to hold mionline web service client response
 * This one is for UpdateLoanBillingStatus response (BillingStatusUpdateDTO)
 */
public class WSMionlineClientSaxHandlerBillingStatus extends WSMionlineClientSaxHandler
{
	//no element surrounding total elements, so create DTO to hold results when handler created
	private BillingStatusUpdateDTO billingStatusUpdate = new BillingStatusUpdateDTO();

	private static String WSMIO_NUM_LOANS = "nrloans";
	private static String WSMIO_NUM_BILLED = "nrupdatedtobilled";
	private static String WSMIO_NUM_ALREADY_BILLED = "nrmarkedbilled";
	private static String WSMIO_NUM_NOTFOUND = "nrnotfound";
	
	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		if (elementName.equals(WSMIO_NUM_LOANS))
		{
			getBillingStatusUpdate().setTotLoansUpdated(theElement);
		}
		else if (elementName.equals(WSMIO_NUM_BILLED))
		{
			getBillingStatusUpdate().setTotBilled(theElement);
		}
		else if (elementName.equals(WSMIO_NUM_ALREADY_BILLED))
		{
			getBillingStatusUpdate().setTotAlreadyBilled(theElement);
		}
		else if (elementName.equals(WSMIO_NUM_NOTFOUND))
		{
			getBillingStatusUpdate().setTotNotFound(theElement);
		}
	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
	}

	/**
	 * @return the billingStatusUpdate
	 */
	public BillingStatusUpdateDTO getBillingStatusUpdate()
	{
		return billingStatusUpdate;
	}
}
