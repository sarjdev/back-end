package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.esarj;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sarj.sarjdev.core.elasticsearch.service.ElasticSearchService;
import dev.sarj.sarjdev.core.file.ResourceFileContentReader;
import dev.sarj.sarjdev.core.utils.JSONUtils;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.ElasticSearchIndexerTemplate;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.esarj.model.ESarjChargingStation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * Elasticsearch indexer implementation for the ESARJ charging provider.
 * This class extends ElasticSearchIndexerTemplate to provide custom indexing
 * behavior specific to the ESARJ charging provider.
 */
@Slf4j
@Component
public class ESARJProviderElasticSearchIndexer extends ElasticSearchIndexerTemplate<ESarjChargingStation> {
    private final ResourceFileContentReader resourceFileContentReader;

    @Autowired
    public ESARJProviderElasticSearchIndexer(ElasticSearchService elasticSearchService, ResourceFileContentReader resourceFileContentReader) {
        super(elasticSearchService);
        this.resourceFileContentReader = resourceFileContentReader;
    }

    /**
     * Retrieves a list of ESarjChargingStation instances representing the ESARJ charging provider's data.
     *
     * @return A list of ESarjChargingStation instances.
     */
    @Override
    protected List<ESarjChargingStation> getProviderChargingMaps() {
        // Retrieve and return the list of Object instances
        Optional<String> fileContent = resourceFileContentReader.readResourceFileAsStream("providers/esarj/data.json");

        if (fileContent.isEmpty()) {
            log.error("There is no any content for esarj provider.");
            return List.of();
        }
        return JSONUtils.deserializeByTypeReference(fileContent.get(), new TypeReference<>() {
        });
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
