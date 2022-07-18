package com.radian.foundation.os.persistence.spi.expression;

/**
 * Represents elements expression.
 * 
 * @author Giedrius Trumpickas
 */
public final class ElementsExpression extends UnaryExpression
{
	/**
	 * Constructs elements expression.
	 * 
	 * @param exp
	 */
	public ElementsExpression(PropertyExpression exp)
	{
		super(exp);
	}
	
	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.Expression#accept(com.radian.foundation.os.persistence.spi.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitElementsExpression(this);
		visitor.visitElementsExpressionChild(this, 0);
		getExp().accept(visitor);
		visitor.leaveElementsExpressionChild(this, 0);
		visitor.leaveElementsExpression(this);
	}
}
