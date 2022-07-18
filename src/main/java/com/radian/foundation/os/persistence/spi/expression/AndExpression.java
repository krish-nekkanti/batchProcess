package com.radian.foundation.os.persistence.spi.expression;

/**
 * Boolean "and" expression.
 * 
 * @author Giedrius Trumpickas
 */
public final class AndExpression extends BinaryExpression implements BooleanExpression
{
	/**
	 * Constructs "and" expression with given boolean expressions.
	 * 
	 * @param lexp a left side boolean expression
	 * @param rexp a right side boolean expression
	 */
	public AndExpression(BooleanExpression lexp, BooleanExpression rexp)
	{
		super(lexp, rexp);
	}

	/**
	 * @see com.radian.foundation.os.expression.BooleanExpression#and(com.radian.foundation.expression.BooleanExpression)
	 */
	public BooleanExpression and(BooleanExpression exp)
	{
		return new AndExpression(this, exp);
	}

	/**
	 * @see com.radian.foundation.os.persistence.expression.BooleanExpression#or(com.radian.foundation.expression.BooleanExpression)
	 */
	public BooleanExpression or(BooleanExpression exp)
	{
		return new OrExpression(this, exp);
	}

	/**
	 * @see com.radian.foundation.os.persistence.expression.Expression#accept(com.radian.foundation.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitAndExpression(this);
		visitor.visitAndExpressionChild(this, 0);
		getLeftExp().accept(visitor);
		visitor.leaveAndExpressionChild(this, 0);
		visitor.visitAndExpressionChild(this, 1);
		getRightExp().accept(visitor);
		visitor.leaveAndExpressionChild(this, 1);
		visitor.leaveAndExpression(this);
	}
}
