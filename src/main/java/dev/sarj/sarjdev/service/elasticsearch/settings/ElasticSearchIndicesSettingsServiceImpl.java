package dev.sarj.sarjdev.service.elasticsearch.settings;

import dev.sarj.sarjdev.core.elasticsearch.settings.ElasticSearchIndicesService;
import dev.sarj.sarjdev.core.file.ResourceFileContentReader;
import dev.sarj.sarjdev.service.elasticsearch.indexer.template.ElasticSearchIndexerTemplate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static dev.sarj.sarjdev.common.Constant.Elastic.*;

@Component
@AllArgsConstructor
public class ElasticSearchIndicesSettingsServiceImpl implements ElasticSearchIndicesSettingsService {
    private final ResourceFileContentReader resourceFileContentReader;
    private final ElasticSearchIndicesService elasticSearchIndicesService;

    private final List<ElasticSearchIndexerTemplate<?>> elasticSearchIndexer;

    @Override
    public void rotateChargingStationsIndex() {
        String settings = resourceFileContentReader
                .readResourceFileAsStream(ES_INDEX_SETTINGS_RESOURCE_PATH)
                .orElseThrow(IllegalArgumentException::new);

        String mappings = resourceFileContentReader
                .readResourceFileAsStream(ES_INDEX_MAPPINGS_RESOURCE_PATH)
                .orElseThrow(IllegalArgumentException::new);

        String indexName = createIndexName();

        Runnable afterIndexHook = () -> elasticSearchIndexer.parallelStream().forEach(ElasticSearchIndexerTemplate::index);

        elasticSearchIndicesService.create(indexName, ES_INDEX_ALIAS_NAME, settings, mappings, afterIndexHook);
    }

    private String createIndexName() {
        String formattedDateTİme = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));

        return ES_INDEX_ALIAS_NAME + "_" + formattedDateTİme;
    }
}
