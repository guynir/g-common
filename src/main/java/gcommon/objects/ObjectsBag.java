package gcommon.objects;

import java.util.Arrays;
import java.util.Objects;

/**
 * A simple bag that holds a collection of objects. It's {@link #equals(Object)
 * equal} method perform a deep comparison between the contents (objects) held
 * within this instance and the other instance given to the method.
 *
 * @author Guy Nir
 * @since 15/11/2011
 */
public final class ObjectsBag {

    /**
     * List of objects in this bag.
     */
    private final transient Object[] objects;

    /**
     * Class constructor.
     *
     * @param objects Objects to hold in this bag.
     */
    public ObjectsBag(Object... objects) {
        this.objects = objects;
    }

    /**
     * Check equality between this instance and another <code>ObjectBag</code>
     * instance.
     *
     * @param obj Another object to compare to.
     * @return <code>true</code> if both instances are the same or deeply equal,
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        // If we're the same instance, then we're equal !
        if (this == obj) {
            return true;
        }

        // If the other instance is not an ObjectBag, we're not equal.
        if (obj == null || !obj.getClass().equals(ObjectsBag.class)) {
            return false;
        }

        ObjectsBag other = (ObjectsBag) obj;

        // Java 7+ objects equality.
        return Arrays.equals(objects, other.objects);
    }

    /**
     * @return A calculated hash-code for the set of objects this bag holds.
     */
    @Override
    public int hashCode() {
        // Java 7+ auto-hash function.
        return Objects.hash(objects);
    }
}
