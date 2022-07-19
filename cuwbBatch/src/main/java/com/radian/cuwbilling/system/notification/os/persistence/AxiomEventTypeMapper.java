package com.radian.cuwbilling.system.notification.os.persistence;

import java.util.Collection;

import com.radian.cuwbilling.common.bo.codes.AxiomEntityType;
import com.radian.cuwbilling.system.notification.bo.code.AxiomEventCategory;
import com.radian.cuwbilling.system.notification.bo.domain.AxiomEventType;
import com.radian.cuwbilling.system.notification.bo.domain.impl.AxiomEventTypeImpl;
import com.radian.foundation.os.persistence.mapper.BaseRDBMSMapperInitializationException;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;
import com.radian.foundation.os.persistence.spi.Query;

/**
 * TODO: Type description.
 * 
 * @author Valentinas Jurkevicius
 */
public class AxiomEventTypeMapper 
{

    /**
     * @throws
     * com.radian.foundation.os.persistence.mapper.BaseRDBMSMapperInitializationException
     */
    public AxiomEventTypeMapper() throws BaseRDBMSMapperInitializationException
    {
        super();
    }

    /**
     * @param provider
     */
    public AxiomEventTypeMapper(PersistenceProvider provider)
    {
        super(provider);
    }

    public AxiomEventType getEventType(AxiomEntityType type, AxiomEventCategory category) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery("com.radian.cuwbilling.system.notification.os.persistence.AxiomEventTypeMapper.getEventType");
        query.setNamedParameter("entityTypeID", type.getID());
        query.setNamedParameter("eventCategoryID", category.getID());
        Collection coll = query.execute();
        if (coll == null || coll.isEmpty())
        {
            AxiomEventType axiomeEventType = new AxiomEventTypeImpl();
            axiomeEventType.setEntityType(type);
            axiomeEventType.setEventCategory(category);
            getProvider().makePersistent(axiomeEventType);
            return axiomeEventType;
        }

        // create detached object
        AxiomEventType axiomeEventTypeFromDB = (AxiomEventType) coll.iterator().next();
        AxiomEventType axiomeEventType = new AxiomEventTypeImpl();
        axiomeEventType.setEntityType(type);
        axiomeEventType.setEventCategory(category);
        axiomeEventType.setEventName(axiomeEventTypeFromDB.getEventName());
        axiomeEventType.setID(axiomeEventTypeFromDB.getID());
        return axiomeEventType;

    }

    /**
     * This method is used to show the persisted notifications in the system
     * status center
     * 
     * @param type
     * @param category
     * @return
     * @throws PersistenceProviderException
     */
    public AxiomEventType getPersistedEventType(AxiomEntityType type, AxiomEventCategory category) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery("com.radian.cuwbilling.system.notification.os.persistence.AxiomEventTypeMapper.getEventType");
        query.setNamedParameter("entityTypeID", type.getID());
        query.setNamedParameter("eventCategoryID", category.getID());
        Collection coll = query.execute();

        if (coll == null || coll.isEmpty())
        {
            return null;
        } else
        {
            AxiomEventType axiomeEventTypeFromDB = (AxiomEventType) coll.iterator().next();
            AxiomEventType axiomeEventType = new AxiomEventTypeImpl();
            axiomeEventType.setEntityType(type);
            axiomeEventType.setEventCategory(category);
            axiomeEventType.setEventName(axiomeEventTypeFromDB.getEventName());
            axiomeEventType.setID(axiomeEventTypeFromDB.getID());

            return axiomeEventType;
        }
    }

}
