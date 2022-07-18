package com.radian.foundation.common.exception;

import java.io.Serializable;

/**
 *This object represents one element in the Collection of 
 *validation exceptions 
 * 
 * @author nines 
 */
public class ValidationExceptionElement implements Serializable
{
	/**A key that would be used for validation messages*/
	private String key;
	
	/**Messages that can be passed to the key validation message*/
	private Object[] values;
	
	/**Flag to determine if this validation exception is simply a warning
	 * and not a hard validation error*/
	private boolean warning;
	
	public ValidationExceptionElement(String key, Object[] values)
	{
		this.key = key;
		this.values = values;
	}
		
	/**
	 * @return
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * @return
	 */
	public Object[] getValues()
	{
		return values;
	}

	/**
	 * @param string
	 */
	public void setKey(String string)
	{
		key = string;
	}

	/**
	 * @param objects
	 */
	public void setValues(Object[] objects)
	{
		values = objects;
	}
	
	public boolean equals(Object o)
	{
		boolean ret = false;
		
		if(o != null)
		{
			ValidationExceptionElement elm = (ValidationExceptionElement)o;
			if(elm.getKey() != null && elm.getKey().equals(this.key))
			{
				ret = true;
			}
		}
		return ret;
	}
	
	
	
	

	/**
	 * @return
	 */
	public boolean isWarning()
	{
		return warning;
	}

	/**
	 * @param b
	 */
	public void setWarning(boolean b)
	{
		warning = b;
	}

}
