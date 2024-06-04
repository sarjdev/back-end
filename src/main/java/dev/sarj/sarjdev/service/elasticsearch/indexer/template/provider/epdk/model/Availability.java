package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.epdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Availability {
    @JsonProperty("id")
    private int id;

    @JsonProperty("active")
    private int active;

    @JsonProperty("status")
    private String status;

    @JsonProperty("startTime")
    private LocalDateTime startTime;

    @JsonProperty("endTime")
    private LocalDateTime endTime;

    @JsonProperty("reservationid")
    private int reservationId;
}
