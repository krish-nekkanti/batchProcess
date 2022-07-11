package com.radian.foundation.common.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.jms.QueueConnectionFactory;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

import com.radian.foundation.common.config.AxiomConfigurationLocator;
import com.radian.foundation.common.config.Configuration;
import com.radian.foundation.common.config.ConfigurationException;

/**
 * FIXME/TODO - I selectively pulled out some things (Castor and Code Framework
 * initialization, etc) from the original version of the ServiceLocator. I/we
 * can add them back in once we're sure that we want them to reside here
 *  
 * @author nines
 * @author Rolandas Valteris
 */
public class ServiceLocator
{
    /**
     * Local server key
     */
    public static final String LOCAL_SERVER = "local_server";

    /**
     * Contains all ServiceLocator instances
     */
    private static Map instances = new HashMap();

    /**
     * Local reference to the InitialContext
     */
    private InitialContext initialContext;

    /**
     * Server name
     */
    private String serverName;

    /**
     * Cached <code>EJBHome</code>s
     */
    private Map ejbHomes;

    /**
     * Gets local ServiceLocator instance.
     *
     * @return local instance of ServiceLocator
     */
    public static ServiceLocator getInstance() throws ServiceLocatorException
    {
        return getInstance(LOCAL_SERVER);
    }

    /**
     * Gets ServiceLocator instance for the given server.
     *
     * @param server server name
     *
     * @return ServiceLocator instance for the given server
     */
    public static ServiceLocator getInstance(String server) throws ServiceLocatorException
    {
        ServiceLocator instance = null;
        synchronized (instances)
        {
            instance = (ServiceLocator)instances.get(server);
            if (instance == null)
            {
                instance = new ServiceLocator(server);
                instances.put(server, instance);

            }
        }
        return instance;
    }

    /**
     * Creates and configures an instance of the ServiceLocator.
     * 
     * NOTE: usually you shouldn't call this method. It is for unit testing outside container.
     * 
     * @param properties property map to initialize this service locator with
     */
    public static void init(Map properties) throws ServiceLocatorException
    {
        init(LOCAL_SERVER, properties);
    }

    /**
     * Creates and configures an instance of the ServiceLocator.
     * 
     * NOTE: usually you shouldn't call this method. It is for unit testing outside container.
     * 
     * @param server name of server this service locator is associated with
     * @param properties property map to initialize this service locator with
     */
    public static void init(String server, Map properties) throws ServiceLocatorException
    {
        synchronized (instances)
        {
            ServiceLocator instance = new ServiceLocator(server, properties);
            instances.put(server, instance);
        }
    }

    /**
     * Creates and configures an instance of the ServiceLocator.
     * 
     * @param server name of server this service locator is associated with.
     */
    protected ServiceLocator(String server) throws ServiceLocatorException
    {
        this(server, null);
    }

    /**
     * Creates and configures an instance of the ServiceLocator.
     * 
     * @param server name of server this service locator is associated with
     * @param properties property map to initialize this service locator with
     */
    protected ServiceLocator(String server, Map properties) throws ServiceLocatorException
    {
        if (!server.equals(LOCAL_SERVER))
        {
            throw new ServiceLocatorException("Unknown server: [" + server + "]");
        }

        this.serverName = server;

        ejbHomes = Collections.synchronizedMap(new HashMap());

        try
        {
            initialContext =
                new InitialContext(properties != null ? new Hashtable(properties) : null);
        }
        catch (NamingException e)
        {
            throw new ServiceLocatorException(e);
        }
    }

    /**
     * Gets server name for the ServiceLocator instance
     *
     * @return Server name
     */
    public String getServerName()
    {
        return serverName;
    }

    /**
     * Lookups object from JDNI tree, using <code>InitialContext</code>
     * configured for the specific server.
     *
     * @param name a JNDI path name
     *
     * @return object for the given JNDI path name
     *
     * @exception NamingException if error occured when trying to lookup object
     */
    public Object lookup(String name) throws ServiceLocatorException
    {
        try
        {
            return initialContext.lookup(name);
        }
        catch (NamingException e)
        {
            throw new ServiceLocatorException(e);
        }
    }

    /**
     * Gets EJBHome object for the given EJBHome class. This function assumes
     * that EJBHome object has the same JNDI path as fully qualified class name
     *  of the same EJB Home class (e.g. if home class is
     * <code>com.radian.cuwbilling.ejb.CertificateHome</code>, the JNDI path should be
     * <code>com/radian/cuwbilling/ejb/CertificateHome</code>.
     *
     * @param ejbHomeClass an EJB Home object class
     *
     * @return EJBHome object for the given class
     *
     * @exception NamingException if error occured when trying to lookup object
     */
    public EJBHome getEJBHome(Class ejbHomeClass) throws ServiceLocatorException
    {
        return getEJBHome(ejbHomeClass, ejbHomeClass.getName().replace('.', '/'));
    }

