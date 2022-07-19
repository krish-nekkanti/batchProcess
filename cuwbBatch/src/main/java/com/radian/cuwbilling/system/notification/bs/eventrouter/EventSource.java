/**
 * @(#) EventSource.java
 */

package com.radian.cuwbilling.system.notification.bs.eventrouter;

import com.radian.cuwbilling.common.bo.domain.AxiomEntity;

public class EventSource implements java.io.Serializable
{
    private Long ID;

    public EventSource(AxiomEntity entity)
    {
        ID = entity.getID();
    }

    /**
     * @return
     */
    public Long getID()
    {
        return ID;
    }

    /**
     * @param long1
     */
    public void setID(Long long1)
    {
        ID = long1;
    }

}
