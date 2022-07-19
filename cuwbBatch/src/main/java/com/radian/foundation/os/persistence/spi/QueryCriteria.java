package com.radian.foundation.os.persistence.spi;

import com.radian.foundation.os.persistence.spi.expression.BooleanExpression;

/**
 * Query criteria for quering persistent instances.
 * 
 * @author Giedrius Trumpickas
 */
public interface QueryCriteria
{				
	/**
	 * Sets criteria filter.
	 * 
	 * @param filter a filter
	 */
	void setFilter(BooleanExpression filter);
	
	/**
	 * Adds sort order for this criteria
	 * 
	 * @param order a property sort order
	 */
	void addOrder(Order order);
	 
	
	/**
	 * Sets query result limit.
	 * 	 
	 * @param firstResult first result index
	 * @param maxResults a maximum number of results
	 */
	void setLimit(int firstResultIndex, int maxResults);			
}
