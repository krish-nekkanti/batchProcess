/*
 * Placement.java
 *
 * Created on August 13, 2003, 11:14 AM
 */

package com.radian.cuwbilling.common.bo.domain;

import com.radian.cuwbilling.common.bo.domain.Organization;

/**
 * This interface defines a Placement business domain object
 *
 * @author Jeff Sumner
 * @version 1.0
 */
public interface Placement extends BaseDomainObject
{
    /**
     * @returns the placement's work location
     */
    Organization getWorkLocation();

    /**
     * Assigns the placement's work location
     *
     * @param workLocation
     *            An <code>Organization</code> representing the new work
     *            location.
     */
    void setWorkLocation(Organization workLocation);

    String getComments();

    void setComments(String comments);

    String getPlacementNo();

    void setPlacementNo(String no);

    Boolean getAtHomeIndicator();

    void setAtHomeIndicator(Boolean atHome);

	JobPosition getJobPosition();

	void setJobPosition(JobPosition jobPosition);

	StaffMember getStaffMember();

	void setStaffMember(StaffMember staffMember);


}
