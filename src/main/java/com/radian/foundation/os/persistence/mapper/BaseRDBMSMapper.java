package com.radian.foundation.os.persistence.mapper;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import net.sf.hibernate.LockMode;

import com.radian.foundation.bs.QueryBuilder;
import com.radian.foundation.bs.SearchCriteria;
import com.radian.foundation.bs.SearchCriteriaItem;
import com.radian.foundation.bs.ServiceConstants;
import com.radian.foundation.common.logging.Logger;
import com.radian.foundation.common.logging.LogManager;
import com.radian.foundation.os.persistence.spi.Order;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;
import com.radian.foundation.os.persistence.spi.PersistenceProviderFactory;
import com.radian.foundation.os.persistence.spi.Query;
import com.radian.foundation.os.persistence.spi.QueryCriteria;
import com.radian.foundation.os.persistence.spi.expression.BooleanExpression;
import com.radian.foundation.os.persistence.spi.expression.DefaultExpressionBuilder;
import com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder;
import com.radian.foundation.os.persistence.util.ProviderHelper;

/**
 * Base mapper for all RDBMS releated mappers.
 * 
 * @author Giedrius Trumpickas
 */
public class BaseRDBMSMapper
{
	/**
	 * Some useful constant for search queries.
	 */
	private final static int DEFAULT_MAX_RESULTS = 20;
	private final static int DEFAULT_FIRST_RESULT_INDEX = 0;

	/**
	 * Logger instance for derived classes.
	 */
	private Logger logger;

	/**
	 * Provider instance.
	 */
	private PersistenceProvider provider;

	/**
	 * Managed persistence provider factory.
	 */
	private PersistenceProviderFactory ppf;

	/**
	 * Constructs mapper with managed provider.
	 * 
	 * @throws DomainMapperException
	 */
	protected BaseRDBMSMapper() throws BaseRDBMSMapperInitializationException
	{
		try
		{
			ppf = ProviderHelper.getManagedProviderFactory();
			logger = LogManager.getLogger(getClass());
		}
		catch (Exception e)
		{
			throw new BaseRDBMSMapperInitializationException(e);
		}
	}

	/**
	 * Constructs base RDBMS mapper.
	 * 
	 * @param provider
	 */
	protected BaseRDBMSMapper(PersistenceProvider provider)
	{
		if (provider == null)
		{
			throw new IllegalArgumentException("Provider reference can not be null");
		}
		this.provider = provider;
		logger = LogManager.getLogger(getClass());
	}

	/**
	 * Gets persistence provider.
	 * 
	 * @return
	 */
	protected final PersistenceProvider getProvider() throws PersistenceProviderException
	{
		if (ppf != null)
		{
			logger.info("Getting provider reference");
			provider = ppf.getProvider();
		}
		return provider;
	}

	/**
	 * Closes provider.
	 */
	protected final void closeProvider() throws PersistenceProviderException
	{
		if (ppf == null)
		{
			return;
		}
		if (provider.isClosed())
		{
			logger.info("Provider was closed before, ingoring close");
			return;
		}
		logger.info("Closing previously obtained provider reference");
		provider.close();
	}

	/**
	 * Gets logger for this mapper.
	 * 
	 * @return a logger for this mapper
	 */
	protected final Logger getLogger()
	{
		return logger;
	}

	/**
	 * Gets expression builder.
	 * 
	 * @return an expression builder
	 */
	protected static ExpressionBuilder getBuilder()
	{
		return DefaultExpressionBuilder.getBuilder();
	}

	/**
	 * Gets object of given class by identity.
	 * 
	 * @param clazz a canidate class
	 * @param identity an object identity
	 * @return found instance
	 * @throws PersistenceProviderException
	 */
	protected Object get(Class clazz, Object identity) throws PersistenceProviderException
	{
		try
		{
			PersistenceProvider provider = getProvider();
			return provider.getByIdentity(clazz, identity);
		}
		finally
		{
			closeProvider();
		}
	}

	/**
	 * Creates new object in the persistent storage.
	 * 
	 * @param object a new object
	 * @throws PersistenceProviderException
	 */
	protected void save(Object object) throws PersistenceProviderException
	{
		try
		{
			PersistenceProvider provider = getProvider();
			provider.makePersistent(object);
		}
		finally
		{
			closeProvider();
		}
	}

	/**
	 * Deletes persistent objects returned by query.
	 * 
	 * @param object an persistent hquery string
	 * @throws PersistenceProviderException
	 */
	protected void deleteByQuery(String hquery) throws PersistenceProviderException
	{
		try
		{
			PersistenceProvider provider = getProvider();
			provider.deleteByQuery(hquery);
		}
		finally
		{
			closeProvider();
		}
	}

	
	/**
	 * Deletes persistent object.
	 * 
	 * @param object an persistent object
	 * @throws PersistenceProviderException
	 */
	protected void delete(Object object) throws PersistenceProviderException
	{
		try
		{
			PersistenceProvider provider = getProvider();
			provider.delete(object);
		}
		finally
		{
			closeProvider();
		}
	}

