package com.radian.foundation.os.persistence.spi.expression;

/**
 * "Greater than or equals" expression node.
 * 
 * @author Giedrius Trumpickas
 */
public class GreaterThanEqualsExpression extends RelationalComparisonExpression
{
	/**
	 * Constructs "greather than or equals" expression
	 * 
	 * @param left a left side expression
	 * @param right a right side expression
	 */
	public GreaterThanEqualsExpression(Expression left, Expression right)
	{
		super(left, right);
	}

	/**
	 * @see com.radian.foundation.expression.Expression#accept(com.radian.foundation.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitGreaterThanEqualsExpression(this);
		visitor.visitGreaterThanEqualsExpressionChild(this, 0);
		getLeftExp().accept(visitor);
		visitor.leaveGreaterThanEqualsExpressionChild(this, 0);
		visitor.visitGreaterThanEqualsExpressionChild(this, 1);
		getRightExp().accept(visitor);
		visitor.leaveGreaterThanEqualsExpressionChild(this, 1);
		visitor.leaveGreaterThanEqualsExpression(this);
	}
}
