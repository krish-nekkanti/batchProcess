//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.06 at 10:46:28 PM EDT 
//


package com.radian.webserviceclient.cmd.model;

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
 *         &lt;element name="BillingProfileNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustomerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustomerNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BillingProfileStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LagDays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "billingProfileNumber",
    "customerName",
    "customerNumber",
    "billToName",
    "billingProfileStatus",
    "lagDays"
})
@XmlRootElement(name = "GetBillingProfiles")
public class GetBillingProfiles {

    @XmlElement(name = "BillingProfileNumber")
    protected String billingProfileNumber;
    @XmlElement(name = "CustomerName")
    protected String customerName;
    @XmlElement(name = "CustomerNumber")
    protected String customerNumber;
    @XmlElement(name = "BillToName")
    protected String billToName;
    @XmlElement(name = "BillingProfileStatus")
    protected String billingProfileStatus;
    @XmlElement(name = "LagDays")
    protected String lagDays;

    /**
     * Gets the value of the billingProfileNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingProfileNumber() {
        return billingProfileNumber;
    }

    /**
     * Sets the value of the billingProfileNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingProfileNumber(String value) {
        this.billingProfileNumber = value;
    }

    /**
     * Gets the value of the customerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the value of the customerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerName(String value) {
        this.customerName = value;
    }

    /**
     * Gets the value of the customerNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerNumber() {
        return customerNumber;
    }

    /**
     * Sets the value of the customerNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerNumber(String value) {
        this.customerNumber = value;
    }

    /**
     * Gets the value of the billToName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillToName() {
        return billToName;
    }

    /**
     * Sets the value of the billToName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillToName(String value) {
        this.billToName = value;
    }

    /**
     * Gets the value of the billingProfileStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingProfileStatus() {
        return billingProfileStatus;
    }

    /**
     * Sets the value of the billingProfileStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingProfileStatus(String value) {
        this.billingProfileStatus = value;
    }

    /**
     * Gets the value of the lagDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLagDays() {
        return lagDays;
    }

    /**
     * Sets the value of the lagDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLagDays(String value) {
        this.lagDays = value;
    }

}
