/**
 * @(#) BillingCalendarType.java
 */

package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

public class BillingCalendarType extends BaseEnumType
{
    public static final BillingCalendarType STANDARD = new BillingCalendarType(new Long(109001), "Standard", "S");

    private static final BillingCalendarType[] domain = { STANDARD };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public BillingCalendarType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public BillingCalendarType()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final BillingCalendarType instance(BillingCalendarType code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - BillingCalendarType
     * 
     * @return BillingCalendarType for the enumerated type
     */
    public static final BillingCalendarType decodeValue(Long codeID)
    {
        return (BillingCalendarType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - BillingCalendarType
     * 
     * @return BillingCalendarType for the enumerated type
     */
    public static final BillingCalendarType decodeValue(String codeValue)
    {
        return (BillingCalendarType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * 
     * @return BillingCalendarType for the enumerated type
     */
    public static final BillingCalendarType decodeLabel(String codeLabel)
    {
        return (BillingCalendarType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
