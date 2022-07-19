package com.radian.cuwbilling.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is ActivationStatus, which is a domain of values to
 * describe the possible Activation States. Activation State is used across the
 * customer domain for customer and branch values.
 * 
 * @author MSM
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */
public class ActivationStatus extends BaseEnumType
{

    /**
     * ActivationStatus: definition of possible values
     */
    public static final ActivationStatus ACTIVE = new ActivationStatus(new Long(2001), "Active", "A");

    public static final ActivationStatus INACTIVE = new ActivationStatus(new Long(2002), "Inactive", "I");

    /**
     * System code domain
     */
    private static final ActivationStatus[] domain = { ACTIVE, INACTIVE };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public ActivationStatus(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public ActivationStatus()
    {
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - ActivationStatus
     * @return ActivationStatus for the enumerated type
     */
    public static final ActivationStatus decodeValue(Long codeID)
    {
        return (ActivationStatus) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - ActivationStatus
     * @return ActivationStatus for the enumerated type
     */
    public static final ActivationStatus decodeValue(String codeValue)
    {
        return (ActivationStatus) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return ActivationStatus for the enumerated type
     */
    public static final ActivationStatus decodeLabel(String codeLabel)
    {
        return (ActivationStatus) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
