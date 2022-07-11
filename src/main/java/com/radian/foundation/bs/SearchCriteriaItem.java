package com.radian.foundation.bs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a search criteria item that is used when (dynamically)
 * building a search clause from the business service client
 * 
 * @author nines
 *
 */
public class SearchCriteriaItem implements Serializable
{

    /**The name of the field that is being used as a search parameter.  Usually a constant in the business service domain*/
    private String fieldName;

    /**The operator type(equals, less than, not equal, etc) that is being used for this criteria item*/
    private ServiceConstants.CriteriaOperatorTypes.OperatorType operatorType;

    /**The data that is being used to filter the search that is being executed*/
    private Collection criteriaData;


    /**
     * Constructor for SearchCriteriaItem.
     */
    public SearchCriteriaItem() 
    {
        super();
    }

    /**
     * Construct a SearchCriteriaItem using all possible criteria items
     * 
     * @param fieldName of the field being used in the search
     * @param operatorType that is being used for the search(equals, less than, not equal, etc)
     * @param item data that is being used the filter the search
     */
    public SearchCriteriaItem(String fieldName, 
                              ServiceConstants.CriteriaOperatorTypes.OperatorType operatorType,
                              Object item) 
    {
        super();
        this.fieldName = fieldName;
        this.operatorType = operatorType;
        this.criteriaData = new ArrayList(1);
        this.criteriaData.add(item);
    }

    /**
     * Construct a SearchCriteriaItem that does not need to pass a data item
     * to filter the search.  Usually, this constructor is used to set a
     * field and a filter option along the lines of ASCENDING or DESCENDING 
     * 
     * @param fieldName of the field being used in the search
     * @param operatorType that is being used for the search(equals, less than, not equal, etc)
     */
    public SearchCriteriaItem(String fieldName, 
                              ServiceConstants.CriteriaOperatorTypes.OperatorType operatorType
                             ) 
    {
        super();
        this.fieldName = fieldName;
        this.operatorType = operatorType;       
    }

    /**
     * Sets a search criteria item allowing the criteria item to use a 
     * collection of items to limit the search
     * 
     * @param fieldName of the field being used in the search
     * @param operatorType that is being used for the search(equals, less than, not equal, etc)
     * @param items collection of items that are used to filter the search
     */
    public SearchCriteriaItem(String fieldName, 
                              ServiceConstants.CriteriaOperatorTypes.OperatorType operatorType,
                              Collection items) 
    {
        super();
        this.fieldName = fieldName;
        this.operatorType = operatorType;
        this.criteriaData = items;
    }   

    /**
     * Returns the criteriaData.
     * @return Collection
     */
    public Collection getCriteriaData() 
    {
        return criteriaData;
    }

    /**
     * Returns the fieldName.
     * @return String
     */
    public String getFieldName() 
    {
        return fieldName;
    }

    /**
     * Returns the operatorType.
     * @return ServiceConstants.CriteriaOperatorTypes.OperatorType
     */
    public ServiceConstants
    .CriteriaOperatorTypes
    .OperatorType getOperatorType() 
    {
        return operatorType;
    }





}
