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
    private String id;

    /**
     * The location of the charging station.
     */
    private ChargingLocation location;

    /**
     * The title of the charging station.
     */
    private String title;

    /**
     * List of plugs.
     */
    private List<Plug> plugs;

    /**
     * The address of the charging station.
     */
    private String address;

    /**
     * The city where the charging station is located.
     */
    private String city;

    /**
     * The list of points of interest associated with the charging station.
     */
    private List<String> pointOfInterests;

    /**
     * The total number of plugs available at the charging station.
     */
    private Integer plugsTotal;

    /**
     * The charging provider associated with the charging station.
     */
    private ChargingProvider provider;
    /**
     * Provide live stats for charging station.
     */
    private Boolean provideLiveStats;

    @Override
    public ChargingIndexDocument toIndexDocument() {
        return toIndexDocument(this);
    }
}
