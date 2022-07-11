/**
 * TimeSheetDTO.java
 * Created on September 03, 2003, 2:00 PM
 */

package com.radian.cuwbilling.billing.cuw.bo.dto;

import java.util.Date;

import com.radian.cuwbilling.billing.common.bo.codes.BillingStatus;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.cuwbilling.common.bo.dto.PersonDTO;
import com.radian.foundation.bo.dto.BaseDTO;

/**
 * TimeSheetDTO
 * 
 * @author KMadireddy
 */
public class TimeSheetDTO extends BaseDTO
{

	/**
	 * under writer code, which could uniquely identify the under writer
	 */
	private String underwriterCode;
	
	/**
	 * underwriter display name
	 */
	private String underwriterName;
	
    /**
     * billed indicated whether the TimeSheet is billed or not.
     */
    private Boolean billed;
    
    private BillingStatus billingStatus;

    /**
     * dateWorked indicates the date worked.
     */
    private DateDTO dateWorked;

    /**
     * employeeID indicates the ID of the employee associated with the
     * TimeSheet. employeeID type (open issue) needs to be determined.
     */
    private Long employeeID;

    /**
     * employeeName indicates the Name of the employee associated with the
     * TimeSheet. 
     */
    private String employeeName;
    
    /**
     * lastModified indicates the last time the TimeSheet is modified.
     */
    private DateDTO lastModified;

    /**
     * timeSheetHours indicates the hours worked.
     */
    private Double timeSheetHours;

    /**
     * The following fields are added as per the new requirement for for
     * TimeSheetDTO (from CustomerIO) 09-09-03
     */
    private Long timeSheetID;

    
    public String getUnderwriterCode() {
		return underwriterCode;
	}

	public void setUnderwriterCode(String underwriterCode) {
		this.underwriterCode = underwriterCode;
	}
	
	

	public String getUnderwriterName()
	{
		return underwriterName;
	}

	public void setUnderwriterName(String underwriterName)
	{
		this.underwriterName = underwriterName;
	}

	public Boolean getBilled()
	{
		return billed;
	}

	/**
     * Getter for Time Sheet billed status
     * 
     * @return a (Boolean) value indicating the billed status
     */
    public Boolean isBilled()
    {
        return billed;
    }

    /**
     * Setter for Time Sheet billed status
     * 
     * @param billed
     *            a New (Boolean) value indicating billed status
     */
    public void setBilled(Boolean billed)
    {
        this.billed = billed;
    }

    /**
     * Getter for date worked.
     * 
     * @return a value of date worked.
     */
    public DateDTO getDateWorked()
    {
        return dateWorked;
    }
    
    /**
     * 
     * @return
     */
	public Date getDateWorkedAsDate()
	{
		return DateDTO.toDate(dateWorked);
	}

	/**
	 * 
	 * @param dateWorked
	 */
	public void setDateWorkedAsDate(Date dateWorked)
	{
		this.dateWorked = DateDTO.dateToDateDTO(dateWorked);
	}

    /**
     * Setter for date worked.
     * 
     * @param dateWorked
     *            a New value of date worked.
     */
    public void setDateWorked(DateDTO dateWorked)
    {
        this.dateWorked = dateWorked;
    }

    /**
     * Getter for employeeID
     * 
     * @return value of employeeID
     */
    public Long getEmployeeID()
    {
        return employeeID;
    }

    /**
     * Setter for employeeID
     * 
     * @param employeeID
     *            a New value of employeeID
     */
    public void setEmployeeID(Long employeeID)
    {
        this.employeeID = employeeID;
    }
    
    

    public String getEmployeeName()
	{
		return employeeName;
	}

	public void setEmployeeName(String employeeName)
	{
		this.employeeName = employeeName;
	}

	/**
     * Getter for last modified date.
     * 
     * @return a value of last modified date.
     */
    public DateDTO getLastModified()
    {
        return lastModified;
    }

    /**
     * Setter for last modified date.
     * 
     * @param lastModified
     *            a New value of last modified date.
     */
    public void setLastModified(DateDTO lastModified)
    {
        this.lastModified = lastModified;
    }

    /**
     * Getter for regular hours worked
     * 
     * @return a value of regular hours worked
     */
    public Double getTimeSheetHours()
    {
        return timeSheetHours;
    }

    /**
     * Setter for regular hours worked
     * 
     * @param regularHours
     *            a value of the regular hours worked
     */
    public void setTimeSheetHours(Double timeSheetHours)
    {
        this.timeSheetHours = timeSheetHours;
    }

    public Long getTimeSheetID()
    {
        return timeSheetID;
    }

    public void setTimeSheetID(Long timeSheetID)
    {
        this.timeSheetID = timeSheetID;
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
	
    public String toString()
    {
        StringBuffer sb = new StringBuffer("[TIMESHEET DTO id=");
        sb.append(String.valueOf(timeSheetID));
        sb.append(" customerID:");
        sb.append(" employeeID:");
        sb.append(String.valueOf(employeeID));
        sb.append(" employeeName:");
        sb.append(" dateWorked:");
        if (dateWorked != null)
        {
            sb.append(dateWorked.toString());
        }
        sb.append(" hours:");
        sb.append(String.valueOf(timeSheetHours));
        sb.append(" billed:");
        sb.append(String.valueOf(billed));
        sb.append("]");
        return sb.toString();

    }

    public boolean equals(Object x)
    {
        if ((x != null) && (this.getClass() == x.getClass()))
        {
            TimeSheetDTO ts = (TimeSheetDTO) x;

            return ((this.isBilled().booleanValue() == ts.isBilled().booleanValue())
                    && (this.getEmployeeID().doubleValue() == ts.getEmployeeID()
                    .doubleValue()));

        }
        return false;
    }

}