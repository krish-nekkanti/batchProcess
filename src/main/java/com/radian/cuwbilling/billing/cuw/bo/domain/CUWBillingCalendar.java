/*
 * BillingInclusion.java
 *
 * Created on September 3, 2003, 5:10 PM
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.radian.cuwbilling.billing.common.bo.codes.BillingCalendarType;
import com.radian.cuwbilling.billing.common.bo.codes.BillingPeriodType;
import com.radian.cuwbilling.billing.common.bo.codes.BillingProfileType;
import com.radian.cuwbilling.billing.cuw.bs.CUWBillingException;
import com.radian.cuwbilling.common.bo.domain.AxiomEntity;

/**
 * 
 * @author DLeed
 */
public interface CUWBillingCalendar extends AxiomEntity
{

    public BillingProfileType getBillingProfileType();

    public void setBillingProfileType(BillingProfileType type);

    public BillingCalendarType getCalendarType();

    public void setCalendarType(BillingCalendarType type);

    public BillingPeriodType getBillingPeriodType();

    public void setBillingPeriodType(BillingPeriodType type);

    public Collection getBillingCycles();

    public CUWBillingPeriod getBillingPeriod(Long periodID);

    public List getSortedBillingCycles();

    public CUWBillingPeriod getPeriodContainingDate(Date billDate);

    public void addBillingCycle(CUWBillingPeriod month);

    public void removeBillingCycle(CUWBillingPeriod month);

    public void removeBillingCycles();

    public Date getStartDate();

    public Date getEndDate();

    public Date getLastBillingDate();

    public CUWBillingPeriod getLastBillingPeriod() throws CUWBillingException;

    public String getFiscalYear();

    public void setFiscalYear(String year);
}
