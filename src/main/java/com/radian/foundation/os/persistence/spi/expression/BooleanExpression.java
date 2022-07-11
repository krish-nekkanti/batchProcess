package com.radian.foundation.os.persistence.spi.expression;


/**
 * Represents boolean expression interface.
 * 
 * @author Giedrius Trumpickas
 */
public interface BooleanExpression extends Expression
{		
	BooleanExpression and(BooleanExpression exp);
	BooleanExpression or(BooleanExpression exp);					
}
