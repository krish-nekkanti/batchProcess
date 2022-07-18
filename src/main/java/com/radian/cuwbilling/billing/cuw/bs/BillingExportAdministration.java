/*
 * Created on Feb 3, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.radian.cuwbilling.billing.cuw.bs;

import java.util.Date;

import com.radian.cuwbilling.billing.common.bs.BillingException;
import com.radian.cuwbilling.system.messaging.bo.dto.ResponseInfo;

/**
 * @author SGuddimath
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public interface BillingExportAdministration
{
    public void exportBillingProfiles(Date createDate) throws CUWBillingException, BillingException;

    public void exportBillingInvoices() throws CUWBillingException, BillingException;

    public void exportBillingAdjustments() throws CUWBillingException, BillingException;    
}
