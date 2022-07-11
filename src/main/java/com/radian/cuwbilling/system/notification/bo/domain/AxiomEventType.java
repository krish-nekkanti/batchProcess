/**
 * @(#) AxiomEventType.java
 */

package com.radian.cuwbilling.system.notification.bo.domain;

import com.radian.cuwbilling.common.bo.codes.AxiomEntityType;
import com.radian.cuwbilling.system.notification.bo.code.AxiomEventCategory;

/**
 * EventType encapsulates information about the type of an event - category
 * (UPDATED, ADDED etc) and entity type (Customer, Billing profile etc)
 * 
 */
public interface AxiomEventType extends EventType
{
    Long getID();

    void setID(Long id);

    public String getEventName();

    public void setEventName(String eventName);

    public AxiomEntityType getEntityType();

    public void setEntityType(AxiomEntityType entityType);

    public AxiomEventCategory getEventCategory();

    public void setEventCategory(AxiomEventCategory eventCategory);
}
