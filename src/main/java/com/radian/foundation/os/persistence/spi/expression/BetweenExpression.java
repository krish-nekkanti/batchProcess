package com.radian.foundation.os.persistence.spi.expression;

/**
 * Represents "between" expression node.
 * 
 * @author Giedrius Trumpickas
 */
public final class BetweenExpression implements BooleanExpression
{
	private Expression min;
	private Expression max;
	private Expression arg;

	/**
	 * Constructs between expression.
	 * 
	 * @param arg an argument
	 * @param min a left side range value expression
	 * @param max a right side range value expression
	 */
	BetweenExpression(Expression arg, Expression min, Expression max)
	{
		this.arg = arg;
		this.min = min;
		this.max = max;
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
		visitor.visitBetweenExpression(this);
		visitor.visitBetweenExpressionChild(this, 0);
		arg.accept(visitor);
		visitor.leaveBetweenExpressionChild(this, 0);
		visitor.visitBetweenExpressionChild(this, 1);
		min.accept(visitor);
		visitor.leaveBetweenExpressionChild(this, 1);
		visitor.visitBetweenExpressionChild(this, 2);
		max.accept(visitor);
		visitor.leaveBetweenExpressionChild(this, 2);
		visitor.leaveBetweenExpression(this);
	}
}
