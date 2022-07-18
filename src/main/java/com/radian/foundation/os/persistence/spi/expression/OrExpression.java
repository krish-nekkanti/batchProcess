package com.radian.foundation.os.persistence.spi.expression;

/**
 * Implementation represents boolean "or" expression.
 * 
 * @author Giedrius Trumpickas
 */
public final class OrExpression
	extends BinaryExpression
	implements BooleanExpression
{
	/**
	 * Constructs boolean "or" expression with given two boolean expressions.
	 * 
	 * @param lexp a left side boolean expression
	 * @param rexp a right side boolean expression
	 */
	public OrExpression(BooleanExpression lexp, BooleanExpression rexp)
	{
		super(lexp, rexp);
	}
	
	/**	  
	 * @see com.radian.foundation.expression.BooleanExpression#and(com.radian.foundation.expression.BooleanExpression)
	 */
	public final BooleanExpression and(BooleanExpression exp)
	{
		return new AndExpression(this, exp);
	}
		
	/**	 
	 * @see com.radian.foundation.expression.BooleanExpression#or(com.radian.foundation.expression.BooleanExpression)
	 */
	public final BooleanExpression or(BooleanExpression exp)
	{
		return new OrExpression(this, exp);
	}

	/**	  
	 * @see com.radian.foundation.expression.Expression#accept(com.radian.foundation.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitOrExpression(this);
		visitor.visitOrExpressionChild(this, 0);
		getLeftExp().accept(visitor);
		visitor.leaveOrExpressionChild(this, 0);
		visitor.visitOrExpressionChild(this, 1);
		getRightExp().accept(visitor);
		visitor.leaveOrExpressionChild(this, 1);
		visitor.leaveOrExpression(this);
	}
}
