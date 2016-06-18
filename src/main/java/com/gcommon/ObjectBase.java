package com.gcommon;

/**
 * Provide a common mechanisms for POJOs, such as {@link Object#equals(Object)} and {@link Object#toString()}.
 *
 * @author Guy Nir
 * @since 10/10/2012
 */
public abstract class ObjectBase implements ObjectBagAware {

    /**
     * Check equality between two instances. The equality is based on both type of the two objects
     * (e.g.: this.getClass() == other.getClass()) and all the instances fields are equal.
     *
     * @param other Another instance to compare to.
     * @return {@code true} if both instances are equal (or are the same), {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !getClass().equals(other.getClass())) {
            return false;
        }

        // Compare the two instances based on the <i>object-aware</i> mechanism.
        ObjectBagAware o = (ObjectBagAware) other;
        return objectsBag().equals(o.objectsBag());
    }

    @Override
    public int hashCode() {
        return objectsBag().hashCode();
    }

    /**
     * @return A string representing the object's state. Based on {@link ObjectPrinter}.
     */
    @Override
    public String toString() {
        return ObjectPrinter.newPrinter(this).toString();
    }
}
