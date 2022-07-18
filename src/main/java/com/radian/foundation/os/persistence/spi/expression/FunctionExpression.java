package com.radian.foundation.os.persistence.spi.expression;

/**
 * Base class for function expressions.
 * 
 * @author Giedrius Trumpickas
 */
public abstract class FunctionExpression implements Expression
{
	/**
	 * Function argument.
	 */
	private Expression exp;
	
	/**
	 * Constructs function expression with given argument.
	 * 
	 * @param arg a function argument expression
	 */
	public FunctionExpression(Expression arg)
	{
		this.exp = arg;
	}
	
	/**
	 * Gets function argument expression.
	 * 
	 * @return function argument expression
	 */
	public Expression getExpression()
	{
		return exp;
	}
}
