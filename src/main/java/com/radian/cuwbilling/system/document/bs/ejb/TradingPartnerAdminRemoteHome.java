/*
 * Created on Dec 4, 2003
 */
package com.radian.cuwbilling.system.document.bs.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * This is the Remote Home interface for the TradingPartnerAdmin EJB.
 * 
 * @author jsumner
 */
public interface TradingPartnerAdminRemoteHome extends EJBHome
{
    /**
     * This method corresponds to the ejbCreate method in the bean
     * "TradingPartnerAdminBean.java". The parameter sets of the two methods are
     * identical. When the client calls
     * <code>TradingPartnerAdminRemoteHome.create()</code>, the container
     * allocates an instance of the EJBean and calls <code>ejbCreate()</code>.
     * 
     * @return TradingPartnerAdminRemote
     * @exception RemoteException
     *                if there is a communications or systems failure
     * @exception CreateException
     *                if there is a problem creating the bean
     */
    public TradingPartnerAdminRemote create() throws CreateException, RemoteException;
}
