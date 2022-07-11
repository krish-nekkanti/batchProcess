/*
 * BillingInclusion.java
 *
 * Created on September 3, 2003, 5:10 PM
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import com.radian.cuwbilling.billing.common.bo.codes.InclusionType;
import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;

/**
 * 
 * @author DLeed
 */
public interface BillingInclusion extends BaseDomainObject
{
    public InclusionType getInclusionCategory();

    public void setInclusionCategory(InclusionType category);

    public String getInclusionName();

    public void setInclusionName(String name);

    public String getInclusionAttributeName();

    public void setInclusionAttributeName(String attribName);

    public Boolean isRequired();

    public void setRequired(Boolean required);

    public Long getSortOrder();

    public void setSortOrder(Long sortOrder);

}
