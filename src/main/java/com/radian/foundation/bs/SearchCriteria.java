package com.radian.foundation.bs;

import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Base SearchCriteria functionality required by all business services
 * 
 * Generally, the business service criteria sub-class will contain the
 * domain-specific fields that are used as search parameters 
 * 
 * @author nines
 *
 */
public class SearchCriteria implements Serializable
{

    /**The requested position in the result set(defaults to 0)
     * Note - attemps to set the absolute position to anything other then
     * a value greater then or equal to zero will be ignored*/
    private long absolutePosition = 0;

    /**Flag to signify that the caller of this query wishes the query to be asynchronous*/
    private boolean asyncQuery = false;

    /**The maximum number of rows to display*/
    private long maxDisplayRows = 20;

    /**Initializing the array to one...anticipating that there is, mostly, only one criteria item that one is searching on...of course the array will increase automatically as values are added*/
    private Collection criteriaItems = new ArrayList();


    /**
     * Constructor for SearchCriteria.
     */
    public SearchCriteria() 
    {
        super();
    }

    public SearchCriteria(long absolutePosition, 
                          long maxDisplayRows) 
    {
        super();
        
        //absolute pos is 0 index based...if it's less then 0 just leave it at 0(the default)
        if(absolutePosition >=0)
        	this.absolutePosition = absolutePosition;
        	
        this.maxDisplayRows = maxDisplayRows;       

    }   

    public void addCriteriaItem(SearchCriteriaItem item)
    {
        this.criteriaItems.add(item);
    }

    /**
     * Returns the absolutePosition.
     * @return int
     */
    public long getAbsolutePosition() 
    {
        return absolutePosition;
    }

    /**
     * Returns the asyncQuery.
     * @return boolean
     */
    public boolean isAsyncQuery() 
    {
        return asyncQuery;
    }

    /**
     * Returns the maxDisplayRows.
     * @return int
     */
    public long getMaxDisplayRows() 
    {
        return maxDisplayRows;
    }

    /**
     * Sets the absolutePosition.
     * @param absolutePosition The absolutePosition to set
     */
    public void setAbsolutePosition(long absolutePosition) 
    {
		//absolute pos is 0 index based...if it's less then 0 just leave it at 0(the default)
		if(absolutePosition >=0)
        	this.absolutePosition = absolutePosition;
    }

    /**
     * Sets the asyncQuery.
     * @param asyncQuery The asyncQuery to set
     */
    public void setAsyncQuery(boolean asyncQuery) 
    {
        this.asyncQuery = asyncQuery;
    }

    /**
     * Sets the maxDisplayRows.
     * @param maxDisplayRows The maxDisplayRows to set
     */
    public void setMaxDisplayRows(long maxDisplayRows) 
    {
        this.maxDisplayRows = maxDisplayRows;
    }

    /**
     * Criteria items required when executing this search
     * 
     * @return a collection of SearchCriteriaItem objects 
     */
    public Collection getCriteriaItems() 
    {
        return criteriaItems;
    }

}
