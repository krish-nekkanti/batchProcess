//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.16 at 02:30:41 PM GST 
//


package com.radian.webserviceclient.miOnline.endPoints;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WMCUWGetAllUnbilledLoansForDatesResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "wmcuwGetAllUnbilledLoansForDatesResult"
})
@XmlRootElement(name = "WMCUWGetAllUnbilledLoansForDatesResponse")
public class WMCUWGetAllUnbilledLoansForDatesResponse {

    @XmlElement(name = "WMCUWGetAllUnbilledLoansForDatesResult")
    protected String wmcuwGetAllUnbilledLoansForDatesResult;

    /**
     * Gets the value of the wmcuwGetAllUnbilledLoansForDatesResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWMCUWGetAllUnbilledLoansForDatesResult() {
        return wmcuwGetAllUnbilledLoansForDatesResult;
    }

    /**
     * Sets the value of the wmcuwGetAllUnbilledLoansForDatesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWMCUWGetAllUnbilledLoansForDatesResult(String value) {
        this.wmcuwGetAllUnbilledLoansForDatesResult = value;
    }

}
