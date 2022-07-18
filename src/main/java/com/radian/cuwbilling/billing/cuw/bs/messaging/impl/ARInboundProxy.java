/*
 * @(#)ARInboundProxy.java
 */
package com.radian.cuwbilling.billing.cuw.bs.messaging.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.radian.cuwbilling.billing.cuw.bo.dto.ARPaymentImportDTO;
import com.radian.cuwbilling.billing.cuw.bs.BillingImportAdministration;
import com.radian.cuwbilling.billing.cuw.bs.CUWBillingException;
import com.radian.cuwbilling.billing.cuw.bs.messaging.ARInboundWebService;
import com.radian.cuwbilling.system.messaging.bo.dto.RequestInfo;
import com.radian.cuwbilling.system.messaging.bo.dto.ResponseInfo;

/**
 * Implementation class for import transactions
 * 
 * @author James Carpenter
 */
public class ARInboundProxy implements ARInboundWebService
{

    private static final String MESSAGING_RESPONSE_SUCCESS = "0";

    /*
     * (non-Javadoc)
     * 
     * @see com.radian.cuwbilling.billing.cuw.bs.messaging.ARInboundWebService#receivePayments(com.radian.cuwbilling.system.messaging.bo.dto.RequestInfo,
     *      com.radian.cuwbilling.billing.cuw.bo.dto.ARPaymentImportDTO[])
     */
    public void receivePayments(RequestInfo ri, ARPaymentImportDTO[] dto)
    {
        Collection dtos = new ArrayList();
        for (int i = 0; i < dto.length; i++)
        {
            dtos.add(dto[i]);
        }

        try
        {
            BillingImportAdministration service =null;// CUWBillingServiceFactory.instance().getBillingImportAdminService();
            service.importPayments(ri.getTrackingNumber(), dtos);
        } catch (CUWBillingException be)
        {
            // throwing a run time exception back to the framework will cause
            // the original
            // message to be put on the error queue
            throw new RuntimeException("CUWBillingException in ARInboundProxy.receivePayments(): Could not import payment records. " + be.toString());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.radian.cuwbilling.billing.cuw.bs.messaging.ARInboundWebService#receivePayments(com.radian.cuwbilling.system.messaging.bo.dto.ResponseInfo)
     */
    public void receivePayments(ResponseInfo ri)
    {
        try
        {
            // Pull the information out of the response
            String trackingNumber = null;
            Boolean success = null;
            String desc = null;

            if (ri != null)
            {
                trackingNumber = ri.getTrackingNumber();

                if (ri.getB2BResponseInfo() != null)
                {
                    success = new Boolean(ri.getB2BResponseInfo().getCode().equals(MESSAGING_RESPONSE_SUCCESS));
                    desc = ri.getB2BResponseInfo().getDesc();
                } else if (ri.getTradingPartnerResponseInfo() != null)
                {
                    success = new Boolean(ri.getTradingPartnerResponseInfo().getCode().equals(MESSAGING_RESPONSE_SUCCESS));
                    desc = ri.getTradingPartnerResponseInfo().getDesc();
                }
            }

            // Save the info to the log
            BillingImportAdministration service = null;//CUWBillingServiceFactory.instance().getBillingImportAdminService();
            service.importPayments(trackingNumber, success, desc);
        } catch (CUWBillingException be)
        {
            // throwing a run time exception back to the framework will cause
            // the original
            // message to be put on the error queue
            throw new RuntimeException("CUWBillingException in ARInboundProxy.receivePayments(): Could not import payment records. " + be.toString());
        }

    }

}
