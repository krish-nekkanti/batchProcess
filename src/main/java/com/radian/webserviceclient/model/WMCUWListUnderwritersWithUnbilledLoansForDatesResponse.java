//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.18 at 02:08:17 PM EDT 
//


package com.radian.webserviceclient.model;

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
 *         &lt;element name="WMCUWListUnderwritersWithUnbilledLoansForDatesResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "wmcuwListUnderwritersWithUnbilledLoansForDatesResult"
})
@XmlRootElement(name = "WMCUWListUnderwritersWithUnbilledLoansForDatesResponse")
public class WMCUWListUnderwritersWithUnbilledLoansForDatesResponse {

    @XmlElement(name = "WMCUWListUnderwritersWithUnbilledLoansForDatesResult")
    protected String wmcuwListUnderwritersWithUnbilledLoansForDatesResult;

    /**
     * Gets the value of the wmcuwListUnderwritersWithUnbilledLoansForDatesResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWMCUWListUnderwritersWithUnbilledLoansForDatesResult() {
        return wmcuwListUnderwritersWithUnbilledLoansForDatesResult;
    }

    /**
     * Sets the value of the wmcuwListUnderwritersWithUnbilledLoansForDatesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWMCUWListUnderwritersWithUnbilledLoansForDatesResult(String value) {
        this.wmcuwListUnderwritersWithUnbilledLoansForDatesResult = value;
    }

}
