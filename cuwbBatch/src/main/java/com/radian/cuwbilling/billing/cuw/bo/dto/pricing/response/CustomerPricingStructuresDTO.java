package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;

import java.util.*;
import com.radian.foundation.bo.dto.BaseDTO;

public class CustomerPricingStructuresDTO extends BaseDTO
{
	private Collection pricings;
	private String errorCode;
	private String errorMessage;
	
	public void setErrorCode(String code)
	{
		this.errorCode = code;
	}
	
	public String getErrorCode()
	{
		return errorCode;
	}
	
	public void setErrorMessage(String msg)
	{
		this.errorMessage = msg;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
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
