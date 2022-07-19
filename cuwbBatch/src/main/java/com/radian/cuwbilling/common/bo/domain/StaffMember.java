/**
 * @(#) StaffMember.java
 */

package com.radian.cuwbilling.common.bo.domain;

import java.util.Collection;

import com.radian.cuwbilling.common.bo.domain.EmailAddress;
import com.radian.cuwbilling.common.bo.domain.Person;
import com.radian.cuwbilling.common.bo.domain.Branch;
//import com.radian.cuwbilling.organization.radian.common.bo.codes.Department;
import com.radian.cuwbilling.common.bo.codes.JobFunctionCategory;
//import com.radian.cuwbilling.organization.radian.common.bo.codes.StaffType;

/**
 * This interface defines a Staff Member business domain object
 *
 * A StaffMember is a Person employed by a RadianOrganization
 *
 * @author Rick Mohr
 * @version 1.0
 */
public interface StaffMember extends Person
{
    /**
     * gets the staff member job title
     */
    public String getJobTitle();

    /**
     * sets the staff member job title
     *
     * @param jobTitle
     *            new staff member job title
     */
    public void setJobTitle(String jobTitle);
}
