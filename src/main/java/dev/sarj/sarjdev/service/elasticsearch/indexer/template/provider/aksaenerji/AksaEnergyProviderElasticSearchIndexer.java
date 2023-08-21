package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.aksaenerji;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sarj.sarjdev.core.elasticsearch.service.ElasticSearchService;
import dev.sarj.sarjdev.core.file.ResourceFileContentReader;
import dev.sarj.sarjdev.core.utils.JSONUtils;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.ElasticSearchIndexerTemplate;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.aksaenerji.model.AksaEnergyChargingStation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * An implementation of the ElasticSearchIndexerTemplate specific to the Aksa Energy charging provider.
 */
@Slf4j
@Component
public class AksaEnergyProviderElasticSearchIndexer extends ElasticSearchIndexerTemplate<AksaEnergyChargingStation> {
    private final ResourceFileContentReader resourceFileContentReader;


    /**
     * Constructor to initialize the AksaEnergyProviderElasticSearchIndexer.
     *
     * @param elasticSearchService      The ElasticSearchService instance.
     * @param resourceFileContentReader The ResourceFileContentReader instance.
     */
    public AksaEnergyProviderElasticSearchIndexer(ElasticSearchService elasticSearchService, ResourceFileContentReader resourceFileContentReader) {
        super(elasticSearchService);
        this.resourceFileContentReader = resourceFileContentReader;
    }

    /**
     * Retrieves the list of AksaEnergyChargingStation instances specific to the Aksa Energy provider.
     *
     * @return A list of AksaEnergyChargingStation instances.
     */
    @Override
    protected List<AksaEnergyChargingStation> getProviderChargingMaps() {
        // Retrieve and return the list of Object instances
        Optional<String> fileContent = resourceFileContentReader.readResourceFileAsStream("providers/aksaenergy/data.json");

        if (fileContent.isEmpty()) {
            log.error("There is no any content for Aksa Energy provider.");
            return List.of();
        }
        return JSONUtils.deserializeByTypeReference(fileContent.get(), new TypeReference<>() {
        });
    }

    /**
     * Retrieves the charging provider associated with the indexing process (Aksa Energy).
     *
     * @return The ChargingProvider enum representing the Aksa Energy provider.
     */
    @Override
    protected ChargingProvider getChargingProvider() {
        return ChargingProvider.AKSAENERGY;
    }
}
