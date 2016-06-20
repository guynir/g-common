package gcommon.objects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provide hint for {@link ObjectPrinter#newPrinter(Object)} whether to introspect a field's value and print it or not.
 * <p></p>
 * Typically, simple field values (such as {@code int}, {@code float}, {@code java.lang.String}) are intuitive for
 * printing. However, other complex types (e.g.: user objects, collections) are sometimes not designated for printing.
 *
 * @author Guy Nir
 * @since 15/11/2011
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PrinterHint {

    /**
     * Determine if a field is to be introspected for printing or not.
     *
     * @return {@code true} if field should be introspected, {@code false} if not. A non instrospected field is simply
     *         displayed with no associated value.
     */
    boolean value() default true;
}
