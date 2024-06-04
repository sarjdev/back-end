package dev.sarj.sarjdev.service.elasticsearch.indexer.model;


import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        searchText = Stream.of(getTitle(), getLocation().getAddress(), getLocation().getDistrictName(), getLocation().getCityName())
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.joining(" "));

        return this;
    }
}
