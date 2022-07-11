package com.radian.foundation.os.persistence.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.sf.hibernate.Hibernate;

import com.radian.foundation.common.exception.CascadingRuntimeException;
import com.radian.foundation.common.logging.LogManager;
import com.radian.foundation.common.logging.Logger;
import com.radian.foundation.os.persistence.providers.hibernate.HibernateProviderFactory;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderFactory;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;

/**
 * Variuos utility methods for persistence provider
 *
 * @author Giedrius Trumpickas
 */
public class ProviderHelper
{
	/**
	 * Logger reference.
	 */
	private final static Logger logger = LogManager.getLogger(ProviderHelper.class);

	/**
	 * Persistence provider factory JNDI name
	 */
	private final static String PROVIDER_FACTORY = "com/radian/foundation/os/persistence/CUWBillingPersistenceProviderFactory";

	private final static String PROVIDER_FACTORY_MIONLINE = "com/radian/foundation/os/persistence/MIOnlinePersistenceProviderFactory";

	/**
	 * Cached reference to the managed persistence provider factory.
	 */
	private static PersistenceProviderFactory cachedMPF = null;
	
	private static PersistenceProviderFactory cachedMIOnlineMPF = null;

	/**
	 * Cached reference to the non-managed persstence provider factory.
	 */
	private static PersistenceProviderFactory cachedNMPF = null;

	/**
	 * Lock to synchronize access.
	 */
	private final static Object lock = new Object();

	/**
	 * Gets persistence provider factory reference.
	 *
	 * @param context
	 * @return
	 * @throws NamingException
	 * @throws PersistenceProviderException
	 */
	public static PersistenceProviderFactory getProviderFactory(Context context)
		throws NamingException, PersistenceProviderException
	{
		synchronized(lock)
		{
			if(cachedMPF == null)
			{
				cachedMPF = (PersistenceProviderFactory)lookupHelper(context, PROVIDER_FACTORY, false);
			}
		}
		return cachedMPF;
	}

	public static PersistenceProviderFactory getMIOnlineProviderFactory(Context context)
			throws NamingException, PersistenceProviderException
		{
			synchronized(lock)
			{
				if(cachedMIOnlineMPF == null)
				{
					cachedMIOnlineMPF = (PersistenceProviderFactory)lookupHelper(context, PROVIDER_FACTORY_MIONLINE, false);
				}
			}
			return cachedMIOnlineMPF;
	}

	/**
	 * Helper method for provider factory obtaining inside the same  J2EE container.
	 *
	 * @return provider instance
	 * @throws NamingException
	 * @throws PersistenceProviderException
	 */
	public static PersistenceProviderFactory getManagedProviderFactory()
		throws NamingException, PersistenceProviderException
	{
		synchronized(lock)
		{
			if(cachedMPF == null)
			{
				cachedMPF = (PersistenceProviderFactory)lookupHelper(new InitialContext(), PROVIDER_FACTORY, true);
			}
		}
		return cachedMPF;
	}

	public static PersistenceProviderFactory getMIOnlineManagedProviderFactory()
			throws NamingException, PersistenceProviderException
		{
			synchronized(lock)
			{
				if(cachedMIOnlineMPF == null)
				{
					cachedMIOnlineMPF = (PersistenceProviderFactory)lookupHelper(new InitialContext(), PROVIDER_FACTORY_MIONLINE, true);
				}
			}
			return cachedMIOnlineMPF;
	}

	/**
	 * Forces close of given persistence provider, ingores exceptions.
	 *
	 * @param provider a provider
	 */
	public static void forceProviderClose(PersistenceProvider provider)
	{
		try
		{
			if(provider == null) return;
			provider.close();
		}
		catch(PersistenceProviderException e)
		{
		}
	}

	/**
	 * Gets non managed provider factory instance.
	 *
	 * @param configFile a configuration file
	 * @return non managed provider factory instance
	 * @throws PersistenceProviderException
	 */
	public static PersistenceProviderFactory getNonManagedProviderFactory(String configFile) throws PersistenceProviderException
	{
		return new HibernateProviderFactory(configFile);
	}

	/**
	 * Lookup object by given name in the given context,
	 *
	 * @param context a JNDI context
	 * @param name an object name
	 * @param closeCtx a falg to close context after lookup
	 * @return found object
	 * @throws NamingException
	 */
	private static Object lookupHelper(Context context, String name, boolean closeCtx) throws NamingException
	{
		try
		{
			return context.lookup(name);
		}
		catch(NamingException e)
		{
			throw e;
		}
		finally
		{
			if(closeCtx)
			{
				context.close();
			}
		}
	}

	/**
	 * Gets instance "real" class.
	 *
	 * @param proxy a persistent instance proxy
	 * @return "real" class for persistent instance
	 * @exception CascadingRuntimeException if invocation fails
	 */
	public static Class getInstanceClass(Object proxy)
	{
		try
		{
			return Hibernate.getClass(proxy);
		}
		catch(Throwable t)
		{
			throw new CascadingRuntimeException(t);
		}
	}
}
