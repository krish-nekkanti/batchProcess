/**
 * @(#) InvoiceItem.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import com.radian.cuwbilling.common.bo.codes.USState;
import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.domain.Money;

public interface BDRCostCenterAllocation extends BaseDomainObject
{
    Money getAmount();

    String getCostCenterNumber();
    
    String getCostCenterState();

    void setAmount(Money amount);

    void setCostCenterNumber(String costCenterCode);
    
    void setCostCenterState(String costCenterState);

    BillingDetailRecord getParentBDR();

    void setParentBDR(BillingDetailRecord bdr);

    AROutboundTrx getOutboundTrans();

    void setOutboundTrans(AROutboundTrx outboundTrans);

}
