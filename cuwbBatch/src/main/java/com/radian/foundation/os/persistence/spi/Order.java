package com.radian.foundation.os.persistence.spi;

/**
 * Represents property sort order in the query.
 * 
 * @author Giedrius Trumpickas
 */
public final class Order
{
	/**
	 * Property name
	 */
	private String name;
		
	/**
	 * Flag which indicates ordering.
	 */
	private boolean ascending; 	
	
	/**
	 * Indicates case insensitive ordering
	 */
	private boolean caseInsensitive;
	
	/**
	 * Constructs 
	 * @param ascending
	 * @param name
	 */
	private Order(boolean ascending, String name, boolean caseInsensitive)
	{
		this.ascending = ascending;
		this.name = name;
		this.caseInsensitive = caseInsensitive;
	}
	
	/**
	 * Creates ascending order for given property
	 * 
	 * @param name a property name
	 * @return ascending order for given property
	 */
	public static Order asc(String name)
	{	
		return new Order(true, name, false);		
	}
		
	/**
	 * Creates descending order for given property.
	 * 
	 * @param name a property name
	 * @return descending order
	 */
	public static Order desc(String name)
	{
		return new Order(false, name, false);			
	}
	
	/**
	 * Creates descending order for given property.
	 * 
	 * @param name a property name
	 * @param caseInsenstive a flag which indicates to ingore case for sorting
	 * @return order instance
	 */
	public static Order desc(String name, boolean caseInsenstive)
	{
		return new Order(false, name, caseInsenstive);
	}
	
	/**
	 * Creates ascending order for given property.
	 * 
	 * @param name a property name
	 * @param caseInsenstive a flag which indicates to ingore case for sorting
	 * @return order instance
	 */
	public static Order asc(String name, boolean caseInsenstive)
	{
		return new Order(true, name, caseInsenstive);
	}
	
	/**
	 * Tests if order is ascending.
	 * 
	 * @return <code>true</code> if order is ascending
	 */
	public boolean isAscending()
	{
		return ascending;
	}
		
	/**
	 * Tests if order is descending.
	 * 
	 * @return <code>true</code> if order is descending
	 */
	public boolean isDescending()
	{
		return !ascending;
	}
			
	/**
	 * Gets property name.
	 * 
	 * @return property name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Tests if this order is case insensitive.
	 * 
	 * @return <code>true</code> if order is case insesitive
	 */
	public boolean isCaseInsensitive()
	{
		return caseInsensitive;
	}
	
	public String toString()
	{		
		if(ascending){
			if(caseInsensitive){
				return "upper(" + name + ") asc";
			}			
			return name + " asc";
		}
		if(caseInsensitive){
			return "upper(" + name + ") desc";
		}
		return name + " desc";
	}
}
