/**
 * @(#) Money.java
 */

package com.radian.cuwbilling.common.bo.domain;

import java.io.Serializable;

/**
 * Money class.
 */
public class Money implements Serializable
{
    private Double value;

    private String currency = "US Dollars";

    /**
     * Default ctor.
     */
    public Money()
    {
    }

    /**
     * Constructs money with given value.
     * 
     * @param value
     */
    public Money(Double value)
    {
        this.value = value;
    }

    /**
     * Returns the currency.
     * 
     * @return String
     */
    public String getCurrency()
    {
        return currency;
    }

    /**
     * Returns the value.
     * 
     * @return Double
     */
    public Double getValue()
    {
        return value;
    }

    /**
     * Sets the currency.
     * 
     * @param currency
     *            The currency to set
     */
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    /**
     * Sets the value.
     * 
     * @param value
     *            The value to set
     */
    public void setValue(Double value)
    {
        this.value = value;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other)
    {
        return (other instanceof Money) && (getValue() == ((Money) other).getValue() || (getValue() != null && getValue().equals(((Money) other).getValue())))
                && (getCurrency() == ((Money) other).getCurrency() || (getCurrency() != null && getCurrency().equals(((Money) other).getCurrency())));
    }

}
