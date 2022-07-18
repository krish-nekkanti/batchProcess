package com.radian.foundation.os.persistence.spi.expression;

import java.util.Collection;

/**
 * Represents values list expression.
 * 
 * @author Giedrius Trumpickas
 */
public class ValuesListExpression implements Expression
{
	/**
	 * List of values.
	 */
	private Collection values;
	
	/**
	 * Constructs values range expression with given values.
	 * 
	 * @param values a collection of values
	 */
	ValuesListExpression(Collection values)
	{
		this.values = values;	
	}
	
	/**
	 * Gets list values
	 * @return a list values
	 */
	public Collection getValues()
	{
		return values;	
	}
		
	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.Expression#accept(com.radian.foundation.os.persistence.spi.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitValuesListExpression(this);
		visitor.leaveValuesListExpression(this);	
	}
}
