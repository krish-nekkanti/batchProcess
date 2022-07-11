/**
 * TimeSheetGenerationBean.java
 *
 * Created on September 5, 2003, 9:58 AM
 */

package com.radian.cuwb.batch.timeShet;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.ServiceLocator;
import org.springframework.context.annotation.Configuration;

import com.radian.cuwbilling.billing.common.bs.BillingException;
import com.radian.cuwbilling.billing.common.os.persistence.TimeSheetImportMapper;
import com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper;
import com.radian.cuwbilling.billing.cuw.bo.domain.TimeSheetImportEntry;
import com.radian.cuwbilling.billing.cuw.bo.domain.impl.TimeSheetImportEntryImpl;
import com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetMsgDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetStatisticsDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.assembler.SingletonRepList;
import com.radian.cuwbilling.billing.cuw.bs.CUWBillingException;
import com.radian.cuwbilling.billing.cuw.bs.TimeSheetSearchCriteria;
import com.radian.cuwbilling.common.bo.codes.AxiomEntityType;
import com.radian.cuwbilling.common.bo.domain.impl.NullEntityImpl;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.cuwbilling.system.batch.bo.dto.ImportExportMsgDTO;
import com.radian.cuwbilling.system.batch.bs.ImportExportMsgAdministration;
import com.radian.cuwbilling.system.batch.bs.ImportExportMsgException;
import com.radian.cuwbilling.system.common.bo.code.ImportExportMsgType;
import com.radian.cuwbilling.system.common.bs.SystemServiceFactory;
import com.radian.cuwbilling.system.notification.bo.code.AxiomEventCategory;
import com.radian.cuwbilling.system.notification.bs.eventrouter.EventRouterDelegate;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;

/**
 * This Stateless Session bean class implements the remote interface for the
 * generation of Time Sheet Info.
 *
 * @author KMadireddy
 */
public class TimeSheetGenerationBean
{
	/**
	 * Reach to remote ftp server; 
	 *   Validate timesheet file; 
	 *   Transfer the file to DB server if the file is in valid format.
	 * @return: true: if file is transferred to DB server; false: otherwise
	 */
	public TimeSheetStatisticsDTO getRemoteTimeSheetFile()
	{
		//getLogger().entering(getClass(), "getRemoteTimeSheetFile()");
		//boolean retVal = false;
		
    	FTPClient client = new FTPClient();
    	FTPClient client2 = new FTPClient();
    	FTPClient clientBk = new FTPClient();
    	OutputStream osBk = null, os = null;
    	boolean loginSuccessful;
    	boolean isContinueFlag = true;
    	TimeSheetStatisticsDTO timesheetStats = new TimeSheetStatisticsDTO();
    	Properties prop = new Properties(); //Repositioned code - 09APR2013
    	
        try {
        	
        	Configuration ftpConfig =
                 ServiceLocator.getInstance().getConfiguration("ftp-config");
        	
        	//Properties prop = new Properties();
        	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(ftpConfig.getString("configFile"));
        	prop.load(is);
        	is.close();

        	client.connect(prop.getProperty("timesheet.import.from.host"));
        	loginSuccessful = client.login(prop.getProperty("timesheet.import.from.username"),prop.getProperty("timesheet.import.from.password"));
							Thread.sleep(1000);  //New 17Apr2013
        	if(!loginSuccessful)
        	{
        		//getLogger().error("Failed to login onto server: "+ prop.getProperty("timesheet.import.from.host"));
        	}

	        client2.connect(prop.getProperty("timesheet.import.to.host"));
        	loginSuccessful = client2.login(prop.getProperty("timesheet.import.to.username"), 
	        				prop.getProperty("timesheet.import.to.password"));
        	Thread.sleep(1000);  //New 17Apr2013
        	if(!loginSuccessful)
        	{
        		//getLogger().error("Failed to login onto server: "+ prop.getProperty("timesheet.import.to.host"));
        	}
	        
	        clientBk.connect(prop.getProperty("timesheet.import.backup.host"));
	        loginSuccessful = clientBk.login(prop.getProperty("timesheet.import.backup.username"), 
	        				prop.getProperty("timesheet.import.backup.password"));
        	if(!loginSuccessful)
        	{
        		//getLogger().error("Failed to login onto server: "+ prop.getProperty("timesheet.import.backup.host"));
        	}
	        
	        client2.setFileType(FTPClient.ASCII_FILE_TYPE);

	        String folder= prop.getProperty("timesheet.import.from.folder");
	        if( folder != null && folder.length() >1 ) // none production env
	        {
	        	client.changeWorkingDirectory(prop.getProperty("timesheet.import.from.folder"));
	        }
	        Thread.sleep(1000);  //New 17Apr2013
	        FTPFile [] files = client.listFiles();
	        
	        String [] errorMessage = new String[1];
	        
	        String timesheetFileName = getTrueFileName( files );
	        
	        if( timesheetFileName == null )
	        { 
	        	timesheetStats.setRetVal(false);
	        } else {
	        	timesheetStats.setFilename(timesheetFileName);
		        byte [] timesheetBytes = this.getValidatedTimeSheet(client.retrieveFileStream(files[0].getName()), errorMessage, isContinueFlag, timesheetStats);
	
		        // send an email with any errors that occurred
		        if (errorMessage[0] != null && errorMessage[0].length()>1 && isContinueFlag == false)
		        {
		            sendNotification(false, errorMessage[0]);
		            timesheetStats.setRetVal(false);
		        } 
		        else 
		        { // timesheet file exists
			        if( files != null && files.length > 0)
			        {
			        	// backup file
			        	clientBk.enterLocalPassiveMode();
			        	//getLogger().entering(getClass(), "Reply String after enter local passive mode for client - " + clientBk.getReplyString());
			        	clientBk.setFileType(FTPClient.ASCII_FILE_TYPE);
			        	clientBk.changeWorkingDirectory(prop.getProperty("timesheet.import.backup.folder"));
			        	osBk = clientBk.storeFileStream(getBackupTimesheetName(files[0].getName()));
			        	osBk.write(timesheetBytes);
			        	osBk.close();
				        
			        	//get timesheet file transferred to DB server
				        os = client2.storeFileStream("Timesheet.csv");
				        os.write(timesheetBytes);
				        os.close();
				        
	//			        client.retrieveFile(files[0].getName(), os);
				        
				        //delete time sheet file in the remote loc
				        client.setFileType(FTPClient.ASCII_FILE_TYPE);
				        client.deleteFile(files[0].getName());
	
				        client2.completePendingCommand();
				        
				        if (errorMessage[0] != null && errorMessage[0].length()>1 && isContinueFlag == true)
				        {
				        	sendNotification(false, errorMessage[0]);
				        }
				        timesheetStats.setRetVal(true);
			        }
		        }
	        }
	        if( os != null ) 
	        {
	        	os.close(); 
	        	//getLogger().entering(getClass(), "Reply String after os close - " + clientBk.getReplyString());
	        }
	       // //getLogger().entering(getClass(), "Reply String before complete pending command - " + clientBk.getReplyString());
        	clientBk.completePendingCommand();	
        	//getLogger().entering(getClass(), "Reply String after complete pending command - " + clientBk.getReplyString());
	        clientBk.logout();
	       // //getLogger().entering(getClass(), "Reply String after client log out - " + clientBk.getReplyString());
        }catch (Exception e) {
        	//getLogger().error("Process failed in getting the timesheet file.", e);
        } finally {
	        // release resources
        	try {
		        client.logout();
		        client.disconnect();
		        client2.logout();
		        client2.disconnect();
		        clientBk.disconnect();
		        if( os != null )
		        {
		        	os.close();
		        }
        	} catch ( Exception e ){
        		//getLogger().error("Error closing ftp connections.", e);
        	}
        }
        
        return timesheetStats;
	}
	
