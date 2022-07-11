package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is BDRType, which is a domain of values to describe the
 * possible Types of invoice items.
 * 
 * @author Drea Leed
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */
public class BDRType extends BaseEnumType
{

    /**
     * BDRType: definition of possible values
     */
    public static final BDRType PER_LOAN = new BDRType(new Long(216001), "Per Loan", "PL");

    public static final BDRType PER_DIEM = new BDRType(new Long(216002), "Per Diem", "PD");

    public static final BDRType OVERTIME = new BDRType(new Long(216003), "Overtime", "OV");

    public static final BDRType EXPENSE = new BDRType(new Long(216004), "Expense", "Expense");

    public static final BDRType EXPENSE_ADJUSTMENT = new BDRType(new Long(216005), "Expense Adjustment", "Adjustment");

    public static final BDRType MISC_ADJUSTMENT = new BDRType(new Long(216006), "Miscellaneous", "Misc. Item");

    public static final BDRType INVOICE_ADJUSTMENT = new BDRType(new Long(216007), "Adjustment", "Adjustment");

    /**
     * System code domain
     */
    private static final BDRType[] domain = { PER_LOAN, PER_DIEM, OVERTIME, EXPENSE, EXPENSE_ADJUSTMENT, MISC_ADJUSTMENT, INVOICE_ADJUSTMENT };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    private BDRType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    private BDRType()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final BDRType instance(BDRType code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - BDRType
     * @return BDRType for the enumerated type
     */
    public static final BDRType decodeValue(Long codeID)
    {
        return (BDRType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - BDRType
     * @return BDRType for the enumerated type
     */
    public static final BDRType decodeValue(String codeValue)
    {
        return (BDRType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return BDRType for the enumerated type
     */
    public static final BDRType decodeLabel(String codeLabel)
    {
        return (BDRType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
