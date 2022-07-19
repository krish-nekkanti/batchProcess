package com.radian.cuwbilling.system.batch.os.persistence;

import com.radian.cuwbilling.system.batch.BatchHistoryItem;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;


/**
 * @author JWu
 *
 * Date: June 30, 2006
 */

public class BatchHistoryMapper 
{

	private static final Logger log = null; 
	
    public BatchHistoryMapper(PersistenceProvider provider)
    {
        //super(provider);
    }

	public BatchHistoryMapper()
	{
		super();
	}

	public void create (BatchHistoryItem batchRun) throws PersistenceProviderException
	{
        super.save(batchRun);
	}

	public BatchHistoryItem read (Long id) throws PersistenceProviderException
	{
        return (BatchHistoryItem) super.get(BatchHistoryItem.class, id);
	}

    public void remove(BatchHistoryItem batchRun) throws PersistenceProviderException
    {
        delete(batchRun);
    }
}
