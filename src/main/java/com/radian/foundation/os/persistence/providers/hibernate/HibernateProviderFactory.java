package com.radian.foundation.os.persistence.providers.hibernate;

import java.sql.Connection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;
import com.radian.foundation.os.persistence.spi.PersistenceProviderFactory;

/**
 * Provider factory implementation for Hibernate.
 * 
 * @author Giedrius Trumpickas
 */
public final class HibernateProviderFactory implements PersistenceProviderFactory
{
	/**
	 * Hibernate session factory.
	 */
	private SessionFactory sessionFactory;
	
	/**
	 * Configuration
	 */
	private Configuration config;

	/**
	 * Constructs hibernate provider with given configuration.
	 * 
	 * @param configURI a configuration file URI
	 */
	public HibernateProviderFactory(String configURI) throws PersistenceProviderException
	{
		configure(configURI);
	}
	
	/**
	 * @see com.radian.foundation.os.persistence.spi.PersistenceProviderFactory#getProvider()
	 */
	public PersistenceProvider getProvider() throws PersistenceProviderException
	{
		try
		{
			Session session = sessionFactory.openSession();
			return new HibernateProvider(session);
		}
		catch (HibernateException e)
		{
			throw new PersistenceProviderException(e);
		}
	}
	
	/**
	 * Gets provider for specified JDBC connection.
	 * 
	 * @param connection a JDBC connection
	 * @return provider instance for given JDBC connection
	 * @throws PersistenceProviderException
	 */
	public PersistenceProvider getProvider(Connection connection)
		throws PersistenceProviderException
	{
		Session session = sessionFactory.openSession(connection);
		return new HibernateProvider(session);
	}
	
	/**
	 * Conigures session factory.
	 * 
	 * @param configURI
	 * @throws PersistenceProviderException
	 */
	private void configure(String configURI) throws PersistenceProviderException
	{
		try
		{
			config = new Configuration();
			config.configure(configURI);
			sessionFactory = config.buildSessionFactory();
		}
		catch (HibernateException e)
		{
			throw new PersistenceProviderException(e);
		}
	}
	
	/**
	 * Gets data source property value from configuration file.
	 * 
	 * @return data source JNDI name or <code>null</code> if such property is not specified
	 * @throws PersistenceProviderException
	 */
	public String getDataSourceName() throws PersistenceProviderException
	{
		if(config == null){
			throw new PersistenceProviderException("Unconfigured factory");
		}
		return config.getProperty("hibernate.connection.datasource");
	}
}
