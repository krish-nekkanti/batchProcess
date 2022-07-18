package com.radian.cuwbilling.system.messaging.bo.dto;

import java.util.Collection;

import com.radian.cuwbilling.common.bo.dto.AttachmentWrapperDTO;

/**
 * User: ayre Date: Oct 27, 2002 Time: 2:59:09 PM
 */
public class MessageHeader implements java.io.Serializable
{
    private TradingPartnerInfo m_TradingPartnerInfo; // could be FNMA, EMAIL,
                                                        // FAX, FreddieMac, etc

    private DeliveryMethod m_DeliveryMethod;

    private String m_MsgTimestamp;

    private String m_TrackingNumber;

    private String m_MsgVersion;

    // this could be underwriting findings, status log
    protected Collection attachments = null;

    /** Creates a new instance of MessageHeader */
    public MessageHeader()
    {
    }

    /** TradingPartner */
    public TradingPartnerInfo getTradingPartnerInfo()
    {
        return m_TradingPartnerInfo;
    }

    public void setTradingPartnerInfo(TradingPartnerInfo p_TradingPartnerInfo)
    {
        m_TradingPartnerInfo = p_TradingPartnerInfo;
    }

    /** DeliveryMethods attr */
    public DeliveryMethod getDeliveryMethod()
    {
        return m_DeliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod p_Method)
    {
        m_DeliveryMethod = p_Method;
    }

    /** msg timestamp attr */
    public String getMsgTimestamp()
    {
        return m_MsgTimestamp;
    }

    public void setMsgTimestamp(String p_Timestamp)
    {
        m_MsgTimestamp = p_Timestamp;
    }

    /** msg tracking ID */
    public String getTrackingNumber()
    {
        return m_TrackingNumber;
    }

    public void setTrackingNumber(String p_TrackingNumber)
    {
        m_TrackingNumber = p_TrackingNumber;
    }

    /** version of this message */
    public String getMsgVersion()
    {
        return m_MsgVersion;
    }

    public void setMsgVersion(String p_MsgVersion)
    {
        m_MsgVersion = p_MsgVersion;
    }

    public Collection getAttachments()
    {
        return attachments;
    }

    public void setAttachments(Collection attachments)
    {
        this.attachments = attachments;
    }

    public void setAttachment(AttachmentWrapperDTO wrapper)
    {
        attachments.add(wrapper);
    }
}
