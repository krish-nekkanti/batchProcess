/**
 * @(#) BillingPeriodType.java
 */

package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

public class BillingPeriodType extends BaseEnumType
{
    public static final BillingPeriodType MONTHLY = new BillingPeriodType(new Long(189001), "MONTHLY", "M");

    private static final BillingPeriodType[] domain = { MONTHLY };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public BillingPeriodType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public BillingPeriodType()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final BillingPeriodType instance(BillingPeriodType code)
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
    public static final BillingPeriodType decodeValue(Long codeID)
    {
        return (BillingPeriodType) BaseEnumType.decodeValue(codeID, domainList.iterator());
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
    public static final BillingPeriodType decodeValue(String codeValue)
    {
        return (BillingPeriodType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
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
    public static final BillingPeriodType decodeLabel(String codeLabel)
    {
        return (BillingPeriodType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
