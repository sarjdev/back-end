package dev.sarj.sarjdev.core.aspect.performance;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * AOP Aspect for measuring performance timing of methods.
 */
@Aspect
@Slf4j
@Component
public class PerformanceAspect {

    /**
     * Measures the performance timing of controller methods.
     *
     * @param joinPoint The join point at which the advice is applied.
     * @return The result of the advised method execution.
     * @throws Throwable if any error occurs during method execution.
     */
    @Around("execution(* dev.sarj.sarjdev.controller.*Controller.*(..))")
    public Object measureControllerPerformanceTiming(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();

        Object data = joinPoint.proceed();

        Instant end = Instant.now();

        measurePerformanceTiming(start, end, joinPoint, ElapsedType.API_ELAPSED);

        return data;
    }

    /**
     * Measures the performance timing of core methods.
     *
     * @param joinPoint The join point at which the advice is applied.
     * @return The result of the advised method execution.
     * @throws Throwable if any error occurs during method execution.
     */
    @Around("execution(* dev.sarj.sarjdev.core..*.*(..))")
    public Object measureCorePerformanceTiming(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();

        Object data = joinPoint.proceed();

        Instant end = Instant.now();

        measurePerformanceTiming(start, end, joinPoint, ElapsedType.SERVICE_ELAPSED);

        return data;
    }

    /**
     * Measures the performance timing of service methods.
     *
     * @param joinPoint The join point at which the advice is applied.
     * @return The result of the advised method execution.
     * @throws Throwable if any error occurs during method execution.
     */
    @Around("execution(* dev.sarj.sarjdev.service..*.*(..))")
    public Object measureServicePerformanceTiming(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();

        Object data = joinPoint.proceed();

        Instant end = Instant.now();

        measurePerformanceTiming(start, end, joinPoint, ElapsedType.SERVICE_ELAPSED);

        return data;
    }

    /**
     * Measures the performance timing of a method and logs the result.
     *
     * @param start       The start time of method execution.
     * @param end         The end time of method execution.
     * @param joinPoint   The join point at which the advice is applied.
     * @param elapsedType The type of elapsed time (API_ELAPSED or SERVICE_ELAPSED).
     */
    public void measurePerformanceTiming(Instant start, Instant end, ProceedingJoinPoint joinPoint, ElapsedType elapsedType) {
        Signature signature = joinPoint.getSignature();

        String className = signature.getDeclaringType().getSimpleName();
        String methodSignature = getMethodSignature(signature);

        Duration duration = Duration.between(start, end);

        double executionTimeMillis = (double) duration.getNano() / TimeUnit.MILLISECONDS.toNanos(1);

        log.info(elapsedType + " | " + className + "." + methodSignature + " is proceed in " + executionTimeMillis + " ms.");
    }

    /**
     * Retrieves the method signature including parameter types.
     *
     * @param signature The method signature.
     * @return The formatted method signature.
     */
    private String getMethodSignature(Signature signature) {
        Class<?>[] parameterTypes = ((MethodSignature) signature).getMethod().getParameterTypes();

        String delimitedTypeParameters = Arrays.stream(parameterTypes)
                .map(Class::getSimpleName)
                .collect(Collectors.joining("_"));

        return signature.getName() + "(" + delimitedTypeParameters + ")";
    }
}
