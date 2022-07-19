/*
 * Created on Mar 2, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.radian.cuwbilling.system.batch.bs;

import com.radian.foundation.common.exception.ApplicationException;
import com.radian.foundation.common.exception.ValidationException;

/**
 * @author NKode
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ImportExportMsgException extends ValidationException
{
    /**
     * Constructor for ImportExportMsgException.
     * 
     * @param key
     */
    public ImportExportMsgException(String key)
    {
        super(key);
    }

    /**
     * Constructor for ImportExportMsgException.
     * 
     * @param key,
     *            t
     */
    public ImportExportMsgException(String key, Throwable t)
    {
        super(key, t);
    }

    /**
     * Constructor for ImportExportMsgException.
     * 
     * @param key,
     *            values[]
     */
    public ImportExportMsgException(String key, Object[] values)
    {

        super(key, values);
    }

    /**
     * Constructor for ImportExportMsgException.
     * 
     * @param e
     */
    public ImportExportMsgException(ApplicationException e)
    {
        super(e);
    }
}
