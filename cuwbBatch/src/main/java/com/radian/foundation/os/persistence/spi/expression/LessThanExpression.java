package com.radian.foundation.os.persistence.spi.expression;

/**
 * Represents "less than" expression node.
 * 
 * @author Giedrius Trumpickas
 */
public class LessThanExpression extends RelationalComparisonExpression
{
		 		
	/**
	 * Constructs "less than" expression
	 * 
	 * @param left a left side expression
	 * @param right a right side expression
	 */
	public LessThanExpression(
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
		visitor.visitLessThanExpression(this);
		visitor.visitLessThanExpressionChild(this, 0);
		getLeftExp().accept(visitor);
		visitor.leaveLessThanExpressionChild(this, 0);
		visitor.visitLessThanExpressionChild(this, 1);
		getRightExp().accept(visitor);
		visitor.leaveLessThanExpressionChild(this, 1);						
		visitor.leaveLessThanExpression(this);
	}
}
