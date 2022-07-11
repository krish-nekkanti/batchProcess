package com.radian.foundation.bs;

import java.util.Collection;

import com.radian.foundation.common.logging.LogManager;
import com.radian.foundation.common.logging.Logger;
import com.radian.foundation.os.persistence.spi.expression.BooleanExpression;
import com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder;

/**
 * Helper methods for dynamically creating an OQL/"Axiom Persistence Framework"
 * queries based on the Business Service Framework Criteria builder. The
 * Business Service is a simpler/more narrow version(on purpose, by design) of
 * the OQL framework
 * 
 * @author nines
 *  
 */
public class QueryBuilder
{
	private final static Logger logger = LogManager.getLogger(QueryBuilder.class);
	
	/**
	 *  
	 */
	public QueryBuilder()
	{
		super();
	}

	/**
	 * Builds a chain of BooleanExpression AND statements based on an
	 * expression builder and search critera
	 * 
	 * @param builder created by the persistence framework to build OQL
	 *            expressions
	 * @param criteria passed to the business service
	 * @return an OQL BooleanExpression to be used to execute a query
	 *  
	 */
	public static BooleanExpression buildANDExpression
	(
		ExpressionBuilder builder,
		SearchCriteria criteria)
	{

		BooleanExpression rootExpression = null;

		if (criteria != null)
		{

			Collection col = criteria.getCriteriaItems();
			if(logger.isDebugEnabled())
			{
				logger.debug("Iterating through search criteria items");
			}
			java.util.Iterator it = col.iterator();
			while (it.hasNext())
			{

				SearchCriteriaItem critItem = (SearchCriteriaItem) it.next();
				String fieldName = critItem.getFieldName();
				Collection data = critItem.getCriteriaData();
				
				Object o = null;
				if (data != null)
				{
					if (data.size() == 1)
					{
						o = data.iterator().next();
					}

				}
				ServiceConstants.CriteriaOperatorTypes.OperatorType opType = critItem.getOperatorType();
				if(logger.isDebugEnabled())
				{
					logger.debug("Found criteria item with field name - " + fieldName + " operator type - " + opType);
				}
				BooleanExpression exp = null;
				if (opType.equals(ServiceConstants.CriteriaOperatorTypes.EQUALS))
				{
					if(logger.isDebugEnabled())
					{
						logger.debug("Building EQUALS expression");
					}
					exp = builder.equals(fieldName, o);
				}
				else
					if (opType.equals(ServiceConstants.CriteriaOperatorTypes.LIKE))
					{
						if (o instanceof String)
						{
							if(logger.isDebugEnabled())
							{
								logger.debug("Building LIKE expression");
							}
							exp = builder.like(fieldName, o.toString() + "%");
						}

					}
					else
						if (opType.equals(ServiceConstants.CriteriaOperatorTypes.NOT_EQUAL))
						{
							if(logger.isDebugEnabled())
							{
								logger.debug("Building NOT_EQUAL expression");
							}
							exp = builder.notEquals(fieldName, o);
						}
						else
							if (opType.equals(ServiceConstants.CriteriaOperatorTypes.GREATER_THAN))
							{
								if(logger.isDebugEnabled())
								{
									logger.debug("Building GREATER_THAN expression");
								}
								exp = builder.greaterThan(fieldName, o);

							}
							else
								if (opType
									.equals(ServiceConstants.CriteriaOperatorTypes.LESS_THAN))
								{
									if(logger.isDebugEnabled())
									{
										logger.debug("Building LESS_THAN expression");
									}
									exp = builder.lessThan(fieldName, o);

								}
								else
									if (opType.equals(ServiceConstants.CriteriaOperatorTypes.IN))
									{
										if(logger.isDebugEnabled())
										{
											logger.debug("Building IN expression");
											logger.debug("IN expression value - " + data);
										}
										exp = builder.in(fieldName, data);

									}
									else
										if (opType
											.equals(ServiceConstants.CriteriaOperatorTypes.OR))
										{
											if(logger.isDebugEnabled())
											{
												logger.debug("Building OR expression");
												logger.warn("This expression is not supported");
											}
										}

				if (rootExpression == null)
				{
					rootExpression = exp;
				}
				else
					if (exp != null)
					{
						rootExpression = rootExpression.and(exp);
					}
					else
					{
						logger.warn("Expression is null");
					}
			}
		}
		return rootExpression;
	}
	
	/**
	 * Gets item data.
	 * 
	 * @param item a item
	 * @return item data
	 */
	private static Object getItemData(SearchCriteriaItem item)
	{
		if ((item == null) || item.getCriteriaData() == null)
		{
			return null;
		}
		if (item.getCriteriaData().size() == 1)
		{
			return item.getCriteriaData().iterator().next();
		}
		return item.getCriteriaData();
	}
}
