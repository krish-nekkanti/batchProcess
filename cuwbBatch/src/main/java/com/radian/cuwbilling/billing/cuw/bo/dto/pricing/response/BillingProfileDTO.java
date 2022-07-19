/*
 * BillingProfileDTO.java
 *
 * Created on July 24, 2003, 1:48 PM
 */

package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;

import java.util.Collection;

import com.radian.cuwbilling.common.bo.dto.AddressDTO;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.cuwbilling.common.bo.dto.PhoneDTO;
import com.radian.foundation.bo.dto.BaseDTO;

/**
 *
 * @author DLeed
 */
public class BillingProfileDTO extends BaseDTO
{
    private String billingProfileDisplayID;

    private Long billingProfileID;

    private DateDTO creationDate;
    
    private String CreatedByUser;

    private Long customerID;

    private String customerName;

    private String customerDisplayID;

    private String lastModifiedByUser;

    private DateDTO modifiedDate;

    private Boolean showDetailOnBill;
     
    private Boolean includeCSV;

   private Collection inclusions;

    private String activationStatusCd;
    
    private String activationStatus;

    private Boolean customerChase;
    
    private String billingCycleCode;
    
    private String lagDays;
    
    private String billToAttention;
    
    private String billToInvoiceName;
    
    private PhoneDTO billToPhone;
    
    private PhoneDTO billToFax;
    
    private Collection billToEmails;

    private String deliveryServiceType;
    
    private String deliveryServiceTypeCd;
    
    private AddressDTO billToLocation;
    
    private Boolean isUsedForServiceCenter;
    
    private Boolean isThirdParty;
    
    private Boolean isBillByLocation;
    
    private int branchCount;
    
    private Boolean defaultBillingProfile;
    
    private Collection overrideEmails;


    /** Creates a new instance of BillingProfileDTO */
    public BillingProfileDTO()
    {
    }

    public BillingProfileDTO(Long billingProfileID)
    {
        setBillingProfileID(billingProfileID);
    }

    /**
     * Getter for property billingProfileID.
     *
     * @return Value of property billingProfileID.
     */
    public Long getBillingProfileID()
    {
        return billingProfileID;
    }

	public void setBillToInvoiceName(String billToInvoiceName) {
		this.billToInvoiceName = billToInvoiceName;
	}

	/**
     * Setter for property billingProfileID.
     *
     * @param billingProfileID
     *            New value of property billingProfileID.
     */
    public void setIsUsedForServiceCenter(Boolean flag)
    {
        this.isUsedForServiceCenter = flag;
    }
    
    public Boolean getIsUsedForServiceCenter()
    {
        return isUsedForServiceCenter;
    }
    
    public void setIsBillByLocation(Boolean flag)
    {
        this.isBillByLocation = flag;
    }
    
    public Boolean getIsBillByLocation()
    {
        return isBillByLocation;
    }
    
    public void setIsThirdParty(Boolean flag)
    {
        this.isThirdParty = flag;
    }
    
    public Boolean getIsThirdParty()
    {
        return isThirdParty;
    }

    /**
	 * @return the deliveryServiceTypeCd
	 */
	public String getDeliveryServiceTypeCd() {
		return deliveryServiceTypeCd;
	}

	/**
	 * @param deliveryServiceTypeCd the deliveryServiceTypeCd to set
	 */
	public void setDeliveryServiceTypeCd(String deliveryServiceTypeCd) {
		this.deliveryServiceTypeCd = deliveryServiceTypeCd;
	}

	/**
     * Setter for property billingProfileID.
     *
     * @param billingProfileID
     *            New value of property billingProfileID.
     */
    public void setBillingProfileID(Long billingProfileID)
    {
        this.billingProfileID = billingProfileID;
    }
    

    public void setBillToLocation(AddressDTO loc)
    {
        this.billToLocation = loc;
    }
    
    public String getBillToAttention()
    {
    	return billToAttention;
    }
    
    public String getBillToInvoiceName()
    {
        return billToInvoiceName;
    }
    

    /**
	 * @return the billToFax
	 */
	public PhoneDTO getBillToFax() {
		return billToFax;
	}

	/**
	 * @param billToFax the billToFax to set
	 */
	public void setBillToFax(PhoneDTO billToFax) {
		this.billToFax = billToFax;
	}

	/**
	 * @return the billToPhone
	 */
	public PhoneDTO getBillToPhone() {
		return billToPhone;
	}

