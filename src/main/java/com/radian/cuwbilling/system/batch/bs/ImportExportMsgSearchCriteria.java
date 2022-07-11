/*
 * Created on Mar 2, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.radian.cuwbilling.system.batch.bs;

import java.util.Collection;

import com.radian.foundation.bs.SearchCriteria;
import com.radian.foundation.bs.SearchCriteriaItem;
import com.radian.foundation.bs.ServiceConstants;

/**
 * @author NKode
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImportExportMsgSearchCriteria extends SearchCriteria
{
    /** Creates a new instance of ImportExportMsgSearchCriteria */
    public ImportExportMsgSearchCriteria()
    {
        super();
    }

    public void addCriteria(ImportExportMsgSearchCriteria.ImportExportMsgSearchCriteriaFields.CriteriaType field,
            ServiceConstants.CriteriaOperatorTypes.OperatorType operator, Object data)
    {
        addCriteriaItem(new SearchCriteriaItem(field.toString(), operator, data));
    }

    public void addCriteria(ImportExportMsgSearchCriteria.ImportExportMsgSearchCriteriaFields.CriteriaType field,
            ServiceConstants.CriteriaOperatorTypes.OperatorType operator, Collection data)
    {
        addCriteriaItem(new SearchCriteriaItem(field.toString(), operator, data));
    }

    public void addCriteria(ImportExportMsgSearchCriteria.ImportExportMsgSearchCriteriaFields.CriteriaType field,
            ServiceConstants.CriteriaOperatorTypes.OperatorType operator)
    {
        addCriteriaItem(new SearchCriteriaItem(field.toString(), operator));
    }

    public interface ImportExportMsgSearchCriteriaFields
    {
        public static final CriteriaType BATCH_ID = new CriteriaType("batchNumber");

        public static final CriteriaType CREATE_BEGIN_DATE = new CriteriaType("createdBeginDate");

        public static final CriteriaType CREATE_END_DATE = new CriteriaType("createdEndDate");

        public static final CriteriaType IMPORT_EXPORT_MSG_TYPE = new CriteriaType("msgTypeRaw");

        public static final CriteriaType STATUS = new CriteriaType("success");

        public final class CriteriaType
        {
            private String operatorType;

            public String getOperatorType()
            {
                return operatorType;
            }

            public CriteriaType(String operatorType)
            {
                this.operatorType = operatorType;
            }

            public boolean equals(Object o)
            {

                CriteriaType tp = (CriteriaType) o;
                if (this.operatorType != null && this.operatorType.equals(tp.operatorType))
                {
                    return true;
                }

                return false;
            }

            public String toString()
            {
                return operatorType;
            }
        }
    }
}
