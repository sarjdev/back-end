package dev.sarj.sarjdev.core.aspect.performance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to mark methods for performance measurement.
 * <p>
 * This annotation is used to annotate methods that need to be
 * monitored for performance metrics by the {@link PerformanceAspect} aspect.
 * The annotated methods will have their execution time measured and logged,
 * and the results can be stored in a database for further analysis.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * @Performance(value = ElapsedType.API_ELAPSED)
 * public void someMethod() {
 *     // method implementation
 * }
 * }
 * </pre>
 * </p>
 *
 * @see PerformanceAspect
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Performance {

    /**
     * Specifies the type of elapsed time measurement.
     * <p>
     * This value is used to classify the type of method being measured,
     * such as API methods or service methods. The default value is {@link ElapsedType#SERVICE_ELAPSED}.
     * </p>
     *
     * @return The type of elapsed time measurement.
     */
    ElapsedType value() default ElapsedType.SERVICE_ELAPSED;

    /**
     * Specifies whether the method should be excluded from performance measurement.
     * <p>
     * If set to true, the method will not be included in performance logging
     * or measurement. This can be useful for methods that are known to have
     * constant execution time or methods that should not be monitored.
     * The default value is false.
     * </p>
     *
     * @return Whether the method should be excluded from performance measurement.
     */
    boolean exclude() default false;
}
