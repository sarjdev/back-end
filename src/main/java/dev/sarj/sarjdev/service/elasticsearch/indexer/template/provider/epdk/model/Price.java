package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.epdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Price {
    @JsonProperty("active")
    private int active;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("startTime")
    private LocalDateTime startTime;

    @JsonProperty("endTime")
    private LocalDateTime endTime;
}
