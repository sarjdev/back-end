package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.zes;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sarj.sarjdev.core.elasticsearch.service.ElasticSearchService;
import dev.sarj.sarjdev.core.file.ResourceFileContentReader;
import dev.sarj.sarjdev.core.utils.JSONUtils;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.ElasticSearchIndexerTemplate;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.zes.model.ZESChargingStation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * ElasticSearch indexer implementation for the ZES charging station provider.
 */
@Slf4j
@Component
public class ZESProviderElasticSearchIndexer extends ElasticSearchIndexerTemplate<ZESChargingStation> {
    private final ResourceFileContentReader resourceFileContentReader;

    /**
     * Constructor to initialize the ZESProviderElasticSearchIndexer.
     *
     * @param elasticSearchService      The ElasticSearchService instance.
     * @param resourceFileContentReader The ResourceFileContentReader instance.
     */
    public ZESProviderElasticSearchIndexer(ElasticSearchService elasticSearchService, ResourceFileContentReader resourceFileContentReader) {
        super(elasticSearchService);
        this.resourceFileContentReader = resourceFileContentReader;
    }

    /**
     * Retrieves the list of ZESChargingStation instances specific to the ZES provider.
     *
     * @return A list of ZESChargingStation instances.
     */
    @Override
    protected List<ZESChargingStation> getProviderChargingMaps() {
        // Retrieve and return the list of Object instances
        Optional<String> fileContent = resourceFileContentReader.readResourceFileAsStream("providers/zes/data.json");

        if (fileContent.isEmpty()) {
            log.error("There is no any content for ZES provider.");
            return List.of();
        }
        return JSONUtils.deserializeByTypeReference(fileContent.get(), new TypeReference<>() {
        });
    }

    /**
     * Retrieves the charging provider associated with the indexing process (ZES).
     *
     * @return The ChargingProvider enum representing the ZES provider.
     */
    @Override
    protected ChargingProvider getChargingProvider() {
        return ChargingProvider.ZES;
    }
}
