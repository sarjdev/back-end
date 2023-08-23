package dev.sarj.sarjdev.service.elasticsearch.settings;

/**
 * This interface defines methods for managing Elasticsearch indices settings related to charging stations.
 */
public interface ElasticSearchIndicesSettingsService {
    /**
     * Rotates the charging stations index by creating a new index with updated settings and mappings,
     * and updating the alias to point to the new index.
     */
    void rotateChargingStationsIndex();
}
