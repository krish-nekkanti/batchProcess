/**
 * @(#) ExpenseItem.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import com.radian.cuwbilling.billing.common.bo.codes.ExpenseType;
import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.domain.Money;

public interface Expense extends BaseDomainObject
{
    ExpenseType getExpenseType();

    Money getAmount();

    void setAmount(Money amount);

    void setExpenseType(ExpenseType type);

    String getOtherDescription();

    void setOtherDescription(String description);

    BillingDetailRecord getParentBDR();

    void setParentBDR(BillingDetailRecord bdr);

}
