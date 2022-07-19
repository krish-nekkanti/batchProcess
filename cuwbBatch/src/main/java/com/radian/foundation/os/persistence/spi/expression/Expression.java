package com.radian.foundation.os.persistence.spi.expression;

import java.io.Serializable;


/**
 * Base class for filter expression AST nodes.
 * 
 * @author Giedrius Trumpickas
 */
public interface Expression extends Serializable
{
	/**
	 * Accepts <code>ExpressionVisitor</code>
	 * 
	 * @param visitor a visitor
	 */	
	void accept(ExpressionVisitor visitor);		
}
