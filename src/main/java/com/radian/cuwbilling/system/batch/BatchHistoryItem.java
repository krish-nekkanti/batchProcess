package com.radian.cuwbilling.system.batch;

import java.io.Serializable;
import java.util.Date;

public class BatchHistoryItem implements Serializable
{
	protected Long batchRunID = null;
    protected Long batchStep = null;
    protected String exceptionInfo = null;
    protected Date executionDate = null;
    protected String flowName = null;
    protected int returnState;
    protected String taskName = null;

	/**
	 * A unique key identifying a business entity.
	 */
	private Long ID;

	/**
	 * @Returns the persistence <code>ID</code> uniquely identifying this business Entity.
	 */
	public Long getID( )
	{
		return this.ID;
	}

	/**
	 * Assigns a unique persistence <code>ID</code> to this business Entity.
	 *
	 * @param   id  A unique identifier used for the persistence key.
	 */
	public void setID( Long id )
	{
		this.ID = id;
	}

	public Long getBatchStep()
	{
		return batchStep;
	}

	public void setBatchStep(Long batchStep)
	{
		this.batchStep = batchStep;
	}

	public String getExceptionInfo()
	{
		return exceptionInfo;
	}

	public void setExceptionInfo(String exceptionInfo)
	{
		this.exceptionInfo = exceptionInfo;
	}

	public Date getExecutionDate()
	{
		return executionDate;
	}

	public void setExecutionDate(Date executionDate)
	{
		this.executionDate = executionDate;
	}

	public String getFlowName()
	{
		return flowName;
	}

	public void setFlowName(String flowName)
	{
		this.flowName = flowName;
	}

	public int getReturnState()
	{
		return returnState;
	}

	public void setReturnState(int returnState)
	{
		this.returnState = returnState;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	public Long getBatchRunID() {
		return batchRunID;
	}

	public void setBatchRunID(Long batchRunID) {
		this.batchRunID = batchRunID;
	}


}
