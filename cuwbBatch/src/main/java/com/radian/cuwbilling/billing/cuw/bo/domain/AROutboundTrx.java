/**
 * @(#) InvoiceItem.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import java.util.Collection;

import com.radian.cuwbilling.billing.common.bo.codes.ARTransmissionStatus;
import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.domain.Money;

public interface AROutboundTrx extends BaseDomainObject
{
    void addSummaryCCAllocation(BDRCostCenterAllocation bdrCostCenterAllocation);

    void removeSummaryCCAllocation(BDRCostCenterAllocation bdrCostCenterAllocation);

    void addSummaryGLAllocation(BDRGLAccountAllocation bdrGLAllocation);

    void removeSummaryGLAllocation(BDRGLAccountAllocation bdrGLAllocation);

    Collection getSummaryCCAllocations();

    Collection getSummaryGLAllocations();

    AdjustmentBDR getAdjustment();

    Money getAmount();

    ARTransmissionStatus getARTransmissionStatus();

    String getBillingProfileNum();

    Invoice getInvoice();

    void setAdjustment(AdjustmentBDR adjustment);

    void setAmount(Money amount);

    void setARTransmissionStatus(ARTransmissionStatus status);

    void setBillingProfileNum(String billingProfileNum);

    void setInvoice(Invoice invoice);

    void setTrackingNumber(String trackingNumber);

    String getTrackingNumber();
}
