/*
 * TradingPartnerSearchCriteria.java
 *
 * Created on November 13, 2003
 */

package com.radian.cuwbilling.system.document.bs;

import java.util.Collection;

import com.radian.foundation.bs.SearchCriteria;
import com.radian.foundation.bs.SearchCriteriaItem;
import com.radian.foundation.bs.ServiceConstants;

/**
 * This class serves as the search criteria to support searching functionality
 * for trading partners.
 * 
 * @author jsumner
 */
public class TradingPartnerSearchCriteria extends SearchCriteria
{
    //
    // TODO: These need to be verified against changes in the domain model.
    //
    public static final String CUSTOMER = "customer.customerName";

    public static final String DELIVERY_PACKAGE = "deliveryPackageTypes";

    public static final String ACTIVATION_STATUS = "status";

    /** Creates a new instance of StaffMemberSearchCriteria */
    public TradingPartnerSearchCriteria()
    {
        super();
    }

    public void addCriteria(String field, ServiceConstants.CriteriaOperatorTypes.OperatorType operator, Object data)
    {
        addCriteriaItem(new SearchCriteriaItem(field, operator, data));
    }

    public void addCriteria(String field, ServiceConstants.CriteriaOperatorTypes.OperatorType operator, Collection data)
    {

        addCriteriaItem(new SearchCriteriaItem(field, operator, data));
    }

    public void addCriteria(String field, ServiceConstants.CriteriaOperatorTypes.OperatorType operator)
    {
        addCriteriaItem(new SearchCriteriaItem(field, operator));
    }

}
