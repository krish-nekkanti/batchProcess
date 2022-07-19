/**
 * @(#) TimeSheetImportEntryImpl.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain.impl;

import java.util.Date;

import com.radian.cuwbilling.billing.cuw.bo.domain.TimeSheetImportEntry;
import com.radian.cuwbilling.common.bo.domain.impl.BaseDomainObjectImpl;

public class TimeSheetImportEntryImpl extends BaseDomainObjectImpl implements TimeSheetImportEntry
{

    private String underwriterCode;

    private Date date;

    private Double hours;

    public TimeSheetImportEntryImpl()
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

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setHours(Double hours)
    {
        this.hours = hours;
    }


    public String getUnderwriterCode() {
		return underwriterCode;
	}

	public void setUnderwriterCode(String underwriterCode) {
		this.underwriterCode = underwriterCode;
	}

}