	/**
	 * @param billToPhone the billToPhone to set
	 */
	public void setBillToPhone(PhoneDTO billToPhone) {
		this.billToPhone = billToPhone;
	}

	public void setBillToEmails(Collection emails)
    {
        this.billToEmails = emails;
    }
	
	public void setOverrideEmails(Collection emails)
    {
        this.overrideEmails = emails;
    }

    /**
     * Setter for property billToInformation.
     *
     * @param billToInformation
     *            New value of property billToInformation.
     */
    public void setBillToAttention(String billToAttention)
    {
        this.billToAttention = billToAttention;
    }

    public String getDeliveryServiceType()
    {
        return deliveryServiceType;
    }

    /**
     * Setter for property deliveryService.
     *
     * @param deliveryService
     *            New value of property deliveryService
     */
    public void setDeliveryServiceType(String deliveryService)
    {
        this.deliveryServiceType = deliveryService;
    }
    
    public AddressDTO getBillToLocation()
    {
        return billToLocation;
    }
    
    public Collection getBillToEmails()
    {
        return billToEmails;
    }
    
    public Collection getOverrideEmails()
    {
        return overrideEmails;
    }
    
    /**
     * Getter for property billingCycleCode.
     * 
     * @return Value of property billingCycleCode.
     */
    public String getBillingCycleCode()
    {
        return billingCycleCode;
    }

    /**
     * Setter for property billingCycleCode.
     * 
     * @param billingCycleCode
     *            New value of property billingCycleCode.
     */
    public void setBillingCycleCode(String billingCycleCode)
    {
    	if(billingCycleCode != null && billingCycleCode.length() > 0)
    	{
    		this.billingCycleCode = billingCycleCode;
    	}
    	else
    	{
    		this.billingCycleCode = "Monthly";
    	}
    }

    /**
     * Getter for property creationDate.
     *
     * @return Value of property creationDate.
     */
    public DateDTO getCreationDate()
    {
        return creationDate;
    }

    /**
	 * @return the createdByUser
	 */
	public String getCreatedByUser() 
	{
		return CreatedByUser;
	}

	/**
	 * @param createdByUser the createdByUser to set
	 */
	public void setCreatedByUser(String createdByUser) {
		CreatedByUser = createdByUser;
	}

	/**
     * Setter for property creationDate.
     *
     * @param creationDate
     *            New value of property creationDate.
     */
    public void setCreationDate(DateDTO creationDate)
    {
        this.creationDate = creationDate;
    }

    /**
     * Getter for property customerID
     *
     * @return Value of property customerID
     */
    public Long getCustomerID()
    {
        return customerID;
    }

    /**
     * Setter for property customerID
     *
     * @param customerID
     *            New value of property customerID
     */
    public void setCustomerID(Long customerID)
    {
        this.customerID = customerID;
    }

    /**
     * Getter for property customerName
     *
     * @return Value of property customerName
     */
    public String getCustomerName()
    {
        return customerName;
    }

    /**
     * Setter for property customerName
     *
     * @param customerName
     *            New value of property customerName
     */
    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    /**
     * Getter for property customerDisplayID
     *
     * @return Value of property customerDisplayID
     */
    public String getCustomerDisplayID()
    {
        return customerDisplayID;
    }

    /**
     * Setter for property customerDisplayID
     *
     * @param customerDisplayID
     *            New value of property customerDisplayID
     */
    public void setCustomerDisplayID(String customerDisplayID)
    {
        this.customerDisplayID = customerDisplayID;
    }

    /**
     * Getter for property detailOnBillCode.
     *
     * @return Value of property detailOnBillCode.
     */
    public Boolean getShowDetailOnBill()
    {
        return showDetailOnBill;
    }
    
    public Boolean getIncludeCSV()
    {
        return includeCSV;
    }

   
    /**
	 * @return the inclusions
	 */
	public Collection getInclusions() 
	{
		return inclusions;
	}

	/**
	 * @param inclusions the inclusions to set
	 */
	public void setInclusions(Collection inclusions) 
	{
		this.inclusions = inclusions;
	}

    /**
     * Setter for property detailOnBillCode.
     *
     * @param detailOnBillCode
     *            New value of property detailOnBillCode.
     */
    public void setShowDetailOnBill(Boolean showDetailOnBill)
    {
        this.showDetailOnBill = showDetailOnBill;
    }
    
    public void setIncludeCSV(Boolean includeCSV)
    {
        this.includeCSV = includeCSV;
    }

