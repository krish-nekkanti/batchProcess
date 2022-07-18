/**
 * @(#) BillingExportPendingDestSummaryDTO.java
 */

package com.radian.cuwbilling.billing.cuw.bo.dto;

import com.radian.foundation.bo.dto.BaseDTO;

public class PendingDistSummaryDTO extends BaseDTO
{

    private String glAccountNo;/* gl account number */

    private String costCenterNo;
    
    private String costCenterState;

    private String operatingUnit;

    private Double monetaryAmt;

    public String getGlAccountNo()
    {
        return this.glAccountNo;
    }

    public void setGlAccountNo(String glAccountNo)
    {
        this.glAccountNo = glAccountNo;
    }

    public String getCostCenterNo()
    {
        return this.costCenterNo;
    }
    
    public String getCostCenterState()
    {
        return this.costCenterState;
    }

    public void setCostCenterNo(String costCenterNo)
    {
        this.costCenterNo = costCenterNo;
    }
    
    public void setCostCenterState(String costCenterState)
    {
        this.costCenterState = costCenterState;
    }

    public String getOperatingUnit()
    {
        return this.operatingUnit;
    }

    public void setOperatingUnit(String operatingUnit)
    {
        this.operatingUnit = operatingUnit;
    }

    public Double getMonetaryAmt()
    {
        return this.monetaryAmt;
    }

    public void setMonetaryAmt(Double monetaryAmt)
    {
        this.monetaryAmt = monetaryAmt;
    }

}
