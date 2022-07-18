/*
 * BaseAssembler.java
 *
 * Created on September 17, 2003, 8:31 AM
 */

package com.radian.cuwbilling.common.bo.dto;

import org.apache.commons.beanutils.BeanUtils;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.foundation.bo.dto.BaseDTO;
import com.radian.foundation.common.exception.ValidationException;
import com.radian.foundation.common.logging.Logger;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;

/**
 * This class represents an assembler design pattern. It should be derived from
 * when building DTO<->DO transformation code.
 * 
 * @author Arul Srinivasan
 */
public abstract class BaseAssembler
{

    /** Creates a new instance of BaseAssembler */
    private transient Logger logger;

    public BaseAssembler()
    {
        logger =null;
    }

    protected final Logger getLogger()
    {
        return logger;
    }

    /**
     * This method converts a given domain object into a DTO
     * 
     * @param obj
     *            The DO object to convert.
     * @param provider
     *            the persistence provider to use.
     * @throws PersistenceProviderException,
     *             ValidationException
     * @return a new DTO object of the correct type.
     */
    public abstract BaseDTO createDTO(BaseDomainObject obj, PersistenceProvider provider) throws PersistenceProviderException, ValidationException;

    /**
     * This method <B>CREATES</B> a given DTO object into a domain object.
     * 
     * @param dto
     *            The DTO object to convert.
     * @param provider
     *            the persistence provider to use.
     * @throws PersistenceProviderException,
     *             ValidationException
     * @return a domain object representing the DTO given.
     */
    public abstract BaseDomainObject createDO(BaseDTO dto, PersistenceProvider provider) throws PersistenceProviderException, ValidationException;

    /**
     * This method moves information from a DTO to an existing domain object.
     * 
     * @param dto
     *            The DTO object to convert.
     * @param obj
     *            The destination domain object.
     * @param provider
     *            the persistence provider to use.
     * @throws PersistenceProviderException,
     *             ValidationException
     */
    public abstract void toDO(BaseDTO dto, BaseDomainObject obj, PersistenceProvider provider) throws PersistenceProviderException, ValidationException;

    /**
     * This method uses the BeanUtils to copy the attributes where the names are
     * same.
     * 
     * @param dto
     *            The destination.
     * @param obj
     *            The origin.
     * @throws ValidationException
     */
    protected void setDTO(BaseDTO dto, BaseDomainObject obj) throws ValidationException
    {
        try
        {
            BeanUtils.copyProperties(dto, obj);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new ValidationException(e);
        }
    }

    /**
     * This method uses the BeanUtils to copy the attributes where the names are
     * same.
     * 
     * @param obj
     *            The destination.
     * @param dto
     *            The origin.
     * @throws ValidationException
     */
    protected void setDO(BaseDomainObject obj, BaseDTO dto) throws ValidationException
    {
        try
        {
            BeanUtils.copyProperties(obj, dto);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new ValidationException(e);
        }
    }

}
