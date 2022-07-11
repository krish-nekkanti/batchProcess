/*
 * TimesheetMapper.java
 *
 * Created on September 5, 2003, 12:34 PM
 */

package com.radian.cuwbilling.billing.common.os.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;

import com.radian.cuwbilling.billing.common.bo.codes.BillingStatus;
import com.radian.cuwbilling.billing.cuw.bo.domain.TimeSheetEntry;
import com.radian.cuwbilling.billing.cuw.bo.domain.impl.TimeSheetEntryImpl;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.foundation.bs.SearchCriteria;
import com.radian.foundation.os.persistence.mapper.BaseRDBMSMapper;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;
import com.radian.foundation.os.persistence.spi.Query;

/**
 *
 * @author Drea Leed temporary stub mapper for buisness services development
 */
public class TimesheetMapper extends BaseRDBMSMapper
{
    private final static String FIND_BY_UNDERWRITER_CODE = 
    	"com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper.getByUnderwriterCode";

    private final static String CHECK_EXISTING = 
    	"com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper.getByUnderwriterCodeAndDate";

    private final static String FIND_BY_UNDERWRITER_CODE_DATE_STATUS = 
    	"com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper.getByUnderwriterCodeAndDateAndStatus";

    private final static String FIND_BY_UNDERWRITER_CODE_DATE_RANGE = 
    	"com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper.getByUnderwriterCodeAndDateRange";

    private final static String FIND_BY_DATE_RANGE = 
    	"com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper.getByDateRange";

    private final static String FIND_BY_UNDERWRITER_CODE_AND_DATES = 
    	"com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper.getUnbilledByUnderwriterCodeAndDates";

    private final static String FIND_UNBILLED_BY_UNDERWRITER_CODE = 
    	"com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper.getUnbilledByUnderwriterCode";                
	
	private final static String GET_TIMESHEET_MSG_ENTRIES = 
		"com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper.getTimeSheetMsgEntries";
	
	private final static String FIND_TIMESHEET_BY_PERIOD_DATES_AND_REPID = "com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper.getTimesheetByDatesAndRepID";
	
	private final static String FIND_BY_UNDERWRITER_CODE_AND_DATE = "com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper.getByUnderwriterCodeAndWorkDate";

    /** Creates a new instance of BillingMapper */
    public TimesheetMapper(PersistenceProvider provider)
    {
        super(provider);
    }

    /*
     * Retrieves a timesheet by ID.
     *
     */
    public TimeSheetEntry read(Long timesheetID) throws PersistenceProviderException
    {
        return (TimeSheetEntry) get(TimeSheetEntryImpl.class, timesheetID);
    }

    /*
     * Creates a new Billing Calendar.
     */
    public void create(TimeSheetEntry timesheet) throws PersistenceProviderException
    {
        save(timesheet);
    }

    /*
     * removes timesheet
     */
    public void remove(TimeSheetEntry timesheet) throws PersistenceProviderException
    {
        delete(timesheet);
    }
    
    /*
     * removes by query
     */
    public void cleanUpMonthRecord() throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(FIND_BY_DATE_RANGE);
        
