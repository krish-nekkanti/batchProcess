package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.request;

import com.radian.foundation.bo.dto.BaseDTO;

public class BillingProfileRequestDTO extends BaseDTO
{

    /** Creates a new instance of BillingProfileSummaryDTO */
    public BillingProfileRequestDTO()
    {
    	this.billingProfileDisplayID = "";
        this.customerDisplayID = "";
        this.customerName = "";
        this.billToName = "";
        this.activationStatus = "";
        this.lagDays = "";
    }

    public BillingProfileRequestDTO(String displayID)
    {
        setBillingProfileDisplayID(displayID);
    }

    /** Creates a new instance of BillingProfileSummaryDTO */
    public BillingProfileRequestDTO(String billingProfileDisplayID, 
    								String customerDisplayID, 
    								String customerName, 
    								String billToName,
    								String activationStatus,
    								String lagDays)
    {
        this.billingProfileDisplayID = billingProfileDisplayID;
        this.customerDisplayID = customerDisplayID;
        this.customerName = customerName;
        this.billToName = billToName;
        this.activationStatus = activationStatus;
        this.lagDays = lagDays;

    }



    private String billingProfileDisplayID;
    
    private String customerDisplayID;
    
    private String customerName;

    private String billToName;

    private String activationStatus;

    private String lagDays;

    /**
     * 
     * @return
     */
	public String getBillingProfileDisplayID() {
		return billingProfileDisplayID;
	}

	/**
	 * 
	 * @param billingProfileDisplayID
	 */
	public void setBillingProfileDisplayID(String billingProfileDisplayID) {
		this.billingProfileDisplayID = billingProfileDisplayID;
	}

	/**
	 * @return the activationStatus
	 */
	public String getActivationStatus() 
	{
		return activationStatus;
	}

	/**
	 * @param activationStatus the activationStatus to set
	 */
	public void setActivationStatus(String activationStatus) 
	{
		this.activationStatus = activationStatus;
	}

	/**
	 * 
	 * @return
	 */
	public String getBillToName() {
		return billToName;
	}

	/**
	 * 
	 * @param billToName
	 */
	public void setBillToName(String billToName) {
		this.billToName = billToName;
	}

	/**
	 * 
	 * @return
	 */
	public String getCustomerDisplayID() {
		return customerDisplayID;
	}
	
	/**
	 * 
	 * @param customerDisplayID
	 */
	public void setCustomerDisplayID(String customerDisplayID) {
		this.customerDisplayID = customerDisplayID;
	}

	/**
	 * 
	 * @return
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 
	 * @return
	 */
	public String getLagDays() {
		return lagDays;
	}

	/**
	 * 
	 * @param lagDays
	 */
	public void setLagDays(String lagDays) {
		this.lagDays = lagDays;
	}

}
