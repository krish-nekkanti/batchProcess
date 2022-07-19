/**
 * @(#) InclusionType.java
 */

package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

public class InclusionType extends BaseEnumType
{
    public static final InclusionType PER_DIEM = new InclusionType(new Long(191001), "Per Diem", "PD");

    public static final InclusionType PER_LOAN = new InclusionType(new Long(191002), "Per Loan", "PL");

    public static final InclusionType OVERTIME_EXPENSES = new InclusionType(new Long(191003), "Overtime & Expenses", "OE");

    public static final InclusionType EUNDERWRITING = new InclusionType(new Long(191004), "eUnderwriting", "EU");

    public static final InclusionType CHASE = new InclusionType(new Long(191005), "Chase", "C");

    public static final InclusionType CHASE_PER_DIEM = new InclusionType(new Long(191006), "Chase Per Diem", "CPD");

    public static final InclusionType CHASE_PER_LOAN = new InclusionType(new Long(191007), "Chase Per Loan", "CPL");

    public static final InclusionType OTHER_PER_DIEM = new InclusionType(new Long(191008), "Other Per Diem", "OPD");

    public static final InclusionType OVERTIME = new InclusionType(new Long(191009), "Overtime", "OT");

    public static final InclusionType EXPENSES = new InclusionType(new Long(191010), "Expenses", "E");

    public static final InclusionType MISCELLANEOUS = new InclusionType(new Long(191011), "Miscellaneous", "M");

    private static final InclusionType[] domain = { PER_DIEM, PER_LOAN, OVERTIME_EXPENSES, EUNDERWRITING, CHASE, CHASE_PER_DIEM, CHASE_PER_LOAN,
            OTHER_PER_DIEM, OVERTIME, EXPENSES, MISCELLANEOUS };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    private InclusionType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    private InclusionType()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final InclusionType instance(InclusionType code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - InclusionType
     * 
     * @return InclusionType for the enumerated type
     */
    public static final InclusionType decodeValue(Long codeID)
    {
        return (InclusionType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - InclusionType
     * 
     * @return InclusionType for the enumerated type
     */
    public static final InclusionType decodeValue(String codeValue)
    {
        return (InclusionType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * 
     * @return InclusionType for the enumerated type
     */
    public static final InclusionType decodeLabel(String codeLabel)
    {
        return (InclusionType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