	/**
	 * Helper method to execute find request from bussiness service tier.
	 * 
	 * @param candidateClass a canidate class
	 * @param criteria a search criteria
	 * @return of found instances
	 * @throws PersistenceProviderException
	 */
	protected Collection findByCriteria(Class candidateClass, SearchCriteria criteria)
		throws PersistenceProviderException
	{
		try
		{
			PersistenceProvider provider = getProvider();
			QueryCriteria queryCriteria = provider.createCriteria(candidateClass);
			ExpressionBuilder builder = DefaultExpressionBuilder.getBuilder();
			if (criteria != null)
			{
				Collection parameters = new LinkedList();
				BooleanExpression filter = QueryBuilder.buildANDExpression(builder, criteria);
				queryCriteria.setFilter(filter);
				queryCriteria.setLimit(
					(int) criteria.getAbsolutePosition(),
					(int) criteria.getMaxDisplayRows());
				// add order from search criteria
				addOrder(queryCriteria, criteria);
			}
			else
			{
				queryCriteria.setLimit(DEFAULT_FIRST_RESULT_INDEX, DEFAULT_MAX_RESULTS);
			}
			Query query = provider.newQuery(queryCriteria);
			return query.execute();
		}
		finally
		{
			closeProvider();
		}
	}

	/**
	 * Helper method to execute find request from bussiness service tier.
	 * 
	 * @param candidateClass a canidate class
	 * @param criteria a search criteria
	 * @return of found instances
	 * @throws PersistenceProviderException
	 */
	protected Collection findByCriteriaBindParams(Class candidateClass, SearchCriteria criteria)
		throws PersistenceProviderException
	{
		return findByCriteria(candidateClass, criteria);
	}

	/**
	 * Finds total number of instance wjo maches given criteria.
	 * 
	 * @param candidateClass a candidate class
	 * @param criteria a search criteria
	 * @return number of instances
	 * @throws PersistenceProviderException
	 */
	protected int getCount(Class candidateClass, SearchCriteria criteria)
		throws PersistenceProviderException
	{
		try
		{
			PersistenceProvider provider = getProvider();
			QueryCriteria queryCriteria = provider.createCriteria(candidateClass);
			ExpressionBuilder builder = DefaultExpressionBuilder.getBuilder();
			if (criteria != null)
			{				
				BooleanExpression filter = QueryBuilder.buildANDExpression(builder, criteria);
				queryCriteria.setFilter(filter);
			}
			return provider.getCount(queryCriteria);
		}
		finally
		{
			closeProvider();
		}
	}

	/**
	 * Executes named query.
	 * 
	 * @param name a query name
	 * @param parameters a query parameters
	 * @param firstIndex a first object index
	 * @param maxResults a maximum number of results to return
	 * @return collection of found borrower
	 * @throws PersistenceProviderException
	 */
	protected Collection executeNamedQuery(
		String name,
		Object[] parameters,
		Integer firstIndex,
		Integer maxResults)
		throws PersistenceProviderException
	{
		try
		{
			if (getLogger().isDebugEnabled())
			{
				logger.debug("Executing named query - " + name);
			}
			Query q = getProvider().newQuery(name);
			if (firstIndex != null && maxResults != null)
			{
				if (getLogger().isDebugEnabled())
				{
					logger.debug("First result index - " + firstIndex);
					logger.debug("Max results to return - " + maxResults);
				}
				q.setLimit(firstIndex.intValue(), maxResults.intValue());
			}
			if (parameters != null)
			{
				if (logger.isDebugEnabled())
				{
					logger.debug("With parameters array of size - " + parameters.length);
					for (int i = 0; i < parameters.length; i++)
					{
						logger.debug("Parameter at index - " + i + " value is - " + parameters[i]);
					}
				}
				q.setParameters(parameters);
			}
			return q.execute();
		}
		finally
		{
			closeProvider();
		}
	}

	/**
	 * Executes named query.
	 * 
	 * @param name a query name
	 * @param parameters query parameters
	 * @return found results
	 * @throws PersistenceProviderException
	 */
	protected Collection executeNamedQuery(String name, Object[] parameters)
		throws PersistenceProviderException
	{
		return executeNamedQuery(name, parameters, null, null);
	}

