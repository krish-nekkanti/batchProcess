package com.radian.foundation.os.persistence.spi.expression;

/** 
 * Represents boolean "not" expression.
 * 
 * @author Giedrius Trumpickas
 */
public final class NotExpression implements BooleanExpression
{
	/**
	 * Boolean expression
	 */
	private BooleanExpression boolExp;

	/**
	 * Constructs boolean "not" expression with given boolean expression.
	 * 
	 * @param boolExp a boolean expression
	 */
	public NotExpression(BooleanExpression boolExp)
	{
		this.boolExp = boolExp;
	}

	/**
	 * Gets boolean expression
	 * 
	 * @return boolean expression
	 */
	public BooleanExpression getBoolExp()
	{
		return boolExp;
	}
	
	/**	  
	 * @see com.radian.foundation.expression.BooleanExpression#and(com.radian.foundation.expression.BooleanExpression)
	 */
	public BooleanExpression and(BooleanExpression exp)
	{
		return new AndExpression(this, exp);
	}
		
	/**	  
	 * @see com.radian.foundation.expression.BooleanExpression#or(com.radian.foundation.expression.BooleanExpression)
	 */
	public BooleanExpression or(BooleanExpression exp)
	{
		return new OrExpression(this, exp);
	}

	/**
	 * @see com.radian.foundation.expression.Expression#accept(com.radian.foundation.expression.ExpressionVisitor)
	 */
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visitNotExpression(this);
		visitor.visitNotExpressionChild(this, 0);
		getBoolExp().accept(visitor);
		visitor.leaveNotExpressionChild(this, 0);
		visitor.leaveNotExpression(this);
	}
}
