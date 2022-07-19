package com.radian.foundation.os.persistence.spi.expression;

/**
 * @author Giedrius Trumpickas
 */
public class UnnamedParameterExpression	 implements ParameterExpression
{
	
	/**
	 * @see com.radian.foundation.expression.Expression#accept(com.radian.foundation.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitUnnamedParameterExpression(this);		
		visitor.leaveUnnamedParameterExpression(this);		
	}
}
