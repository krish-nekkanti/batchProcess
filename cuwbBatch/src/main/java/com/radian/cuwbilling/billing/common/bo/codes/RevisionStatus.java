package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is RevisionStatus, which indicates the status of a given
 * invoice item.
 * 
 * @author
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */
public class RevisionStatus extends BaseEnumType
{

    /**
     * RevisionStatus: definition of possible values
     */
    public static final RevisionStatus REGENERATED = new RevisionStatus(new Long(209003), "Regenerated", "G");

    public static final RevisionStatus ORIGINAL = new RevisionStatus(new Long(209001), "Original", "");

    public static final RevisionStatus RESENT = new RevisionStatus(new Long(209002), "Resent", "R");

    /**
     * System code domain
     */
    private static final RevisionStatus[] domain = { REGENERATED, ORIGINAL, RESENT };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    private RevisionStatus(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public RevisionStatus()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final RevisionStatus instance(RevisionStatus code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - RevisionStatus
     * @return RevisionStatus for the enumerated type
     */
    public static final RevisionStatus decodeValue(Long codeID)
    {
        return (RevisionStatus) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - RevisionStatus
     * @return RevisionStatus for the enumerated type
     */
    public static final RevisionStatus decodeValue(String codeValue)
    {
        return (RevisionStatus) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return RevisionStatus for the enumerated type
     */
    public static final RevisionStatus decodeLabel(String codeLabel)
    {
        return (RevisionStatus) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
