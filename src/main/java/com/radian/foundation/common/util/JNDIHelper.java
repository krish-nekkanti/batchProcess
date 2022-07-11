package com.radian.foundation.common.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.ejb.EJBHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

import weblogic.jndi.WLContext;

/**
 * The utility class that contains various convenience methods for JNDI access.
 *
 * @author Valdas Sevelis
 * @author Giedrius Trumpickas
 */
public class JNDIHelper
{
    /**
     * Lookups object from JDNI tree, using default <code>InitialContext</code>
     * configuration.
     *
     * @param name a JNDI path name
     * @return object for the given JNDI path name
     *
     * @exception NamingException if error occured when trying to lookup object
     */
    public static Object lookup(String name) throws NamingException
    {
        Context ctx = null;
        try
        {
            ctx = new InitialContext();
            return ctx.lookup(name);
        }
        finally
        {
            close(ctx);
        }
    }

    /**
     * Lookups object from JDNI tree, using provider url to create <code>InitialContext</code>
     * configuration.
     *
     * @param providerURL the url to use when creating the InitialContext
     * @param name a JNDI path name
     * @return object for the given JNDI path name
     *
     * @exception NamingException if error occured when trying to lookup object
     */
    public static Object lookup(String providerURL, String name) throws NamingException
    {
        Context ctx = null;
        try
        {
            Hashtable ht = new Hashtable();
            //TODO - make INITIAL_CONTEXT_FACTORY a configurable option
            //Yes, we're not passing the string for the INITIAL_CONTEXT_FACTORY as
            //an argument, but it probably will not change very frequently.
            ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
            ht.put(Context.PROVIDER_URL,providerURL);
            ctx = new InitialContext(ht);
            return ctx.lookup(name);
        }
        finally
        {
            close(ctx);
        }
    }

    /**
     * Binds object to a name in JDNI tree, using default <code>InitialContext</code>
     * configuration.
     *
     * @param name a JNDI path name
     * @param obj object to bind to a given JNDI name
     *
     * @throws NamingException if error occured when trying to bind object
     * 
     * @author Rolandas Valteris
     */
    public static void bind(String name, Object obj) throws NamingException
    {
        Context ctx = null;
        try
        {
            ctx = new InitialContext();
            ctx.bind(name, obj);
        }
        finally
        {
            close(ctx);
        }
    }
    
    /**
     * Binds object to a name in JDNI tree, using default <code>InitialContext</code>
     * configuration.
     *
     * @param name a JNDI path name
     * @param obj object to bind to a given JNDI name
     *
     * @throws NamingException if error occured when trying to bind object
     * 
     * @author Rolandas Valteris
     */
    public static void bindNonReplicatable(String name, Object obj) throws NamingException
    {
        Context ctx = null;
        try
        {
        	Hashtable ht= new Hashtable();
        	ht.put(WLContext.REPLICATE_BINDINGS, "false");
            ctx = new InitialContext(ht);
            ctx.bind(name, obj);
        }
        finally
        {
            close(ctx);
        }
    }

    /**
     * Binds object to a name in JDNI tree, using provider url to create <code>InitialContext</code>
     * configuration.
     *
     * @param name a JNDI path name
     * @param obj object to bind to a given JNDI name
     *
     * @throws NamingException if error occured when trying to bind object
     * 
     * @author Rolandas Valteris
     */
    public static void bind(String providerURL, String name, Object obj) throws NamingException
    {
        Context ctx = null;
        try
        {
            Hashtable ht = new Hashtable();
            //TODO - make INITIAL_CONTEXT_FACTORY a configurable option
            //Yes, we're not passing the string for the INITIAL_CONTEXT_FACTORY as
            //an argument, but it probably will not change very frequently.
            ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
            ht.put(Context.PROVIDER_URL,providerURL);
            ctx = new InitialContext(ht);
            ctx.bind(name, obj);
        }       
        finally
        {
            close(ctx);
        }
    }

    /**
     * Unbinds named object from JDNI tree, using default <code>InitialContext</code>
     * configuration.
     *
     * @param name a JNDI path name
     *
     * @throws NamingException if error occured when trying to bind object
     * 
     * @author Rolandas Valteris
     */
    public static void unbind(String name) throws NamingException
    {
        Context ctx = null;
        try
        {
            ctx = new InitialContext();
            ctx.unbind(name);
        }
        finally
        {
            close(ctx);
        }
    }

    /**
     * Unbinds named object from JDNI tree, using provider url to create <code>InitialContext</code>
     * configuration.
     *
     * @param name a JNDI path name
     *
     * @throws NamingException if error occured when trying to bind object
     * 
     * @author Rolandas Valteris
     */
    public static void unbind(String providerURL, String name) throws NamingException
    {
        Context ctx = null;
        try
        {
            Hashtable ht = new Hashtable();
            //TODO - make INITIAL_CONTEXT_FACTORY a configurable option
            //Yes, we're not passing the string for the INITIAL_CONTEXT_FACTORY as
            //an argument, but it probably will not change very frequently.
            ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
            ht.put(Context.PROVIDER_URL,providerURL);
            ctx = new InitialContext(ht);
            ctx.unbind(name);
        }
        finally
        {
            close(ctx);
        }
    }

    /**
     * Closes JNDI context and ignores any errors that may occur.
     * It is safe to pass <code>null</code> objects to this function.
     *
     * @param ctx a JNDI context to be closed
     */
    public static void close(Context ctx)
    {
        try
        {
            if (ctx != null) ctx.close();
        }
        catch (NamingException e)
        {
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
     * @return EJBHome object for the given class
     *
     * @exception NamingException if error occured when trying to lookup object
     */
    public static EJBHome lookupEJBHome(Class ejbHomeClass) throws NamingException
    {
        EJBHome ejbHome = lookupEJBHome(ejbHomeClass.getName().replace('.', '/'));
        return (EJBHome) PortableRemoteObject.narrow(ejbHome, ejbHomeClass);
    }

    /**
     * Gets an EJBHome object for the given JNDI path.
     *
     * @param ejbHome a JNDI path for EJBHome
     *
     * @return EJBHome object for the given name
     *
     * @exception NamingException if error occured when trying to lookup object
     */
    public static EJBHome lookupEJBHome(String ejbHome) throws NamingException
    {
        return (EJBHome)lookup(ejbHome);
    }


    /**
     * Gets an EJBHome object for the given JNDI path and provider URL
     *
     * @param providerURL the url to use when creating the InitialContext
     * @param ejbHome a JNDI path for EJBHome
     * @return EJBHome object for the given name
     *
     * @exception NamingException if error occured when trying to lookup object
     */
    public static EJBHome lookupEJBHome(String providerURL, String ejbHome) throws NamingException
    {
        return (EJBHome)lookup(providerURL,ejbHome);
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
    public static Connection getConnection(String dataSourceName) throws NamingException, SQLException
    {
        DataSource ds = (DataSource)lookup(dataSourceName);
        return ds.getConnection();
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
    public static Connection getConnection(String providerURL,String dataSourceName) throws NamingException, SQLException
    {
        DataSource ds = (DataSource)lookup(providerURL,dataSourceName);
        return ds.getConnection();
    }

    /**
    * Utility method ot narrow  EJB objects
    *
    * @param ref a reference to the class that is narrowed
    * @param c the .class the is narrowed
    * @return the remote object
    *
    */
    public static Object narrow(Object ref, Class c) {
        return PortableRemoteObject.narrow(ref, c);
    }
}
