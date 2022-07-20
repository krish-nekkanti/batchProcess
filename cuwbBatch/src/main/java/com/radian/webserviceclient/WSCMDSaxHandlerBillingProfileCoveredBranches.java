package com.radian.webserviceclient;

import java.util.Collection;
import java.util.ArrayList;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.BranchDTO;
import com.radian.cuwbilling.common.bo.codes.USState;
import com.radian.cuwbilling.common.bo.dto.AddressDTO;
import com.radian.cuwbilling.common.bo.dto.DateDTO;

public class WSCMDSaxHandlerBillingProfileCoveredBranches extends WSCMDClientSaxHandler
{
	private Collection branches = null;
	private BranchDTO branch = null;
	
	private static String WSCMD_PROFILE_COVERED_BRANCHES = "ArrayOfBillingProfileCoverLocation";
	private static String WSCMD_PROFILE_COVERED_BRANCH_INFO = "BillingProfileCoverLocation";
	private static String WSCMD_BP_COVERED_LOC_ID = "BillingProfileCoverLocId";
	private static String WSCMD_BP_ID = "BillingProfileId";
	private static String WSCMD_CUST_LOC_ID = "CustomerLocationId";
	private static String WSCMD_LOC_CREATED_BY_NAME = "CreatedByNam";
	private static String WSCMD_LOC_CREATED_DATE = "CreatedDtm";
	private static String WSCMD_LOC_UPDATED_BY_NAME = "UpdatedByNam";
	private static String WSCMD_LOC_UPDATED_DATE = "UpdatedDtm";
    private static String WSCMD_LOC_NUM = "LocationNum";
    private static String WSCMD_LOC_NAME = "LocationNam";
    private static String WSCMD_LOC_ALT_NAME = "LocationAltNam";
    private static String WSCMD_LOC_ADDR_1 = "LocationStreetAddr1";
    private static String WSCMD_LOC_ADDR_2 = "LocationStreetAddr2";
    private static String WSCMD_LOC_ADDR_3 = "LocationStreetAddr3";
    private static String WSCMD_LOC_CITY = "LocationCty";
    private static String WSCMD_LOC_STATE_CD = "LocationStateCd";
    private static String WSCMD_ACTIVATION_STATUS_CD = "ActivationStatusCd";
    private static String WSCMD_LOC_ADDR = "LocationAddress";
    private static String WSCMD_LOC_CITY_STATE = "LocationCityState";

			
	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		try {
		if (branch != null)
		{ 
			if (elementName.equals(WSCMD_BP_COVERED_LOC_ID))
			{
				branch.setBillingProfileCoverlocId(theElement);
			} 
			else if (elementName.equals(WSCMD_BP_ID))
			{
				branch.setBillingProfileId(theElement);
			}
			else if (elementName.equals(WSCMD_CUST_LOC_ID))
			{
				branch.setCustomerlocationId(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_NUM))
			{
				branch.setLocationNum(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_NAME))
			{
				branch.setLocationName(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_CREATED_BY_NAME))
			{
				branch.setCreatedByName(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_CREATED_DATE))
			{
				branch.setCreatedDate(new DateDTO(theElement));
			}
			else if (elementName.equals(WSCMD_LOC_UPDATED_BY_NAME))
			{
				branch.setLastModifiedByName(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_UPDATED_DATE))
			{
				branch.setModifiedDate(new DateDTO(theElement));
			}
			else if (elementName.equals(WSCMD_LOC_ALT_NAME))
			{
				branch.setLocationAltName(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_ADDR_1))
			{
				branch.getLocationAddress().setStreet1(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_ADDR_2))
			{
				branch.getLocationAddress().setStreet2(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_ADDR_3))
			{
				branch.getLocationAddress().setStreet3(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_CITY))
			{
				branch.getLocationAddress().setCity(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_STATE_CD))
			{
				branch.getLocationAddress().setState(USState.decodeAbbreviation(theElement));
			}
			else if (elementName.equals(WSCMD_ACTIVATION_STATUS_CD))
			{
				branch.setActivationStatusCd(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_ADDR))
			{
				branch.setLocationAddressString(theElement);
			}
			else if (elementName.equals(WSCMD_LOC_CITY_STATE))
			{
				branch.setLocationCityState(theElement);
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
		if (elementName.equals(WSCMD_PROFILE_COVERED_BRANCHES))
		{	
			branches = new ArrayList();
		}
		if (elementName.equals(WSCMD_PROFILE_COVERED_BRANCH_INFO))
		{	
			branch = new BranchDTO();
			branch.setLocationAddress(new AddressDTO());
			branches.add(branch);
		}
	}

	/**
	 * @return the custInfo
	 */
	public Collection getBillingProfileCoveredBranches()
	{
		return branches;
	}
}

