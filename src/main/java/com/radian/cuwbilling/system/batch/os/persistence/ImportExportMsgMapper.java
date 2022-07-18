package com.radian.cuwbilling.system.batch.os.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

import com.radian.cuwbilling.system.batch.bo.domain.ImportExportMessage;
import com.radian.cuwbilling.system.batch.bo.domain.impl.ImportExportMessageImpl;
import com.radian.cuwbilling.system.batch.bs.ImportExportMsgSearchCriteria;
import com.radian.foundation.bs.SearchCriteriaItem;
import com.radian.foundation.bs.ServiceConstants;
import com.radian.foundation.os.persistence.mapper.BaseRDBMSMapper;
import com.radian.foundation.os.persistence.mapper.BaseRDBMSMapperInitializationException;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;
import com.radian.foundation.os.persistence.spi.Query;

/**
 * ImportExportMsgMapper.
 */
public final class ImportExportMsgMapper extends BaseRDBMSMapper
{
    private static final String FIND_IMPORT_EXPORT_MSG_BY_CRITERIA = "com.radian.cuwbilling.system.batch.os.persistence.ImportExportMsgMapper.findImportExportMessageByCriteria";

    private static final String DB_WILDCARD = "%";

    /**
     * Consturcts an ImportExportMessage mapper.
     * 
     * @param provider
     *            a persistence provider
     */
    public ImportExportMsgMapper(PersistenceProvider provider)
    {
        super(provider);
    }

    public ImportExportMsgMapper() throws BaseRDBMSMapperInitializationException
    {
        super();
    }

    /**
     * Gets ImportExportMessage by id.
     * 
     * @param id
     *            of a ImportExportMessage
     * @return ImportExportMessage
     * @throws PersistenceProviderException
     */
    public ImportExportMessage read(Long importExportMsgID) throws PersistenceProviderException
    {
        return (ImportExportMessage) get(ImportExportMessageImpl.class, importExportMsgID);
    }

    /**
     * Creates ImportExportMessage.
     * 
     * @param ImportExportMessage
     * @throws PersistenceProviderException
     */
    public void create(ImportExportMessage cp) throws PersistenceProviderException
    {
        save(cp);
    }

    /**
     * Deletes ImportExportMessage.
     * 
     * @param ImportExportMessage
     * @throws PersistenceProviderException
     */
    public void remove(ImportExportMessage cp) throws PersistenceProviderException
    {
        delete(cp);
    }

    /**
     * Finds ImportExportMessage instance by given search criteria. For search
     * basing on creation date we are truncating the database date and the
     * passing parameter date and then comapring both in the named query. We are
     * doing this to avoid timestamp problem
     * 
     * @param criteria
     *            a search criteria
     * @return collection of found instances
     * @throws PersistenceProviderException
     */
    public Collection getByCriteria(ImportExportMsgSearchCriteria criteria) throws PersistenceProviderException, ParseException
    {
        Query namedQuery = getProvider().newQuery(FIND_IMPORT_EXPORT_MSG_BY_CRITERIA);
        namedQuery.setNamedParameter("batchNumber", "");
        namedQuery.setNamedParameter("batchNumberFlag", Boolean.FALSE);

        namedQuery.setNamedParameter("createdBeginDate", "");
        namedQuery.setNamedParameter("createdBeginDateFlag", Boolean.FALSE);

        namedQuery.setNamedParameter("createdEndDate", "");
        namedQuery.setNamedParameter("createdEndDateFlag", Boolean.FALSE);

        namedQuery.setNamedParameter("msgTypeRaw", "");
        namedQuery.setNamedParameter("msgTypeRawFlag", Boolean.FALSE);

        namedQuery.setNamedParameter("success", "");
        namedQuery.setNamedParameter("successFlag", Boolean.FALSE);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        Collection col = criteria.getCriteriaItems();
        if (col != null)
        {
            Iterator it = col.iterator();
            while (it.hasNext())
            {
                SearchCriteriaItem item = (SearchCriteriaItem) it.next();
                Iterator itemIt = item.getCriteriaData().iterator();

                if (item.getOperatorType().equals(ServiceConstants.CriteriaOperatorTypes.LIKE))
                    namedQuery.setNamedParameter(item.getFieldName(), itemIt.next() + DB_WILDCARD);
                else
                {
                    namedQuery.setNamedParameter(item.getFieldName(), itemIt.next());
                }

                namedQuery.setNamedParameter(item.getFieldName() + "Flag", Boolean.TRUE);
            }
        }
        return namedQuery.execute();
    }
}
