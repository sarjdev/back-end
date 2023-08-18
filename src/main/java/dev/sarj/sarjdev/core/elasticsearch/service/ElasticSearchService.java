package dev.sarj.sarjdev.core.elasticsearch.service;

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
}
