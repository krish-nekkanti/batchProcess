/*
 * BillingProfileSummaryDTO.java
 *
 * Created on July 24, 2003, 1:13 PM
 */

package com.radian.cuwbilling.billing.cuw.bo.dto;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.radian.cuwbilling.billing.common.bo.codes.BillingProfileStatus;
import com.radian.cuwbilling.common.bo.dto.AddressDTO;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.cuwbilling.common.bo.dto.MoneyDTO;
import com.radian.cuwbilling.common.bo.dto.PhoneDTO;
import com.radian.foundation.bo.dto.BaseDTO;

/**
 *
 * @author DLeed
 */
public class BillingProfileSummaryDTO extends BaseDTO
{

    /** Creates a new instance of BillingProfileSummaryDTO */
    public BillingProfileSummaryDTO()
    {
    }

    public BillingProfileSummaryDTO(Long ID)
    {
        setBillingProfileID(ID);
    }

    /** Creates a new instance of BillingProfileSummaryDTO */
    public BillingProfileSummaryDTO(Long billingProfileID, String billingProfileDisplayID, String statusCodeDesc, String customerDisplayID,
    		String customerName, String billToName, AddressDTO customerAddress, Date creationDate, Date modifiedDate)
    {
        this.billingProfileID = billingProfileID;
        this.billingProfileDisplayID = billingProfileDisplayID;
        this.statusCodeDesc = statusCodeDesc;
        this.customerAddress = customerAddress;
        this.customerDisplayID = customerDisplayID;
        this.customerName = customerName;
        this.billToName = billToName;
        this.creationDate = DateDTO.dateToDateDTO(creationDate);
        this.modifiedDate = DateDTO.dateToDateDTO(modifiedDate);
    }

