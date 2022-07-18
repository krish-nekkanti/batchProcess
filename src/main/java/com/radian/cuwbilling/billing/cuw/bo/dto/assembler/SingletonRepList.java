package com.radian.cuwbilling.billing.cuw.bo.dto.assembler;

import java.util.HashMap;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.RepInfoDTO;
import com.radian.foundation.common.logging.Logger;
import com.radian.webserviceclient.WSClientException;
import com.radian.webserviceclient.WSClientExceptionStatus;
import com.radian.webserviceclient.WSClientMionlineNew;

/**
 * @author jwu
 * 
 * To hold and manage the rep list 
 * 
 */
public class SingletonRepList 
{
	
	//Logs and MIOnlineClient class has to integrate -KP
   public static SingletonRepList instance = null;
   
   private static HashMap reps = new HashMap();
   private static WSClientMionlineNew wsMionline = null;
   
   private static Logger logger = null;
   
   protected SingletonRepList() {
      // Exists to defeat instantiation 
   }
   public static synchronized SingletonRepList getInstance() {
      if(instance != null) {
         return instance;
      }
      try {
    	  instance = new SingletonRepList();
      }
      catch(Exception cnf) {
         logger.fatal("Couldn't get class SingletonRepList" );    
      }

      return instance;
   }
   
   private void addRep( String repId, Object repName )
   {
	   reps.put(repId, repName);
   }
   
   public String getRepName( String repId )
   {
	   Object repName = reps.get(repId);
	   if( repName == null ||((String)repName).length()==0)
	   {
//    	   call web service to get the underwriter name
		   repName = getUnderwriterFromMI(repId);
    	   this.addRep(repId, repName);
	   }
	   return (String)repName;
   }
   
   /**
    * 
    * @param uwCode
    * @return
    * @throws WSClientException
    */
   private static String getUnderwriterFromMI( String uwCode )
   {
   	RepInfoDTO dtoRepinfo =  null;
   	
   	try {
			//call MIOnline
   		if(wsMionline == null )
   		{
   			wsMionline=null;
   		}
			dtoRepinfo =  wsMionline.getRepInfo(uwCode);
			
   	} catch (WSClientException e)
		{	//any exception, except status, is wrapped and rethrown as WSClientException
			e.printStackTrace();
		}
		catch (WSClientExceptionStatus e)
		{	//access to call_returnstatus from MIOnline web service call
			e.getStatus().getErrorcode();
			//getLogger().debug(e.getMessage());
		}
		
		if( dtoRepinfo == null)
		{
			return "";
		}
		return dtoRepinfo.getName();

   }
}

                     


