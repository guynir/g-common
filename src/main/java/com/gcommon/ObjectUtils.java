package com.gcommon;

/**
 * Collection of general-object utilities.
 *
 * @author Guy Nir
 * @since 15/11/2011
 */
public class ObjectUtils {

    /**
     * Check if a given object <i>o</i> is of a given <i>type</i>. More specifically, <i>o</i> can be casted to object
     * of type <i>type</i>.
     * <p/>
     *
     * @param type Type to verify. Must not be <code>null</code>!
     * @param o    Object to evaluate. If this value is <code>null</code>, the returned value is always
     *             <code>false</code>.
     * @return If <i>o</i> is non-<code>null</code> and is of type <i>type</i>.
     */
    public static boolean isInstanceOf(Class<?> type, Object o) {
        return o != null && type.equals(o.getClass());
    }
}
