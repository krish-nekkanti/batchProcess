package com.radian.foundation.os.persistence.spi.expression;

/**
 * Node for "like pattern" expression. 
 * 
 * @author Giedrius Trumpickas
 */
public class LikeExpression extends BinaryExpression implements BooleanExpression
{
	/**
	 * Constructs like expression with given child expressions
	 * 
	 * @param exp1 a left side expression
	 * @param exp2 a right side expression
	 */
	public LikeExpression(Expression exp1, Expression exp2)
	{
		super(exp1, exp2);
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
		visitor.visitLikeExpression(this);
		visitor.visitLikeExpressionChild(this, 0);
		getLeftExp().accept(visitor);
		visitor.leaveLikeExpressionChild(this, 0);
		visitor.visitLikeExpressionChild(this, 1);
		getRightExp().accept(visitor);
		visitor.leaveLikeExpressionChild(this, 1);
		visitor.leaveLikeExpression(this);
	}
}
