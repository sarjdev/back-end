package dev.sarj.sarjdev.core.elasticsearch.settings;

import java.io.InputStream;

/**
 * This interface defines methods for creating Elasticsearch indices with settings, mappings, and aliases.
 */
public interface ElasticSearchIndicesService {

    /**
     * Creates an Elasticsearch index with specified settings, mappings, and alias.
     *
     * @param indexName The name of the index to be created.
     * @param alias     The alias to be associated with the index.
     * @param settings  The JSON representation of index settings.
     * @param mappings  The JSON representation of index mappings.
     */
    void create(String indexName, String alias, String settings, String mappings, Runnable afterIndexHook);

    /**
     * Creates an Elasticsearch index with specified settings, mappings, and alias.
     *
     * @param indexName The name of the index to be created.
     * @param alias     The alias to be associated with the index.
     * @param setting   The input stream containing index settings.
     * @param mapping   The input stream containing index mappings.
     */
    void create(String indexName, String alias, InputStream setting, InputStream mapping, Runnable afterIndexHook);
}
