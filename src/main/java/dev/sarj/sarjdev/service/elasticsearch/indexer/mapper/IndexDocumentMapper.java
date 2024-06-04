package dev.sarj.sarjdev.service.elasticsearch.indexer.mapper;

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
}
