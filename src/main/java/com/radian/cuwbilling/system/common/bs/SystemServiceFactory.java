/*
 * DocumentServiceFactory.java
 *
 * Created on November 13, 2003
 */

package com.radian.cuwbilling.system.common.bs;

import com.radian.cuwbilling.system.batch.bs.ImportExportMsgAdministration;
import com.radian.cuwbilling.system.batch.bs.impl.ImportExportMsgAdminDelegate;
import com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration;
import com.radian.cuwbilling.system.document.bs.impl.TradingPartnerAdminDelegate;
//import com.radian.cuwbilling.system.externallink.bs.ExternalLinkAdministration;
//import com.radian.cuwbilling.system.externallink.bs.impl.ExternalLinkAdminDelegate;
import com.radian.foundation.bs.BaseFactory;
import com.radian.foundation.bs.ServiceException;

/**
 * This class is an object factory that provides the appropriate system domain
 * business delegate to the client.
 *
 * @author JSumner
 */
public class SystemServiceFactory extends BaseFactory
{
    /**
     * Retrieves an instance of the SystemServiceFactory object
     *
     * @return SystemServiceFactory
     */
    public static SystemServiceFactory instance()
    {
        return (SystemServiceFactory) instance(SystemServiceFactory.class);
    }

    /**
     * Retrieves a reference to the Trading Partner Administration service.
     *
     * @return TradingPartnerAdministration
     * @throws ServiceException
     */
    public TradingPartnerAdministration getTradingPartnerAdminService()
    {
        return TradingPartnerAdminDelegate.instance();
    }

    /**
     * Retrieves a reference to the Import/Export Msg Administration service.
     *
     * @return ImportExportMsgAdministration
     * @throws ServiceException
     */
    public ImportExportMsgAdministration getImportExportMsgAdminService()
    {
        return ImportExportMsgAdminDelegate.instance();
    }

    /**
     * Retrieves a reference to the ExternalLink service.
     *
     * @return IExternalLinkAdministration
     * @throws ServiceException
     */
    /*public ExternalLinkAdministration getExternalLinkService()
    {
        return ExternalLinkAdminDelegate.instance();
    }*/
}
