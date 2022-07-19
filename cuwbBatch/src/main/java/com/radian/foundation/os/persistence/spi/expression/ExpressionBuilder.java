package com.radian.foundation.os.persistence.spi.expression;

import java.util.Collection;

/**
 * Expression builder interface.
 * 
 * @author Giedrius Trumpickas
 */
public interface ExpressionBuilder
{		
		
	BooleanExpression not(BooleanExpression exp);
	BooleanExpression and(BooleanExpression exp1, BooleanExpression exp2);
	BooleanExpression or(BooleanExpression exp1, BooleanExpression exp2);				
	
	//BooleanExpression between(String name);			
	
	//BooleanExpression equals(String name);
	BooleanExpression equals(String name, Object value);					
		
	//BooleanExpression notEquals(String name);	
	BooleanExpression notEquals(String name, Object value);			
							
	//BooleanExpression lessThan(String name);
	BooleanExpression lessThan(String name, Object value);								
	
	//BooleanExpression lessThanEqual(String name);
	BooleanExpression lessThanEqual(String name, Object value);	
					
	//BooleanExpression greaterThan(String name);
	BooleanExpression greaterThan(String name, Object value);
	
	//BooleanExpression greaterThanEqual(String name);
	BooleanExpression greaterThanEqual(String name, Object value);
						
	//BooleanExpression like(String name);
	BooleanExpression like(String name, String pattern);
	//BooleanExpression likeUpperCase(String name);
	BooleanExpression likeUpperCase(String name, String pattern);
	
	//BooleanExpression in(String name);
	BooleanExpression in(String name, Collection values);
	
	/**
	 * Example: select d from Department d, People where p in s( elements(d.people))
	 */
	BooleanExpression in(String propertyName, String associationName);
	
	BooleanExpression isNull(String name);
}
