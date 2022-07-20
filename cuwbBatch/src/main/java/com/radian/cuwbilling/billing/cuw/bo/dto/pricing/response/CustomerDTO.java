package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;

import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.foundation.bo.dto.BaseDTO;

public class CustomerDTO extends BaseDTO
{
	private Long customerID;
    private String customerName;   
    private String customerNumber;
    private String createdByName;
    private DateDTO createdDate;
    private String lastModifiedByName;
    private DateDTO modifiedDate;
			

    public void setCustomerID(Long custID)
    {
    	this.customerID = custID;
    }

    public Long getCustomerID()
    {
    	return customerID;
    }
    
    public void setCustomerName(String custName)
    {
    	this.customerName = custName;
    }

    public String getCustomerName()
    {
    	return customerName;
    }
    
    public void setCustomerNumber(String custNumber)
    {
    	this.customerNumber = custNumber;
    }

    public String getCustomerNumber()
    {
    	return customerNumber;
    }

	/**
	 * @return the createdByName
	 */
	public String getCreatedByName() 
	{
		return createdByName;
	}

	/**
	 * @param createdByName the createdByName to set
	 */
	public void setCreatedByName(String createdByName) 
	{
		this.createdByName = createdByName;
	}

	/**
	 * @return the createdDate
	 */
	public DateDTO getCreatedDate() 
	{
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(DateDTO createdDate) 
	{
		this.createdDate = createdDate;
	}

	/**
	 * @return the lastModifiedByName
	 */
	public String getLastModifiedByName() 
	{
		return lastModifiedByName;
	}

	/**
	 * @param lastModifiedByName the lastModifiedByName to set
	 */
	public void setLastModifiedByName(String lastModifiedByName) 
	{
		this.lastModifiedByName = lastModifiedByName;
	}

	/**
	 * @return the modifiedDate
	 */
	public DateDTO getModifiedDate() 
	{
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(DateDTO modifiedDate) 
	{
		this.modifiedDate = modifiedDate;
	}
    
    
}
