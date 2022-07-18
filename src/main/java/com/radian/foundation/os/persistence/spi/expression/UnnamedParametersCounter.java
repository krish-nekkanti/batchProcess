package com.radian.foundation.os.persistence.spi.expression;

/**  
 * Counts unnamed parameters in given expression.
 * 
 * @author Giedrius Trumpickas
 */
public final class UnnamedParametersCounter extends BaseExpressionVisitor
{
	/**
	 * Parameters count.
	 */
	private int count;
	
	/**
	 * Counts unnamed parameters in given expression.
	 * 
	 * @param exp an expression
	 * @return parameters count
	 */
	public int getParametersCount(Expression exp)
	{
		count = 0;
		exp.accept(this);
		return count;		
	}
			
	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionVisitor#visitUnnamedParameterExpression(com.radian.foundation.os.persistence.spi.expression.UnnamedParameterExpression)
	 */
	public void visitUnnamedParameterExpression(UnnamedParameterExpression exp)
	{		
		count++;
	}
}