    /* constructor explicitly used for customer data export */
    /** Creates a new instance of BillingProfileSummaryDTO */
    public BillingProfileSummaryDTO(Long billingProfileID, String billingProfileDisplayID, String statusCodeDesc, String customerDisplayID,
            String billToName, AddressDTO customerAddress, PhoneDTO phoneDTO, Date creationDate, Date modifiedDate)
    {
        this.billingProfileID = billingProfileID;
        this.billingProfileDisplayID = billingProfileDisplayID;
        this.statusCodeDesc = statusCodeDesc;
        this.customerAddress = customerAddress;
        this.customerDisplayID = customerDisplayID;
        this.billToName = billToName;
        this.creationDate = DateDTO.dateToDateDTO(creationDate);
        this.modifiedDate = DateDTO.dateToDateDTO(modifiedDate);
        this.phoneNumber = phoneDTO;
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
     * Getter for property customerAddress.
     *
     * @return Value of property customerAddress.
     */
    public AddressDTO getCustomerAddress()
    {
        return customerAddress;
    }

    /**
     * Setter for property customerAddress.
     *
     * @param customerAddress
     *            New value of property customerAddress.
     */
    public void setCustomerAddress(AddressDTO customerAddress)
    {
        this.customerAddress = customerAddress;

    }

    /**
     * Getter for property customerDisplayID.
     *
     * @return Value of property customerDisplayID.
     */
    public String getCustomerDisplayID()
    {
        return customerDisplayID;
    }

    /**
     * Setter for property customerDisplayID.
     *
     * @param customerDisplayID
     *            New value of property customerDisplayID.
     */
    public void setCustomerDisplayID(String customerDisplayID)
    {
        this.customerDisplayID = customerDisplayID;
    }
    
    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    /**
     * Getter for property customerID.
     *
     * @return Value of property customerID.
     */
    public Long getCustomerID()
    {
        return customerID;
    }

    /**
     * Setter for property customerID.
     *
     * @param customerID
     *            New value of property customerID.
     */
    public void setCustomerID(Long customerID)
    {
        this.customerID = customerID;
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


    /**
	 * @return the statusCodeDesc
	 */
	public String getStatusCodeDesc() 
	{
		return statusCodeDesc;
	}

	/**
	 * @param statusCodeDesc the statusCodeDesc to set
	 */
	public void setStatusCodeDesc(String statusCodeDesc) 
	{
		this.statusCodeDesc = statusCodeDesc;
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

    /**
     * Getter for property phoneNumber.
     *
     * @return Value of property phoneNumber.
     */
    public PhoneDTO getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Setter for property phoneNumber.
     *
     * @param phoneNumber
     *            New value of property phoneNumber.
     */
    public void setPhoneNumber(PhoneDTO phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getBillToName()
    {
        return (billToName != null ? billToName.trim() : "");
    }

    public void setBillToName(String name)
    {
        this.billToName = name;
    } 

    /**
     * Getter for property attentionToPerson.
     *
     * @return Value of property attentionToPerson.
     */

    public String getAttentionToPerson()
    {
        return attentionToPerson;
    }

    /**
     * Setter for property attentionToPerson.
     *
     * @param attentionToPerson
     *            New value of property attentionToPerson.
     */
    public void setAttentionToPerson(String attentionToPerson)
    {
        this.attentionToPerson = attentionToPerson;
    }

    /**
     * @return
     */
    public Long getOverrideLocationID()
    {
        return overrideLocationID;
    }

    /**
     * @param long1
     */
    public void setOverrideLocationID(Long long1)
    {
        overrideLocationID = long1;
    }

    private Date getCreationDateAsDate()
    {
        return DateDTO.toDate(this.creationDate);
    }

    public void setCreationDateAsDate(Date creationDate)
    {
        this.creationDate = DateDTO.dateToDateDTO(creationDate);
    }

    private Date getModifiedDateAsDate()
    {
        return DateDTO.toDate(this.modifiedDate);
    }

    public void setModifiedDateAsDate(Date modifiedDate)
    {
        this.modifiedDate = DateDTO.dateToDateDTO(modifiedDate);
    }


    /**
     * @return
     */
    public MoneyDTO getPaymentOnAccount()
    {
        return paymentOnAccount;
    }

    /**
     * @return
     */
    public MoneyDTO getTotalOutstandingAmount()
    {
        return totalOutstandingAmount;
    }

    /**
     * @param paymentOnAccount
     */
    public void setPaymentOnAccount(MoneyDTO paymentOnAccount)
    {
        this.paymentOnAccount = paymentOnAccount;
    }

    /**
     * @param totalOutstandingAmount
     */
    public void setTotalOutstandingAmount(MoneyDTO totalOutstandingAmount)
    {
        this.totalOutstandingAmount = totalOutstandingAmount;
    }


    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[BILLING PROFILE SUMMARY DTO id=");
        sb.append(billingProfileID);
        sb.append(" Display ID=\"");
        sb.append(billingProfileDisplayID);
        sb.append("\" billToName=\"");
        sb.append(billToName);
        sb.append(" Creation Date=\"");
        if (creationDate != null)
        {
            sb.append(creationDate.toString());
        }
        sb.append("\" customerAddress=");
        if (customerAddress != null)
        {
            sb.append("[ADDRESS DTO street1=\"");
            sb.append(customerAddress.getStreet1());
            sb.append("\" street2=\"");
            sb.append(customerAddress.getStreet2());
            sb.append("\" city=\"");
            sb.append(customerAddress.getCity());
            sb.append("\" state=\"");
            sb.append(customerAddress.getState().toString());
            sb.append("\" zip=\"");
            sb.append(customerAddress.getZipCode());
            sb.append("\"]");
        } else
        {
            sb.append("NULL");
        }

        sb.append(" Customer ID=");
        sb.append(" Customer Display ID=\"");
        sb.append(customerDisplayID);
        sb.append("\" Customer Name=\"");
        sb.append("\" Modified Date=\"");
        if (modifiedDate != null)
        {
            sb.append(modifiedDate.toString());
        }
        sb.append("\" Status code=");
        if (statusCodeDesc != null)
        {
            sb.append(statusCodeDesc);
        }
        sb.append("\" Paymnet On Account=");
        sb.append(paymentOnAccount.toString());
        sb.append("\" Total Outstanding Amount=");
        sb.append(totalOutstandingAmount.toString());
        sb.append(" ) ]");
        return sb.toString();
    }

    private Long billingProfileID;

    private String billingProfileDisplayID;

    private String billToName;

    private DateDTO creationDate;

    private AddressDTO customerAddress;

    private String customerDisplayID;

    private DateDTO modifiedDate;

    private MoneyDTO paymentOnAccount;

    private PhoneDTO phoneNumber;

    private String statusCodeDesc;

    private MoneyDTO totalOutstandingAmount;

    private String attentionToPerson;

    private Long customerID;
    
    private String customerName;

    private Long overrideLocationID;

}
