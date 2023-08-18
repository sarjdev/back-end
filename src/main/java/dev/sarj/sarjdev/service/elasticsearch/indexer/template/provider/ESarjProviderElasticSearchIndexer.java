package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider;

import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.elasticsearch.indexer.model.ChargingIndexDocument;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.ElasticSearchIndexerTemplate;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.model.ESarjChargingStation;

import java.util.List;


/**
 * Elasticsearch indexer implementation for the ESARJ charging provider.
 * This class extends ElasticSearchIndexerTemplate to provide custom indexing
 * behavior specific to the ESARJ charging provider.
 */
public class ESarjProviderElasticSearchIndexer extends ElasticSearchIndexerTemplate<ESarjChargingStation> {
    /**
     * Indexes a list of ChargingIndexDocument instances into Elasticsearch for the ESARJ provider.
     *
     * @param indexDocuments The list of ChargingIndexDocument instances to be indexed.
     */
    @Override
    protected void indexOnES(List<ChargingIndexDocument> indexDocuments) {
        // [TODO] yusuf.yilmaz
    }

    /**
     * Retrieves a list of ESarjChargingStation instances representing the ESARJ charging provider's data.
     *
     * @return A list of ESarjChargingStation instances.
     */
    @Override
    protected List<ESarjChargingStation> getProviderChargingMaps() {
        // Retrieve and return the list of Object instances
        // [TODO] Retrieve and return the actual data
        return null;
    }

    /**
     * Retrieves the charging provider associated with the indexing process (ESARJ).
     *
     * @return The ChargingProvider enum representing the ESARJ provider.
     */
    @Override
    protected ChargingProvider getChargingProvider() {
        return ChargingProvider.ESARJ;
    }
}
