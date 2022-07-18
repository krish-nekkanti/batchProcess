/*
 * @(#)ARInboundWebService.java
 */
package com.radian.cuwbilling.billing.cuw.bs.messaging;

import com.radian.cuwbilling.billing.cuw.bo.dto.ARPaymentImportDTO;
import com.radian.cuwbilling.system.messaging.bo.dto.RequestInfo;
import com.radian.cuwbilling.system.messaging.bo.dto.ResponseInfo;

/**
 * Provides business methods for receiving AR Payment data
 * 
 * @author James Carpenter
 */
public interface ARInboundWebService
{
    /**
     * Invoked with message from EAI with payment information
     * 
     * @param ri
     *            RequestInfo containing general details about invocation
     * @param dto
     */
    public void receivePayments(RequestInfo ri, ARPaymentImportDTO[] dto);

    /**
     * Invoked when an error occurs during payment import
     * 
     * @param ri
     *            ResponseInfo containing failure status and exception
     *            information
     */
    public void receivePayments(ResponseInfo ri);
}
