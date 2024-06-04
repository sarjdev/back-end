package dev.sarj.sarjdev.entity.domain.charging;

import lombok.*;

/**
 * Represents an operator entity.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Operator {
    /**
     * The unique identifier of the operator.
     */
    private Long id;

    /**
     * The title or name of the operator.
     */
    private String title;

    /**
     * The brand associated with the operator.
     */
    private String brand;
}