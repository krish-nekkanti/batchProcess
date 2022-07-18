package com.radian.cuwbilling.billing.common.os.persistence;

import java.util.Collection;

import com.radian.cuwbilling.billing.cuw.bo.domain.AROutboundTrx;
import com.radian.cuwbilling.billing.cuw.bo.domain.ARTrx;
import com.radian.cuwbilling.billing.cuw.bo.domain.BillingDetailRecord;
import com.radian.cuwbilling.billing.cuw.bo.domain.ExpenseBDR;
import com.radian.cuwbilling.billing.cuw.bo.domain.impl.AROutboundTrxImpl;
import com.radian.cuwbilling.billing.cuw.bo.domain.impl.ARTrxImpl;
import com.radian.foundation.os.persistence.mapper.BaseRDBMSMapper;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;
import com.radian.foundation.os.persistence.spi.Query;

/**
 * 
 * @author Sunil Are temporary stub mapper for buisness services development
 */
public class ARTrxMapper extends BaseRDBMSMapper
{
    /**
     * Creates a new instance of BillingMapper
     */
    public ARTrxMapper(PersistenceProvider provider)
    {
        super(provider);
    }

    /**
     * Retrieves an ARTrx by ID.
     */
    public ARTrx readARTrx(Long ARTrxID) throws PersistenceProviderException
    {
        return (ARTrx) get(ARTrxImpl.class, ARTrxID);
    }

    /**
     * Creates a new ARTrx object.
     */
    public void create(ARTrx inboundTrx) throws PersistenceProviderException
    {
        save(inboundTrx);
    }

    /**
     * Retrieves an AROutboundTrx by ID.
     */
    public AROutboundTrx readAROutboundTrx(Long AROutboundTrxID) throws PersistenceProviderException
    {
        return (AROutboundTrx) get(AROutboundTrxImpl.class, AROutboundTrxID);
    }

    /**
     * Retrieves an AROutboundTrx by ID.
     */
    public AROutboundTrx readAROutboundTrxBybdrID(Long bdrID) throws PersistenceProviderException
    {
    	AROutboundTrx art = null;
        Query query = getProvider().newQuery("com.radian.cuwbilling.billing.cuw.os.persistence.ARTrxMapper.readAROutboundTrxByAdjustmentBDR");
        query.setNamedParameter("bdrID", bdrID);
        Collection results = query.execute();
        if (!results.isEmpty())
        {
        	art = (AROutboundTrx) results.iterator().next();
        }
        return art;
    }
    
    /**
     * Creates a new AROutboundTrx object.
     */
    public void create(AROutboundTrx outboundTrx) throws PersistenceProviderException
    {
        save(outboundTrx);
    }
    
    /**
     * Deletes the specified AROutboundTrx object from DB
     *
     * @param struct -
     *            AROutboundTrx object to be deleted
     * @throws PersistenceProviderException
     */
    public void remove(AROutboundTrx struct) throws PersistenceProviderException
    {
        delete(struct);
    }
}
