/*
 * @(#)BillingImportAdministration.java
 */
package com.radian.cuwbilling.billing.cuw.bs;

import java.util.Collection;

/**
 * Provides business methods for administering import of billing information
 * 
 * @author James Carpenter
 */
public interface BillingImportAdministration
{
    /**
     * Receives a Collection of ARPaymentImportDTO's and adds them to the
     * appropriate invoice
     * 
     * @param batchNumber
     *            Identifier for import batch
     * @param payments
     *            Collection of ARPaymentImportDTO's
     * 
     * @throws CUWBillingException
     */
    public void importPayments(String batchNumber, Collection payments) throws CUWBillingException;

    /**
     * Take information about an I/O initiated payment import attempt and
     * records it in the import/export meassage log
     * 
     * @param batchNumber
     *            Identifies the import attempt
     * @param successFlag
     *            Determines success/failure of the attempt
     * @param description
     *            Message describing the detail of the outcome of the import
     *            attempt
     * @throws CUWBillingException
     */
    public void importPayments(String batchNumber, Boolean successFlag, String description) throws CUWBillingException;

    public void processPaymentImport() throws CUWBillingException;
}
