package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;

import java.util.Collection;

import com.radian.foundation.bo.dto.BaseDTO;

public class UnderwriterPricingStructuresDTO extends BaseDTO
{
	private String repID;
	private Collection pricings;
	private String errorMessage;
	private String errorCode;
	
	public void setRepID(String repID)
	{
		this.repID= repID;
	}
	
	public String getRepID()
	{
		return repID;
	}
	
	public void setErrorMessage(String msg)
	{
		this.errorMessage = msg;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
	
	public void setErrorCode(String code)
	{
		this.errorCode = code;
	}
	
	public String getErrorCode()
	{
		return errorCode;
	}
	
	public void setPricings(Collection pricings)
	{
		this.pricings = pricings;
	}
	
	public Collection getPricings()
	{
		return pricings;
	}
}
