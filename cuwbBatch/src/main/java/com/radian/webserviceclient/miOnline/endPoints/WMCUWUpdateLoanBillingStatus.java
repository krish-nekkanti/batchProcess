//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.16 at 02:30:41 PM GST 
//


package com.radian.webserviceclient.miOnline.endPoints;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="strRepID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="loanIdsList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "strRepID",
    "loanIdsList"
})
@XmlRootElement(name = "WMCUWUpdateLoanBillingStatus")
public class WMCUWUpdateLoanBillingStatus {

    protected String strRepID;
    protected String loanIdsList;

    /**
     * Gets the value of the strRepID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrRepID() {
        return strRepID;
    }

    /**
     * Sets the value of the strRepID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrRepID(String value) {
        this.strRepID = value;
    }

    /**
     * Gets the value of the loanIdsList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanIdsList() {
        return loanIdsList;
    }

    /**
     * Sets the value of the loanIdsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanIdsList(String value) {
        this.loanIdsList = value;
    }

}
