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
public class Salutation extends BaseEnumType
{

    /**
     * Salutation: definition of possible values
     */
    public static final Salutation MR = new Salutation(new Long(187001), "Mr.", "M");

    public static final Salutation MS = new Salutation(new Long(187003), "Ms.", "S");

    public static final Salutation MRS = new Salutation(new Long(187002), "Mrs.", "R");

    public static final Salutation DR = new Salutation(new Long(187004), "Dr.", "D");

    /**
     * System code domain
     */
    private static final Salutation[] domain = { MR, MS, MRS, DR };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public Salutation(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public Salutation()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final Salutation instance(Salutation code)
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
    public static final Salutation decodeValue(Long codeID)
    {
        return (Salutation) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - Salutation
     * @return Salutation for the enumerated type
     */
    public static final Salutation decodeValue(String codeValue)
    {
        return (Salutation) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return Salutation for the enumerated type
     */
    public static final Salutation decodeLabel(String codeLabel)
    {
        return (Salutation) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
