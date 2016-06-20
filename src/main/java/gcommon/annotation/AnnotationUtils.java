package gcommon.annotation;


import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Helper class that extends the annotation facilities provided by
 * {@link org.springframework.core.annotation.AnnotationUtils}.
 *
 * @author Guy Nir
 * @since 19/11/2011
 */
public class AnnotationUtils {

    /**
     * Locate all methods that are immediately annotated with a given <i>annotationType</i>. The word 'immediate'
     * suggests that only method that are annotated are called. Methods that are annotated by a parent implementation
     * (or interface) but are not annotated by child method are no reported.<p>
     * This method searches for all methods in the class heirarchy.
     *
     * @param object         Object to inspect methods.
     * @param annotationType Type of annotation to search on methods.
     * @return List of method immediately annotated with <i>annotationType</i>. If no such method found, return an empty
     *         array.
     * @throws IllegalArgumentException If either <i>object</i> or <i>annotationType</i> are {@code null}.
     */
    public static Method[] findAnnotatedMethod(Object object, Class<? extends Annotation> annotationType)
            throws IllegalArgumentException {
        Assert.notNull(object, "Object cannot be null.");
        Assert.notNull(annotationType, "Annotation type cannot be null.");

        // Uncover and discover the actual bean (without proxies).
            Class<?> clazz = AopProxyUtils.ultimateTargetClass(object);

        List<Method> annotatedMethods = new LinkedList<>();
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(annotationType)) {
                annotatedMethods.add(method);
            }
        }

        return annotatedMethods.toArray(new Method[annotatedMethods.size()]);
    }
}
