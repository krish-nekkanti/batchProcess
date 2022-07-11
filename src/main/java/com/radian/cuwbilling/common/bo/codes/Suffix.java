package com.radian.cuwbilling.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is Salutation, which is a domain of values to describe
 * the possible types of personal salutations. Salutation usually pertains to
 * borrowers, but can also pertain to any persion in which the system is
 * interested.
 * 
 * @author MSM
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */
public class Suffix extends BaseEnumType
{

    /**
     * Salutation: definition of possible values
     */
    public static final Suffix SR = new Suffix(new Long(1), "SR", "SR");

    public static final Suffix JR = new Suffix(new Long(2), "JR", "JR");

    public static final Suffix II = new Suffix(new Long(3), "II", "II");

    public static final Suffix III = new Suffix(new Long(4), "III", "III");

    public static final Suffix IV = new Suffix(new Long(5), "IV", "IV");

    public static final Suffix V = new Suffix(new Long(6), "V", "V");

    /**
     * System code domain
     */
    private static final Suffix[] domain = { SR, JR, II, III, IV, V };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public Suffix(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public Suffix()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final Suffix instance(Suffix code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - Salutation
     * @return Salutation for the enumerated type
     */
    public static final Suffix decodeValue(Long codeID)
    {
        return (Suffix) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - Salutation
     * @return Salutation for the enumerated type
     */
    public static final Suffix decodeValue(String codeValue)
    {
        return (Suffix) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return Salutation for the enumerated type
     */
    public static final Suffix decodeLabel(String codeLabel)
    {
        return (Suffix) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