    /**
     * Retrieves an EJBLocalHome object base on the given EJBLocalHome
     * class.This function assumes that EJBHome object has the same JNDI path as
     * fully qualified class name  of the same EJB Home class (e.g. if home
     * class is <code>com.radian.cuwbilling.ejb.CertificateHome</code>, the JNDI path
     * should be <code>com/radian/cuwbilling/ejb/CertificateHome</code>.
     * 
     * @param ejbHomeClass
     * 
     * @return EJBLocalHome
     * 
     * @throws ServiceLocatorException
     */
    public EJBLocalHome getEJBLocalHome(Class ejbLocalHomeClass)
        throws ServiceLocatorException
    {
        return getEJBLocalHome(
            ejbLocalHomeClass,
            ejbLocalHomeClass.getName().replace('.', '/'));
    }

    public EJBLocalHome getEJBLocalHome(Class ejbLocalHomeClass, String ejbName)
		throws ServiceLocatorException
    {

        EJBLocalHome ejbHome = (EJBLocalHome)this.ejbHomes.get(ejbName);
        if (ejbHome == null)
        {
			ejbHome = (EJBLocalHome)PortableRemoteObject.narrow(lookup(ejbName), ejbLocalHomeClass);
            ejbHomes.put(ejbName, ejbHome);
        }

        return ejbHome;
    }

    /**
     * Gets an EJBHome object for the given JNDI path.
     *
     * @param ejbHomeClass an EJB Home object class
     * @param ejbName a JNDI path for EJBHome
     *
     * @return EJBHome object for the given name
     *
     * @exception ServiceLocatorException if error occured when trying to lookup object
     */
    public EJBHome getEJBHome(Class ejbHomeClass, String ejbName)
        throws ServiceLocatorException
    {
        //        return (EJBHome)lookup(ejbHome);
        EJBHome ejbHome = (EJBHome)this.ejbHomes.get(ejbName);
        if (ejbHome == null)
        {
            //  lookup home and store it in the ejbHomes map
            ejbHome = (EJBHome)PortableRemoteObject.narrow(lookup(ejbName), ejbHomeClass);
            ejbHomes.put(ejbName, ejbHome);
        }

        return ejbHome;
    }

    /**
    * Utility method ot narrow  EJB objects
    *
    * @param ref a reference to the class that is narrowed
    * @param c the .class the is narrowed
    * @return the remote object
    *
    */
    public Object narrow(Object ref, Class c) throws ServiceLocatorException
    {
        return PortableRemoteObject.narrow(ref, c);
    }

    /**
     * Gets JDBC connection from JNDI tree for the given data source.
     *
     * @param dataSourceName a data source name
     * @return JDBC connection for the given name
     *
     * @exception NamingException if error occured when trying to lookup object
     * @exception SQLException    if a database-access error occurs
     */
    public Connection getConnection(String dataSourceName) throws ServiceLocatorException
    {
        try
        {
            return ((DataSource)lookup(dataSourceName)).getConnection();
        }
        catch (SQLException e)
        {
            throw new ServiceLocatorException(e);
        }
    }

    /**
     * Gets <code>QueueConnectionFactory</code> service for the current
     * server.
     *
     * @return an instance of <code>QueueConnectionFactory</code>
     *
     * @exception NamingException if error occured when trying to lookup object
     */
    public QueueConnectionFactory getQueueConnectionFactory() throws ServiceLocatorException
    {
        return (QueueConnectionFactory)lookup("jms/QueueConnectionFactory");
    }

    /**
     * Gets <code>QueueConnectionFactory</code> service for the current
     * server.
     *
     * @return an instance of <code>TopicConnectionFactory</code>
     *
     * @exception NamingException if error occured when trying to lookup object
     */
    public TopicConnectionFactory getTopicConnectionFactory() throws ServiceLocatorException
    {
        return (TopicConnectionFactory)lookup("jms/TopicConnectionFactory");
    }

    /**
     * Returns the initialContext.
     * @return InitialContext
     */
    public InitialContext getInitialContext()
    {
        return initialContext;
    }

    /**
     * Sets the initialContext.
     * @param initialContext The initialContext to set
     */
    public void setInitialContext(InitialContext initialContext)
    {
        this.initialContext = initialContext;
    }

    /**
     * Gets r/o application configuration.
     * Specificly, it returns root node of the configuration.
     * 
     * @return r/o application configuration.
     */
    public Configuration getConfiguration() throws ServiceLocatorException
    {
        try
        {
            return AxiomConfigurationLocator.getInstance().getConfiguration();
        }
        catch (ConfigurationException e)
        {
            throw new ServiceLocatorException(e);
        }
    }

    /**
     * Gets specific node of r/o application configuration.
     * If <code>path</code> is relative path, then the node returned is relative to the root
     * node, i.e. code <code>this.getConfiguration("codes-config/data-source")</code> is 
     * equavalent to <code>this.getConfiguration().getChild("codes-config")
     * .getChild("data-source")</code>.
     * 
     * @return specific node of r/o application configuration.
     * @see Configuration#getConfiguration(String)
     */
    public Configuration getConfiguration(String path) throws ServiceLocatorException
    {
        try
        {
            return AxiomConfigurationLocator
                .getInstance()
                .getConfiguration()
                .getConfiguration(
                path);
        }
        catch (ConfigurationException e)
        {
            throw new ServiceLocatorException(e);
        }
    }

}
