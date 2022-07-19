package com.radian.foundation.os.persistence.spi.expression;

/**
 * Represents unary expression.
 * 
 * @author Giedrius Trumpickas
 */
public abstract class UnaryExpression implements Expression
{
	private Expression exp;
	
	UnaryExpression(Expression exp)
	{
		this.exp = exp;
	}
	
	/**
	 * Gets expression.
	 * 
	 * @return expression
	 */
	public final Expression getExp()
	{
		return exp;
	}
}
