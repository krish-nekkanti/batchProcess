/**
 * @(#) TimeSheetEntry.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import java.util.Date;

import com.radian.cuwbilling.billing.common.bo.codes.BillingStatus;
import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.domain.Placement;

public interface TimeSheetEntry extends BaseDomainObject
{
    Date getDate();

    Double getHours();

    String getUnderwriterCode();

    BillingStatus getBillingStatus();

    void setDate(Date date);

    void setHours(Double hours);

    void setBillingStatus(BillingStatus status);

    void setUnderwriterCode(String underwriterCode);

    void setBDR(BillingDetailRecord record);

    BillingDetailRecord getBDR();
    
    public Boolean getFinalizedInd();
    
    public void setFinalizedInd(Boolean flag);
}
