package com.radian.foundation.bo.dto;

/**
 * DTO that represents a "code" value.  We define
 * a code value to be an enumerated list of values
 * that are generally static(i.e. they do not change
 * very frequently) in nature.
 * <p>
 * An example of a typical
 * code value would be a list of drop-down values that
 * are always available
 *
 * @author nines
 *
 */
public class CodeDTO extends BaseDTO
{

	/**Internal unique identifier for this code*/
	protected Long ID;

	/**The user-centric display value associated with this code*/
	protected String displayValue;

	/**An abbreviation for the display value(note - may be null...not all codes have an abbreviation)*/
	protected String abbreviation;




	/**
	 * Default constructor
	 */
	public CodeDTO()
	{
		super();

	}

	public CodeDTO( Long ID, String displayValue)
	{
		super();
		this.ID = ID;
		this.displayValue = displayValue;
	}

	public boolean equals(Object o)
	{
		boolean ret = false;
		if(ID != null && o != null && o instanceof CodeDTO)
		{
			CodeDTO dto = (CodeDTO)o;
			if(ID.equals(dto.ID))
			{
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * @return
	 */
	public String getDisplayValue()
	{
		return displayValue;
	}

	/**
	 * @return
	 */
	public Long getID()
	{
		return ID;
	}

	public void setDisplayValue(String displayValue)
	{
		this.displayValue = displayValue;
	}


	public void setID(Long ID)
	{
		this.ID = ID;
	}

	/**
	 * @return
	 */
	public String getAbbreviation()
	{
		return abbreviation;
	}


	/**
	 * @return
	 */
	public String getValue()
	{
		return this.displayValue;
	}
	
	public String toString()
	{
		return this.displayValue;
	}


}
