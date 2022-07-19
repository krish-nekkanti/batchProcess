/*
 * @(#)AROutboundSender.java
 *
 * @author James Carpenter
 */
package com.radian.cuwbilling.billing.cuw.bs.messaging;

import java.util.Collection;

import com.radian.cuwbilling.billing.common.bs.BillingException;
import com.radian.cuwbilling.billing.cuw.bo.dto.ARTransactionSummaryDTO;
import com.radian.cuwbilling.billing.cuw.bs.messaging.impl.AROutboundProxy;

/**
 * Provides business methods for sending AR data
 */
public abstract class AROutboundSender
{
    /**
     * Sends added or updated customer data to accounting system
     * 
     * @param billingProfileSummaryDTOs
     *            data for customers to be sent
     * @return guid for tracking delivery
     * @throws BillingException
     */
    public abstract boolean sendCustomerData(Collection billingProfileSummaryDTOs) throws BillingException;

    /**
     * Sends added or updated invoice data to accounting system
     * 
     * @param arTransactionSummary
     *            data for invoices to be sent
     * @return guid for tracking delivery
     * @throws BillingException
     */
    public abstract boolean sendARInvoiceData(ARTransactionSummaryDTO arTransactionSummary) throws BillingException;

    /**
     * Sends added or updated invoice data to accounting system
     * 
     * @param arTransactionSummary
     *            data for invoice adjustments to be sent
     * @return guid for tracking delivery
     * @throws BillingException
     */
    public abstract boolean sendARInvoiceAdjData(ARTransactionSummaryDTO arTransactionSummary) throws BillingException;

    /**
     * Factory method for returning AROutbound Senders
     * 
     * @return
     */
    public static AROutboundSender getSender()

    {
        return new AROutboundProxy();
    }
}
