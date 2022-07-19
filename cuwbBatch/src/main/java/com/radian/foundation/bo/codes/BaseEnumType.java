package com.radian.foundation.bo.codes;

import com.radian.foundation.bo.dto.CodeDTO;

import java.util.Iterator;

/**
 * This class defines the base class for the type-safe enum pattern.
 *
 * @author  R. Mohr
 * @see 	Type-Safe Enum Pattern
 * @version 1.0
 */
public abstract class BaseEnumType extends CodeDTO
{

	public BaseEnumType()
	{
		super();
	}
	/**
	 * Return the specific BaseEnumType for the respective enumerated
	 * type key.
	 *
	 * @param	codeValue	Enumerated type key - PQARating
	 * @param   it  Iterator to domain list of values
	 *
	 * @return	BaseEnumType for the enumerated type
	 */
	public static final BaseEnumType decodeValue( Long codeID, Iterator it )
	{
		BaseEnumType code = (BaseEnumType) null;
		BaseEnumType found = (BaseEnumType) null;

		while(it.hasNext())
		{
			code = (BaseEnumType) it.next();
			if(code.getID().equals(codeID))
			{
				found = code;
				break;
			}
		}
		return found;
	}

	/**
	 * Return the specific BaseEnumType for the respective enumerated
	 * type value.
	 *
	 * @param	codeValue	Enumerated type code value - PQARating
	 * @param   it  Iterator to domain list of values
	 *
	 * @return	BaseEnumType for the enumerated type
	 */
	public static final BaseEnumType decodeValue( String codeValue, Iterator it )
	{
		BaseEnumType code = (BaseEnumType) null;
		BaseEnumType found = (BaseEnumType) null;

		while(it.hasNext())
		{
			code = (BaseEnumType) it.next();
			if(code.getDisplayValue().equals(codeValue))
			{
				found = code;
				break;
			}
		}
		return found;
	}

	/**
	 * Return the specific BaseEnumType for the respective enumerated
	 * code label.
	 *
	 * @param	codeValue	Enumerated type code value - BaseEnumType
	 * @param   it  Iterator to domain list of values
	 *
	 * @return	BaseEnumType for the enumerated type
	 */
	public static final BaseEnumType decodeLabel( String codeLabel, Iterator it )
	{
		BaseEnumType code = (BaseEnumType) null;
		BaseEnumType found = (BaseEnumType) null;

		while(it.hasNext())
		{
			code = (BaseEnumType) it.next();
			if(code.getDisplayValue().equals(codeLabel))
			{
				found = code;
				break;
			}
		}
		return found;
	}

	/**
	 * Guarantees all equal objects of the enumerated type are also identical:
	 * a.equals(b) if and only if a==b
	 */
	//TODO - remove - in base class
	//public final boolean equals( Object that )
	//{
	//	BaseEnumType code = (BaseEnumType) that;
	//	return this.getDisplayValue().equals(code.getDisplayValue());
	//}


	/**
	 * Guarantees descendants cannot override hashCode method
	 */
	public final int hashCode( )
	{
		return super.hashCode();
	}

	/**
	 * Restricted use constructor
	 */
	protected BaseEnumType( Long id, String displayValue, String abbreviation )
	{
	  this.ID = id;
	  this.displayValue = displayValue;
	  this.abbreviation = abbreviation;
	}

	/**
	 * @Return Label for the enumerated type
	 */
	//TODO - remove - in base class
	//public String toString( )
	//{
	//	return displayValue;
	//}


}
