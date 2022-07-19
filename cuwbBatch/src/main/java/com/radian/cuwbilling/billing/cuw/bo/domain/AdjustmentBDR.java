/**
 * @(#) OvertimeBDR.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

public interface AdjustmentBDR extends BillingDetailRecord
{
    String getInternalNote();

    void setInternalNote(String note);
}
