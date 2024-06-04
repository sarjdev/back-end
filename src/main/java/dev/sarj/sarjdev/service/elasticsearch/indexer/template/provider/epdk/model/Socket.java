package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.epdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Socket {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("subType")
    private String subType;

    @JsonProperty("socketNumber")
    private String socketNumber;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("power")
    private double power;

    @JsonProperty("prices")
    private List<Price> prices;

    @JsonProperty("availability")
    private List<Availability> availability;
}