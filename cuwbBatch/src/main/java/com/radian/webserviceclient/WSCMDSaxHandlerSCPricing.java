package com.radian.webserviceclient;

import java.util.ArrayList;
import java.util.List;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.request.PricingStructureDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustomerDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustomerPricingStructuresDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.UnbilledLoanDTO;

public class WSCMDSaxHandlerSCPricing extends WSCMDClientSaxHandler
{
	private CustomerPricingStructuresDTO pricingInfo;
	private List structs;
	
	private static String WSMIO_PRICING_STRUTS = "ArrayOfServiceCenterPricingCustomized";
	private static String WSMIO_PRICING = "ServiceCenterPricingCustomized";
	private static String WSMIO_ERROR_CODE = "errorDescriptionProperty";
	private static String WSMIO_ERRROR_MSG = "errorDescriptionProperty";
	
	private static String WSMIO_PRICING_SERVICE_CENTER_ID = "PricingServiceCenterId";
	private static String WSMIO_CUST_ID = "CustomerId";
	private static String WSMIO_START_DT = "StartDt";
	private static String WSMIO_END_DT = "EndDt";
	private static String WSMIO_MI_CREDIT_CD = "MiCreditCd";
	private static String WSMIO_MI_CREDIT_DESC = "MiCreditDesc";
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
				if (elementName.equals(WSMIO_PRICING_SERVICE_CENTER_ID))
				{
					getPricingStructure().setPricingID(theElement);
				}
				if (elementName.equals(WSMIO_CUST_ID))
				{
					getPricingStructure().setCustID(theElement);
				}
				if (elementName.equals(WSMIO_START_DT))
				{
					getPricingStructure().setStartDate(theElement);
				}
				if (elementName.equals(WSMIO_END_DT))
				{
					getPricingStructure().setEndDate(theElement);
				}
				if (elementName.equals(WSMIO_MI_CREDIT_CD))
				{
					getPricingStructure().setMiCreditCD(theElement);
				}
				if (elementName.equals(WSMIO_MI_CREDIT_DESC))
				{
					getPricingStructure().setMiCreditAt(theElement);
				}
				if (elementName.equals(WSMIO_PRICING_PER_FILE_FEE_ID))
				{
					getPricingStructure().setPricingPerFileFeeId(theElement);
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
			pricingInfo = new CustomerPricingStructuresDTO();
			structs = new ArrayList();
			pricingInfo.setPricings(structs);
		}
		if (elementName.equals(WSMIO_PRICING))
		{	
			PricingStructureDTO dto = new PricingStructureDTO();
			dto.setCostCenterNumber("000000");
			structs.add(dto);
		}
	}

	/**
	 * @return the custInfo
	 */
	public CustomerPricingStructuresDTO getPrcingInfo()
	{
		return pricingInfo;
	}
	
	private PricingStructureDTO getPricingStructure()
	{
		return (PricingStructureDTO)structs.get(structs.size() - 1);
	}
}
