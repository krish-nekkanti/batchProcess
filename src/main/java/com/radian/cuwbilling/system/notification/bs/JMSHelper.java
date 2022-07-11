package com.radian.cuwbilling.system.notification.bs;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSHelper
{
    private static JMSHelper instance = null;

    private static final String CONNECTION_FACTORY = "javax/jms/QueueConnectionFactory";

    private JMSHelper()
    {
    }

    public static JMSHelper getInstance()
    {
        if (instance == null)
        {
            instance = new JMSHelper();
        }

        return (instance);
    }

    public void send(String queueName, java.io.Serializable obj) throws NamingException, JMSException
    {
        QueueConnection queueConnection = null;
        Queue queue = null;
        QueueSession queueSession = null;
        QueueSender queueSender = null;
        ObjectMessage message = null;
        QueueConnectionFactory queueConnectionFactory = null;

        Context jndiContext = null;

        try
        {
            synchronized (InitialContext.class)
            {
                jndiContext = new InitialContext();
                queueConnectionFactory = (QueueConnectionFactory) jndiContext.lookup(CONNECTION_FACTORY);
                queue = (Queue) jndiContext.lookup(queueName);
            }

            queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            queueSession = queueConnection.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
            queueSender = queueSession.createSender(queue);
            message = queueSession.createObjectMessage();
            message.setObject(obj);
            queueSender.send(message);
            queueSession.commit();

        } finally
        {
            if (queueSession != null)
            {
                try
                {
                    queueSession.close();
                } catch (JMSException exc)
                {
                }
            }

            if (queueConnection != null)
            {
                try
                {
                    queueConnection.close();
                } catch (JMSException exc)
                {
                }
            }
        }
    }

}
