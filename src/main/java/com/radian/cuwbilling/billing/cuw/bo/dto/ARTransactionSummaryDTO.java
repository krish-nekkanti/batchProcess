/**
 * @(#) BillingARTrasactionSummaryDTO.java
 */

package com.radian.cuwbilling.billing.cuw.bo.dto;

import java.util.Collection;
import java.util.HashSet;

import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.foundation.bo.dto.BaseDTO;

public class ARTransactionSummaryDTO extends BaseDTO
{

    private DateDTO transactionDate; /* need to find */
    
    private DateDTO accountingDate; /* need to find */

    private Double controlAmt; /* sum of all invoice amounts */

    private String groupId; /* need to find */

    private Collection pendingInvoices;

    public void setPendingInvoices(Collection pendingInvoices)
    {
        this.pendingInvoices = pendingInvoices;
    }

    public Collection getPendingInvoices()
    {
        return this.pendingInvoices;
    }

    public void setTransactionDate(DateDTO transactionDate)
    {
        this.transactionDate = transactionDate;
    }

    public DateDTO getTransactionDate()
    {
        return this.transactionDate;
    }
    
    public void setAccountingDate(DateDTO accountingDate)
    {
        this.accountingDate = accountingDate;
    }

    public DateDTO getAccountingDate()
    {
        return this.accountingDate;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupId()
    {
        return this.groupId;
    }

    public void setControlAmt(Double controlAmt)
    {
        this.controlAmt = controlAmt;
    }

    public Double getControlAmt()
    {
        return this.controlAmt;
    }

    public void addPendingInvoice(PendingInvoiceSummaryDTO pendingInvoice)
    {
        if (pendingInvoices == null)
        {
            pendingInvoices = new HashSet();
        }
        this.pendingInvoices.add(pendingInvoice);
    }

}
