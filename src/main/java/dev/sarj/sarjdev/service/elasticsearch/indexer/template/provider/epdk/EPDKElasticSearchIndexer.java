package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.epdk;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sarj.sarjdev.core.elasticsearch.service.ElasticSearchService;
import dev.sarj.sarjdev.core.file.ResourceFileContentReader;
import dev.sarj.sarjdev.core.utils.JSONUtils;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.configuration.condition.ShouldProvideEPDKCondition;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.ElasticSearchIndexerTemplate;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.epdk.model.Station;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Elasticsearch indexer implementation for the EPDK charging provider.
 * This class extends ElasticSearchIndexerTemplate to provide custom indexing
 * behavior specific to the ESARJ charging provider.
 */
@Slf4j
@Component
@Conditional(ShouldProvideEPDKCondition.class)
public class EPDKElasticSearchIndexer extends ElasticSearchIndexerTemplate<Station> {
    private final ResourceFileContentReader resourceFileContentReader;
    public EPDKElasticSearchIndexer(ElasticSearchService elasticSearchService, ResourceFileContentReader resourceFileContentReader) {
        super(elasticSearchService);
        this.resourceFileContentReader = resourceFileContentReader;
    }

    /**
     * Retrieves a list of Station instances representing the EPDK charging provider's data.
     *
     * @return A list of Station instances.
     */
    @Override
    protected List<Station> getProviderChargingMaps() {
        // Retrieve and return the list of Object instances
        Optional<String> fileContent = resourceFileContentReader.readResourceFileAsStream("providers/epdk/data.json");

        if (fileContent.isEmpty()) {
            log.error("There is no any content for esarj provider.");
            return List.of();
        }

        return JSONUtils.deserializeByTypeReference(fileContent.get(), new TypeReference<>() {
        });
    }

    /**
     * Retrieves the charging provider associated with the indexing process (EPDK).
     *
     * @return The ChargingProvider enum representing the EPDK provider.
     */
    @Override
    protected ChargingProvider getChargingProvider() {
        return ChargingProvider.EPDK;
    }
}
