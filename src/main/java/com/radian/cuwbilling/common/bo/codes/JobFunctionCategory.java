package com.radian.cuwbilling.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 *
 * The enumerated type is JobFunctionCategory, which is a domain of values to
 * describe the possible Job Function chosen.
 *
 * @author Drea Leed
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */
public class JobFunctionCategory extends BaseEnumType
{

    /**
     * JobFunctionCategory: definition of possible values
     */
    public static final JobFunctionCategory UNDERWRITER = new JobFunctionCategory(new Long(118001), "Underwriting", "U");

    public static final JobFunctionCategory OTHER = new JobFunctionCategory(new Long(118002), "Other", "O");

    // not in db
    public static final JobFunctionCategory SALES = new JobFunctionCategory(new Long(2), "Sales", "S");

    public static final JobFunctionCategory RISK_MANAGEMENT = new JobFunctionCategory(new Long(3), "Risk Management", "R");

    /**
     * System code domain
     */
    private static final JobFunctionCategory[] domain = { UNDERWRITER, OTHER };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    private JobFunctionCategory(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    /**
     * Gets an instance of the code
     *
     * @param code
     * @return
     */
    public static final JobFunctionCategory instance(JobFunctionCategory code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     *
     * @param codeValue
     *            Enumerated type key - JobFunctionCategory
     * @return JobFunctionCategory for the enumerated type
     */
    public static final JobFunctionCategory decodeValue(Long codeID)
    {
        return (JobFunctionCategory) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     *
     * @param codeValue
     *            Enumerated type code value - JobFunctionCategory
     * @return JobFunctionCategory for the enumerated type
     */
    public static final JobFunctionCategory decodeValue(String codeValue)
    {
        return (JobFunctionCategory) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     *
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return JobFunctionCategory for the enumerated type
     */
    public static final JobFunctionCategory decodeLabel(String codeLabel)
    {
        return (JobFunctionCategory) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