    /**
     * Getter for property customerChase.
     *
     * @return Value of property customerChase.
     */
    public Boolean getCustomerChase()
    {
        return customerChase;
    }

    /**
     * Setter for property customeChase.
     *
     * @param perDiemFlag
     *            New value of property perDiemFlag.
     */
    public void setCustomerChase(Boolean customerChase)
    {
        this.customerChase = customerChase;
    }
    
    /**
     * Getter for property lastModifiedByUser.
     *
     * @return Value of property lastModifiedByUser.
     */
    public String getLastModifiedByUser()
    {
        return lastModifiedByUser;
    }

    /**
     * Setter for property lastModifiedByUser.
     *
     * @param lastModifiedByUser
     *            New value of property lastModifiedByUser.
     */
    public void setLastModifiedByUser(String lastModifiedByUser)
    {
        this.lastModifiedByUser = lastModifiedByUser;
    }

    /**
     * Getter for property modifiedDate.
     *
     * @return Value of property modifiedDate.
     */
    public DateDTO getModifiedDate()
    {
        return modifiedDate;
    }

    /**
     * Setter for property modifiedDate.
     *
     * @param modifiedDate
     *            New value of property modifiedDate.
     */
    public void setModifiedDate(DateDTO modifiedDate)
    {
        this.modifiedDate = modifiedDate;
    }

    public String getActivationStatus()
    {
        return activationStatus;
    }

    public void setActivationStatus(String status)
    {
        this.activationStatus = status;
    }

    
    /**
	 * @return the activationStatusCd
	 */
	public String getActivationStatusCd() {
		return activationStatusCd;
	}

	/**
	 * @param activationStatusCd the activationStatusCd to set
	 */
	public void setActivationStatusCd(String activationStatusCd) {
		this.activationStatusCd = activationStatusCd;
	}

	/**
     * Getter for property billingProfileDisplayID.
     *
     * @return Value of property billingProfileDisplayID.
     */
    public String getBillingProfileDisplayID()
    {
        return billingProfileDisplayID;
    }

    /**
     * Setter for property billingProfileDisplayID.
     *
     * @param billingProfileDisplayID
     *            New value of property billingProfileDisplayID.
     */
    public void setBillingProfileDisplayID(String billingProfileDisplayID)
    {
        this.billingProfileDisplayID = billingProfileDisplayID;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("\n[BILLING PROFILE DTO  ID=");
        sb.append(String.valueOf(billingProfileID));
        sb.append(" Display ID=\"");
        sb.append(billingProfileDisplayID);
        sb.append("\n    Show Detail on Bill=\"");
        sb.append(showDetailOnBill.toString());
        sb.append("\" Status=\"");
        sb.append(activationStatus);
        sb.append("  Customer ID= ");
        sb.append(String.valueOf(customerID));
        sb.append(":  Customer Display ID= ");
        sb.append(customerDisplayID);
        sb.append(": Customer Name= ");
        sb.append(customerName);
        sb.append(": Bill to Attention= ");
        sb.append(billToAttention);
        sb.append(": Bill to Invoice Name= ");
        sb.append(billToInvoiceName);
        sb.append(": Bill to Phone= ");
        sb.append(billToPhone);
        sb.append(": Bill to Fax= ");
        sb.append(billToFax);
        sb.append("\n\tBill To Information=");
        sb.append("\n\t)\n ]");

        return sb.toString();
    }
    
    /**
     * Getter for property getInvoiceAfterXDays.
     * 
     * @return Value of property getInvoiceAfterXDays.
     */
    public String getLagDays()
    {
        return lagDays;
    }

    /**
     * Setter for property getInvoiceAfterXDays.
     * 
     * @param getInvoiceAfterXDays
     *            New value of property getInvoiceAfterXDays.
     */
    public void setLagDays(String lagDays)
    {
        this.lagDays = lagDays;
    }

	/**
	 * @return the branchCourt
	 */
	public int getBranchCount() {
		return branchCount;
	}

	/**
	 * @param branchCourt the branchCourt to set
	 */
	public void setBranchCount(int branchCount) {
		this.branchCount = branchCount;
	}

	/**
	 * @return the defaultBillingProfile
	 */
	public Boolean getDefaultBillingProfile() {
		return defaultBillingProfile;
	}

	/**
	 * @param defaultBillingProfile the defaultBillingProfile to set
	 */
	public void setDefaultBillingProfile(Boolean defaultBillingProfile) {
		this.defaultBillingProfile = defaultBillingProfile;
	}
    
    
}