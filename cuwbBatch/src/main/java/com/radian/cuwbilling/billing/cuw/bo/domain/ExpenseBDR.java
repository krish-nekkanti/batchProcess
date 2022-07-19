/**
 * @(#) ExpenseItem.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import java.util.Collection;

import com.radian.cuwbilling.common.bo.domain.Placement;

public interface ExpenseBDR extends AdjustmentBDR
{
    void addExpense(Expense expense);

    Collection getExpenses();

    void removeExpense(Expense expense);

    CUWBillingPeriod getBillingPeriod();

    void setBillingPeriod(CUWBillingPeriod billingPeriod);

}
