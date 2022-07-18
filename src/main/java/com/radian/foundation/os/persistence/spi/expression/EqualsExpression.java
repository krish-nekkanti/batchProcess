package com.radian.foundation.os.persistence.spi.expression;

/**
 * Implementation represents "equals" expression.
 * 
 * @author Giedrius Trumpickas
 */
public class EqualsExpression extends RelationalComparisonExpression
{
	/**
	 * Constructs "equals" expression.
	 * 
	 * @param left a left side expression
	 * @param right a right side expression
	 */
	public EqualsExpression(Expression left, Expression right)
	{
		super(left, right);
	}
		
	/**	  
	 * @see com.radian.foundation.expression.Expression#accept(com.radian.foundation.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitEqualsExpression(this);
		visitor.visitEqualsExpressionChild(this, 0);
		getLeftExp().accept(visitor);
		visitor.leaveEqualsExpressionChild(this, 0);
		visitor.visitEqualsExpressionChild(this, 1);
		getRightExp().accept(visitor);
		visitor.leaveEqualsExpressionChild(this, 1);
		visitor.leaveEqualsExpression(this);
	}
}
