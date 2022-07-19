package com.radian.cuwbilling.billing.cuw.bo.dto.assembler;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.radian.cuwb.batch.timeShet.BillingExportBean;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.RepInfoDTO;
import com.radian.webserviceclient.WSClientException;
import com.radian.webserviceclient.WSClientExceptionStatus;
import com.radian.webserviceclient.WSClientMionlineNew;
public class SingletonRepList {
	//Logs and MIOnlineClient class has to integrate -KP
	public static SingletonRepList instance = null;
	private static HashMap<String,Object> reps = new HashMap<String,Object>();
	private static WSClientMionlineNew wsMionline = null;
	static Logger logger = LoggerFactory.getLogger(BillingExportBean.class);
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
			logger.error("Couldn't get class SingletonRepList" );    
		}
		return instance;
	}
	private void addRep( String repId, Object repName ){
		reps.put(repId, repName);
	}
	public String getRepName( String repId ){
		Object repName = reps.get(repId);
		if( repName == null ||((String)repName).length()==0){
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
	private static String getUnderwriterFromMI( String uwCode ){
		RepInfoDTO dtoRepinfo =  null;
		try {
			if(wsMionline == null ){
				wsMionline=null;
			}
			dtoRepinfo =  wsMionline.getRepInfo(uwCode);
		} catch (WSClientException e){	
			logger.error("Error "+e);
		}
		catch (WSClientExceptionStatus e){	
			logger.error("Error "+e);
		}
		if( dtoRepinfo == null){
			return "";
		}
		return dtoRepinfo.getName();
	}
}
