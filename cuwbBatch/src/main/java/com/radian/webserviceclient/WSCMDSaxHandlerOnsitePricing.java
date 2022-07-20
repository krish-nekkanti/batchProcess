package com.radian.webserviceclient;

import java.util.ArrayList;
import java.util.List;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.request.PricingStructureDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustomerPricingStructuresDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.UnderwriterPricingStructuresDTO;

public class WSCMDSaxHandlerOnsitePricing extends WSCMDClientSaxHandler
{
	private UnderwriterPricingStructuresDTO pricingInfo;
	private List structs;
	
	private static String WSMIO_PRICING_STRUTS = "ArrayOfOnsitePricingCustomized";
	private static String WSMIO_PRICING = "OnsitePricingCustomized";
	private static String WSMIO_ERROR_CODE = "errorDescriptionProperty";
	private static String WSMIO_ERRROR_MSG = "errorDescriptionProperty";
	
	private static String WSMIO_PRICING_ONSITE_ID = "PricingOnsiteId";
	private static String WSMIO_CUST_ID = "CustomerId";
	private static String WSMIO_CUST_LOC_ID = "CustomerLocationId";
	private static String WSMIO_BILL_PROF_NUM = "BillingProfileNum";
	private static String WSMIO_START_DT = "StartDt";
	private static String WSMIO_END_DT = "EndDt";
	private static String WSMIO_CUW_PRICING_CD = "CuwPricingCd";
	private static String WSMIO_PER_DAY_FEE_AMT = "PerDayFeeAmt";
	private static String WSMIO_PREMIUM_RATE_FEE_AMT = "PremiumRateFeeAmt";
	private static String WSMIO_DAILY_MIN_FEE_AMT = "DailyMinimumFeeAmt";
	private static String WSMIO_MI_CREDIT_AMT = "MiCreditAmt";
	private static String WSMIO_STAND_ALONE_IND = "StandaloneInd";
	private static String WSMIO_PIGGYBACK_IND = "PiggybackInd";
	private static String WSMIO_STAND_ALONE_FEE_AMT = "StandaloneFeeAmt";
	private static String WSMIO_PIGGYBACK_FEE_AMT = "PiggybackFeeAmt";
	private static String WSMIO_CUW_PRICING_DESC = "CuwPricingDesc";
	private static String WSMIO_MI_CREDIT_CD = "MiCreditCd";
	private static String WSMIO_MI_CREDIT_DESC = "MiCreditDesc";
	private static String WSMIO_COST_CENTER_NUM = "CostCenterNum";
	private static String WSMIO_PRICING_PER_FILE_FEE_ID = "PricingPerFileFeeId";
	private static String WSMIO_SERVICE_CD = "ServiceCd";
	private static String WSMIO_SERVICE_DESC = "ServiceDesc";
	private static String WSMIO_AU_SYSTEM_CD = "AuSystemCd";
	private static String WSMIO_AU_SYSTEM_DESC = "AuSystemDesc";
	private static String WSMIO_FILE_FEE_AMT = "FileFeeAmt";
	private static String WSMIO_FILE_MI_CREDIT_AMT = "FileMiCreditAmt";
	private static String WSMIO_FILE_AU_FEE_AMT = "FileAusFeeAmt";
	private static String WSMIO_FILE_FEE_WITHOUT_MI_AMT = "FileFeeWithoutMiAmt";
	
	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#bindElementContent(java.lang.String, java.lang.String)
	 */
	public void bindElementContent(String elementName, String theElement)
	{
		if (pricingInfo != null)
		{	//fill in pricing info
			if (structs != null)
			{
				if (elementName.equals(WSMIO_MI_CREDIT_AMT))
				{
					getPricingStructure().setMICreditAmt(theElement);
				}
				if (elementName.equals(WSMIO_PRICING_ONSITE_ID))
				{
					getPricingStructure().setPricingID(theElement);
				}
				if (elementName.equals(WSMIO_CUST_ID))
				{
					getPricingStructure().setCustID(theElement);
				}
				if (elementName.equals(WSMIO_CUST_LOC_ID))
				{
					getPricingStructure().setCustLocID(theElement);
				}
				if (elementName.equals(WSMIO_BILL_PROF_NUM))
				{
					getPricingStructure().setProfileNumber(theElement);
				}
				if (elementName.equals(WSMIO_CUW_PRICING_CD))
				{
					getPricingStructure().setCuwPricingCd(theElement);
				}
				if (elementName.equals(WSMIO_PER_DAY_FEE_AMT))
				{
					getPricingStructure().setPerDayFeeAmt(theElement);
				}
				if (elementName.equals(WSMIO_DAILY_MIN_FEE_AMT))
				{
					getPricingStructure().setDailyMinimumFeeAmt(theElement);
				}
				if (elementName.equals(WSMIO_PREMIUM_RATE_FEE_AMT))
				{
					getPricingStructure().setPremiumRateFeeAmt(theElement);
				}
				if (elementName.equals(WSMIO_STAND_ALONE_IND))
				{
					getPricingStructure().setStandaloneInd(theElement);
				}
				if (elementName.equals(WSMIO_PIGGYBACK_IND))
				{
					getPricingStructure().setPiggybackInd(theElement);
				}
				if (elementName.equals(WSMIO_STAND_ALONE_FEE_AMT))
				{
					getPricingStructure().setStandaloneFeeAmt(theElement);
				}
				if (elementName.equals(WSMIO_PIGGYBACK_FEE_AMT))
				{
					getPricingStructure().setPiggybackFeeAmt(theElement);
				}
				if (elementName.equals(WSMIO_CUW_PRICING_DESC))
				{
					getPricingStructure().setCuwPricingDesc(theElement);
				}
				if (elementName.equals(WSMIO_START_DT))
				{
					getPricingStructure().setStartDate(theElement);
				}
				if (elementName.equals(WSMIO_END_DT))
				{
					getPricingStructure().setEndDate(theElement);
				}
				if (elementName.equals(WSMIO_START_DT))
				{
					getPricingStructure().setStartDate(theElement);
				}
				if (elementName.equals(WSMIO_MI_CREDIT_CD))
				{
					getPricingStructure().setMiCreditCD(theElement);
				}
				if (elementName.equals(WSMIO_MI_CREDIT_DESC))
				{
					getPricingStructure().setMiCreditAt(theElement);
				}
				if (elementName.equals(WSMIO_COST_CENTER_NUM))
				{
					getPricingStructure().setCostCenterNumber(theElement);
				}
				if (elementName.equals(WSMIO_PRICING_PER_FILE_FEE_ID))
				{
					getPricingStructure().setPricingPerFileFeeId(theElement);
				}
				if (elementName.equals(WSMIO_PRICING_ONSITE_ID))
				{
					getPricingStructure().setPricingOnsiteId(theElement);
				}
				if (elementName.equals(WSMIO_SERVICE_CD))
				{
					getPricingStructure().setServiceCd(theElement);
				}
				if (elementName.equals(WSMIO_SERVICE_DESC))
				{
					getPricingStructure().setServiceDesc(theElement);
				}
				if (elementName.equals(WSMIO_AU_SYSTEM_CD))
				{
					getPricingStructure().setAuSystemCd(theElement);
				}
				if (elementName.equals(WSMIO_AU_SYSTEM_DESC))
				{
					getPricingStructure().setAuSystemDesc(theElement);
				}
				if (elementName.equals(WSMIO_FILE_FEE_AMT))
				{
					getPricingStructure().setFileFeeAmt(theElement);
				}
				if (elementName.equals(WSMIO_FILE_MI_CREDIT_AMT))
				{
					getPricingStructure().setFileMiCreditAmt(theElement);
				}
				if (elementName.equals(WSMIO_FILE_AU_FEE_AMT))
				{
					getPricingStructure().setFileAusFeeAmt(theElement);
				}
				if (elementName.equals(WSMIO_FILE_FEE_WITHOUT_MI_AMT))
				{
					getPricingStructure().setFileFeeWithoutMiAmt(theElement);
				}
			}
			else if (elementName.equals(WSMIO_ERROR_CODE))
			{
				pricingInfo.setErrorCode(theElement);
			}
			else if (elementName.equals(WSMIO_ERRROR_MSG))
			{
				pricingInfo.setErrorMessage(theElement);
			}	
		}
	}

	/* (non-Javadoc)
	 * @see com.radian.webserviceclient.WSMionlineClientSaxHandler#startElementBinding(java.lang.String)
	 */
	public void startElementBinding(String elementName)
	{
		if (elementName.equals(WSMIO_PRICING_STRUTS))
		{	//have rep info
			pricingInfo = new UnderwriterPricingStructuresDTO();
			structs = new ArrayList();
			pricingInfo.setPricings(structs);
		}
		if (elementName.equals(WSMIO_PRICING))
		{	
			structs.add(new PricingStructureDTO());
		}
	}

	/**
	 * @return the custInfo
	 */
	public UnderwriterPricingStructuresDTO getPrcingInfo()
	{
		return pricingInfo;
	}
	
	private PricingStructureDTO getPricingStructure()
	{
		return (PricingStructureDTO)structs.get(structs.size() - 1);
	}
}
