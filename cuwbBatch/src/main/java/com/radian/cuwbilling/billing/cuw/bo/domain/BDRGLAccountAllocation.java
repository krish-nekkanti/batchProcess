/**
 * @(#) InvoiceItem.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.domain.Money;

public interface BDRGLAccountAllocation extends BaseDomainObject
{

    Money getAmount();

    GLAccount getGLAccount();

    void setAmount(Money amount);

    void setGLAccount(GLAccount glAccount);

    BillingDetailRecord getParentBDR();

    void setParentBDR(BillingDetailRecord bdr);

    AROutboundTrx getOutboundTrans();

    void setOutboundTrans(AROutboundTrx outboundTrans);
}
