/**
 * @(#) OvertimeBDR.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import java.util.Date;

import com.radian.cuwbilling.billing.common.bo.codes.ARTransactionType;
import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.domain.Money;

public interface ARTrx extends BaseDomainObject
{
    Date getDepositDate();

    String getDepositID();

    Invoice getInvoice();

    String getReferenceNumber();

    Money getAmount();

    void setAmount(Money amount);

    Date getTransactionDate();

    ARTransactionType getTransactionType();

    void setDepositDate(Date depositDate);

    void setDepositID(String depositID);

    void setInvoice(Invoice invoice);

    void setReferenceNumber(String referenceNumber);

    void setTransactionDate(Date transactionDate);

    void setTransactionType(ARTransactionType transactionType);

}
