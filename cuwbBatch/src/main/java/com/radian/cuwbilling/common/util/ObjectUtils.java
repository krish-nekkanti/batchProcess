package com.radian.cuwbilling.common.util;

/**
 * Variuos object utility methods.
 * 
 * @author Giedrius Trumpickas
 */
public final class ObjectUtils
{
    /**
     * Hashing magic constant see Knuth books for details.
     */
    private final static int HASH_MAGIC = 31;

    /**
     * No instances are allowed
     */
    private ObjectUtils()
    {
    }

    /**
     * Compares two given objects for equality.
     * 
     * @param left
     *            a left side object
     * @param right
     *            a right side object
     * @return <code>true</code> if object are equal
     */
    public static boolean equals(Object left, Object right)
    {
        if (left == right)
            return true;
        if ((left == null) || (right == null))
            return false;
        return left.equals(right);
    }

    /**
     * Computes hash code from given objects.
     * 
     * @param left
     *            a left side object
     * @param right
     *            a right side object
     * @return hash code
     */
    public static int getHashCode(Object left, Object right)
    {
        int hash = 1;
        hash = (left == null) ? 0 : (hash * HASH_MAGIC + left.hashCode());
        hash = (right == null) ? 0 : (hash * HASH_MAGIC + right.hashCode());
        return hash;
    }

    /**
     * Similar to <code>String.valueOf(Object)</code> except that it returns
     * empty string <code>""</code> instead of <code>"null"</code> when the
     * paramter <code>obj</code> is <code>null</code>.
     * 
     * @param obj
     * @return string representation of <code>obj</code>
     */
    public static String toString(Object obj)
    {
        return obj == null ? "" : obj.toString();
    }
}
