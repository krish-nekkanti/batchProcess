package com.radian.foundation.os.persistence.spi.expression;

/**
 * To upper case function expression.
 * 
 * @author Giedrius Trumpickas
 */
public class ToUpperCaseExpression extends FunctionExpression
{
	/**
	 * @param arg
	 */
	public ToUpperCaseExpression(Expression arg)
	{
		super(arg);
	}
	
	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.Expression#accept(com.radian.foundation.os.persistence.spi.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitToUpperCaseExpression(this);
		visitor.visitToUpperCaseExpressionChild(this, 0);
		getExpression().accept(visitor);
		visitor.leaveToUpperCaseExpressionChild(this, 0);
		visitor.leaveToUpperCaseExpression(this);
	}
}
