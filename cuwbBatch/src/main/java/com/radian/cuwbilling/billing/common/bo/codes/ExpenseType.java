package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is ExpenseType, which is a domain of values to describe
 * the possible Expenses.
 * 
 * @author Drea Leed
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */
public class ExpenseType extends BaseEnumType
{

    /**
     * ExpenseType: definition of possible values
     */
    public static final ExpenseType AIR = new ExpenseType(new Long(218001), "Air", "A");

    public static final ExpenseType GROUND = new ExpenseType(new Long(218002), "Ground Transportation", "G");

    public static final ExpenseType HOTEL = new ExpenseType(new Long(218003), "Hotel", "H");

    public static final ExpenseType MEALS = new ExpenseType(new Long(218004), "Meals", "M");

    public static final ExpenseType OTHER = new ExpenseType(new Long(218005), "Other", "O");

    /**
     * System code domain
     */
    private static final ExpenseType[] domain = { HOTEL, GROUND, AIR, MEALS, OTHER };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    private ExpenseType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    private ExpenseType()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final ExpenseType instance(ExpenseType code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - ExpenseType
     * @return ExpenseType for the enumerated type
     */
    public static final ExpenseType decodeValue(Long codeID)
    {
        return (ExpenseType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - ExpenseType
     * @return ExpenseType for the enumerated type
     */
    public static final ExpenseType decodeValue(String codeValue)
    {
        return (ExpenseType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return ExpenseType for the enumerated type
     */
    public static final ExpenseType decodeLabel(String codeLabel)
    {
        return (ExpenseType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