	/**
	 * Finds all maching instances.
	 * 
	 * @param candidateClass a candidate class
	 * @return collection of found instances
	 * @throws PersistenceProviderException
	 */
	protected Collection findAllInstances(Class candidateClass) throws PersistenceProviderException
	{
		try
		{
			PersistenceProvider provider = getProvider();
			QueryCriteria queryCriteria = provider.createCriteria(candidateClass);
			Query query = provider.newQuery(queryCriteria);
			return query.execute();
		}
		finally
		{
			closeProvider();
		}
	}

	/**
	 * Executes named query.
	 * 
	 * @param name a query name
	 * @param firstIndex a first result index
	 * @param maxResults a number of results to return
	 * @return collection of found results
	 * @throws PersistenceProviderException
	 */
	protected Collection executeNamedQuery(String name, Integer firstIndex, Integer maxResults)
		throws PersistenceProviderException
	{
		return executeNamedQuery(name, null, firstIndex, maxResults);
	}

	/**
	 * Executes filtered query.
	 * 
	 * @param clazz a cadnidate class
	 * @param filter a filter
	 * @param firstResultIndex a first result index
	 * @param maxResults a maximum number of result to return
	 * @return collection of found results
	 * @throws PersistenceProviderException
	 */
	protected Collection executeFilterQuery(
		Class clazz,
		BooleanExpression filter,
		Integer firstResultIndex,
		Integer maxResults)
		throws PersistenceProviderException
	{
		try
		{
			PersistenceProvider p = getProvider();
			QueryCriteria crit = p.createCriteria(clazz);
			crit.setFilter(filter);
			if ((firstResultIndex != null) || (maxResults != null))
			{
				crit.setLimit(firstResultIndex.intValue(), maxResults.intValue());
			}
			Query q = p.newQuery(crit);
			return q.execute();
		}
		finally
		{
			closeProvider();
		}
	}
	
	/**
	 * Executes filtered query with pessimistic locking.
	 * 
	 * @param clazz a cadnidate class
	 * @param filter a filter
	 * @return collection of found results
	 * @throws PersistenceProviderException
	 */
	
	protected Collection executeFilterQueryWithLock(
		Class clazz,
		BooleanExpression filter)
		throws PersistenceProviderException
	{
		try
		{
			PersistenceProvider p = getProvider();
			QueryCriteria crit = p.createCriteria(clazz);
			crit.setFilter(filter);
			
			Query q = p.newQuery(crit);
			
			q.setLockMode("this", LockMode.UPGRADE);
			return q.execute();
		}
		finally
		{
			closeProvider();
		}
		}

	/**
	 * Counts instances.
	 * 
	 * @param clazz a class
	 * @param filter a instances filter
	 * @return number of instances
	 * @throws PersistenceProviderException
	 */
	protected int getCount(Class clazz, BooleanExpression filter)
		throws PersistenceProviderException
	{
		try
		{
			PersistenceProvider p = getProvider();
			QueryCriteria crit = p.createCriteria(clazz);
			crit.setFilter(filter);
			return p.getCount(crit);
		}
		finally
		{
			closeProvider();
		}
	}

	/**
	 * Adds sort order to the given query criteria from give search criteria.
	 * 
	 * @param criteria a criteria
	 * @param uiCriteria an ui criteria
	 */
	private void addOrder(QueryCriteria criteria, SearchCriteria uiCriteria)
	{
		Collection items = uiCriteria.getCriteriaItems();
		Iterator it = items.iterator();
		while (it.hasNext())
		{
			SearchCriteriaItem item = (SearchCriteriaItem) it.next();
			if (item
				.getOperatorType()
				.equals(ServiceConstants.CriteriaOperatorTypes.SORT_ASCENDING))
			{				
				//criteria.addOrder(Order.asc(item.getFieldName()));
				//FIXME: changed to ingore case by default
				criteria.addOrder(Order.asc(item.getFieldName(), true));
			}
			// FIXME: code block bellow is a hack
			if (item
					.getOperatorType()
					.equals(ServiceConstants.CriteriaOperatorTypes.SORT_ASCENDING_CASESENS)){				
					//criteria.addOrder(Order.asc(item.getFieldName()));
					//FIXME: changed to ingore case by default
					criteria.addOrder(Order.asc(item.getFieldName(), false));
			}			
			if (item
				.getOperatorType()
				.equals(ServiceConstants.CriteriaOperatorTypes.SORT_DECENDING)){
				//criteria.addOrder(Order.desc(item.getFieldName()));
				//FIXME: changed to ingore case by default
				criteria.addOrder(Order.desc(item.getFieldName(), true));
			}
//			//FIXME: code block bellow is a hack
			if (item
					.getOperatorType()
					.equals(ServiceConstants.CriteriaOperatorTypes.SORT_DECENDING_CASESENS)){
					//criteria.addOrder(Order.desc(item.getFieldName()));
					//FIXME: changed to ingore case by default
					criteria.addOrder(Order.desc(item.getFieldName(), false));
				}
		}
	}
}
