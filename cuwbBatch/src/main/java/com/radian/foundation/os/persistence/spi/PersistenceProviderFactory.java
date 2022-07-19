
package com.radian.foundation.os.persistence.spi;

import java.sql.Connection;

/**
 * Factory for obtaining provider instances.
 * 
 * @author Giedrius Trumpickas
 */
public interface PersistenceProviderFactory
{
	/**
	 * Creates instance of persistence provider.
	 * 
	 * @return instance of persistence porvider
	 * @exception PersistenceProviderException if provider can not be created
	 */
	PersistenceProvider getProvider() throws PersistenceProviderException;
	
	/**
	 * Creates instance of persistence provider.
	 * 
	 * @return instance of persistence porvider
	 * @exception PersistenceProviderException if provider can not be created
	 */
	PersistenceProvider getProvider(Connection conn) throws PersistenceProviderException;
}
