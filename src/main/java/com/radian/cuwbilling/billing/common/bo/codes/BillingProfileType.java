/**
 * @(#) BillingProfileType.java
 */

package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

public class BillingProfileType extends BaseEnumType
{
    public static final BillingProfileType CUW = new BillingProfileType(new Long(190001), "CUW", "C");

    private static final BillingProfileType[] domain = { CUW };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public BillingProfileType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public BillingProfileType()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final BillingProfileType instance(BillingProfileType code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - BillingProfileType
     * 
     * @return BillingProfileType for the enumerated type
     */
    public static final BillingProfileType decodeValue(Long codeID)
    {
        return (BillingProfileType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - BillingProfileType
     * 
     * @return BillingProfileType for the enumerated type
     */
    public static final BillingProfileType decodeValue(String codeValue)
    {
        return (BillingProfileType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * 
     * @return BillingProfileType for the enumerated type
     */
    public static final BillingProfileType decodeLabel(String codeLabel)
    {
        return (BillingProfileType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
