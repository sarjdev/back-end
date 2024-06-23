package dev.sarj.sarjdev.entity.domain.dao;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "access_log")
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "host", length = 255)
    private String host;

    @Column(name = "user_agent", length = 512)
    private String userAgent;

    @Column(name = "http_method", length = 10)
    private String httpMethod;

    @Column(name = "url", length = 2048)
    private String url;

    @Column(name = "path", length = 2048)
    private String path;

    @Column(name = "query_params", length = 2048)
    private String queryParams;

    @Column(name = "referer", length = 255)
    private String referer;

    @Column(name = "x_forwarded_for", length = 255)
    private String xForwardedFor;

    @Column(name = "request_time")
    private Instant requestTime;

    @Column(name = "response_time")
    private Instant responseTime;

    @Column(name = "stack_trace", columnDefinition = "TEXT")
    private String stackTrace;

    @Column(name = "status")
    private Integer status;

    @Column(name = "content_length")
    private Long contentLength;

    @Column(name = "execution_time_ms", precision = 10, scale = 3)
    private BigDecimal executionTimeMs;
}
