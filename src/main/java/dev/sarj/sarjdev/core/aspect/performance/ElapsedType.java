package dev.sarj.sarjdev.core.aspect.performance;

/**
 * Enumeration representing different types of elapsed time.
 * <p>
 * This enumeration is used to classify the elapsed time measurements
 * captured by the {@link PerformanceAspect} aspect.
 * </p>
 */
public enum ElapsedType {
    /**
     * Represents elapsed time for API methods.
     */
    API_ELAPSED,

    /**
     * Represents elapsed time for service methods.
     */
    SERVICE_ELAPSED
}