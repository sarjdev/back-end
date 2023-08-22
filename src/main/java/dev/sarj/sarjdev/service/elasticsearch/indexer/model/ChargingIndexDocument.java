package dev.sarj.sarjdev.service.elasticsearch.indexer.model;


import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a document to be indexed in an Elasticsearch index for charging stations.
 */
@Getter
@Setter
@Builder
public class ChargingIndexDocument {

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
    private int plugsTotal;

    /**
     * The charging provider associated with the charging station.
     */
    private ChargingProvider provider;

    /**
     * The concatenated search text to enhance search capabilities.
     */
    private String searchText;
    /**
     * Provide live stats for charging station.
     */
    private Boolean provideLiveStats;

    /**
     * Builds the search text by concatenating title, address, and city.
     *
     * @return The ChargingIndexDocument instance with updated search text.
     */
    public ChargingIndexDocument buildSearchText() {
        searchText = title + " " + address + " " + city;
        return this;
    }
}
