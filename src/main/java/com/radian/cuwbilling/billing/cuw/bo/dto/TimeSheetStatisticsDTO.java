package com.radian.cuwbilling.billing.cuw.bo.dto;

import com.radian.foundation.bo.dto.BaseDTO;

public class TimeSheetStatisticsDTO extends BaseDTO
{
	private boolean retVal;
	
	private String filename;
	
	private int totalTimesheet;
	
	private double totalHours;
	
	private int loadedTimesheet;
	
	private double hoursWorked;
	
	private int rejectedTimesheet;
	
	private double rejectedHours;

	public boolean isRetVal() {
		return retVal;
	}

	public void setRetVal(boolean retVal) {
		this.retVal = retVal;
	}

	public Integer getTotalTimesheet() {
		return totalTimesheet;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public void setTotalTimesheet(Integer totalTimesheet) {
		this.totalTimesheet = totalTimesheet;
	}

	public Double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Double totalHours) {
		this.totalHours = totalHours;
	}

	public Integer getLoadedTimesheet() {
		return loadedTimesheet;
	}

	public void setLoadedTimesheet(Integer loadedTimesheet) {
		this.loadedTimesheet = loadedTimesheet;
	}

	public Double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(Double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public Integer getRejectedTimesheet() {
		return rejectedTimesheet;
	}

	public void setRejectedTimesheet(Integer rejectedTimesheet) {
		this.rejectedTimesheet = rejectedTimesheet;
	}

	public Double getRejectedHours() {
		return rejectedHours;
	}

	public void setRejectedHours(Double rejectedHours) {
		this.rejectedHours = rejectedHours;
	}	
}
