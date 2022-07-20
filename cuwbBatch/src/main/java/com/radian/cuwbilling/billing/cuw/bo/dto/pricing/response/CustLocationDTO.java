package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;

import com.radian.foundation.bo.dto.BaseDTO;

public class CustLocationDTO extends BaseDTO
{
	private String customerNumber;
	private String locNumber;
	private String locName;
	private String attentionName;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String phoneExt;
	private String faxNumber;
	private String faxExt;
	private String activationStatus;
	private String locType;
	private String institutionType;
	private String salesRepNumber;
	private String locAltName;
	
	public void setCustomerNumber(String num)
	{
		this.customerNumber = num;
	}
	
	public String getCustomerNumber()
	{
		return customerNumber;
	}
	
	public void setLocNumber(String num)
	{
		this.locNumber = num;
	}
	
	public String getLocNumber()
	{
		return locNumber;
	}
	
	public void setLocName(String name)
	{
		this.locName = name;
	}
	
	public String getLocName()
	{
		return locName;
	}
	
	public void setLocAltName(String name)
	{
		this.locAltName = name;
	}
	
	public String getLocAltName()
	{
		return locAltName;
	}
	
	public void setAttentionName(String name)
	{
		this.attentionName = name;
	}
	
	public String getAttentionName()
	{
		return attentionName;
	}
	
	public void setAddress1(String addr)
	{
		this.address1 = addr;
	}
	
	public String getAddress1()
	{
		return address1;
	}
	
	public void setAddress2(String addr)
	{
		this.address2 = addr;
	}
	
	public String getAddress2()
	{
		return address2;
	}
	
	public void setAddress3(String addr)
	{
		this.address3 = addr;
	}
	
	public String getAddress3()
	{
		return address3;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	
	public String getZip()
	{
		return zip;
	}
	
	public void setPhoneNumber(String num)
	{
		this.phoneNumber = num;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public void setPhoneExt(String ext)
	{
		this.phoneExt = ext;
	}
	
	public String getPhoneExt()
	{
		return phoneExt;
	}
	
	public void setFaxNumber(String num)
	{
		this.faxNumber = num;
	}
	
	public String getFaxNumber()
	{
		return faxNumber;
	}
	
	public void setFaxExt(String ext)
	{
		this.faxExt = ext;
	}
	
	public String getFaxExt()
	{
		return faxExt;
	}
	
	public void setActivationStatus(String status)
	{
		this.activationStatus = status;
	}
	
	public String getActivationStatus()
	{
		return activationStatus;
	}
	
	public void setLocType(String type)
	{
		this.locType = type;
	}
	
	public String getLocType()
	{
		return locType;
	}
	
	public void setInstitutionType(String type)
	{
		this.institutionType = type;
	}
	
	public String getInstitutionType()
	{
		return institutionType;
	}
	
	public void setSalesRepNumber(String num)
	{
		this.salesRepNumber = num;
	}
	
	public String getSalesRepNumber()
	{
		return salesRepNumber;
	}
}
