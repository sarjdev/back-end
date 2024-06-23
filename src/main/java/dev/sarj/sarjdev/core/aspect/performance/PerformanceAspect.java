package dev.sarj.sarjdev.core.aspect.performance;

import dev.sarj.sarjdev.entity.domain.dao.MethodExecutionTime;
import dev.sarj.sarjdev.service.methodexecutiontime.MethodExecutionTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * AOP Aspect for measuring performance timing of methods.
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class PerformanceAspect {

    private final MethodExecutionTimeService methodExecutionTimeService;

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

        CompletableFuture.runAsync(() ->
        {
            measurePerformanceTiming(start, end, joinPoint, ElapsedType.API_ELAPSED);
        });

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

        CompletableFuture.runAsync(() ->
        {
            measurePerformanceTiming(start, end, joinPoint, ElapsedType.SERVICE_ELAPSED);
        });

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

        Boolean shouldExitPerformanceMetricCalculation = checkMethodByPassThePerformanceTiming(joinPoint);

        if (shouldExitPerformanceMetricCalculation) {
            return joinPoint.proceed();
        }

        Instant start = Instant.now();

        Object data = joinPoint.proceed();

        Instant end = Instant.now();

        CompletableFuture.runAsync(() ->
        {
            measurePerformanceTiming(start, end, joinPoint, ElapsedType.SERVICE_ELAPSED);
        });

        return data;
    }

    /**
     * Checks if the method or the class has the Performance annotation with the exclude flag set to true.
     *
     * @param joinPoint The join point representing the method being called.
     * @return true if the method or the class has to exclude flag set to true, false otherwise.
     * @throws Throwable if there is an error in accessing method or class annotations.
     */
    private Boolean checkMethodByPassThePerformanceTiming(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // Check if the method contains the Performance annotation with exclude set to true
        Boolean doesMethodContain =
                Optional.ofNullable(
                                methodSignature.getMethod().getAnnotation(Performance.class)
                        )
                        .map(Performance::exclude)
                        .orElse(Boolean.FALSE);

        // Check if the class contains the Performance annotation with exclude set to true
        Boolean doesClassContain = Optional.ofNullable(
                        methodSignature.getDeclaringType().getAnnotation(Performance.class)
                )
                .map(annotations -> (Performance) annotations)
                .map(Performance::exclude)
                .orElse(Boolean.FALSE);

        // Return true if either the method or the class has to exclude flag set to true
        return doesMethodContain || doesClassContain;
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

        String methodSignature = getMethodSignature(signature);
        String className = signature.getDeclaringType().getSimpleName();
        String packageName = joinPoint.getSignature().getDeclaringTypeName()
                .substring(0, joinPoint.getSignature().getDeclaringTypeName().lastIndexOf("."));

        Duration duration = Duration.between(start, end);

        double executionTimeMillis = (double) duration.getNano() / TimeUnit.MILLISECONDS.toNanos(1);

        MethodExecutionTime methodExecutionTime = MethodExecutionTime.builder()
                .elapsedType(elapsedType)
                .className(className)
                .packagePath(packageName)
                .methodName(methodSignature)
                .executionDate(Instant.now())
                .executionTimeMs(BigDecimal.valueOf(executionTimeMillis))
                .build();

        methodExecutionTimeService.save(methodExecutionTime);

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
