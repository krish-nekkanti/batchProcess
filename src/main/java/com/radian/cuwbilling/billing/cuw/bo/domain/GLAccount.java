/**
 * @(#) GLAccount.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;

public interface GLAccount extends BaseDomainObject
{

    String getAccountName();

    String getAccountNumber();

    void setAccountName(String accountName);

    void setAccountNumber(String accountNumber);

}
