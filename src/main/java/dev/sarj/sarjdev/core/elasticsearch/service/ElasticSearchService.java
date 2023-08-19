package dev.sarj.sarjdev.core.elasticsearch.service;

import java.util.List;

/**
 * A service interface for indexing data into Elasticsearch.
 */
public interface ElasticSearchService {
    /**
     * Indexes a document into Elasticsearch.
     *
     * @param indexName The name of the index.
     * @param document  The document to be indexed.
     * @return The ID of the indexed document.
     */
    String indexDocument(String indexName, Object document);

    /**
     * Retrieves all data of a specified type from Elasticsearch.
     *
     * @param indexName The name of the index.
     * @param clazz     The class representing the type of data to retrieve.
     * @param <T>       The type of data to retrieve.
     * @return A list containing all data of the specified type from the index.
     */
    <T> List<T> getAllData(String indexName, Class<T> clazz);
}
