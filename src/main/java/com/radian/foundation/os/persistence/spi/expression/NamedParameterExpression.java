package com.radian.foundation.os.persistence.spi.expression;

/**
 * Represents named prameter expression
 * 
 * @author Giedrius Trumpickas
 */
public final class NamedParameterExpression	implements ParameterExpression
{
	/**
	 * Parameter name.
	 */
	private String name;

	/**
	 * Constructs named parameter expception with given name.
	 * 
	 * @param name a parameter name
	 */
	public NamedParameterExpression(String name)
	{
		this.name = name;
	}

	/**
	 * Gets parameter name.
	 * 
	 * @return parmeter name
	 */
	public String getName()
	{
		return name;
	}
	
	/**	  
	 * @see com.radian.foundation.expression.Expression#accept(com.radian.foundation.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitNamedParameterExpression(this);
		visitor.visitNamedParameterExpressionChild(this, 0);
		visitor.leaveNamedParameterExpressionChild(this, 0);
		visitor.leaveNamedParameterExpression(this);			
	}
}
