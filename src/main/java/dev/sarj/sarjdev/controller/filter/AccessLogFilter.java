package dev.sarj.sarjdev.controller.filter;

import dev.sarj.sarjdev.core.utils.ExceptionUtils;
import dev.sarj.sarjdev.entity.domain.dao.AccessLog;
import dev.sarj.sarjdev.service.accesslog.AccessLogService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccessLogFilter implements Filter {

    private final AccessLogService accessLogService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        AccessLog.AccessLogBuilder builder = AccessLog.builder()
                .ipAddress(request.getRemoteAddr())
                .host(request.getRemoteHost())
                .userAgent(request.getHeader("User-Agent"))
                .httpMethod(request.getMethod())
                .url(request.getRequestURL().toString())
                .path(request.getServletPath())
                .queryParams(request.getQueryString())
                .referer(request.getHeader("Referer"))
                .xForwardedFor(request.getHeader("X-Forwarded-For"))
                .requestTime(Instant.now());

        try {
            // Continue the filter chain
            chain.doFilter(request, response);

        } catch (Exception exception) {
            builder.stackTrace(ExceptionUtils.getStackTrace(exception))
                    .responseTime(Instant.now())
                    .status(500)
                    .contentLength(0L);

            CompletableFuture.runAsync(() -> prepareAndSaveAccessLog(builder));

            throw exception;
        }

        builder
                .responseTime(Instant.now())
                .status(response.getStatus())
                .contentLength(0L);

        CompletableFuture.runAsync(() -> prepareAndSaveAccessLog(builder));
    }

    private void prepareAndSaveAccessLog(AccessLog.AccessLogBuilder builder) {

        AccessLog accessLog = builder.build();
        String xForwardedFor = accessLog.getXForwardedFor();

        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            accessLog.setIpAddress(xForwardedFor.split(",")[0]);
        }

        Duration duration = Duration.between(accessLog.getRequestTime(), accessLog.getResponseTime());
        double executionTimeMillis = (double) duration.getNano() / TimeUnit.MILLISECONDS.toNanos(1);

        accessLog.setExecutionTimeMs(BigDecimal.valueOf(executionTimeMillis));

        accessLogService.save(accessLog);
    }
}
