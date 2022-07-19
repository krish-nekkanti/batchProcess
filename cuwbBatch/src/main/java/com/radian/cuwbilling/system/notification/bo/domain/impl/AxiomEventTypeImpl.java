/**
 * @(#) AxiomEventType.java
 */

package com.radian.cuwbilling.system.notification.bo.domain.impl;

import java.io.IOException;

import com.radian.cuwbilling.common.bo.codes.AxiomEntityType;
import com.radian.cuwbilling.system.notification.bo.code.AxiomEventCategory;
import com.radian.cuwbilling.system.notification.bo.domain.AxiomEventType;

/**
 * EventType encapsulates information about the type of an event - category
 * (UPDATED, ADDED etc) and entity type (Customer, Billing profile etc)
 * 
 */
public class AxiomEventTypeImpl implements AxiomEventType
{

    private Long ID;

    private String eventName;

    private AxiomEntityType entityType;

    private AxiomEventCategory eventCategory;

    public boolean equals(Object other)
    {
        if (!(other instanceof AxiomEventType))
        {
            return false;
        }
        AxiomEventType otherType = (AxiomEventType) other;
        return entityType.equals(otherType.getEntityType()) && eventCategory.equals(otherType.getEventCategory());
    }

    public AxiomEntityType getEntityType()
    {
        return entityType;
    }

    public void setEntityType(AxiomEntityType entityType)
    {
        this.entityType = entityType;
    }

    /**
     * @return
     */
    public AxiomEventCategory getEventCategory()
    {
        return eventCategory;
    }

    /**
     * @param category
     */
    public void setEventCategory(AxiomEventCategory category)
    {
        eventCategory = category;
    }

    /**
     * @return
     */
    public String getEventName()
    {
        return eventName;
    }

    /**
     * @param string
     */
    public void setEventName(String string)
    {
        eventName = string;
    }

    /**
     * @return
     */
    private Long getEntityTypeRaw()
    {
        return entityType.getID();
    }

    /**
     * @return
     */
    private Long getEventCategoryRaw()
    {
        return eventCategory.getID();
    }

    /**
     * @param long1
     */
    private void setEntityTypeRaw(Long long1)
    {
        entityType = AxiomEntityType.decodeValue(long1);
    }

    /**
     * @param long1
     */
    private void setEventCategoryRaw(Long long1)
    {
        eventCategory = AxiomEventCategory.decodeValue(long1);
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        System.out.println("Deserializing: " + getClass().getName());

        ID = (Long) stream.readObject();
        eventName = (String) stream.readObject();
        entityType = AxiomEntityType.decodeValue((Long) stream.readObject());
        eventCategory = AxiomEventCategory.decodeValue((Long) stream.readObject());

    }

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException
    {
        System.out.println("Serializing: " + getClass().getName());

        stream.writeObject(ID);
        stream.writeObject(eventName);
        stream.writeObject(entityType.getID());
        stream.writeObject(eventCategory.getID());

    }

    /**
     * @return
     */
    public Long getID()
    {
        return ID;
    }

    /**
     * @param long1
     */
    public void setID(Long long1)
    {
        ID = long1;
    }

}
