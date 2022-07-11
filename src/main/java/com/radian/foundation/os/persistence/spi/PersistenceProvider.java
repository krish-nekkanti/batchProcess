package com.radian.foundation.os.persistence.spi;

import java.sql.Connection;

/**
 * User level provider API.
 *  
 * @author Giedrius Trumpickas
 */
public interface PersistenceProvider
{
	/**
	 * Makes given instance persistent.
	 * 
	 * @param object an object instance
	 * @exception PersistenceProviderException
	 */
	void makePersistent(Object pObject) throws PersistenceProviderException;
	
	/**
	 * Deletes persisten instance. 
	 *
	 * @param pObject an persistent instance
	 * @exception PersistenceProviderException
	 */
	void delete(Object pObject) throws PersistenceProviderException;
	
	/**
	 * Deletes persisten instances returned by query. 
	 *
	 * @param pObject an persistent instance
	 * @exception PersistenceProviderException
	 */
	void deleteByQuery(String query) throws PersistenceProviderException;
	
	/**
	 * Refreshes object state from database.
	 * 
	 * @param pObject a persistence object
	 * @throws PersistenceProviderException
	 */
	void refresh(Object pObject) throws PersistenceProviderException;
	
	/**	 
	 * Gets object by identity.
	 * 
	 * @param candidateClass
	 * @param identity an object identity
	 * @return an instance of object
	 * @exception PersistenceProviderException
	 */
	Object getByIdentity(Class canidateClass, Object identity) throws PersistenceProviderException;

	/**
	 * Creates new query for given query criteria.
	 *	 	 
	 * @param criteria a query criteria	 
	 * @return query object for given query criteria
	 * @exception PersistenceProviderException if query creation fails 
	 */
	Query newQuery(QueryCriteria criteria) throws PersistenceProviderException;

	/**
	 * Returns named query instance.
	 * 
	 * @param name a query name
	 * @return named query instance
	 * @throws PersistenceProviderException
	 */
	Query newQuery(String name) throws PersistenceProviderException;
	
	
	Query newQuery(String name, Order[] order) throws PersistenceProviderException;
	
	/**
	 * Counts instances which maches given criteria.
	 * 
	 * @param criteria a criteria	 
	 * @return total number of instance which maches given filter
	 * @exception PersistenceProviderException if query instances counting fails 
	 */
	int getCount(QueryCriteria criteria) throws PersistenceProviderException;

	/**
	 * Creates query criteria for given canidate class.
	 * 
	 * @param canidateClass a canodate class
	 * @return query criteria
	 * @throws PersistenceProviderException if criteria creation fails
	 */
	QueryCriteria createCriteria(Class canidateClass) throws PersistenceProviderException;

	/**
	 * Synchronizes all changes made in session with data store.
	 * 
	 * @throws PersistenceProviderException
	 */
	void flush() throws PersistenceProviderException;

	/**
	 * Closes this persistence provider. 
	 *
	 * @throws PersistenceProviderException
	 */
	void close() throws PersistenceProviderException;

	/**
	 * Tests if this instance is closed.
	 * 	 
	 * @return <code>true</code> if this instance is closed	 
	 */
	boolean isClosed() throws PersistenceProviderException;

	/**
	 * Begins transaction.
	 * 
	 * @throws PersistenceProviderException
	 */
	void begin() throws PersistenceProviderException;

	/**
	 * Commits transaction.
	 * 
	 * @throws PersistenceProviderException
	 */
	void commit() throws PersistenceProviderException;

	/**
	 * Rollbacks currect transaction.
	 * 
	 * @throws PersistenceProviderException
	 */
	void rollback() throws PersistenceProviderException;
	
	/**
	 * Gets underlying JDBC connection.
	 * 
	 * @return
	 * @throws PersistenceProviderException
	 */
	Connection getConnection() throws PersistenceProviderException;
}
