package com.radian.foundation.os.persistence.util;

import java.security.Principal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.security.auth.Subject;

import weblogic.security.Security;
import weblogic.security.SubjectUtils;

import com.radian.foundation.common.logging.LogManager;
import com.radian.foundation.common.logging.Logger;

/**
 * An utility class to propogate user context to the database. 
 * 
 * @author Giedrius Trumpickas
 */
public class UserContextManager
{
	/**
	 * A logger for this class.
	 */
	private final static Logger log = LogManager.getLogger(UserContextManager.class);
	
	/**
	 * A stored procedure to propogate security context.
	 */
	private final static String CONTEXT_SP = "{call user_context_pkg.authenticate (?, ?, ?)}";
	
	/**
	 * Propogates user context to the oracle.
	 * 
	 * @param user a user
	 * @return a user id or <code>null</code> if user does not exist
	 * @throws SQLException
	 */
	public static Long propogatePrincipal(Connection conn, Principal user) throws SQLException
	{
		if(user == null)
		{
			log.warn("User principal is null");
			return null;
		}
		return propogateUser(conn, user.getName());
	}
	
	/**
	 * Propogates current user to the oracle.
	 * 
	 * @param conn a connection
	 * @param username a user name
	 * @return user id from database if given user exist in the database or <code>null</code>
	 * @throws SQLException
	 */
	public static Long propogateUser(Connection conn, String username) throws SQLException
	{
		long start = 0;
		if(log.isDebugEnabled())
		{	
			start = System.currentTimeMillis();
			log.debug("Propogating user to the database, username is - " + username);
		}
		CallableStatement sp = null;
		try
		{
			sp = conn.prepareCall(CONTEXT_SP);
			sp.setString(1, username);
			sp.registerOutParameter(2, Types.NUMERIC);
			sp.registerOutParameter(3, Types.NUMERIC);
			sp.execute();
			long id = sp.getLong(2);
			long code = sp.getLong(3);
			if(code > 0){
				return null;
			}
			if(log.isDebugEnabled())
			{	
				log.debug("User database id - " + id);
			}
			return new Long(id);
		}
		catch(SQLException e)
		{
			log.error("Propogation of the principal failed", e);
			throw e;
		}
		finally
		{
			close(sp);
			if(log.isDebugEnabled())
			{	
				long end = System.currentTimeMillis();
				log.debug("Propogation done, took - " + (end - start) + " miliseconds");
			}
		}
	}
	
	/**
	 * Propogates current user in the weblogic to the database.
	 * 
	 * @param conn a JDBC connection
	 * @return user id or <code>null</code> if such user does not exist in database
	 * @throws SQLException if database error occurs
	 */
	public static Long propogateCurrentUser(Connection conn) throws SQLException
	{
		Subject subject = Security.getCurrentSubject();
		Principal p = SubjectUtils.getUserPrincipal(subject);
		return propogatePrincipal(conn, p);
	}
	
	private static void close(Statement st) throws SQLException
	{
		if(st == null) return;
		st.close();
	}
}
