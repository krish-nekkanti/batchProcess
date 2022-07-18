package com.radian.foundation.os.persistence.spi.expression;

/**
 * Is null expression node.
 * 
 * @author Giedrius Trumpickas
 */
public class IsNullExpression implements BooleanExpression
{
	private PropertyExpression property;
	
	/**
	 * Constructs "is null" expression for given property.
	 * 
	 * @param property a property expression
	 */
	public IsNullExpression(PropertyExpression property)
	{
		this.property = property;
	}
	
	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.BooleanExpression#and(com.radian.foundation.os.persistence.spi.expression.BooleanExpression)
	 */
	public BooleanExpression and(BooleanExpression exp)
	{
		return new AndExpression(this, exp);
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.BooleanExpression#or(com.radian.foundation.os.persistence.spi.expression.BooleanExpression)
	 */
	public BooleanExpression or(BooleanExpression exp)
	{
		return new OrExpression(this, exp);
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.Expression#accept(com.radian.foundation.os.persistence.spi.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitIsNullExpression(this);
		visitor.visitIsNullExpressionChild(this, 0);
		property.accept(visitor);
		visitor.leaveIsNullExpressionChild(this, 0);
		visitor.leaveIsNullExpression(this);
	}

}
