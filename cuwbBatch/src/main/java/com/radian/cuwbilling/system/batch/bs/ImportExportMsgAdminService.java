/*
 * Created on Mar 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.radian.cuwbilling.system.batch.bs;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.radian.cuwbilling.billing.cuw.bs.CUWBillingException;
import com.radian.cuwbilling.system.batch.bo.domain.ImportExportMessage;
import com.radian.cuwbilling.system.batch.bo.dto.ImportExportMsgDTO;
import com.radian.cuwbilling.system.batch.bo.dto.assembler.ImportExportMsgAssembler;
import com.radian.cuwbilling.system.batch.os.persistence.ImportExportMsgMapper;
import com.radian.foundation.common.exception.ValidationException;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;
/**
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImportExportMsgAdminService{
	/**
	 * Creates new Import/Export Message
	 * 
	 * @param ImportExportMsgDTO
	 * @throws ImportExportMsgException
	 */
	Logger logger = LoggerFactory.getLogger(ImportExportMsgAdminService.class);
	PersistenceProvider provider = null;
	ImportExportMsgMapper mapper = new ImportExportMsgMapper(provider);
	
	public Long create(ImportExportMsgDTO importExportMsgDTO) throws ImportExportMsgException{
		logger.debug( "create(ImportExportMsgDTO importExportMsgDTO)");
		ImportExportMessage importExportMsgDO = null;
		Long importExportMsgID = null;
		try{
			ImportExportMsgAssembler assembler = new ImportExportMsgAssembler();
			importExportMsgDO = (ImportExportMessage) assembler.createDO(importExportMsgDTO, provider);
			mapper.create(importExportMsgDO);
			importExportMsgID = importExportMsgDO.getID();
		} catch (ValidationException e){
			logger.error(e.getMessage(), e);
			throw new ImportExportMsgException("Validation Exception in ImportExportMsgAdminBean.create(ImportExportMsgDTO importExportMsgDTO)", e);
		} catch (PersistenceProviderException e){
			logger.error(e.getMessage(), e);
			throw new ImportExportMsgException("PersistenceProviderException in ImportExportMsgAdminBean.create(ImportExportMsgDTO importExportMsgDTO) ", e);
		} catch (RuntimeException e){
			throw new ImportExportMsgException("RuntimeProviderException in ImportExportMsgAdminBean.create(ImportExportMsgDTO importExportMsgDTO) ", e);
		} finally{
		}
		logger.debug( "create(ImportExportMsgDTO importExportMsgDTO)");
		return importExportMsgID;
	}
	/**
	 * Get Import/Export Message by ID
	 * 
	 * @param importExportMsgID
	 * @throws ImportExportMsgException
	 */
	public ImportExportMsgDTO getByID(Long importExportMsgID) throws ImportExportMsgException{
		logger.debug( "getByID()");
		
		ImportExportMsgDTO importExportMsgDTO = null;
		try{
			ImportExportMessage importExportMsgDO = mapper.read(importExportMsgID);
			ImportExportMsgAssembler assembler = new ImportExportMsgAssembler();
			importExportMsgDTO = (ImportExportMsgDTO) assembler.createDTO(importExportMsgDO, provider);
		} catch (ValidationException e){
			logger.error(e.getMessage(), e);
			throw new ImportExportMsgException("Validation Exception in ImportExportMsgAdminBean.getByID()", e);
		} catch (PersistenceProviderException e){
			logger.error(e.getMessage(), e);
			throw new ImportExportMsgException("PersistenceProviderException in ImportExportMsgAdminBean.getByID()", e);
		} finally{
		}
		logger.debug("getByID()");
		return importExportMsgDTO;
	}
	/**
	 * retrieves a Collection of
	 * {@link com.radian.cuwbilling.system.batch.bo.dto.ImportExportMsgDTO}
	 * 
	 * @param importExportMsgSearchCriteria
	 * @throws CUWBillingException
	 */
	public List<ImportExportMsgDTO> findByCriteria(ImportExportMsgSearchCriteria importExportMsgSearchCriteria) throws ImportExportMsgException{
		logger.debug( "findByCriteria()");
		
		List<ImportExportMessage> importExportMsgDOs = null;
		List<ImportExportMsgDTO> importExportMsgDTOs = new ArrayList<ImportExportMsgDTO>();
		try{
			importExportMsgDOs = (List<ImportExportMessage>) mapper.getByCriteria(importExportMsgSearchCriteria);
			if ((importExportMsgDOs != null) && (importExportMsgDOs.size() > 0)){
				Iterator<ImportExportMessage> iter = importExportMsgDOs.iterator();
				ImportExportMsgAssembler assembler = new ImportExportMsgAssembler();
				while (iter.hasNext()){
					ImportExportMessage importExportMsg = (ImportExportMessage) iter.next();
					importExportMsgDTOs.add((ImportExportMsgDTO) assembler.createDTO(importExportMsg, provider));
				}
			}
		} catch (ParseException e){
			logger.error(e.getMessage(), e);
			throw new ImportExportMsgException("ParseException in ImportExportMsgAdminBean.findByCriteria()", e);
		} catch (PersistenceProviderException e){
			logger.error(e.getMessage(), e);
			throw new ImportExportMsgException("PersistenceProviderException in ImportExportMsgAdminBean.findByCriteria()", e);
		} catch (ValidationException e){
			logger.error(e.getMessage(), e);
			throw new ImportExportMsgException("Validation Exception in ImportExportMsgAdminBean.findByCriteria()", e);
		} finally{
		}
		logger.debug( "findByCriteria");
		return importExportMsgDTOs;
	}
}
