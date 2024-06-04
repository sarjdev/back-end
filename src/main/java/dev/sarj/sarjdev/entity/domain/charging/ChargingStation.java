package dev.sarj.sarjdev.entity.domain.charging;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.elasticsearch.indexer.mapper.IndexDocumentMapper;
import dev.sarj.sarjdev.service.elasticsearch.indexer.model.ChargingIndexDocument;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargingStation implements IndexDocumentMapper, Serializable {
    /**
     * The unique ID of the document.
     */
    private Long id;

    /**
     * The title of the charging station.
     */
    private String title;

    /**
     * The location of the charging station.
     */
    private ChargingLocation location;

    private GeoLocation geoLocation;

    /**
     * Represents an operator entity.
     */
    private Operator operator;

    /**
     * The URL for making reservations or bookings.
     */
    private String reservationUrl;

    /**
     * The phone number that associated with station provider.
     */
    private String phone;

    /**
     * Active status of station.
     */
    private Boolean stationActive;

    /**
     * List of plugs.
     */
    private List<Plug> plugs;

    /**
     * The total number of plugs available at the charging station.
     */
    private Integer plugsTotal;

    /**
     * The charging provider associated with the charging station.
     */
    private ChargingProvider provider;

    /**
     * Represents a list of payment types.
     */
    private List<PaymentType> paymentTypes;

    /**
     * Provide live stats for charging station.
     */
    private Boolean provideLiveStats;

    public ChargingIndexDocument toIndexDocument() {
        return new ChargingIndexDocument();
    }
}
