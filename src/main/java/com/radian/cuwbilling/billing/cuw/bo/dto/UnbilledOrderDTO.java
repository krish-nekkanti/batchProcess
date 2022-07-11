package com.radian.cuwbilling.billing.cuw.bo.dto;

import java.util.Date;

import com.radian.cuwbilling.billing.common.bo.codes.BDRBillingStatus;
import com.radian.cuwbilling.billing.common.bo.codes.BillingStatus;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.cuwbilling.common.bo.dto.PersonDTO;
import com.radian.foundation.bo.dto.BaseDTO;

public class UnbilledOrderDTO extends BaseDTO
{
	/**
	 * radian order item number
	 */
    private String orderItemNum;
    
    /**
     * fulfillment date of the order
     */
    private DateDTO decisionDate;
    
    /**
     * UW code from mi online
     */
    private String underwriterCode;
    
    /**
     * under writer name
     */
    private String underwriterName;
    
    /**
     *  first of second lien
     */
    private String lienTypeName;
    
    /**
     *  whether this is a piggy
     */
    private Boolean piggyBackFlag;
    
    /** 
     * cuw service type name 
     */
    private String serviceTypeName;
    
    /**
     * billed or unbilled
     */
    private BillingStatus billingStatus;
    
    /**
     *  mi issued flag 
     */
    private Boolean miFlag;

    
	public DateDTO getDecisionDate()
	{
		return decisionDate;
	}

	public void setDecisionDate(DateDTO decisionDate)
	{
		this.decisionDate = decisionDate;
	}
	
	public Date getDecisionDateAsDate()
	{
		return DateDTO.toDate(decisionDate);
	}

	public void setDecisionDateAsDate(Date decisionDate)
	{
		this.decisionDate = DateDTO.dateToDateDTO(decisionDate);
	}

	public String getLienTypeName()
	{
		return lienTypeName;
	}

	public void setLienTypeName(String lienTypeName)
	{
		this.lienTypeName = lienTypeName;
	}

	public Boolean getMiFlag()
	{
		return miFlag;
	}

	public void setMiFlag(Boolean miFlag)
	{
		this.miFlag = miFlag;
	}

	public String getOrderItemNum()
	{
		return orderItemNum;
	}

	public void setOrderItemNum(String orderItemNumber)
	{
		this.orderItemNum = orderItemNumber;
	}

	public Boolean getPiggyBackFlag()
	{
		return piggyBackFlag;
	}

	public void setPiggyBackFlag(Boolean piggyBackFlag)
	{
		this.piggyBackFlag = piggyBackFlag;
	}

	public String getServiceTypeName()
	{
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName)
	{
		this.serviceTypeName = serviceTypeName;
	}

	public String getUnderwriterName()
	{
		return underwriterName;
	}

	public void setUnderwriterName(String underwriterName)
	{
		this.underwriterName = underwriterName;
	}

	public String getUnderwriterCode()
	{
		return underwriterCode;
	}

	public void setUnderwriterCode(String underwriterCode)
	{
		this.underwriterCode = underwriterCode;
	}

    public Long getBillingStatusRaw()
    {
        if (billingStatus != null)
            return billingStatus.getID();
        return null;
    }

    /**
     * @param category
     */
    public void setBillingStatusRaw(Long statusID)
    {
        billingStatus = BillingStatus.decodeValue(statusID);
    }

    /**
     * @return
     */
    public BillingStatus getBillingStatus()
    {
        return billingStatus;
    }

    /**
     * @param status
     */
    public void setBillingStatus(BillingStatus status)
    {
        billingStatus = status;
    }
	
	
}
