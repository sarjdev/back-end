package dev.sarj.sarjdev.service.elasticsearch.indexer.template;

import dev.sarj.sarjdev.core.elasticsearch.service.ElasticSearchService;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.elasticsearch.indexer.mapper.IndexDocumentMapper;
import dev.sarj.sarjdev.service.elasticsearch.indexer.model.ChargingIndexDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static dev.sarj.sarjdev.common.Constant.Elastic.ES_INDEX_ALIAS_NAME;

/**
 * Abstract template class for indexing data into Elasticsearch.
 * It provides a common structure for indexing data from various providers.
 *
 * @param <T> The type of objects implementing IndexDocumentMapper interface.
 */
@Slf4j
@RequiredArgsConstructor
public abstract class ElasticSearchIndexerTemplate<T extends IndexDocumentMapper> {
    private final ElasticSearchService elasticSearchService;

    /**
     * Indexes data into Elasticsearch.
     * This method follows a template pattern where the concrete implementation of
     * indexing steps is defined in subclasses.
     */
    public void index() {
        List<T> providerChargingMaps = getProviderChargingMaps();

        if (CollectionUtils.isEmpty(providerChargingMaps)) {
            ChargingProvider providerName = getChargingProvider();
            log.error("There is no any charging maps for {} provider.", providerName.name());
            return;
        }

        List<ChargingIndexDocument> chargingIndexDocuments = providerChargingMaps
                .stream()
                .map(T::toIndexDocument)
                .toList();

        indexOnES(chargingIndexDocuments);
    }

    /**
     * Indexes a list of ChargingIndexDocument instances into Elasticsearch.
     *
     * @param chargingIndexDocuments The list of ChargingIndexDocument instances to be indexed.
     */
    private void indexOnES(List<ChargingIndexDocument> chargingIndexDocuments) {
        for (ChargingIndexDocument chargingIndexDocument : chargingIndexDocuments) {
            elasticSearchService.indexDocument(ES_INDEX_ALIAS_NAME, chargingIndexDocument.getId().toString(), chargingIndexDocument);
        }
    }

    /**
     * Retrieves a list of provider-specific charging map objects implementing IndexDocumentMapper.
     *
     * @return A list of provider-specific charging map objects.
     */
    protected abstract List<T> getProviderChargingMaps();

    /**
     * Retrieves the charging provider associated with the indexing process.
     *
     * @return The ChargingProvider enum representing the provider.
     */
    protected abstract ChargingProvider getChargingProvider();
}
