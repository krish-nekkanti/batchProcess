/**
 * @author wu
 *
 */

package com.radian.webserviceclient;

import java.util.Collection;
import java.util.ArrayList;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CMDCodeDTO;
import com.radian.cuwbilling.common.bo.dto.DateDTO;

public class WSCMDSaxHandlerCode extends WSMionlineClientSaxHandler
{
	private Collection codes = null;
	private CMDCodeDTO code = null;

	private static String WSCMD_CODES = "ArrayOfCodesCustomized";
	private static String WSCMD_CODE_INFO = "CodesCustomized";
	private static String WSCMD_CODE_CD = "Cd";
	private static String WSCMD_CODE_DESC = "Desc";
	private static String WSCMD_ACTIVATION_IND = "ActiveInd";
	
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
		if ( codes != null)
		{
			if (elementName.equals(WSCMD_CODE_CD))
			{
				code.setCode(theElement);
			}
			else if (elementName.equals(WSCMD_CODE_DESC))
			{
				code.setCodeDesc(theElement);
			}
			else if (elementName.equals(WSCMD_ACTIVATION_IND))
			{
				code.setActivationInd(theElement);
			}
			else if (elementName.equals(WSCMD_CREATED_BY_NAME))
			{
				code.setCreatedByName(theElement);
			}
			else if (elementName.equals(WSCMD_CREATED_DATE))
			{
				code.setCreatedDate(new DateDTO(theElement));
			}
			else if (elementName.equals(WSCMD_UPDATED_BY_NAME))
			{
				code.setLastModifiedByName(theElement);
			}
			else if (elementName.equals(WSCMD_UPDATED_DATE))
			{
				code.  setModifiedDate(new DateDTO(theElement));
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
		if (elementName.equals(WSCMD_CODES))
		{	
			codes = new ArrayList();
		}
		if (elementName.equals(WSCMD_CODE_INFO))
		{	
			code = new CMDCodeDTO();
			codes.add(code);
		}
	}

	/**
	 * @return the custInfo
	 */
	public Collection getCodes()
	{
		return codes;
	}
}
