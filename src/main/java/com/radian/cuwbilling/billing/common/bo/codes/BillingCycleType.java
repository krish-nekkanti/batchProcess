/**
 * @(#) BillingCycleType.java
 */

package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

public class BillingCycleType extends BaseEnumType
{
    public static final BillingCycleType DAILY = new BillingCycleType(new Long(11001), "Daily", "D");

    // This is commented to synchronize with DB.
    // TODO: If the weekly billing cylcle is added to the DB then uncomment this
    // and make appropriate changes to the domain array.
    // public static final BillingCycleType WEEKLY = new BillingCycleType(new
    // Long(2), "Weekly","W");

    public static final BillingCycleType MONTHLY = new BillingCycleType(new Long(11002), "Monthly", "M");

    public static final BillingCycleType QUARTERLY = new BillingCycleType(new Long(11003), "Quarterly", "Q");

    // private static final BillingCycleType[] domain =
    // {DAILY,WEEKLY,QUARTERLY};
    private static final BillingCycleType[] domain = { DAILY, MONTHLY, QUARTERLY };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public BillingCycleType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public BillingCycleType()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final BillingCycleType instance(BillingCycleType code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - BillingCycleType
     * 
     * @return BillingCycleType for the enumerated type
     */
    public static final BillingCycleType decodeValue(Long codeID)
    {
        return (BillingCycleType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - BillingCycleType
     * 
     * @return BillingCycleType for the enumerated type
     */
    public static final BillingCycleType decodeValue(String codeValue)
    {
        return (BillingCycleType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * 
     * @return BillingCycleType for the enumerated type
     */
    public static final BillingCycleType decodeLabel(String codeLabel)
    {
        return (BillingCycleType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
