package com.radian.cuwbilling.common.bo.dto;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.radian.cuwbilling.common.bo.domain.Money;
import com.radian.cuwbilling.common.ui.web.actions.FormattingUtil;
import com.radian.foundation.bo.dto.BaseDTO;

/**
 * 
 * 
 * @author nines, stritzinger
 */
public class MoneyDTO extends BaseDTO implements Comparable
{
    // TODO: add values for localized default type of currency,
    // get/set currency code,
    // get amount as string with currency code,
    // related: convert to amount in other currency (code elsewhere)

    private double amount = 0.0;

    private NumberFormat format = new DecimalFormat("#####0.00");

    private NumberFormat formatWithCommas = new DecimalFormat("#,###,###,##0.00");

    /**
     * Converts <code>Double</code> to <code>MoneyDTO</code>.
     * 
     * @return double as axiom Money. <code>null</code> if parameter d is
     *         <code>null</code>.
     */
    public static MoneyDTO doubleToMoneyDTO(Double d)
    {
        if (d == null)
            return null;

        return new MoneyDTO(d.doubleValue());
    }

    /**
     * Converts <code>MoneyDTO</code> to <code>double</code>.
     * 
     * @return axiom money as double. <code>null</code> if parameter ad is
     *         <code>null</code>.
     */
    public static Double toDouble(MoneyDTO ad)
    {
        if (ad == null)
            return null;

        return new Double(ad.getAmount());
    }

    /**
     * default constructor. required to make ths class a bean
     */
    public MoneyDTO()
    {
        super();
    }

    public MoneyDTO(Double amt)
    {
        super();
        if (amt != null)
        {
            this.amount = amt.doubleValue();
        }

    }

    /**
     * Money constructor sets amount from string
     * 
     * @param value
     */
    public MoneyDTO(String money)
    {

        if (money != null && !money.trim().equals(""))
        {
            this.amount = FormattingUtil.parseFormattedNumber(money);
        }
    }

    /**
     * Money constructor sets amount
     * 
     * @param value
     */
    public MoneyDTO(double value)
    {
        this.setAmount(value);

    }

    /**
     * Method getAmount.
     * 
     * @return double
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * Method getAmountAsString.
     * 
     * @return String
     */
    public String getAmountAsString()
    {
        String strAmount = format.format(amount);
        return strAmount;
    }

    /**
     * Method getAmountAsStringWithCommas.
     * 
     * @return String
     */
    public String getAmountAsStringWithCommas()
    {
        String strAmount = formatWithCommas.format(amount);
        return strAmount;
    }

    /**
     * @see java.lang.Object#toString() keeps compatibility to initial API
     */
    public String toString()
    {
        return Double.toString(this.amount);
    }

    /**
     * Method setAmount.
     * 
     * @param value
     */
    public void setAmount(double value)
    {
        this.amount = value;
    }

    /**
     * Method toMoney.
     * 
     * @param value
     */
    public static Money toMoney(MoneyDTO m)
    {
        if (m == null)
            return null;

        return new Money(new Double(m.getAmount()));
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * 
     * The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt>
     * for all <tt>x</tt> and <tt>y</tt>. (This implies that
     * <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * 
     * The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * 
     * Finally, the implementer must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * 
     * It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>. Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact. The recommended
     * language is "Note: this class has a natural ordering that is inconsistent
     * with equals."
     * 
     * @param o
     *            the Object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     * 
     * @throws ClassCastException
     *             if the specified object's type prevents it from being
     *             compared to this Object.
     */
    public int compareTo(Object o)
    {
        return Double.compare(amount, ((MoneyDTO) o).amount);
    }

    public boolean equals(Object o)
    {
        if (!(o instanceof MoneyDTO))
            return false;
        return amount == ((MoneyDTO) o).amount;
    }

}
