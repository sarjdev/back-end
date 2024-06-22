package dev.sarj.sarjdev.entity.domain.dao;

import dev.sarj.sarjdev.core.aspect.performance.ElapsedType;
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
@Table(name = "method_execution_times")
public class MethodExecutionTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_path", nullable = false)
    private String packagePath;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "method_name", nullable = false)
    private String methodName;

    @Enumerated(EnumType.STRING)
    @Column(name = "elapsed_type", nullable = false)
    private ElapsedType elapsedType;

    @Column(name = "execution_time_ms", nullable = false, precision = 10, scale = 3)
    private BigDecimal executionTimeMs;

    @Column(name = "execution_date")
    private Instant executionDate = Instant.now();
}
