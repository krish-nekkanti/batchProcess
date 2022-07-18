package com.radian.cuwbilling.system.messaging.bo.dto;

/**
 * User: ayre Date: Oct 27, 2002 Time: 3:08:42 PM
 */
public class ResponseInfo extends MessageHeader
{

    private ResponseStatusInfo m_B2BResponseInfo;

    private ResponseStatusInfo m_TradingPartnerResponseInfo;

    /** Creates a new instance of MessageInfo */
    public ResponseInfo()
    {
    }

    /** TradingPartnerRespInfo attr */
    public ResponseStatusInfo getTradingPartnerResponseInfo()
    {
        return m_TradingPartnerResponseInfo;
    }

    public void setTradingPartnerResponseInfo(ResponseStatusInfo p_Info)
    {
        m_TradingPartnerResponseInfo = p_Info;
    }

    /** B2BResponseInfo attr */
    public ResponseStatusInfo getB2BResponseInfo()
    {
        return m_B2BResponseInfo;
    }

    public void setB2BResponseInfo(ResponseStatusInfo p_Info)
    {
        m_B2BResponseInfo = p_Info;
    }

}
