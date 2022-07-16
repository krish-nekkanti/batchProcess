package com.radian.webserviceclient.miOnline.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CopySource {
	
	
	public static void main(String[] args) throws IOException {
		String packages="import com.radian.cuwbilling.billing.common.bo.codes.BillingStatus;\r\n"
//				+ "import com.radian.cuwbilling.billing.common.bs.BillingException;\r\n"
//				+ "import com.radian.cuwbilling.billing.common.os.persistence.TimeSheetImportMapper;\r\n"
//				+ "import com.radian.cuwbilling.billing.common.os.persistence.TimesheetMapper;\r\n"
//				+ "import com.radian.cuwbilling.billing.cuw.bo.domain.TimeSheetImportEntry;\r\n"
//				+ "import com.radian.cuwbilling.billing.cuw.bo.domain.impl.TimeSheetImportEntryImpl;\r\n"
//				+ "import com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetDTO;\r\n"
//				+ "import com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetMsgDTO;\r\n"
//				+ "import com.radian.cuwbilling.billing.cuw.bo.dto.TimeSheetStatisticsDTO;\r\n"
//				+ "import com.radian.cuwbilling.billing.cuw.bo.dto.UnbilledOrderDTO;\r\n"
//				+ "import com.radian.cuwbilling.billing.cuw.bo.dto.assembler.SingletonRepList;\r\n"
//				+ "import com.radian.cuwbilling.billing.cuw.bs.CUWBillingException;\r\n"
//				+ "import com.radian.cuwbilling.billing.cuw.bs.TimeSheetSearchCriteria;\r\n"
//				+ "import com.radian.cuwbilling.common.bo.codes.AxiomEntityType;\r\n"
//				+ "import com.radian.cuwbilling.common.bo.domain.impl.NullEntityImpl;\r\n"
//				+ "import com.radian.cuwbilling.common.bo.dto.DateDTO;\r\n"
//				+ "import com.radian.cuwbilling.system.batch.bo.dto.ImportExportMsgDTO;\r\n"
//				+ "import com.radian.cuwbilling.system.batch.bs.ImportExportMsgAdministration;\r\n"
//				+ "import com.radian.cuwbilling.system.batch.bs.ImportExportMsgException;\r\n"
//				+ "import com.radian.cuwbilling.system.common.bo.code.ImportExportMsgType;\r\n"
//				+ "import com.radian.cuwbilling.system.common.bs.SystemServiceFactory;\r\n"
//				+ "import com.radian.cuwbilling.system.notification.bo.code.AxiomEventCategory;\r\n"
//				+ "import com.radian.cuwbilling.system.notification.bs.eventrouter.EventRouterDelegate;\r\n"
//				+ "import com.radian.foundation.common.config.Configuration;\r\n"
//				+ "import com.radian.foundation.common.util.ServiceLocator;\r\n"
//				+ "import com.radian.foundation.os.persistence.spi.PersistenceProvider;\r\n"
//				+ "import com.radian.foundation.os.persistence.spi.PersistenceProviderException;"
//				+"import com.radian.cuwbilling.common.bo.codes.AxiomEntityType;\r\n"
//				+ "import com.radian.cuwbilling.common.bo.domain.AxiomEntity;\r\n"
//				+ "import com.radian.cuwbilling.system.notification.bo.code.AxiomEventCategory;\r\n"
//				+ "import com.radian.cuwbilling.system.notification.bo.domain.AxiomEventType;\r\n"
//				+ "import com.radian.cuwbilling.system.notification.bo.domain.Event;\r\n"
//				+ "import com.radian.cuwbilling.system.notification.bs.JMSHelper;\r\n"
//				+ "import com.radian.cuwbilling.system.notification.os.persistence.AxiomEventTypeMapper;\r\n"
//				+ "import com.radian.foundation.common.logging.LogManager;\r\n"
//				+ "import com.radian.foundation.common.logging.Logger;\r\n"
//				+ "import com.radian.foundation.os.persistence.spi.PersistenceProvider;\r\n"
//				+ "import com.radian.foundation.os.persistence.spi.PersistenceProviderException;"
//				+ "import com.radian.foundation.bo.codes.BaseEnumType;"
//				+ "import com.radian.foundation.common.logging.LogManager;\r\n"
//				+ "import com.radian.foundation.common.logging.Logger;\r\n"
				+ "import com.radian.foundation.common.exception.ApplicationException;\r\n"
				+ "import com.radian.cuwbilling.common.bo.domain.BaseDomainObject"
				+ "import com.radian.foundation.os.persistence.spi.PersistenceProvider;";
		
		
		String imporotNames[]=packages.split("\n");
		String sourceFolder="C:"+File.separator+"Users"+File.separator+"knekkanti-d"+File.separator+"git"+File.separator+"CUWBilling"+File.separator+"java"+File.separator+"main\\";
		String sourceFolder1="C:"+File.separator+"Users"+File.separator+"knekkanti-d"+File.separator+"git"+File.separator+"CUWBilling"+File.separator+"foundation"+File.separator+"java"+File.separator+"main\\";

		String targetFolder="C:"+File.separator+"Users"+File.separator+"knekkanti-d"+File.separator+"git"+File.separator+"CUWBilling2"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"\\";;
		//"C:"+File.separator+""+File.separator+"Users"+File.separator+"knekkanti-d"+File.separator+"git"+File.separator+"CUWBilling"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"java"+File.separator+"com"+File.separator+"radian"+File.separator+"cuwb"+File.separator+"batch"+File.separator+"timeSheet\\";
		for (String importName : imporotNames) {
			importName=importName.replace("import ","");
			importName=importName.replace(".",""+File.separator+"");
			importName=importName.replace(";\r",".java");

			String aa = sourceFolder+importName;
			System.out.println(importName);
			File sourcePath = new File(aa);
			System.out.println(aa);
			//importName=importName.replace("com\\radian\\","");
			System.out.println(targetFolder+importName);

			try {
				FileUtils.copyFile(sourcePath,new File(targetFolder+importName));
			} catch (Exception e) {
				 aa = sourceFolder1+importName;
				System.out.println(importName);
				 sourcePath = new File(aa);
					try {
						FileUtils.copyFile(sourcePath,new File(targetFolder+importName));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			}
			
		}
	}

}