	/**
	 * Read timesheet from external table;
	 *   Creat timesheet entries and store them into DB
	 *   
	 * @return
	 */
	public boolean importTimeSheets(TimeSheetStatisticsDTO timesheetStats)
	{
		//getLogger().entering(getClass(), "importTimeSheets()");
		
		try {
			
			PersistenceProvider provider = getProvider();
			TimesheetMapper mapper = new TimesheetMapper(provider); 
			
			Collection timeSheetEntries = mapper.getTimesheetImportEntries(); 
			
			if( timeSheetEntries != null && timeSheetEntries.size() > 0)
			{
				create(timeSheetEntries, timesheetStats);
			}
			sendStatsNotification(timesheetStats);
		} catch (Exception e) {
			//getLogger().error("Process failed in importing the timesheet file.", e);
			return false;
		}
		
		return true;
	}

    /**
     * Create a collection of TimeSheets
     *
     * @return a Collection of Longs representing the IDs of a Collection of
     *         Time Sheets
     * @param timeSheetMessages
     *            a collection of (@link
     *            com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetMsgDTO) objects
     * @throws BillingException
     */
    public Collection create(Collection timeSheetMessages, TimeSheetStatisticsDTO timesheetStats) throws BillingException
    {
       // //getLogger().entering(getClass(), "create(Collection)");

        long startTime = System.currentTimeMillis();
        long endTime;
        long createLoopStartTime;
        long createLoopEndTime;

        String todayDateStr = new Date().toString();
        Collection errorMessages = new ArrayList();
        Collection ids = new ArrayList();
        StringBuffer errorMessage = new StringBuffer();
        int goodRecords = 0;
        UserTransaction userTran = null;
        PersistenceProvider provider = null;

        // timesheet DTO's are imported in bulks to avoid transaction timeout
        final int bulkSize = 500;
        int recordsProcessed = 0;

        createLoopStartTime = System.currentTimeMillis();
        try
        {
            userTran = getSessionContext().getUserTransaction();
            provider = beginManualTransaction(userTran);
            
            TimeSheetImportMapper mapper = new TimeSheetImportMapper(provider);
            mapper.cleanUpMonthRecord();

            for (Iterator itr = timeSheetMessages.iterator(); itr.hasNext();)
            {
                TimeSheetMsgDTO timeSheet = (TimeSheetMsgDTO) itr.next();
                Long timesheetID = createTimesheet(timeSheet, errorMessages, provider, timesheetStats);
                if (timesheetID != null && timesheetID.longValue() > 0)
                {
                    ids.add(timesheetID);
                    goodRecords++;
                }

                if (++recordsProcessed % bulkSize == 0)
                {
                    commitManualTransaction(userTran, provider);
                    if (//getLogger().isDebugEnabled())
                    {
                       // //getLogger().debug("Transaction committed at " + recordsProcessed + " records");
                    }
                    provider = beginManualTransaction(userTran);
                }
            }

            commitManualTransaction(userTran, provider);
            if (//getLogger().isDebugEnabled())
            {
               // //getLogger().debug("Transaction committed at the end of record import; records processed: " + recordsProcessed);
            }
        } catch (Exception e)
        {
            rollbackManualTransaction(userTran, provider);

            throw new BillingException("Exception during timesheet import.", e);
        } finally
        {
            forceProviderClose(provider);
        }
        createLoopEndTime = System.currentTimeMillis();

        // log & send notifications
        try
        {
            userTran.begin();

            if (goodRecords > 0)
            {
                logTimeSheetNotification(todayDateStr, Boolean.TRUE, "The system was able to successfully import " + goodRecords + " timesheets.",
                        ImportExportMsgType.IMPORT_TIMESHEETS);
                sendNotification(true, String.valueOf(goodRecords));
            }

            recordsProcessed = 0;
            for (Iterator errIt = errorMessages.iterator(); errIt.hasNext();)
            {
                String thisErr = (String) errIt.next();
                errorMessage.append(thisErr).append("<br>");
                logTimeSheetNotification(todayDateStr, Boolean.FALSE, thisErr, ImportExportMsgType.IMPORT_TIMESHEETS);

                if (++recordsProcessed % bulkSize == 0)
                {
                    userTran.commit();
                    if (//getLogger().isDebugEnabled())
                    {
                       // //getLogger().debug("Transaction committed at " + recordsProcessed + " records.");
                    }
                    userTran.begin();
                }
            }

            // send an email with any errors that occurred
            if (!errorMessages.isEmpty())
            {
                sendNotification(false, errorMessage.toString());
            }

            userTran.commit();
        } catch (Exception e)
        {
            rollbackManualTransaction(userTran);

            throw new BillingException("Exception during log and notify.", e);
        }

        endTime = System.currentTimeMillis();
        if (//getLogger().isInfoEnabled())
        {
           // //getLogger().info(
                    "create(Collection) took " + ((endTime - startTime) / 1000.0) + "s; create loop - " + ((createLoopEndTime - createLoopStartTime) / 1000.0)
                            + "s; notifications - " + ((endTime - createLoopEndTime) / 1000.0) + "s");
        }
        //timesheetStats.setHoursWorked(hoursWorked);
        timesheetStats.setLoadedTimesheet(goodRecords);
       // //getLogger().exiting(getClass(), "create(Collection)");
        return ids;
    }

    /**
     * Create a timesheet.
     *
     * @param timeSheetMessage
     *            the DTO to create timesheet from.
     * @return the ID of the timesheet.
     * @throws RemoteException
     * @throws BillingException
     */
    public Long create(TimeSheetMsgDTO timeSheet) throws BillingException
    {
       // //getLogger().entering(getClass(), "create(TimeSheetMsgDTO)");

        Collection errorMessages = new ArrayList();
        Long id = null;
        UserTransaction userTran = null;
        PersistenceProvider provider = null;
        TimeSheetStatisticsDTO dto = new TimeSheetStatisticsDTO();
        
        try
        {
            userTran = getSessionContext().getUserTransaction();
            provider = beginManualTransaction(userTran);
            id = createTimesheet(timeSheet, errorMessages, provider, dto);
            commitManualTransaction(userTran, provider);
        } catch (Exception e)
        {
            rollbackManualTransaction(userTran, provider);

            throw new BillingException("Exception during timesheet create.", e);
        } finally
        {
            forceProviderClose(provider);
        }

       // //getLogger().exiting(getClass(), "create(TimeSheetMsgDTO)");
        return id;
    }

    /**
     * Creates a timesheet.
     *
     * @param timeSheet
     *            a timesheet DTO.
     * @param errorMessages
     *            a collection of error messages.
     * @param provider
     *            persistence provider.
     *
     * @return an ID of the created timesheet.
     * @throws BillingException
     */
    private Long createTimesheet(TimeSheetMsgDTO timeSheet, Collection errorMessages, PersistenceProvider provider, TimeSheetStatisticsDTO stats)
    {
       // //getLogger().entering(getClass(), "createTimesheet");

        long startTime = System.currentTimeMillis();
        long endTime;
        long startMapTime = 0;
        long endMapTime = 0;
        Long id = null;
        String errorMessage = null;
        double totalhrs = stats.getHoursWorked();
    	double hrs = 0.0;
        
        SingletonRepList repList = SingletonRepList.getInstance();

        try
        {
            if ((timeSheet.getHoursWorked() == 0.0) || (timeSheet.getUnderWriterCode() == null) 
                    || (timeSheet.getDateWorked() == null))
            {
                if ((timeSheet.getUnderWriterCode() == null) || (timeSheet.getUnderWriterCode().trim().length() == 0))
                {
                    errorMessage = ";;" + ((timeSheet.getDateWorked() == null) ? ";" : timeSheet.getDateWorked().toString() + ";") + timeSheet.getHoursWorked()
                            + ";Placement Number Does Not Exist.";
                }
            } else
            {
                TimeSheetImportMapper mapper = new TimeSheetImportMapper(provider);

                startMapTime = System.currentTimeMillis();
                TimeSheetImportEntry timeEntry = mapMsgDTOToDO(timeSheet, provider);
                endMapTime = System.currentTimeMillis();

                // check that a record for this date and placement does not
                // already exist
                Collection timesheets = mapper.checkExisting(timeEntry.getUnderwriterCode(), timeEntry.getDate());
                if (timesheets.isEmpty())
                {
                    mapper.create(timeEntry);
                    id = timeEntry.getID();
                    totalhrs = totalhrs + timeEntry.getHours();
                    stats.setHoursWorked(totalhrs);
                } else
                {
                    errorMessage = timeSheet.getUnderWriterCode() + ";"
                            + repList.getRepName(timeEntry.getUnderwriterCode()) + ";" 
                            + timeSheet.getDateWorked().toString() + ";"
                            + timeSheet.getHoursWorked() + ";Duplicate Work Date.";
                }
            }
        } catch (PersistenceProviderException e)
        {
            errorMessage = "PersistenceProviderException for " + timeSheet + ", in TimeSheetGenerationBean.createTimesheet: " + e.getMessage();
           // //getLogger().error(e.getMessage(), e);
        } catch (BillingException e)
        {
            // errorMessage = "BillingException in
            // TimeSheetGenerationBean.createTimesheet: " + e.getMessage();
            errorMessage = e.getMessage();
           // //getLogger().error(e.getMessage(), e);
        } finally
        {
            // notification must occur after forceProviderClose flush, to
            // prevent
            // transient object errors during the forceProviderClose of the
            // notification business service
            if (errorMessage != null)
            {
                // logTimeSheetNotification(todayDate.toString(),
                // Boolean.FALSE,errorMessage,ImportExportMsgType.IMPORT_TIMESHEETS);
                errorMessages.add(errorMessage);
            }
        }

        endTime = System.currentTimeMillis();
        if (//getLogger().isInfoEnabled())
        {
            StringBuffer durationMsg = new StringBuffer(250);
            durationMsg.append("createTimesheet for ").append(timeSheet).append(" took ").append(endTime - startTime).append("ms. Mapping - ").append(
                    endMapTime - startMapTime).append("ms");
           // //getLogger().info(durationMsg.toString());
        }
       // //getLogger().exiting(getClass(), "createTimesheet");

        return id;
    }

    /**
     * Retrieve a Time Sheet by underwriterCode
     *
     * @return a TimeSheet (@link
     *         com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetDTO) object
     * @param underwriterCode
     *            the code of the underwriter to retrieve a TimeSheet
     * @throws RemoteException *
     * @throws BillingException *
     */
    public Collection getByUnderwriterCode(String underwriterCode) throws BillingException, RemoteException
    {

       // //getLogger().entering(this.getClass(), "getByUnderwriterCode");

        PersistenceProvider provider = null;
        Collection timesheets = new ArrayList();
        try
        {
            provider = this.getProvider();
            TimeSheetImportMapper mapper = new TimeSheetImportMapper(provider);
            Collection timeEntrySummary = mapper.getByUnderwriterCode(underwriterCode);
            Iterator itr = timeEntrySummary.iterator();
            while (itr.hasNext())
            {
                TimeSheetImportEntry timeEntry = (TimeSheetImportEntry) itr.next();
                TimeSheetDTO timeSheet = mapDOToDTO(timeEntry);
                timesheets.add(timeSheet);
            }
        } catch (PersistenceProviderException e)
        {
           // //getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            throw new BillingException("PersistenceProviderException in TimeSheetGenerationBean.getTimeSheetSummary(TimeSheetSearchCriteria criteria)", e);
        } catch (RuntimeException e)
        {
           // //getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            throw new BillingException("RuntimeException in TimeSheetGenerationBean.getTimeSheetSummary(TimeSheetSearchCriteria criteria)", e);
        } finally
        {
            forceProviderClose(provider);
        }
       // //getLogger().exiting(this.getClass(), "getByUnderwriterCode");
        return timesheets;
    }

    /**
     * Retrieve a Time Sheet by its ID
     *
     * @return a TimeSheet (@link
     *         com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetDTO) object
     * @param timeSheetID
     *            the ID of the TimeSheet to retrieve
     * @throws BillingException *
     */
    public TimeSheetDTO getByID(Long timeSheetID) throws BillingException
    {
       // //getLogger().entering(this.getClass(), "getByID");
        PersistenceProvider provider = null;
        TimeSheetDTO timeSheet = null;
        try
        {
            provider = getProvider();
            TimeSheetImportMapper mapper = new TimeSheetImportMapper(provider);
            TimeSheetImportEntry timeEntry = mapper.read(timeSheetID);
            timeSheet = mapDOToDTO(timeEntry);
        }

        catch (PersistenceProviderException e)
        {
           // //getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            throw new BillingException("PersistenceProviderException in TimeSheetGenerationBean.getByID(Long timeSheetID) ", e);
        } catch (RuntimeException e)
        {
           // //getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            throw new BillingException("RuntimeException in TimeSheetGenerationBean.getByID(Long timeSheetID) ", e);
        } finally
        {
            forceProviderClose(provider);
        }

       // //getLogger().exiting(this.getClass(), "getByID");
        return timeSheet;
    }

    /**
     * Delete a Time Sheet.
     *
     * @param timeSheetID
     *            the ID of the TimeSheet to delete.
     * @throws BillingException
     */
    public void delete(Long timeSheetID) throws BillingException
    {
       // //getLogger().entering(getClass(), "delete");
        PersistenceProvider provider = null;
        UserTransaction userTran = null;
        try
        {
            userTran = getSessionContext().getUserTransaction();
            provider = beginManualTransaction(userTran);
            TimeSheetImportMapper mapper = new TimeSheetImportMapper(provider);
            TimeSheetImportEntry timeEntry = mapper.read(timeSheetID);
            mapper.remove(timeEntry);
            commitManualTransaction(userTran, provider);
        } catch (Exception e)
        {
            rollbackManualTransaction(userTran, provider);
           // //getLogger().error(e.getMessage(), e);
            throw new BillingException("Exception during timesheet delete.", e);
        } finally
        {
            forceProviderClose(provider);
        }

       // //getLogger().exiting(getClass(), "delete");
    }

    /**
     * Search for TimeSheets on a number of different criteria
     *
     * @return a Collection of (@link
     *         com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetDTO) objects
     * @param criteria
     *            a search criteria (@link
     *            com.radian.cuwbilling.billing.cuw.bs.TimeSheetSearchCriteria)
     *            object
     * @throws RemoteException *
     * @throws BillingException *
     */
    public Collection getTimeSheetSummary(TimeSheetSearchCriteria criteria) throws BillingException
    {
       // //getLogger().entering(this.getClass(), "getTimeSheetSummary");

        PersistenceProvider provider = null;
        Collection timeSheetSummary = new ArrayList();
        try
        {
            provider = this.getProvider();
            TimeSheetImportMapper mapper = new TimeSheetImportMapper(provider);
            Collection timeEntrySummary = mapper.getByCriteria(criteria);
            Iterator itr = timeEntrySummary.iterator();
            while (itr.hasNext())
            {
                TimeSheetImportEntry timeEntry = (TimeSheetImportEntry) itr.next();
                TimeSheetDTO timeSheet = mapDOToDTO(timeEntry);
                timeSheetSummary.add(timeSheet);
            }
        } catch (PersistenceProviderException e)
        {
           // //getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            throw new BillingException("PersistenceProviderException in TimeSheetGenerationBean.getTimeSheetSummary(TimeSheetSearchCriteria criteria)", e);
        } catch (RuntimeException e)
        {
           // //getLogger().error(e.getMessage(), e);
            getSessionContext().setRollbackOnly();
            throw new BillingException("RuntimeException in TimeSheetGenerationBean.getTimeSheetSummary(TimeSheetSearchCriteria criteria)", e);
        } finally
        {
            forceProviderClose(provider);
        }

       // //getLogger().exiting(this.getClass(), "getTimeSheetSummary");
        return timeSheetSummary;
    }

    /**
     * Update a TimeSheet.
     *
     * @param timeSheet
     *            a timesheet DTO for update.
     * @throws BillingException
     */
    public void update(TimeSheetDTO timeSheet) throws BillingException
    {
       // //getLogger().entering(this.getClass(), "update");

        PersistenceProvider provider = null;
        UserTransaction userTran = null;
        try
        {
            userTran = getSessionContext().getUserTransaction();
            provider = beginManualTransaction(userTran);
            TimeSheetImportMapper mapper = new TimeSheetImportMapper(provider);
            TimeSheetImportEntry timeSheetEntry = mapper.read(timeSheet.getTimeSheetID());
            mapDTOtoDO(timeSheet, timeSheetEntry);
            commitManualTransaction(userTran, provider);
        } catch (Exception e)
        {
            rollbackManualTransaction(userTran, provider);
           // //getLogger().error(e.getMessage(), e);
            throw new BillingException("Exception during timesheet update.", e);
        } finally
        {
            forceProviderClose(provider);
        }

       // //getLogger().exiting(this.getClass(), "update");
    }

    public void create(String batchNumber, Boolean successFlag, String description) throws CUWBillingException
    {
       // //getLogger().entering(this.getClass(), "importTimeSheets");

        logTimeSheetNotification(batchNumber, successFlag, description, ImportExportMsgType.IMPORT_TIMESHEETS);
        sendNotification(false, description);

       // //getLogger().exiting(this.getClass(), "importTimeSheets");
    }

    /**
     * map TimeSheetImportEntry Domain Object to TimeSheetDTO.
     *
     * @param timeSheetEntry
     *            a (@link com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetDTO)
     *            object reference
     * @return TimeSheetDTO object.
     */
    private TimeSheetDTO mapDOToDTO(TimeSheetImportEntry timeSheetEntry)
    {
        TimeSheetDTO timeSheet = new TimeSheetDTO();
        timeSheet.setDateWorked(DateDTO.dateToDateDTO(timeSheetEntry.getDate()));
        timeSheet.setTimeSheetHours(timeSheetEntry.getHours());
        timeSheet.setTimeSheetID(timeSheetEntry.getID());
        timeSheet.setLastModified(DateDTO.dateToDateDTO(timeSheetEntry.getModifiedDate()));

//        timeSheet.setBilled(Boolean.FALSE);
//        if (timeSheetEntry.getBillingStatus() != null && timeSheetEntry.getBillingStatus() == BillingStatus.BILLED)
//        {
//            timeSheet.setBilled(Boolean.TRUE);
//        }

        String underwriterCode = timeSheetEntry.getUnderwriterCode();
        if (underwriterCode != null)
        {
                timeSheet.setUnderwriterCode(underwriterCode); // placement

        }
        return timeSheet;
    }

    /**
     * Maps TimeSheetMsgDTO to TimeSheetImportEntry DO.
     *
     * @param timeSheet
     *            a (@link com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetMsgDTO)
     *            object for create method.
     * @param provider
     *            a PersistenceProvider reference for retreiving Placement
     *            value.
     * @return TimeSheetImportEntry domain object.
     * @throws BillingException
     */
    private TimeSheetImportEntry mapMsgDTOToDO(TimeSheetMsgDTO timeSheet, PersistenceProvider provider) throws BillingException
    {
       // //getLogger().entering(getClass(), "mapMsgDTOToDO");

        TimeSheetImportEntry timeSheetEntry = new TimeSheetImportEntryImpl();
        timeSheetEntry.setHours(new Double(timeSheet.getHoursWorked()));
        timeSheetEntry.setDate(timeSheet.getDateWorked().getTime());
        timeSheetEntry.setUnderwriterCode(timeSheet.getUnderWriterCode());

        return timeSheetEntry;
    }

    /**
     * map TimeSheetDTO to TimeSheetImportEntry DO.
     *
     * @param timeSheet
     *            a (@link com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetDTO)
     *            object reference for update method
     * @return TimeSheetImportEntry domain object.
     */
    private void mapDTOtoDO(TimeSheetDTO timeSheet, TimeSheetImportEntry timesheetDO)
    {
        TimeSheetImportEntry timeSheetEntry = new TimeSheetImportEntryImpl();
        timeSheetEntry.setID(timeSheet.getTimeSheetID());
        timeSheetEntry.setDate(DateDTO.toDate(timeSheet.getDateWorked()));
        timeSheetEntry.setHours(timeSheet.getTimeSheetHours());
        // cannot update the placement once set
        return;
    }

    /**
     * Creates an import/export log record
     *
     * @param trackingNumber
     *            Allows correlation between a request and response.
     * @param successFlag
     *            Identifies whether the requested action was a success or
     *            failure.
     * @param description
     *            Description of the action/error that occurred.
     * @param messageType
     *            Enum defining type of message being logged.
     */
    private void logTimeSheetNotification(String batchNumber, Boolean successFlag, String description, ImportExportMsgType messageType)
    {
       // //getLogger().entering(this.getClass(), "logTimeSheetNotification");
        try
        {
            // Create new billing import/export log record with success/failure
            // message
            ImportExportMsgDTO message = new ImportExportMsgDTO();
            message.setBatchNumber(batchNumber);
            message.setMessageText(description);
            message.setSuccessFlag(successFlag);
            message.setImportExportMsgType(messageType);
            ImportExportMsgAdministration service = SystemServiceFactory.instance().getImportExportMsgAdminService();
            service.create(message);
        }
        catch (ImportExportMsgException e)
        {
            // don't throw an error, as this is a batch process that should
            // continue
            // regardless
           // //getLogger().error("ImportExportMsgException in logTimeSheetNotification:" + e.getMessage(), e);
        }
       // //getLogger().exiting(this.getClass(), "logImportExportNotification");
    }

    private void sendNotification(boolean success, String message)
    {

        PersistenceProvider provider = null;
        AxiomEventCategory category;
        String messageKey;

        try
        {
            // create a notification
            Object[] params = new Object[1];
            params[0] = message;

            if (success)
            {
                category = AxiomEventCategory.PROCESS_SUCCEEDED;
                messageKey = "timesheet.import.succeeded";
            } else
            {
                category = AxiomEventCategory.PROCESS_FAILED;
                messageKey = "timesheet.import.failed";
            }

            provider = this.getProvider();

            NullEntityImpl nullObj = new NullEntityImpl();
            nullObj.setID(new Long(0));
            EventRouterDelegate.getInstance().send(provider, nullObj, AxiomEntityType.TIMESHEET, category, messageKey, "Notifications", params);

        } catch (PersistenceProviderException e)
        {
            // don't throw an error, as this is a batch process that should
            // continue regardless
           // //getLogger().error("PersistenceProviderException in logTimeSheetNotification:" + e.getMessage(), e);
        } finally
        {
            forceProviderClose(provider);
        }

    }

    private void sendStatsNotification(TimeSheetStatisticsDTO timesheetStats)
    {

        PersistenceProvider provider = null;
        AxiomEventCategory category;
        String messageKey;

        try
        {
            // create a notification
        	
		    Object[] params = new Object[7];
		    params[0] = timesheetStats.getFilename();
		    params[1] = Integer.toString(timesheetStats.getTotalTimesheet());
		    params[2] = Double.toString(timesheetStats.getTotalHours());
		    params[3] = Integer.toString(timesheetStats.getLoadedTimesheet());
		    params[4] = Double.toString(timesheetStats.getHoursWorked());
		    params[5] = Integer.toString(timesheetStats.getRejectedTimesheet());
		    params[6] = Double.toString(timesheetStats.getRejectedHours());
          
            category = AxiomEventCategory.PROCESS_SUCCEEDED;
            messageKey = "timesheet.import.stats";
            

            provider = this.getProvider();

            NullEntityImpl nullObj = new NullEntityImpl();
            nullObj.setID(new Long(0));
            EventRouterDelegate.getInstance().send(provider, nullObj, AxiomEntityType.TIMESHEET, category, messageKey, "Notifications", params);

        } catch (PersistenceProviderException e)
        {
            // don't throw an error, as this is a batch process that should
            // continue regardless
           // //getLogger().error("PersistenceProviderException in logTimeSheetNotification:" + e.getMessage(), e);
        } finally
        {
            forceProviderClose(provider);
        }

    }
    /**
     * Helper to begin bean managed transaction that involves persistence
     * provider.
     *
     * @param userTran
     * @return persistence provider associated with the started transaction.
     * @throws NotSupportedException
     * @throws SystemException
     * @throws PersistenceProviderException
     */
    private PersistenceProvider beginManualTransaction(UserTransaction userTran) throws NotSupportedException, SystemException, PersistenceProviderException
    {
        userTran.begin();
        return getProvider();
    }

    /**
     * Helper to commit a bean managed transaction.
     *
     * @param userTran
     * @param provider
     *            usually this should be the provider returned by the previous
     *            call to <code>beginManualTransaction</code>.
     * @throws SecurityException
     * @throws IllegalStateException
     * @throws RollbackException
     * @throws HeuristicMixedException
     * @throws HeuristicRollbackException
     * @throws SystemException
     * @throws PersistenceProviderException
     */
    private void commitManualTransaction(UserTransaction userTran, PersistenceProvider provider) throws SecurityException, IllegalStateException,
            RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, PersistenceProviderException
    {
        forceProviderClose(provider);
        userTran.commit();
    }

    /**
     * Helper to rollback a bean managed transaction.
     *
     * @param userTran
     *            the transaction to rollback.
     * @param provider
     *            usually this should be the provider returned by the previous
     *            call to <code>beginManualTransaction</code>.
     */
    private void rollbackManualTransaction(UserTransaction userTran, PersistenceProvider provider)
    {
        forceProviderClose(provider);
        rollbackManualTransaction(userTran);
    }

    /**
     * Helper to rollback a bean managed transaction. This is overload for when
     * there is no persistence provider involved in the transaction.
     *
     * @param userTran
     *            the transaction to rollback.
     */
    private void rollbackManualTransaction(UserTransaction userTran)
    {
        try
        {
            if (userTran != null && userTran.getStatus() == Status.STATUS_ACTIVE)
            {
                userTran.rollback();
            }
        } catch (Exception e)
        {
           // //getLogger().warn("Unable to rollback the transaction.", e);
        }
    }
    
    /**
     * Loop through the given stream line by line. Validate each line against given format.
     * 	Construct error message string according to error message collection.
     * 	Return error message string.
     * 
     * @param in: representing timesheet file
     * @return: error message string
     */
    private byte [] getValidatedTimeSheet(InputStream in, String[] error, boolean flag, TimeSheetStatisticsDTO stats)
    {
    	Collection errorMessages = new ArrayList();
    	String anEntry = null;
    	StringBuffer errorMessage = new StringBuffer();
    	String todayDateStr = new Date().toString();
    	StringBuffer timesheet = new StringBuffer();
    	
    	
    	if( in == null)
    	{
    		return null;
    	}
    	DataInputStream din = new DataInputStream(in);

    	try {
    		int total = 0;;
	    	while( (anEntry = din.readLine()) != null )
	    	{
	    		total++;
	    		if(validateTimeSheetEntry(anEntry, errorMessages, flag, stats))
	    		{
	    			timesheet.append(anEntry + "\n");
	    		}
	    	}
	    	stats.setTotalTimesheet(total);
//	    	stats.setTotalHours(totalHrs);
//	    	stats.setRejectedHours(rejectedHrs);
	        stats.setRejectedTimesheet(errorMessages.size());
    	} catch (Exception e) {
    		//getLogger().error("Unable to validate the timesheet file.", e);
    	} finally {
    		try {
    			din.close();
    		} catch (Exception e) {
    			//getLogger().error("Unable to close data input stream .", e);
    		}
    	}
   
        for (Iterator errIt = errorMessages.iterator(); errIt.hasNext();)
        {
            String thisErr = (String) errIt.next();
            errorMessage.append(thisErr).append("<br>");
            logTimeSheetNotification(todayDateStr, Boolean.FALSE, thisErr, ImportExportMsgType.IMPORT_TIMESHEETS);

        }

        error[0] = errorMessage.toString();
        
        return timesheet.toString().getBytes();
    }
    
    /**
     * Validate the timesheet entry is in the format of: xxx,dd.dd,yyyy-MM-dd.
     *   If not, construct error messages and return the error message collection.
     * 
     * @param entry
     * @param errors
     * @return
     */
    private boolean  validateTimeSheetEntry(String entry, Collection errors, boolean flag, TimeSheetStatisticsDTO stats)
    {
    	String error = "";
    	String [] fields = entry.split(",");
    	double rejhrs = stats.getRejectedHours();
    	double totalhrs = stats.getTotalHours();
    	double hrs = 0.0;
    	boolean isContinue = false;
    	
    	// if field number is not 3, return
    	if( fields.length != 3)
    	{
    		error = entry+": "+" field count not right or not delimitted by ','." ;
    		errors.add(error);
    		flag = false;
    		return false;
    	}
    	
    	// check placement number. it should be 3 chars
    	if( fields[0] == null || fields[0].length() != 3 )
    	{
    		if( fields[0].length() == 8 )
	    	{
	    		error += fields[0] + "; " + fields[2] + "; " + fields[1] + "; Service Center Placement.";
	    		isContinue = true;
	    		flag = true;
	    	}
	    	else
	    	{
	    		error += fields[0]+": "+ " underwriter code does not have 3 characters. " ;
	    	}
    	}
    	
    	// hours field should be a valid double number/ integer
    	if(fields[1] != null )
    	{
    		Pattern pattern = Pattern.compile("(^\\d{0,3}\\.\\d{1,3}$)|(^\\d{1,3}\\.?$)");  //set the Regluar Expression
    		Matcher matcher = pattern.matcher(fields[1]);  //validate the input
    		 
    		if(!matcher.matches())   {
    			error += fields[1]+": "+ " wrong 'hours' format. ";
    			//number format wrong
    		}
    		try {
                hrs = Double.valueOf(fields[1]).doubleValue();               
             } catch (NumberFormatException nfe) {
                System.out.println("NumberFormatException: " + nfe.getMessage());
             }
             totalhrs = totalhrs + hrs;
             stats.setTotalHours(totalhrs);
    	}
    	
    	// date worked should be in the format of "yyyy-MM-dd"
    	if(fields[2] != null)
    	{
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		df.setLenient(false);
    		ParsePosition pos = new ParsePosition(0);
    		
    		Date date = df.parse(fields[2], pos);
    		
            // Check all possible things that signal a parsing error
    		if ((date == null) || (pos.getErrorIndex() != -1)) {
    			error += fields[2]+": "+ " wrong date format or invalid date.";
    		} else if( date.after(new Date())) {
    			error += fields[2]+": "+ " future date.";
    		}
    	}
    	
    	if( error != null && error.length()>1)
    	{
    		errors.add(error);  
    		try {
                hrs = Double.valueOf(fields[1]).doubleValue();               
             } catch (NumberFormatException nfe) {
                System.out.println("NumberFormatException: " + nfe.getMessage());
             }
             rejhrs = rejhrs + hrs;
            stats.setRejectedHours(rejhrs);
    		if (isContinue == true)
    		{
    			flag = true;
    		}
    		else
    		{
    			flag = false;
    		}
    		return false;
    	} 
    	flag = true;
    	return true;
    }
    
    /**
     * insert timestamp for the timesheet name
     * @param bkFileName
     * @return
     */
    private String getBackupTimesheetName( String bkFileName ) {
    	if(bkFileName == null ||bkFileName.length()<1 ){
    		return null;
    	}
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    	Date d = new Date();
    	return bkFileName.replaceAll(".csv", "_"+df.format(d)+".csv");
    }
    
    /**
     * this method will skip the directories and return the true file type filename
     * @param files
     * @return
     */
    private String getTrueFileName( FTPFile [] files ){
    	if (files == null || files.length < 1){
    		return null;
    	} else {
    		for (int i=0; i<files.length; i++)
    			if (files[i].getType() == FTPFile.FILE_TYPE){
    				return files[i].getName();
    			}
    		}
    	
    	return null;
    }

}
