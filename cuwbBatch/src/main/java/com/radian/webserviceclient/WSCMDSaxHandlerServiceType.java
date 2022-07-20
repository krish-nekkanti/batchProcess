package com.radian.webserviceclient;
/**
 * @author wu
 *
 */

import java.util.Collection;
import java.util.ArrayList;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CMDServiceTypeDTO;
import com.radian.cuwbilling.common.bo.dto.DateDTO;

public class WSCMDSaxHandlerServiceType extends WSCMDClientSaxHandler
{
	private Collection serviceTypes = null;
	private CMDServiceTypeDTO serviceType = null;

	private static String WSCMD_SERVICES = "ArrayOfServiceType";
	private static String WSCMD_SERVICE_INFO = "ServiceType";
	private static String WSCMD_SERVICE_CD = "ServiceCd";
	private static String WSCMD_SERVICE_DESC = "ServiceDesc";
	private static String WSCMD_ACTIVATION_IND = "ActiveInd";
	private static String WSCMD_AUTOMATED_IND = "AutomatedInd";
	
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
		if ( serviceTypes != null)
		{
			if (elementName.equals(WSCMD_SERVICE_CD))
			{
				serviceType.setCode(theElement);
			}
			else if (elementName.equals(WSCMD_SERVICE_DESC))
			{
				serviceType.setCodeDesc(theElement);
			}
			else if (elementName.equals(WSCMD_ACTIVATION_IND))
			{
				serviceType.setActivationInd(theElement);
			}
			else if (elementName.equals(WSCMD_AUTOMATED_IND))
			{
				serviceType.setAutomatedInd(theElement);
			}
			else if (elementName.equals(WSCMD_CREATED_BY_NAME))
			{
				serviceType.setCreatedByName(theElement);
			}
			else if (elementName.equals(WSCMD_CREATED_DATE))
			{
				serviceType.setCreatedDate(new DateDTO(theElement));
			}
			else if (elementName.equals(WSCMD_UPDATED_BY_NAME))
			{
				serviceType.setLastModifiedByName(theElement);
			}
			else if (elementName.equals(WSCMD_UPDATED_DATE))
			{
				serviceType.  setModifiedDate(new DateDTO(theElement));
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
		if (elementName.equals(WSCMD_SERVICES))
		{	
			serviceTypes = new ArrayList();
		}
		if (elementName.equals(WSCMD_SERVICE_INFO))
		{	
			serviceType = new CMDServiceTypeDTO();
			serviceTypes.add(serviceType);
		}
	}

	/**
	 * @return the custInfo
	 */
	public Collection getServiceTypes()
	{
		return serviceTypes;
	}
}
