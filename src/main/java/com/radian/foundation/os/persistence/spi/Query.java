package com.radian.foundation.os.persistence.spi;

import java.util.Collection;

import net.sf.hibernate.LockMode;

/**
 * Represents query on a persistent instances.
 * 
 * @author Giedrius Trumpickas
 */
public interface Query
{	
	/**
	 * Sets query parameters.
	 * 
	 * @param parameters a list of parameters
	 * @exception QueryException if parameters binding failed
	 */	
	void setParameters(Object[] parameters) throws QueryException;
	
	/**
	 * Sets named parameter value to this query.
	 * 
	 * @param name a prameter name
	 * @param value a parameter value
	 * @throws QueryException
	 */
	void setNamedParameter(String name, Object value) throws QueryException;
	
	/**
	 * Sets named collection as named parameter value.
	 * 
	 * @param name a parameter name
	 * @param values a collection of values
	 * @throws QueryException
	 */
	void setNamedParameterList(String name, Collection values) throws QueryException;
	
	/**
	 * Sets this query limit.
	 * 
	 * @param firstResultIndex a first result index
	 * @param maxResults a maximum number of results
	 * @exception QueryException if this query does not support limitation
	 */
	void setLimit(int firstResultIndex, int maxResults) throws QueryException;
	
	/**
	 * Executes query and returns found instances.
	 * This methid materializes all instances with all relationships.
	 * 	  
	 * @return collection of found instances
	 * @throws QueryException if query execution fails
	 */
	Collection execute() throws QueryException;
	
	/**
	 * Executes query and return iterator to found instances.
	 * This method does lazy instances loadin when iterating.
	 * 
	 * @return iterator to found instances
	 * @throws QueryException
	 */
	 ObjectSet iterate() throws QueryException;	
	 
	 /**
	 * Sets the lock mode for this query.
	 * 
	 * @param arg0 a String for query alias
	 * @param arg a LockMode
	 * @exception QueryException if this query does not support limitation
	 */
	 void setLockMode(String arg0, LockMode arg1) throws QueryException;
	 
	 /**
	  * return sql query string
	  * 
	  * @return
	  */
	 String getQueryString();
	 
}
