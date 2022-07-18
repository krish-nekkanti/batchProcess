package com.radian.foundation.os.persistence.spi.expression;

/**
 * Base class for relational comparison expressions. 
 * 
 * @author Giedrius Trumpickas
 */
abstract class RelationalComparisonExpression extends BinaryExpression implements BooleanExpression
{
	/**
	 * Contructs releational comparison expression
	 * 
	 * @param left a left side expression
	 * @param right a right side expression
	 */
	public RelationalComparisonExpression(Expression left, Expression right)
	{
		super(left, right);				
	}
	
	/**	 
	 * @see com.radian.foundation.expression.BooleanExpression#and(com.radian.foundation.expression.BooleanExpression)
	 */
	public final BooleanExpression and(BooleanExpression exp)
	{
		return new AndExpression(this, exp);
	}
	
	/**	  
	 * @see com.radian.foundation.expression.BooleanExpression#or(com.radian.foundation.expression.BooleanExpression)
	 */
	public final BooleanExpression or(BooleanExpression exp)
	{		
		return new OrExpression(this, exp);
	}
}
