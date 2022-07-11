/**
 * @(#) JobPosition.java
 */

package com.radian.cuwbilling.common.bo.domain;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.codes.JobFunctionCategory;

/**
 * This interface defines a Job Position business domain object
 *
 * A Job Position defines the Position, Job Title and related Authority Levels
 * for a Staff Position.
 *
 * @author Rick Mohr
 * @version 1.0
 */
public interface JobPosition extends BaseDomainObject
{
    /**
     * @Return The Title for this Position.
     */
    public String getJobTitle();

    /**
     * Assigns the Title to this Position.
     *
     * @param title
     *            The job title for this Position.
     */
    public void setJobTitle(String title);

    /**
     * @Return The Gradel Level (1-5) for this Job Position.
     */
    public int getJobGradeLevel();

    /**
     * Assigns the Grade Level (1-5) to this Job Position.
     *
     * @param gradeLevel
     *            The Grade Level for this Job Position.
     */
    public void setJobGradeLevel(int gradeLevel);

    /**
     * @return the job function category for this position
     */
    public JobFunctionCategory getJobFunctionCategory();

    /**
     * sets the job function category for the position
     *
     * @param jobFunctionCategory
     *            specifies the new job function category.
     */
    public void setJobFunctionCategory(JobFunctionCategory jobFunctionCategory);
}
