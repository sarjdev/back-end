package dev.sarj.sarjdev.core.elasticsearch.configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

/**
 * A provider interface for obtaining ElasticsearchClient instances.
 * Implementing classes define a way to provide instances of ElasticsearchClient.
 */
public interface ElasticSearchClientProvider {
    /**
     * Gets an instance of ElasticsearchClient.
     *
     * @return An instance of ElasticsearchClient.
     */
    ElasticsearchClient getElasticsearchClient();
}
