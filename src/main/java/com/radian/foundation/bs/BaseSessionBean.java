package com.radian.foundation.bs;

import java.rmi.RemoteException;
import java.security.Principal;
import java.sql.SQLException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.resource.ResourceException;
import javax.security.auth.Subject;

import weblogic.security.Security;
import weblogic.security.SubjectUtils;

import com.radian.foundation.common.logging.Logger;
import com.radian.foundation.common.logging.LogManager;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;
import com.radian.foundation.os.persistence.spi.PersistenceProviderFactory;
import com.radian.foundation.os.persistence.util.ProviderHelper;
import com.radian.foundation.os.persistence.util.UserContextManager;

/**
 * Base class for session beans with variuos utility methods.
 * 
 * @author Giedrius Trumpickas
 */
public class BaseSessionBean implements SessionBean
{
	/**
	 * Session context
	 */
	private SessionContext context;
	
	/**
	 * Persistence provider factory reference.
	 */
	private transient PersistenceProviderFactory providerFactory;
	
	/**
	 * Session bean logger.
	 */
	private transient Logger logger;
	
	/**
	 * @see javax.ejb.SessionBean#ejbActivate()
	 */
	public void ejbActivate() throws EJBException, RemoteException
	{
		if(logger != null)
		{
			logger.debug("ejbActivate()");
		}
	}

	/**
	 * @see javax.ejb.SessionBean#ejbPassivate()
	 */
	public void ejbPassivate() throws EJBException, RemoteException
	{
		if(logger != null)
		{
			logger.debug("ejbPassivate()");
		}
	}

	/**
	 * @see javax.ejb.SessionBean#ejbRemove()
	 */
	public void ejbRemove() throws EJBException, RemoteException
	{
		//FIXME: investigate why logger is null when shutdowning server
		// it's possible that passivation of the bean is the reason
		if(logger != null)
		{
			logger.debug("ejbRemove()");
		}
	}
	
	/**
	 * Called when this bean instance is created in container.
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() throws CreateException
	{		
		logger = LogManager.getLogger(getClass());		
		logger.debug("ejbCreate()");
		try
		{			
			logger.debug("Trying to lookup persistence provider factory");
			providerFactory = ProviderHelper.getManagedProviderFactory();
			logger.debug("Factory was found");
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new CreateException(e.getMessage());			
		}
	}
	
	/**
	 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	 */
	public void setSessionContext(SessionContext arg0)
		throws EJBException, RemoteException
	{		
		this.context = arg0;		
	}
	
	/**
	 * Gets this session bean session context.
	 * 
	 * @return session context
	 */
	protected SessionContext getSessionContext()
	{
		return context;
	}
		
	/**
	 * Gets persistence provider for current transaction.
	 * 
	 * @return
	 * @throws PersistenceProviderException
	 */
	protected final PersistenceProvider getProvider() throws PersistenceProviderException
	{
		try
		{
			PersistenceProvider p = providerFactory.getProvider();
			propagateCurrentSubject(p);
			return p;
		}
		catch(PersistenceProviderException e)
		{
			getLogger().error("getProvider()", e);
			throw e;
		}
	}
	
	/**
	 * Forces closing of provider by ignoring any exceptions.
	 * 
	 * @param provider
	 */
	protected final void forceProviderClose(PersistenceProvider provider)
	{	
		try
		{
			if(provider == null)
			{
				getLogger().debug("forceProviderClose() - provider is null");
				return;
			}
			provider.close();
		}
		catch(PersistenceProviderException e)
		{
			getLogger().debug("forceProviderClose()", e);
			throw new BaseSessionBeanRuntimeException(e);
		}
	}
	
	/**
	 * Sets rollackOnly on session context.
	 */
	protected void rollbackTransaction()
	{
		context.setRollbackOnly();		
	}
	
	/**
	 * Sets rollackOnly on session context.
	 */
	protected void rollbackTransaction(PersistenceProvider provider)
	{
		forceProviderClose(provider);
		context.setRollbackOnly();		
	}
	
	/**
	 * Gets current user principal
	 * @return current user principal
	 */
	protected final Principal getCurrentUser()
	{
		return getSessionContext().getCallerPrincipal();
	}
	
	/**
	 * Gets logger for this session bean.
	 * 
	 * @return logger instance for this session bean
	 */
	protected final Logger getLogger()
	{
		return logger;
	}
	
	private void propagateCurrentSubject(PersistenceProvider p) throws PersistenceProviderException
	{
		try
		{
			if( p != null)
			{
				//propogate user
				UserContextManager.propogateCurrentUser(p.getConnection());
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			logger.error("propagateCurrentSubject failed", e);			
		}		
	}
}
