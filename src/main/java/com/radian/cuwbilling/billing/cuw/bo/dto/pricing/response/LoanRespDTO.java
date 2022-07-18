package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;

import com.radian.foundation.bo.dto.BaseDTO;

public class LoanRespDTO extends BaseDTO
{
	private String loanNumber;
	
	private String price;
	
	private PricingStatus status;
	
	private String UWFee; 
	
	private String MICredit; 
	
	private String AUSFee; 
	
	public String getLoanNumber()
	{
		return loanNumber;
	}
	
	public String getPrice()
	{
		return price;
	}
	
	public String getUWFee()
	{
		return UWFee;
	}
	
	public String getMICredit()
	{
		return MICredit;
	}
	
	public String getAUSFee()
	{
		return AUSFee;
	}
	
	public PricingStatus getStatus()
	{
		return status;
	}
	
	public void setLoanNumber(String loanNumber)
	{
		this.loanNumber = loanNumber;
	}
	
	public void setPrice(String price)
	{
		this.price = price;
	}
	
	public void setUWFee(String UWFee)
	{
		this.UWFee = UWFee;
	}
	
	public void setMICredit(String MICredit)
	{
		this.MICredit = MICredit;
	}
	
	public void setAUSFee(String AUSFee)
	{
		this.AUSFee = AUSFee;
	}
	
	public void setStatus(PricingStatus status)
	{
		this.status = status;
	}
}
