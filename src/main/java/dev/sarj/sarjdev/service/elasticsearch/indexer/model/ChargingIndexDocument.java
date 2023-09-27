package dev.sarj.sarjdev.service.elasticsearch.indexer.model;


import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a document to be indexed in an Elasticsearch index for charging stations.
 */
@Getter
@Setter
public class ChargingIndexDocument extends ChargingStation {

    /**
     * The concatenated search text to enhance search capabilities.
     */
    private String searchText;

    /**
     * Builds the search text by concatenating title, address, and city.
     *
     * @return The ChargingIndexDocument instance with updated search text.
     */
    public ChargingIndexDocument buildSearchText() {
        searchText = getTitle() + " " + getAddress() + " " + getCity();
        return this;
    }
}
