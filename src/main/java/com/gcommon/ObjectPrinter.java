package com.gcommon;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * A build for generating a {@link Object#toString()}. A caller can either build a string manually (using various
 * {@link #field(String, Object)} overloading) or use the general {@link #newPrinter(Object)} that introspect the entire
 * instance with reflection.
 *
 * @author Guy Nir
 * @since 15/11/2011
 */
public class ObjectPrinter {

    /**
     * Flag indicating whether any field was set.
     */
    private boolean firstField = true;

    /**
     * Internal buffer that holds the string.
     */
    private final StringBuilder buf = new StringBuilder(256);

    /**
     * Class constructor.
     */
    public ObjectPrinter() {
        this("");
    }

    /**
     * Class constructor.
     *
     * @param cls Class to use as a prefix (class's simple name).
     */
    public ObjectPrinter(Class<?> cls) {
        this(cls != null ? cls.getSimpleName() : "");
    }

    /**
     * Class constructor.
     *
     * @param prefix Any prefix to begin string with. This value can be
     *               {@code null} (which is treated as an empty string).
     */
    public ObjectPrinter(String prefix) {
        if (prefix != null) {
            buf.append(prefix).append(' ');
        }
        buf.append("[");
    }

    /**
     * Introspect a given <i>object</i> and create a <i>toString</i> builder for it. By default, all fields are
     * introspected and added. To control a field introspection explicitly, use {@link PrinterHint}.
     *
     * @param object Object to introspect.
     * @return A new and populated printer.
     * @throws IllegalArgumentException If <i>object</i> is {@code null}.
     * @throws IllegalStateException    If one of the fields could not be introspected due to JVM security restriction.
     */
    public static ObjectPrinter newPrinter(Object object) throws IllegalArgumentException, IllegalStateException {
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null.");
        }
        Class<?> cls = object.getClass();
        ObjectPrinter printer = new ObjectPrinter(cls);

        // Generate list of all object's fields and traverse the list.
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                // Allow access to a private field.
                field.setAccessible(true);

                PrinterHint printerHint = field.getAnnotation(PrinterHint.class);
                if (printerHint == null || printerHint.value()) {
                    // For each field, append it to the printer.
                    if (field.getType().isArray()) {
                        printer.field(field.getName(), (Object[]) field.get(object));
                        // } else if (field.getType().isAssignableFrom(Collection.class)) {
                    } else if (Collection.class.isAssignableFrom(field.getType())) {
                        printer.field(field.getName(), (Collection<?>) field.get(object));
                    } else {
                        Object value = field.get(object);
                        printer.field(field.getName(), value);
                    }
                } else {
                    printer.field(field.getName(), "-");
                }
            } catch (IllegalAccessException ex) {
                throw new IllegalStateException("Unexpected: could not introspect field " + field.getName()
                        + " of object "
                        + cls.getName());
            }
        }

        return printer;
    }

    /**
     * @return String representing all fields added so far.
     */
    public String getMessage() {
        return buf.toString() + "]";
    }


    /**
     * Add a field name/value to the string.
     *
     * @param fieldName  Name of field.
     * @param fieldValue Field's value (the {@link Object#toString()} is used to get
     *                   object's representation).
     * @return This instance.
     */
    public ObjectPrinter field(String fieldName, Object fieldValue) {
        prepend(fieldName);
        buf.append(fieldValue != null ? fieldValue.toString() : "null");
        return this;
    }

    /**
     * Add a field (of an array type) to the string. The added pattern is
     * <i>field_name: length</i>.
     *
     * @param fieldName Name of field.
     * @param value     An array of values.
     * @return This instance.
     */
    public ObjectPrinter field(String fieldName, Object[] value) {
        prepend(fieldName);
        if (value != null) {
            buf.append("... (size=").append(value.length).append(")");
        } else {
            buf.append("null");
        }
        return this;
    }

    /**
     * Add a field (of {@code java.util.Collection} type) to the string.
     * The added pattern is <i>field_name: size</i>.
     *
     * @param fieldName Name of field.
     * @param value     Field's value as a collection.
     * @return This instance.
     */
    public ObjectPrinter field(String fieldName, Collection<?> value) {
        prepend(fieldName);
        if (value != null) {
            buf.append("... (size = ").append(value.size()).append(")");
        } else {
            buf.append("null");
        }
        return this;
    }

    /**
     * @return A string representation of all values specified so far. Same as {@link #getMessage()}.
     */
    @Override
    public String toString() {
        return getMessage();
    }

    /**
     * Add a field's name with possible a comma-delimiter, where needed.
     *
     * @param fieldName Name of field to add.
     */
    private void prepend(String fieldName) {
        if (!firstField) {
            buf.append("; ");
        } else {
            firstField = false;
        }
        buf.append(fieldName).append(": ");
    }
}
