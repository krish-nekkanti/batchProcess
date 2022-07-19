package com.radian.foundation.os.persistence.spi.expression;

/**
 * Represents property expression.
 * 
 * @author Giedrius Trumpickas
 */
public class PropertyExpression implements Expression
{
	/**
	 * Property name.	 
	 */
	private String propertyName;
				
	/**
	 * Constructs property expression.
	 * 
	 * @param name a property name
	 */	
	public PropertyExpression(String name)
	{
		this.propertyName = name;				
	}
					
	/**
	 * Gets property name.
	 * 
	 * @return a property name
	 */		
	public final String getPropertyName()
	{
		return propertyName;
	}
				
	/**	  
	 * @see com.radian.foundation.expression.Expression#accept(com.radian.foundation.expression.ExpressionVisitor)
	 */	
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitPropertyExpression(this);		
		visitor.leavePropertyExpression(this);		
	}			
}
