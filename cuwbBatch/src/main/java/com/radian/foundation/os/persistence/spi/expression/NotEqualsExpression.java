package com.radian.foundation.os.persistence.spi.expression;

/**
 * Implementation represents "not equals" expression.
 * 
 * @author Giedrius Trumpickas
 */
public class NotEqualsExpression extends RelationalComparisonExpression
{	
	/**
	 * Constructs "not equals" expression.
	 * 
	 * @param left a left side expression
	 * @param right a right side expression
	 */
	public NotEqualsExpression(Expression left, Expression right)
	{
		super(left, right);	
	}
			
	/**	  
	 * @see com.radian.foundation.expression.Expression#accept(com.radian.foundation.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{		
		visitor.visitNotEqualsExpression(this);
		visitor.visitNotEqualsExpressionChild(this, 0);
		getLeftExp().accept(visitor);
		visitor.leaveNotEqualsExpressionChild(this, 0);
		visitor.visitNotEqualsExpressionChild(this, 1);		
		getRightExp().accept(visitor);		
		visitor.leaveNotEqualsExpressionChild(this, 1);
		visitor.leaveNotEqualsExpression(this);
	}				
}