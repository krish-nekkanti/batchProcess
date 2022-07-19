package com.radian.foundation.os.persistence.spi.expression;

/**
 * Represents "IN" expression.
 * 
 * @author Giedrius Trumpickas
 */
public class InExpression extends BinaryExpression implements BooleanExpression
{	
	InExpression(Expression valueExp, Expression setExp)
	{
		super(valueExp, setExp);
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
		visitor.visitInExpression(this);
		visitor.visitInExpressionChild(this, 0);
		getLeftExp().accept(visitor);
		visitor.leaveInExpressionChild(this, 0);
		visitor.visitInExpressionChild(this, 1);
		getRightExp().accept(visitor);
		visitor.leaveInExpressionChild(this, 1);
		visitor.leaveInExpression(this);		
	}	 
}
