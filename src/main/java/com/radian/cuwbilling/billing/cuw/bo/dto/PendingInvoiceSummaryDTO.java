/**
 * @(#) BillingExportPendingInvoiceSummaryDTO.java
 */

package com.radian.cuwbilling.billing.cuw.bo.dto;

import java.util.Collection;
import java.util.HashSet;

import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.foundation.bo.dto.BaseDTO;

public class PendingInvoiceSummaryDTO extends BaseDTO
{

    private DateDTO invoiceDate;

    private Double invoiceAmt; /* invoice amount */

    private String billingProfileNum;

    private String invoiceNo;

    private String entryType;

    private DateDTO invoiceDueDate;

    private Collection pendDistributions;

    private Long transactionID;

    public Long getTransactionID()
    {
        return this.transactionID;
    }

    public void setTransactionID(Long transactionID)
    {
        this.transactionID = transactionID;
    }

    public Double getInvoiceAmt()
    {
        return this.invoiceAmt;
    }

    public void setInvoiceAmt(Double invoiceAmt)
    {
        this.invoiceAmt = invoiceAmt;
    }

    public String getInvoiceNo()
    {
        return this.invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo)
    {
        this.invoiceNo = invoiceNo;
    }

    public Collection getPendDistributions()
    {
        return this.pendDistributions;
    }

    public void setPendDistributions(Collection pendDistributions)
    {
        this.pendDistributions = pendDistributions;
    }

    public DateDTO getInvoiceDate()
    {
        return this.invoiceDate;
    }

    public void setInvoiceDate(DateDTO invoiceDate)
    {
        this.invoiceDate = invoiceDate;
    }

    public DateDTO getInvoiceDueDate()
    {
        return this.invoiceDueDate;
    }

    public void setInvoiceDueDate(DateDTO invoiceDueDate)
    {
        this.invoiceDueDate = invoiceDueDate;
    }

    public String getBillingProfileNum()
    {
        return this.billingProfileNum;
    }

    public void setBillingProfileNum(String billingProfileNum)
    {
        this.billingProfileNum = billingProfileNum;
    }

    public void addPendDistribution(PendingDistSummaryDTO pendDistribution)
    {
        if (pendDistributions == null)
        {
            pendDistributions = new HashSet();
        }
        this.pendDistributions.add(pendDistribution);
    }

    public String getEntryType()
    {
        return this.entryType;
    }

    public void setEntryType(String entryType)
    {
        this.entryType = entryType;
    }

}
