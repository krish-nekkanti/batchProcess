/**
 * @(#) DocGenException.java
 */

package com.radian.foundation.os.content.composition;

import com.radian.foundation.common.exception.CascadingException;

public class DocGenException extends CascadingException
{
	public DocGenException(Throwable t){
		super(t);
	}
	
	public DocGenException(String msg){
		super(msg);
	}
	
}
