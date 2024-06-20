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
 * </p>
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Performance {

    /**
     * Specifies the type of elapsed time measurement.
     *
     * @return The type of elapsed time measurement.
     */
    ElapsedType value() default ElapsedType.SERVICE_ELAPSED;
}
