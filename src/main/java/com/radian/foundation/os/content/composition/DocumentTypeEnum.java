/**
 * @(#) DocumentTypeEnum.java
 */

package com.radian.foundation.os.content.composition;

import com.radian.foundation.bo.codes.BaseEnumType;

import java.util.List;
import java.util.Arrays;

import java.util.Collections;


public class DocumentTypeEnum extends BaseEnumType
{
	public static final DocumentTypeEnum PDF = new DocumentTypeEnum(new Long(1), "PDF","pdf");
	public static final DocumentTypeEnum TEXT = new DocumentTypeEnum(new Long(2), "TEXT","txt");
	public static final DocumentTypeEnum POSTSCRIPT = new DocumentTypeEnum(new Long(3), "Postscript","ps");
	public static final DocumentTypeEnum HTML = new DocumentTypeEnum(new Long(4), "HTML","htm");


	private static final DocumentTypeEnum[] domain = {PDF,TEXT,POSTSCRIPT,HTML};

	/**
	 * List of all possible BaseEnumTypes for the enumerated type
	 */
	public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

	/**
	 * Restricted use constructor
	 */
	public DocumentTypeEnum(Long id, String label, String codeValue)
	{
		super(id, label, codeValue);
	}

	public DocumentTypeEnum() { }

	/**
	 * Gets an instance of the code
	 *
	 * @param code
	 * @return
	 */
	public static final DocumentTypeEnum instance(DocumentTypeEnum code)
	{
		return decodeValue(code.getID());
	}

	/**
	 * Return the specific BaseEnumType for the respective enumerated
	 * type key.
	 *
	 * @param	codeValue	Enumerated type key - DocumentTypeEnum
	 *
	 * @return	DocumentTypeEnum for the enumerated type
	 */
	public static final DocumentTypeEnum decodeValue( Long codeID )
	{
		return (DocumentTypeEnum) BaseEnumType.decodeValue(codeID, domainList.iterator());
	}

	/**
	 * Return the specific BaseEnumType for the respective enumerated
	 * type value.
	 *
	 * @param	codeValue	Enumerated type code value - DocumentTypeEnum
	 *
	 * @return	DocumentTypeEnum for the enumerated type
	 */
	public static final DocumentTypeEnum decodeValue( String codeValue )
	{
		return (DocumentTypeEnum) BaseEnumType.decodeValue(codeValue, domainList.iterator());
	}

	/**
	 * Return the specific BaseEnumType for the respective enumerated
	 * code label.
	 *
	 * @param	codeValue	Enumerated type code value - BaseEnumType
	 *
	 * @return	DocumentTypeEnum for the enumerated type
	 */
	public static final DocumentTypeEnum decodeLabel( String codeLabel )
	{
		return (DocumentTypeEnum) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
	}
}
