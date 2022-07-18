package com.radian.foundation.os.persistence.spi.expression;

/**
 * Represents binary expression.
 * 
 * @author Giedrius Trumpickas
 */
abstract class BinaryExpression implements Expression
{
	/**
	 * Left side expression.
	 */
	private Expression leftExp;

	/**
	 * Right side expression.
	 */
	private Expression rightExp;

	/**
	 * Constructs binary expression.
	 * 
	 * @param lexp a left side expression
	 * @param rexp a right side expression
	 */
	protected BinaryExpression(Expression lexp, Expression rexp)
	{
		this.leftExp = lexp;
		this.rightExp = rexp;
	}

	/**
	 * Gets left side expression.
	 * 
	 * @return left side expression
	 */
	public final Expression getLeftExp()
	{
		return leftExp;
	}

	/**
	 * Gets right side  expression.
	 * 
	 * @return right side expression
	 */
	public final Expression getRightExp()
	{
		return rightExp;
	}

	/**	 
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public final boolean equals(Object other)
	{
		if (other == null) return false;
		if (other == this) return true;
		if(!other.getClass().equals(getClass()))
		{
			return false;
		}
		// because this class and other classes are equal
		// and we are in binary expression we can asume that oother class
		// was derived from binary expression as well
		BinaryExpression otherExp = (BinaryExpression)other;
		return getLeftExp().equals(otherExp.getLeftExp())
			&& getRightExp().equals(otherExp.getRightExp());
	}
		
	/**	  
	 * @see java.lang.Object#hashCode()
	 */
	public final int hashCode()
	{
		return getLeftExp().hashCode() ^ getRightExp().hashCode();
	}
}
