/**
 * @(#) TimesheetEntryImpl.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain.impl;

import java.util.Date;

import com.radian.cuwbilling.billing.common.bo.codes.BillingStatus;
import com.radian.cuwbilling.billing.cuw.bo.domain.BillingDetailRecord;
import com.radian.cuwbilling.billing.cuw.bo.domain.TimeSheetEntry;
import com.radian.cuwbilling.common.bo.domain.impl.BaseDomainObjectImpl;
import com.radian.cuwbilling.common.bo.domain.Placement;

public class TimeSheetEntryImpl extends BaseDomainObjectImpl implements TimeSheetEntry
{

    private String underwriterCode;

    private Date date;

    private Double hours;

    private BillingStatus billingStatus;

    private BillingDetailRecord bdr;
    
    private Boolean finalizedInd;

    public TimeSheetEntryImpl()
    {
    }

    public Date getDate()
    {
        return date;
    }

    public Double getHours()
    {
        return hours;
    }

    public BillingStatus getBillingStatus()
    {
        return billingStatus;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setHours(Double hours)
    {
        this.hours = hours;
    }

    public void setBillingStatus(BillingStatus status)
    {
        this.billingStatus = status;
    }

    public String getUnderwriterCode() {
		return underwriterCode;
	}

	public void setUnderwriterCode(String underwriterCode) {
		this.underwriterCode = underwriterCode;
	}

	/*
     * Needs to add these two methods because we can not map enums in the mapper
     * xml
     */
    public Long getBillingStatusRaw()
    {
        if (this.billingStatus != null)
            return this.billingStatus.getID();
        else
            return null;
    }

    /*
     * Needs to add this method because we cannot map enums in the mapper
     */
    public void setBillingStatusRaw(Long stat)
    {
        if (stat != null)
        {
            this.billingStatus = BillingStatus.decodeValue(stat);
        }
    }

    /**
     * @return
     */
    public BillingDetailRecord getBDR()
    {
        return bdr;
    }

    /**
     * @param record
     */
    public void setBDR(BillingDetailRecord record)
    {
        bdr = record;
    }
    
    public Boolean getFinalizedInd()
    {
    	return finalizedInd;
    }
    
    public void setFinalizedInd(Boolean flag)
    {
    	this.finalizedInd = flag;
    }
}
