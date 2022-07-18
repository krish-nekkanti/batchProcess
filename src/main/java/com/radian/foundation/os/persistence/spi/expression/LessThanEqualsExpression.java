package com.radian.foundation.os.persistence.spi.expression;

/**
 * Represents "less than or equals" expression node.
 * 
 * @author Giedrius Trumpickas
 */
public class LessThanEqualsExpression extends RelationalComparisonExpression
{
		 		
	/**
	 * Constructs "less than or equals" expression
	 * 
	 * @param left a left side expression
	 * @param right a right side expression
	 */
	public LessThanEqualsExpression(
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
		visitor.visitLessThanEqualsExpression(this);
		visitor.visitLessThanEqualsExpressionChild(this, 0);
		getLeftExp().accept(visitor);
		visitor.leaveLessThanEqualsExpressionChild(this, 0);
		visitor.visitLessThanEqualsExpressionChild(this, 1);
		getRightExp().accept(visitor);
		visitor.leaveLessThanEqualsExpressionChild(this, 1);						
		visitor.leaveLessThanEqualsExpression(this);
	}
}
