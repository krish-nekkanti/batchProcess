package com.radian.foundation.os.persistence.spi.expression;

/**
 * "Greather than" expression.
 * 
 * @author Giedrius Trumpickas
 */
public class GreaterThanExpression extends RelationalComparisonExpression
{	 		
	/**
	 * Constructs "greather than" expression
	 * 
	 * @param left a left side expression
	 * @param right a right side expression
	 */
	public GreaterThanExpression(
		Expression left,
		Expression right)
	{
		super(left, right);		
	}
			
	/**
	 * @see com.radian.foundation.expression.Expression#accept(com.radian.foundation.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{		
		visitor.visitGreaterThanExpression(this);
		visitor.visitGreaterThanExpressionChild(this, 0);
		getLeftExp().accept(visitor);
		visitor.leaveGreaterThanExpressionChild(this, 0);
		visitor.visitGreaterThanExpressionChild(this, 1);
		getRightExp().accept(visitor);
		visitor.leaveGreaterThanExpressionChild(this, 1);						
		visitor.leaveGreaterThanExpression(this);	
	}
}
