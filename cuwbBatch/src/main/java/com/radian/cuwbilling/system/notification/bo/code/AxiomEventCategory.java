/**
 * @(#) AxiomEventCategory.java
 */

package com.radian.cuwbilling.system.notification.bo.code;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * Type-safe enum for the Axiom Event Category. Axtiom Event Category is an
 * enumeration of the evenet types that can happen with Axiom entities - they
 * can be activated, added, deleted or updated
 * 
 * @author Valentinas Jurkevicius
 */
public class AxiomEventCategory extends BaseEnumType
{
    /**
     * Event category that indicated that underlying entity was activated
     */
    public static AxiomEventCategory ENTITY_ACTVATED = new AxiomEventCategory(new Long(46002), "Activated", "T");

    /**
     * Event category that indicated that underlying entity was added
     */
    public static AxiomEventCategory ENTITY_ADDED = new AxiomEventCategory(new Long(46001), "Added", "A");

    /**
     * Event category that indicated that underlying entity was deactivated
     */
    public static AxiomEventCategory ENTITY_DEACTVATED = new AxiomEventCategory(new Long(46003), "Deactivated", "E");

    /**
     * Event category that indicated that underlying entity was updated
     */
    public static AxiomEventCategory ENTITY_UPDATED = new AxiomEventCategory(new Long(46004), "Updated", "U");

    /**
     * Event category that indicated that process running has failed
     */
    public static AxiomEventCategory PROCESS_FAILED = new AxiomEventCategory(new Long(46005), "Process Failed", "F");

    /**
     * Event category that indicated that process running has succeeded
     */
    public static AxiomEventCategory PROCESS_SUCCEEDED = new AxiomEventCategory(new Long(46006), "Process Succeeded", "S");

    /**
     * Event category that indicates that banch was transfered. Temporary not
     * clean solution. TBD:: per entity categories
     */
    public static AxiomEventCategory ENTITY_TRANSFERRED = new AxiomEventCategory(new Long(46007), "Transferred", "R");

    private static final AxiomEventCategory[] domain = { ENTITY_ACTVATED, ENTITY_ADDED, ENTITY_DEACTVATED, ENTITY_UPDATED, PROCESS_FAILED, PROCESS_SUCCEEDED,
            ENTITY_TRANSFERRED };

    /**
     * Constant list of the enumeration values. It is required to be public as
     * per reference implementation recomendation.
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    private AxiomEventCategory(Long id, String label, String abbreviation)
    {
        super(id, label, abbreviation);
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - CommentAccessType
     * @return CommentAccessType for the enumerated type
     */
    public static final AxiomEventCategory decodeValue(Long codeID)
    {
        return (AxiomEventCategory) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - CommentAccessType
     * @return CommentAccessType for the enumerated type
     */
    public static final AxiomEventCategory decodeValue(String codeValue)
    {
        return (AxiomEventCategory) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeLabel
     * 
     * @return CommentAccessType for the enumerated type
     */
    public static final AxiomEventCategory decodeLabel(String codeLabel)
    {
        return (AxiomEventCategory) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }

}
