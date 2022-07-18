package com.radian.foundation.os.messaging.util;

/**
 * User: ayre
 * Date: Aug 28, 2003
 * Time: 1:26:47 PM
 */
public class UUIDUtil
{
	public static String getUUID()
	{
		return new java.rmi.server.UID().toString();
	}

}
