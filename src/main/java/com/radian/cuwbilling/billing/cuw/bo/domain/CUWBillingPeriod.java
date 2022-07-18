/*
 * BillingInclusion.java
 *
 * Created on September 3, 2003, 5:10 PM
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import java.util.Date;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;

/**
 * 
 * @author DLeed
 */
public interface CUWBillingPeriod extends BaseDomainObject
{

    public Date getEndDate();

    public void setEndDate(Date endDate);

    public Date getStartDate();

    public void setStartDate(Date startDate);

    public String getBillingPeriodLabel();

    public void setBillingPeriodLabel(String label);

    public CUWBillingCalendar getCalendar();

    public void setCalendar(CUWBillingCalendar calendar);
    
    public Boolean getFinalizedInd();
    
    public void setFinalizedInd(Boolean flag);
}

