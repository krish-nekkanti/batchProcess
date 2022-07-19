package com.radian.foundation.os.persistence.spi.expression;

import java.util.Collection;

/**
 * Base expression builder.
 * 
 * @author Giedrius Trumpickas
 */
public class DefaultExpressionBuilder implements ExpressionBuilder
{	
	/**
	 * Single builder instance
	 */
	private final static ExpressionBuilder instance = new DefaultExpressionBuilder();
		
	/**
	 * TODO: make it private. Reason why it's protected
	 * is documentum expression builder.	 
	 */
	protected DefaultExpressionBuilder()
	{
	}
	
	/**
	 * Gets default builder instance.
	 * 
	 * @return default builder instance
	 */
	public static ExpressionBuilder getBuilder()
	{
		return instance;	
	}
		
	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#and(com.radian.foundation.os.persistence.spi.expression.BooleanExpression, com.radian.foundation.os.persistence.spi.expression.BooleanExpression)
	 */
	public BooleanExpression and(
		BooleanExpression exp1,
		BooleanExpression exp2)
	{
		return new AndExpression(exp1, exp2);
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#between(java.lang.String)
	 */
	public BooleanExpression between(String name)
	{
		return new BetweenExpression(new PropertyExpression(name), new UnnamedParameterExpression(), new UnnamedParameterExpression());
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#equals(java.lang.String, java.lang.Object)
	 */
	public BooleanExpression equals(String name, Object value)
	{
		return new EqualsExpression(new PropertyExpression(name), new ValueExpression(value));
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#equals(java.lang.String)
	 */
	public BooleanExpression equals(String name)
	{
		return new EqualsExpression(new PropertyExpression(name), new UnnamedParameterExpression());
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#greaterThan(java.lang.String, java.lang.Object)
	 */
	public BooleanExpression greaterThan(String name, Object value)
	{
		return new GreaterThanExpression(new PropertyExpression(name), new ValueExpression(value));
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#greaterThan(java.lang.String)
	 */
	public BooleanExpression greaterThan(String name)
	{
		return new GreaterThanExpression(new PropertyExpression(name), new UnnamedParameterExpression());
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#greaterThanEqual(java.lang.String, java.lang.Object)
	 */
	public BooleanExpression greaterThanEqual(String name, Object value)
	{
		return new GreaterThanEqualsExpression(new PropertyExpression(name), new ValueExpression(value));		
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#greaterThanEqual(java.lang.String)
	 */
	public BooleanExpression greaterThanEqual(String name)
	{
		return new GreaterThanEqualsExpression(new PropertyExpression(name), new UnnamedParameterExpression());
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#in(java.lang.String, java.util.Collection)
	 */
	public BooleanExpression in(String name, Collection values)
	{
		return new InExpression(new PropertyExpression(name), new ValuesListExpression(values));
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#in(java.lang.String)
	 */
	public BooleanExpression in(String name)
	{
		return new InExpression(new PropertyExpression(name), new UnnamedParameterExpression());		
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#lessThan(java.lang.String, java.lang.Object)
	 */
	public BooleanExpression lessThan(String name, Object value)
	{
		return new LessThanExpression(new PropertyExpression(name), new ValueExpression(value));
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#lessThan(java.lang.String)
	 */
	public BooleanExpression lessThan(String name)
	{
		return new LessThanExpression(new PropertyExpression(name), new UnnamedParameterExpression());	
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#lessThanEqual(java.lang.String, java.lang.Object)
	 */
	public BooleanExpression lessThanEqual(String name, Object value)
	{
		return new LessThanEqualsExpression(new PropertyExpression(name), new ValueExpression(value));		
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#lessThanEqual(java.lang.String)
	 */
	public BooleanExpression lessThanEqual(String name)
	{
		return new LessThanEqualsExpression(new PropertyExpression(name), new UnnamedParameterExpression());
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#like(java.lang.String, java.lang.String)
	 */
	public BooleanExpression like(String name, String pattern)
	{
		return new LikeExpression(new ToUpperCaseExpression(new PropertyExpression(name)), new ToUpperCaseExpression(new ValueExpression(pattern)));
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#like(java.lang.String)
	 */
	public BooleanExpression like(String name)
	{
		return new LikeExpression(new ToUpperCaseExpression(new PropertyExpression(name)), new ToUpperCaseExpression(new UnnamedParameterExpression()));
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#not(com.radian.foundation.os.persistence.spi.expression.BooleanExpression)
	 */
	public BooleanExpression not(BooleanExpression exp)
	{
		return new NotExpression(exp);
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#notEquals(java.lang.String, java.lang.Object)
	 */
	public BooleanExpression notEquals(String name, Object value)
	{
		return new NotEqualsExpression(new PropertyExpression(name), new ValueExpression(value));
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#notEquals(java.lang.String)
	 */
	public BooleanExpression notEquals(String name)
	{
		return new NotEqualsExpression(new PropertyExpression(name), new UnnamedParameterExpression());
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#or(com.radian.foundation.os.persistence.spi.expression.BooleanExpression, com.radian.foundation.os.persistence.spi.expression.BooleanExpression)
	 */
	public BooleanExpression or(BooleanExpression exp1, BooleanExpression exp2)
	{
		return new OrExpression(exp1, exp2);
	}	
	
	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#isNull(java.lang.String)
	 */
	public BooleanExpression isNull(String name)
	{
		return new IsNullExpression(new PropertyExpression(name));
	}
	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#likeUpperCase(java.lang.String, java.lang.String)
	 */
	public BooleanExpression likeUpperCase(String name, String pattern)
	{
		return new LikeExpression(new ToUpperCaseExpression(new PropertyExpression(name)), new ToUpperCaseExpression(new ValueExpression(pattern)));
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#likeUpperCase(java.lang.String)
	 */
	public BooleanExpression likeUpperCase(String name)
	{
		return new LikeExpression(new ToUpperCaseExpression(new PropertyExpression(name)), new ToUpperCaseExpression(new UnnamedParameterExpression()));
	}

	/**
	 * @see com.radian.foundation.os.persistence.spi.expression.ExpressionBuilder#in(java.lang.String, java.lang.String)
	 */
	public BooleanExpression in(String propertyName, String associationName)
	{
		return new InExpression(new PropertyExpression(propertyName), new ElementsExpression(new PropertyExpression(associationName)));
	}
}
