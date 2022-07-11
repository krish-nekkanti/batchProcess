package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is BillingProfileStatus, which is a domain of values to
 * describe the possible Activation States. Activation State is used across the
 * customer domain for customer and branch values.
 * 
 * @author MSM
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */
public class BillingProfileStatus extends BaseEnumType
{

    /**
     * BillingProfileStatus: definition of possible values
     */
    public static final BillingProfileStatus ACTIVE = new BillingProfileStatus(new Long(12001), "Active", "A");

    public static final BillingProfileStatus INACTIVE = new BillingProfileStatus(new Long(12002), "Inactive", "I");

    public static final BillingProfileStatus PENDING = new BillingProfileStatus(new Long(12003), "Pending", "P");

    /**
     * System code domain
     */
    private static final BillingProfileStatus[] domain = { ACTIVE, INACTIVE, PENDING };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public BillingProfileStatus(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public BillingProfileStatus()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final BillingProfileStatus instance(BillingProfileStatus code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - BillingProfileStatus
     * @return BillingProfileStatus for the enumerated type
     */
    public static final BillingProfileStatus decodeValue(Long codeID)
    {
        return (BillingProfileStatus) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - BillingProfileStatus
     * @return BillingProfileStatus for the enumerated type
     */
    public static final BillingProfileStatus decodeValue(String codeValue)
    {
        return (BillingProfileStatus) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return BillingProfileStatus for the enumerated type
     */
    public static final BillingProfileStatus decodeLabel(String codeLabel)
    {
        return (BillingProfileStatus) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
