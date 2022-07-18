/*
 * @(#)PaymentImportDTO.java
 *
 * @author James Carpenter
 */
package com.radian.cuwbilling.billing.cuw.bo.dto;

import com.radian.cuwbilling.billing.common.bo.codes.ARTransactionType;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.foundation.bo.dto.BaseDTO;

/**
 * Holds data for payment import from AR system
 */
public class ARPaymentImportDTO extends BaseDTO
{
    private String billingProfileNumber;

    private String invoiceNumber;

    private ARTransactionType transactionType;

    private String referenceNumber;

    private Double amount;

    private DateDTO depositDate;

    private String depositID;

    private DateDTO transactionDate;

    /**
     * @return amount
     */
    public Double getAmount()
    {
        return amount;
    }

    /**
     * @return billingProfileNumber
     */
    public String getBillingProfileNumber()
    {
        return billingProfileNumber;
    }

    /**
     * @return depositDate
     */
    public DateDTO getDepositDate()
    {
        return depositDate;
    }

    /**
     * @return depositID
     */
    public String getDepositID()
    {
        return depositID;
    }

    /**
     * @return invoiceNumber
     */
    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    /**
     * @return referenceNumber
     */
    public String getReferenceNumber()
    {
        return referenceNumber;
    }

    /**
     * @return transactionDate
     */
    public DateDTO getTransactionDate()
    {
        return transactionDate;
    }

    /**
     * @return transactionType
     */
    public ARTransactionType getTransactionType()
    {
        return transactionType;
    }

    /**
     * @param amount
     */
    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    /**
     * @param billingProfileNumber
     */
    public void setBillingProfileNumber(String billingProfileNumber)
    {
        this.billingProfileNumber = billingProfileNumber;
    }

    /**
     * @param depositDate
     */
    public void setDepositDate(DateDTO depositDate)
    {
        this.depositDate = depositDate;
    }

    /**
     * @param depositID
     */
    public void setDepositID(String depositID)
    {
        this.depositID = depositID;
    }

    /**
     * @param invoiceNumber
     */
    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * @param referenceNumber
     */
    public void setReferenceNumber(String referenceNumber)
    {
        this.referenceNumber = referenceNumber;
    }

    /**
     * @param transactionDate
     */
    public void setTransactionDate(DateDTO transactionDate)
    {
        this.transactionDate = transactionDate;
    }

    /**
     * @param transactionType
     */
    public void setTransactionType(ARTransactionType transactionType)
    {
        this.transactionType = transactionType;
    }

    /**
     * Returns a String object representing the a composite of the objects
     * attribute values
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append("BillingProfileNumber=");
        if (this.getBillingProfileNumber() != null)
        {
            sb.append(this.getBillingProfileNumber());
        } else
        {
            sb.append("");
        }

        sb.append("; InvoiceNumber=");
        if (this.getInvoiceNumber() != null)
        {
            sb.append(this.getInvoiceNumber());
        } else
        {
            sb.append("");
        }

        sb.append("; TransactionType=");
        if (this.getTransactionType() != null)
        {
            sb.append(this.getTransactionType().getValue());
        } else
        {
            sb.append("");
        }

        sb.append("; ReferenceNumber=");
        if (this.getReferenceNumber() != null)
        {
            sb.append(this.getReferenceNumber());
        } else
        {
            sb.append("");
        }

        sb.append("; Amount=");
        if (this.getAmount() != null)
        {
            sb.append(this.getAmount().toString());
        } else
        {
            sb.append("");
        }

        sb.append("; DepositDate=");
        if (this.getDepositDate() != null)
        {
            sb.append(this.getDepositDate().toString());
        } else
        {
            sb.append("");
        }

        sb.append("; DepositID=");
        if (this.getDepositID() != null)
        {
            sb.append(this.getDepositID());
        } else
        {
            sb.append("");
        }

        sb.append("; TransactionDate=");
        if (this.getTransactionDate() != null)
        {
            sb.append(this.getTransactionDate().toString());
        } else
        {
            sb.append("");
        }
        return sb.toString();
    }
}
