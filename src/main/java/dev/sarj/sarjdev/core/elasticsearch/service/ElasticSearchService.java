package dev.sarj.sarjdev.core.elasticsearch.service;

import co.elastic.clients.elasticsearch.core.SearchResponse;

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
    String indexDocument(String indexName, String id, Object document);

    /**
     * Retrieves all data of a specified type from Elasticsearch.
     *
     * @param indexName The name of the index.
     * @param clazz     The class representing the type of data to retrieve.
     * @param <T>       The type of data to retrieve.
     * @return A list containing all data of the specified type from the index.
     */
    <T> List<T> getAllData(String indexName, Class<T> clazz, List<String> fields);


    /**
     * Executes an Elasticsearch query on the specified index and returns the search response.
     *
     * @param <T>       The type of objects to be retrieved from the search response.
     * @param indexName The name of the Elasticsearch index to run the query on.
     * @param queryJson The JSON representation of the query to be executed.
     * @param clazz     The class representing the type of objects to be retrieved from the search response.
     * @return A SearchResponse containing the search results matching the query.
     */
    <T> SearchResponse<T> runQuery(String indexName, String queryJson, Class<T> clazz);

    /**
     * Retrieves a document by its ID from the specified index.
     *
     * @param indexName The name of the index.
     * @param id        The unique ID of the document.
     * @param clazz     The class type to which the retrieved document will be mapped.
     * @param <T>       The type of the document to retrieve.
     * @return The retrieved document of the specified type.
     */
    <T> T getById(String indexName, String id, Class<T> clazz);
}
