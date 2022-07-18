/**
 * @(#) BillingPeriodType.java
 */

package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

public class ARTransactionType extends BaseEnumType
{
    public static final ARTransactionType REFUND = new ARTransactionType(new Long(214001), "Refund", "R");

    public static final ARTransactionType PAYMENT = new ARTransactionType(new Long(214002), "Payment", "R");

    public static final ARTransactionType NSF = new ARTransactionType(new Long(214003), "NSF", "R");

    public static final ARTransactionType UNPOST = new ARTransactionType(new Long(214004), "Unpost", "R");

    public static final ARTransactionType VOIDED_REFUND = new ARTransactionType(new Long(214005), "Voided Refund", "V");

    private static final ARTransactionType[] domain = { REFUND, PAYMENT, NSF, UNPOST, VOIDED_REFUND };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public ARTransactionType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public ARTransactionType()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final ARTransactionType instance(ARTransactionType code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - BillingPeriodType
     * 
     * @return BillingPeriodType for the enumerated type
     */
    public static final ARTransactionType decodeValue(Long codeID)
    {
        return (ARTransactionType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - BillingPeriodType
     * 
     * @return BillingPeriodType for the enumerated type
     */
    public static final ARTransactionType decodeValue(String codeValue)
    {
        return (ARTransactionType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * 
     * @return BillingPeriodType for the enumerated type
     */
    public static final ARTransactionType decodeLabel(String codeLabel)
    {
        return (ARTransactionType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
