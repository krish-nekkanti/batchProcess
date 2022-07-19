/**
 * @(#) TimeSheetSearchCriteria.java
 */

package com.radian.cuwbilling.billing.cuw.bs;

import java.util.Collection;

import com.radian.foundation.bs.SearchCriteria;
import com.radian.foundation.bs.SearchCriteriaItem;
import com.radian.foundation.bs.ServiceConstants;

/**
 * 
 * @author KMadireddy
 */
public class TimeSheetSearchCriteria extends SearchCriteria
{

    /** Creates a new instance of TimeSheetSearchCriteria */
    public TimeSheetSearchCriteria()
    {
        super();
    }

    public void addCriteria(TimeSheetSearchCriteria.TimeSheetCriteriaFields.CriteriaType field, ServiceConstants.CriteriaOperatorTypes.OperatorType operator,
            Object data)
    {
        addCriteriaItem(new SearchCriteriaItem(field.toString(), operator, data));
    }

    public void addCriteria(TimeSheetSearchCriteria.TimeSheetCriteriaFields.CriteriaType field, ServiceConstants.CriteriaOperatorTypes.OperatorType operator,
            Collection data)
    {
        addCriteriaItem(new SearchCriteriaItem(field.toString(), operator, data));
    }

    public void addCriteria(TimeSheetSearchCriteria.TimeSheetCriteriaFields.CriteriaType field, ServiceConstants.CriteriaOperatorTypes.OperatorType operator)
    {
        addCriteriaItem(new SearchCriteriaItem(field.toString(), operator));
    }

    public interface TimeSheetCriteriaFields
    {
        public static final CriteriaType DATE = new CriteriaType("dateWorkedAsDate");
        
        public static final CriteriaType UNDERWRITER_CODE = new CriteriaType("underwriterCode");
        
        public static final CriteriaType UNDERWRITER_NAME = new CriteriaType("empolyeeName");
        
        public static final CriteriaType HOURS = new CriteriaType("timeSheetHours");

        public static final CriteriaType IS_BILLED = new CriteriaType("isBilled");
        
        public static final CriteriaType BILLING_STATUS = new CriteriaType("billingStatusRaw");

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
