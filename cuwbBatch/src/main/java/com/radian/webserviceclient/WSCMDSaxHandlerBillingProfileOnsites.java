package com.radian.webserviceclient;

import java.util.Collection;
import java.util.ArrayList;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.UnderwriterRespDTO;
import com.radian.cuwbilling.common.bo.dto.DateDTO;

public class WSCMDSaxHandlerBillingProfileOnsites extends WSCMDClientSaxHandler
{
	private Collection reps = null;
	private UnderwriterRespDTO uw = null;
	
	private static String WSMIO_PROFILE_ONSITES = "ArrayOfPricingOnsiteCustomized";
	private static String WSMIO_REPINFO = "PricingOnsiteCustomized";
	private static String WSMIO_REP_NUM = "RepresentativeNum";
	private static String WSMIO_REP_FIRST_NAM = "RepresentatvieFirstNam";
	private static String WSMIO_REP_LAST_NAM = "RepresentativeLastNam";
		
	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		if (reps != null)
		{
			if (elementName.equals(WSMIO_REP_NUM))
			{
				uw.setUnderwriterCode(theElement);
			}
			if (elementName.equals(WSMIO_REP_FIRST_NAM))
			{
				uw.setUnderwriterFirstName(theElement);
			}
			if (elementName.equals(WSMIO_REP_LAST_NAM))
			{
				uw.setUnderwriterLastName(theElement);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
		if (elementName.equals(WSMIO_PROFILE_ONSITES))
		{	
			reps = new ArrayList();
		}
		if (elementName.equals(WSMIO_REPINFO))
		{	
			uw = new UnderwriterRespDTO();
			reps.add(uw);
		}
	}

	/**
	 * @return the custInfo
	 */
	public Collection getBillingProfileOnsites()
	{
		return reps;
	}
}
