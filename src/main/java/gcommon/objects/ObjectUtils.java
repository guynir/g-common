package gcommon.objects;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
     * <p>
     *
     * @param type Type to verify. Must not be <code>null</code>!
     * @param o    Object to evaluate. If this value is <code>null</code>, the returned value is always
     *             <code>false</code>.
     * @return If <i>o</i> is non-<code>null</code> and is of type <i>type</i>.
     */
    public static boolean isInstanceOf(Class<?> type, Object o) {
        return o != null && type.equals(o.getClass());
    }

    private final static int SIZE = 10 * 1024;

    /**
     * Clone an object, deep copying its state (using Java serialization/deserialization).
     *
     * @param source Object to clone.
     * @param <T>    Generic type of object.
     * @return Cloned object.
     * @throws IllegalStateException If an unexpected error occurred during cloning.
     */
    @SuppressWarnings("unchecked")
    public static <T> T cloneObject(T source) throws IllegalStateException {
        if (source == null) {
            return null;
        }

        try {
            //
            // Serialize object to memory.
            //
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(SIZE);
            ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
            out.writeObject(source);

            //
            // Deserialize object from memory.
            //
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            return (T) ois.readObject();
        } catch (Exception ex) {
            throw new IllegalStateException("Clone failed.", ex);
        }
    }

}
