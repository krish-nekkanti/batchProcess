/**
 * @(#) EventRouterDelegate.java
 */

package com.radian.cuwbilling.system.notification.bs.eventrouter;

import java.text.MessageFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.radian.cuwbilling.common.bo.codes.AxiomEntityType;
import com.radian.cuwbilling.common.bo.domain.AxiomEntity;
import com.radian.cuwbilling.system.notification.bo.code.AxiomEventCategory;
import com.radian.cuwbilling.system.notification.bo.domain.AxiomEventType;
import com.radian.cuwbilling.system.notification.bo.domain.Event;
import com.radian.cuwbilling.system.notification.bs.JMSHelper;
import com.radian.cuwbilling.system.notification.os.persistence.AxiomEventTypeMapper;
import com.radian.foundation.common.logging.LogManager;
import com.radian.foundation.common.logging.Logger;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;

/**
 * send method will asynchronously dispatch event to the EventRouter mdb.
 * 
 */
public class EventRouterDelegate
{
    private static final String QUEUE_NAME = "com/radian/cuwbilling/system/notification/bs/eventrouter/EventRouter/jms-queue";

    private static EventRouterDelegate instance = new EventRouterDelegate();

    private transient Logger logger;

    private EventRouterDelegate()
    {
        logger = LogManager.getLogger(getClass());
    }

    public static EventRouterDelegate getInstance()
    {
        if (instance == null)
        {
            instance = new EventRouterDelegate();
        }
        return instance;
    }

    /**
     * asyncynchronously sends event to the EventRouter mdb
     */
    public void send(Event event)
    {
        try
        {
            logger.debug("[Notification] Event info: Date = " + event.getDate() + ", Event ID = " + ((AxiomEventType) event.getEventType()).getID()
                    + ", Event Name = " + ((AxiomEventType) event.getEventType()).getEventName());
            JMSHelper.getInstance().send(QUEUE_NAME, event);
            logger.debug("[Notification] Event, ID = " + ((AxiomEventType) event.getEventType()).getID() + ", is sent successfully to Event Router Queue!!");
        } catch (Exception exc)
        {
            exc.printStackTrace(); // todo:: log?
        }
    }

    /**
     * Constructs an event and sends event to the event router, which transforms
     * event into the notification and distributes it to the appropriate
     * subscribers.
     * 
     * This is helper method for <code>send( Event event )</code> method
     * 
     * @param provider --
     *            persistence provider used to retrieve event type
     * @param entity --
     *            business entity, which initiated the event (e.g. In the event
     *            "customer changed", it would be customer entity, that was
     *            changed)
     * @param type --
     *            entity type code
     * @param cat --
     *            event category code
     * @param message --
     *            message to be included in the notification
     * 
     * @throws PersistenceProviderException
     *             thrown, if eventType fails to load
     */
    public void send(PersistenceProvider provider, AxiomEntity entity, AxiomEntityType type, AxiomEventCategory cat, String message)
            throws PersistenceProviderException
    {
        AxiomEventTypeMapper aetm = new AxiomEventTypeMapper(provider);
        AxiomEventType eventType = aetm.getEventType(type, cat);

        // send event
        EventRouterDelegate erd = EventRouterDelegate.getInstance();
        Event event = new Event();
        event.setDate(new Date());
        event.setEventSource(new EventSource(entity));
        event.setEventType(eventType);
        event.setMessage(message);
        erd.send(event);

    }

    /**
     * Constructs an event and sends event to the event router, which transforms
     * event into the notification and distributes it to the appropriate
     * subscribers. Additionaly, this method provides ability to retrieve
     * messaage from the resorce bundle and format it with the provided
     * parameters
     * 
     * This is helper method for <code>send( Event event )</code> method
     * 
     * @param provider --
     *            persistence provider used to retrieve event type
     * @param entity --
     *            business entity, which initiated the event (e.g. In the event
     *            "customer changed", it would be customer entity, that was
     *            changed)
     * @param type --
     *            entity type code
     * @param cat --
     *            event category code
     * @param messageKey --
     *            message key in the bundle
     * @param bundleName --
     *            resource bundle name, which contains the message
     * @param params --
     *            array of the message parameters
     * 
     * @throws PersistenceProviderException
     *             thrown, if eventType fails to load
     */
    public void send(PersistenceProvider provider, AxiomEntity entity, AxiomEntityType type, AxiomEventCategory cat, String messageKey, String bundleName,
            Object params[]) throws PersistenceProviderException
    {
        ResourceBundle rb = ResourceBundle.getBundle(bundleName);
        String msgTemplate = rb.getString(messageKey);
        String message = MessageFormat.format(msgTemplate, params);

        logger.debug("[Notification] Entity that triggered the event, Entity ID = " + entity.getID() + ", Entity Name = " + entity.getName()
                + ", Entity Type = " + type.getDisplayValue() + ", Event Category Type = " + cat.getDisplayValue());
        send(provider, entity, type, cat, message);

    }

}
