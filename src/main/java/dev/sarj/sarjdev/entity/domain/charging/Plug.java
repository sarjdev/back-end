package dev.sarj.sarjdev.entity.domain.charging;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class Plug implements Serializable {
    private Long id;
    private PlugType type;
    private PlugSubType subType;
    private String socketNumber;
    private Double power;
    private BigDecimal price;
    private Integer count;
}