        try {
        	String queryString = query.getQueryString().replaceFirst(":startDate", getPeriodStartDateString()).replaceFirst(":endDate", getPeriodEndDateString());
        	deleteByQuery( queryString );
        	
        }catch (Exception e){
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        	
        }
    }
    
    public Collection getByDates(Date startDate, Date endDate) throws PersistenceProviderException
    {
    	Query query = getProvider().newQuery(FIND_BY_DATE_RANGE);
    	query.setNamedParameter("startDate", startDate);
    	query.setNamedParameter("endDate", endDate);
        return query.execute();
    }

    /*
     * returns a collection of domain objects. It cannot accept timesheet DTO
     * object because we did not map Timesheet DTO
     */
    public Collection getByCriteria(SearchCriteria criteria) throws PersistenceProviderException
    {
        return findByCriteria(TimeSheetEntryImpl.class, criteria);
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
        try{
        query.setParameters(new Object[] { underwriterCode, date });
        } catch (Exception e) {
        	e.printStackTrace();
        }
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
    
    public Collection getUnbilledTimesheetsByDates(String underwriterCode, Collection dates) throws PersistenceProviderException
    {
            Query query = getProvider().newQuery(FIND_BY_UNDERWRITER_CODE_AND_DATES);
            query.setNamedParameter("underwriterCode", underwriterCode);
            SimpleDateFormat fmt1 = new SimpleDateFormat("MM/dd/yyyy");
            Collection col = new ArrayList();
            try
            {
	            if (dates != null)
	            {
	            	Iterator it = dates.iterator();
	            	while(it.hasNext())
	            	{
	            		String dateStr = (String)it.next();
	            		Date date = fmt1.parse(dateStr);
	            		col.add(date);
	            	}
	            }
            }
            catch (ParseException e)
            {
            	throw new PersistenceProviderException(e.getMessage());
            }
        	query.setNamedParameter("dates", col);
        	query.setNamedParameter("status", BillingStatus.NOT_PRICED.getID());
            return query.execute();
    }
    
    public Collection getUnbilledTimesheetsByUW(String underwriterCode) throws PersistenceProviderException
    {
            Query query = getProvider().newQuery(FIND_UNBILLED_BY_UNDERWRITER_CODE);
            query.setNamedParameter("underwriterCode", underwriterCode);
        	query.setNamedParameter("status", BillingStatus.NOT_PRICED.getID());
            return query.execute();
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
    
    public TimeSheetEntry getTimesheetsForRepIDAndDate(String underwriterCode, Date date, Boolean isTrialRun, BillingStatus status) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(FIND_BY_UNDERWRITER_CODE_AND_DATE);
        query.setNamedParameter("underwriterCode", underwriterCode);
    	query.setNamedParameter("date", date);
    	if (status != null)
    	{
    		query.setNamedParameter("statusFlag", Boolean.TRUE);
    		query.setNamedParameter("status", status.getID());
    	}
    	else
    	{
    		query.setNamedParameter("statusFlag", Boolean.FALSE);
    		query.setNamedParameter("status", "");
    	}
    	if(Boolean.TRUE.equals(isTrialRun))
    	{
    		query.setNamedParameter("isFinalized", Boolean.FALSE);
    	}
    	else
    	{
    		query.setNamedParameter("isFinalized", Boolean.TRUE);
    	}
    	Collection results = query.execute();
    	if (results != null && results.size() > 0)
    	{
    		return (TimeSheetEntry)results.iterator().next();
    	}
        return null;
    }
    
    private String getPeriodStartDateString()
    {
    	Calendar calendar = getMonthCorrectedCalendar();
    	
    	calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
    	
		return  "'" + (new java.text.SimpleDateFormat("dd-MMM-yyyy")).format(calendar.getTime()) + "'";
    }

    private String getPeriodEndDateString()
    {
    	Calendar calendar = getMonthCorrectedCalendar();
    	
    	calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
    	
  		return "'" + (new java.text.SimpleDateFormat("dd-MMM-yyyy")).format(calendar.getTime()) + "'";
    }
    
    private Calendar getMonthCorrectedCalendar()
    {
    	Calendar cal = Calendar.getInstance();
    	
    	if( cal.get(Calendar.DAY_OF_MONTH) < 6 )
    	{
    		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
    	}
    	
    	return cal;
    }
    
	/*
     * get the collection of timesheets being imported
     */
	public Collection getTimesheetImportEntries() throws PersistenceProviderException
	{
		Query query = getProvider().newQuery(GET_TIMESHEET_MSG_ENTRIES);	
		
		return query.execute();
	}
    
	public void cleanUpTimesheetRecord(Date startDate, String repID, Date endDate) throws PersistenceProviderException
    {
        Query query = getProvider().newQuery(FIND_TIMESHEET_BY_PERIOD_DATES_AND_REPID);
        
        try {
        	String queryString = query.getQueryString().replaceFirst(":startDate", getStartDateString(startDate));
        	queryString = queryString.replaceFirst(":endDate", getStartDateString(endDate));
        	queryString = queryString.replaceFirst(":repID", "'" + repID + "'");
        	deleteByQuery( queryString );
        	
        }catch (Exception e){
        	System.out.println(e.getMessage());
        	e.printStackTrace();	
        }
        finally
        {
        	this.closeProvider();
        }
    }
	
	private String getStartDateString(Date startDate)
    {
    	Calendar calendar = Calendar.getInstance();   	
    	calendar.setTime(startDate);  	
		return  "'" + (new java.text.SimpleDateFormat("dd-MMM-yyyy")).format(calendar.getTime()) + "'";
    }
}
