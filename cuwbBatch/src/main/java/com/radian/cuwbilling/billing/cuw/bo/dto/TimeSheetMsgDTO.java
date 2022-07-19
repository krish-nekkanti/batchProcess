/*
 * TimeSheetMsgDTO.java
 *
 * Created on Dec 11, 2007
 */

package com.radian.cuwbilling.billing.cuw.bo.dto;

import java.util.Date;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.cuwbilling.common.util.ObjectUtils;
import com.radian.foundation.bo.dto.BaseDTO;

public class TimeSheetMsgDTO extends BaseDTO
{

    protected String underWriterCode = "";

    protected double hoursWorked;

    protected DateDTO dateWorked = null;

    public String getUnderWriterCode()
    {
        return underWriterCode;
    }

    public void setUnderWriterCode(String code)
    {
        this.underWriterCode = code;
    }

    public double getHoursWorked()
    {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked)
    {
        this.hoursWorked = hoursWorked;
    }

    public DateDTO getDateWorked()
    {
        return dateWorked;
    }

    public void setDateWorked(DateDTO dateWorked)
    {
        this.dateWorked = dateWorked;
    }

    public Date getDateWorkedRaw()
    {
        return DateDTO.toDate(dateWorked);
    }

    public void setDateWorkedRaw(Date dateWorked)
    {
        this.dateWorked = DateDTO.dateToDateDTO(dateWorked);
    }

    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        if (!(obj instanceof TimeSheetMsgDTO))
            return false;

        // check equality of composite key (placementID,dateWorked)
        TimeSheetMsgDTO otherDto = (TimeSheetMsgDTO) obj;
        return ObjectUtils.equals(getUnderWriterCode(), otherDto.getUnderWriterCode()) && ObjectUtils.equals(getDateWorked(), otherDto.getDateWorked());
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[TIMESHEET MSG DTO placementNumber='");
        sb.append(underWriterCode);
        sb.append("', dateWorked=");
        if (dateWorked != null)
        {
            sb.append(dateWorked.toString());
        }
        sb.append(", hours=");
        sb.append(String.valueOf(hoursWorked));
        return sb.toString();
    }
}
