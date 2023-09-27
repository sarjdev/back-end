package dev.sarj.sarjdev.service.elasticsearch.indexer.mapper;

import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import dev.sarj.sarjdev.service.elasticsearch.indexer.model.ChargingIndexDocument;

/**
 * Functional interface for mapping objects to ChargingIndexDocument instances.
 * Implementing classes provide a way to convert an object to a ChargingIndexDocument
 * that can be indexed in Elasticsearch.
 */
@FunctionalInterface
public interface IndexDocumentMapper {
    /**
     * Converts an object to a ChargingIndexDocument instance.
     *
     * @return A ChargingIndexDocument instance representing the mapped object.
     */
    ChargingIndexDocument toIndexDocument();

    default ChargingIndexDocument toIndexDocument(ChargingStation chargingStation) {
        ChargingIndexDocument document = new ChargingIndexDocument();
        document.setId(chargingStation.getId());
        document.setLocation(chargingStation.getLocation());
        document.setTitle(chargingStation.getTitle());
        document.setPlugs(chargingStation.getPlugs());
        document.setAddress(chargingStation.getAddress());
        document.setCity(chargingStation.getCity());
        document.setPointOfInterests(chargingStation.getPointOfInterests());
        document.setPlugsTotal(chargingStation.getPlugsTotal());
        document.setProvider(chargingStation.getProvider());
        document.setProvideLiveStats(chargingStation.getProvideLiveStats());
        document.buildSearchText();

        return document;
    }
}
