/**
 * @(#) Event.java
 */

package com.radian.cuwbilling.system.notification.bo.domain;

import java.io.IOException;
import java.util.Date;

import com.radian.cuwbilling.system.notification.bs.eventrouter.EventSource;

/**
 * Event object encapsulates data, pertinent to event occured - event type
 * (which has category - added, updated etc), and acctual entity that
 * participated in the event (note todo:: this is a limitation, that event can
 * have only one entity. Best to my knowledge, that 's enough, but have to
 * check)
 */
public class Event implements java.io.Serializable
{
    /**
     * timestamp, when event was trigered
     */
    private Date date;

    private EventSource eventSource;

    private EventType type;

    private String message;

    public Event()
    {
        date = new java.util.Date();
    }

    public Date getDate()
    {
        return date;
    }

    public EventSource getEventSource()
    {
        return eventSource;
    }

    /**
     * returns the type of this event
     */
    public EventType getEventType()
    {
        return type;

    }

    public void setEventType(EventType type)
    {
        this.type = type;
    }

    public void setDate(final Date date)
    {
        this.date = date;
    }

    public void setEventSource(final EventSource eventSource)
    {
        this.eventSource = eventSource;
    }

    /**
     * @return
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param string
     */
    public void setMessage(String string)
    {
        message = string;
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        System.out.println("Deserializing: " + getClass().getName());

        date = (Date) stream.readObject();
        eventSource = (EventSource) stream.readObject();
        type = (EventType) stream.readObject();
        message = (String) stream.readObject();

    }

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException
    {
        System.out.println("Serializing: " + getClass().getName());

        stream.writeObject(date);
        stream.writeObject(eventSource);
        stream.writeObject(type);
        stream.writeObject(message);

    }

}
