package com.radian.cuwbilling.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is UnitOfMeasure. The enumerations of this type are not
 * currently known...using UNKNOWN value for now. Used in InvoiceItem.
 * 
 * @author Drea Leed
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */

public class UnitOfMeasure extends BaseEnumType
{

    /**
     * UnitOfMeasure: definition of possible values. What these values are is
     * not currently known;.
     */
    public static final UnitOfMeasure UNKNOWN = new UnitOfMeasure(new Long(4), "Unknown", "U");

    /**
     * System code domain
     */
    private static final UnitOfMeasure[] domain = { UNKNOWN };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public UnitOfMeasure(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public UnitOfMeasure()
    {
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - UnitOfMeasure
     * @return UnitOfMeasure for the enumerated type
     */
    public static final UnitOfMeasure decodeValue(Long codeID)
    {
        return (UnitOfMeasure) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - UnitOfMeasure
     * @return UnitOfMeasure for the enumerated type
     */
    public static final UnitOfMeasure decodeValue(String codeValue)
    {
        return (UnitOfMeasure) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return UnitOfMeasure for the enumerated type
     */
    public static final UnitOfMeasure decodeLabel(String codeLabel)
    {
        return (UnitOfMeasure) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
