/*
 * TimeSheetImportMapper.java
 *
 * Created on September 5, 2003, 12:34 PM
 */

package com.radian.cuwbilling.billing.common.os.persistence;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import com.radian.cuwbilling.billing.common.bo.codes.BillingStatus;
import com.radian.cuwbilling.billing.cuw.bo.domain.TimeSheetImportEntry;
import com.radian.cuwbilling.billing.cuw.bo.domain.impl.TimeSheetImportEntryImpl;
import com.radian.foundation.bs.SearchCriteria;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;
import com.radian.foundation.os.persistence.spi.Query;

/**
 *
 * @author Drea Leed temporary stub mapper for buisness services development
 */
public class TimeSheetImportMapper 
{
    private final static String FIND_BY_UNDERWRITER_CODE = "com.radian.cuwbilling.billing.common.os.persistence.TimeSheetImportMapper.getByUnderwriterCode";

    private final static String CHECK_EXISTING = "com.radian.cuwbilling.billing.common.os.persistence.TimeSheetImportMapper.getByUnderwriterCodeAndDate";

    private final static String FIND_BY_UNDERWRITER_CODE_DATE_STATUS = "com.radian.cuwbilling.billing.common.os.persistence.TimeSheetImportMapper.getByUnderwriterCodeAndDateAndStatus";

    private final static String FIND_BY_UNDERWRITER_CODE_DATE_RANGE = "com.radian.cuwbilling.billing.common.os.persistence.TimeSheetImportMapper.getByUnderwriterCodeAndDateRange";

    private final static String GET_ALL_TIMESHEET = "com.radian.cuwbilling.billing.common.os.persistence.TimeSheetImportMapper.getAll";

    private final static String FIND_BY_DATE_RANGE_AND_REP_ID = "com.radian.cuwbilling.billing.common.os.persistence.TimeSheetImportMapper.getByDateRangeAndRepID";
    
    private final static String GET_UW_LIST_FOR_UNBILLED_HOURS = "com.radian.cuwbilling.billing.common.os.persistence.TimesheetImportMapper.getUWListForUnbilledHours";
	
    /** Creates a new instance of BillingMapper */
    public TimeSheetImportMapper(PersistenceProvider provider)
    {
        super(provider);
    }

    /*
     * Retrieves a timesheet by ID.
     *
     */
    public TimeSheetImportEntry read(Long timesheetID) throws PersistenceProviderException
    {
        return (TimeSheetImportEntry) get(TimeSheetImportEntryImpl.class, timesheetID);
    }

    /*
     * Creates a new Billing Calendar.
     */
    public void create(TimeSheetImportEntry timesheet) throws PersistenceProviderException
    {
        save(timesheet);
    }

    /*
     * removes timesheet
     */
    public void remove(TimeSheetImportEntry timesheet) throws PersistenceProviderException
    {
        delete(timesheet);
    }
    
    /*
     * removes by query
     */
    public void cleanUpMonthRecord() throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(GET_ALL_TIMESHEET);
        
        try {
        	String queryString = query.getQueryString();
        	deleteByQuery( queryString );
        	
        }catch (Exception e){
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        	
        }
    }
    
    public Collection getByAll() throws PersistenceProviderException
    {
    	Query query = getProvider().newQuery(GET_ALL_TIMESHEET);

    	return query.execute();
    }
    
    public Collection getByDatesAndRepID(Date startDate, Date endDate, String repID) throws PersistenceProviderException
    {
    	Query query = getProvider().newQuery(FIND_BY_DATE_RANGE_AND_REP_ID);
    	query.setNamedParameter("startDate", startDate);
    	query.setNamedParameter("endDate", endDate);
    	query.setNamedParameter("repID", repID);
        return query.execute();
    }

    /*
     * returns a collection of domain objects. It cannot accept timesheet DTO
     * object because we did not map Timesheet DTO
     */
    public Collection getByCriteria(SearchCriteria criteria) throws PersistenceProviderException
    {
        return findByCriteria(TimeSheetImportEntry.class, criteria);
    }

    /*
     * get the collection of Timesheets basing on the employee id
     */
    public Collection getByUnderwriterCode(String uwCode) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(FIND_BY_UNDERWRITER_CODE);
        query.setParameters(new Object[] { uwCode });
        return query.execute();

    }

    /**
     * Get the collection of Timesheets basing on the employee id.
     */
    public Collection checkExisting(String underwriterCode, Date date) throws PersistenceProviderException
    {
        if (getLogger().isDebugEnabled())
        {
            getLogger().entering(this.getClass(), "checkExisting(String, Date)");
        }

        Query query = getProvider().newQuery(CHECK_EXISTING);

        query.setParameters(new Object[] { underwriterCode, date });

        Collection duplicates = query.execute();

        if (getLogger().isDebugEnabled())
        {
            getLogger().exiting(this.getClass(), "checkExisting(String, Date)");
        }
        return duplicates;
    }

    /**
     * Overloaded version that checks for existing timesheets by underwriter code
     * and date.
     */
    public Collection checkExisting(Long underwriterCode, Date date) throws PersistenceProviderException
    {
        return executeNamedQuery(CHECK_EXISTING, new Object[] { underwriterCode, date });
    }

    /*
     * get the collection of timesheets basing on the status,underwriter Code and
     * date range
     */
    public Collection getTimesheets(BillingStatus status, String underwriterCode, Date startDate, Date endDate) throws PersistenceProviderException
    {
        if (status != null)
        {
            Query query = getProvider().newQuery(FIND_BY_UNDERWRITER_CODE_DATE_STATUS);
            query.setParameters(new Object[] { status.getID(), underwriterCode, startDate, endDate });
            return query.execute();
        } else
        {
            return null;
        }
    }

    /*
     * get the collection of timesheets basing on the underwriter code and date
     * range, regardlss of status
     */
    public Collection getTimesheetsForDateRange(String underwriterCode, Date startDate, Date endDate) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(FIND_BY_UNDERWRITER_CODE_DATE_RANGE);
        query.setParameters(new Object[] { underwriterCode, startDate, endDate });
        return query.execute();
    }
    
    public Collection getUWForUnbilledHours() throws PersistenceProviderException
	{
		Query query = getProvider().newQuery(GET_UW_LIST_FOR_UNBILLED_HOURS);
        return query.execute();
	}
}
