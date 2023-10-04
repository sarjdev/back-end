package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.beefull;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sarj.sarjdev.core.elasticsearch.service.ElasticSearchService;
import dev.sarj.sarjdev.core.file.ResourceFileContentReader;
import dev.sarj.sarjdev.core.utils.JSONUtils;
import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.ElasticSearchIndexerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Elasticsearch indexer implementation for the BeeFull charging provider.
 * This class extends ElasticSearchIndexerTemplate to provide custom indexing
 * behavior specific to the BeeFull charging provider.
 */
@Slf4j
@Component
public class BeeFullProviderElasticSearchIndexer extends ElasticSearchIndexerTemplate<ChargingStation> {
    private final ResourceFileContentReader resourceFileContentReader;

    public BeeFullProviderElasticSearchIndexer(ElasticSearchService elasticSearchService, ResourceFileContentReader resourceFileContentReader) {
        super(elasticSearchService);
        this.resourceFileContentReader = resourceFileContentReader;
    }

    /**
     * Retrieves a list of ChargingStation instances representing the BeeFull charging provider's data.
     *
     * @return A list of ChargingStation instances.
     */
    @Override
    protected List<ChargingStation> getProviderChargingMaps() {
        // Retrieve and return the list of Object instances
        Optional<String> fileContent = resourceFileContentReader.readResourceFileAsStream("providers/beefull/data.json");

        if (fileContent.isEmpty()) {
            log.error("There is no any content for esarj provider.");
            return List.of();
        }
        return JSONUtils.deserializeByTypeReference(fileContent.get(), new TypeReference<>() {
        });
    }

    /**
     * Retrieves the charging provider associated with the indexing process (BeeFull).
     *
     * @return The ChargingProvider enum representing the BeeFull provider.
     */
    @Override
    protected ChargingProvider getChargingProvider() {
        return ChargingProvider.BEEFULL;
    }
}
