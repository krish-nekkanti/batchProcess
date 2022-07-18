package com.radian.foundation.os.persistence.spi.expression;

/**
 * Represents value expression.
 * 
 * @author Giedrius Trumpickas
 */
public class ValueExpression implements Expression
{
	/**
	 * An expression value 	 
	 */
	private Object value;
								
	/**
	 * Constructs value expressin with given value 
	 * 
	 * @param value a value
	 */	
	public ValueExpression(Object value)
	{		
		this.value = value;
	}
					
	/**
	 * Gets value.
	 * 
	 * @return value
	 */			
	public final Object getValue()
	{
		return value;
	}	
	
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitValueExpression(this);		
		visitor.leaveValueExpression(this);					
	}
}
