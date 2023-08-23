package dev.sarj.sarjdev.core.elasticsearch.settings;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import dev.sarj.sarjdev.core.elasticsearch.configuration.ElasticSearchClientProvider;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * Implementation of the {@link ElasticSearchIndicesService} interface for creating Elasticsearch indices
 * with settings, mappings, and aliases.
 */
@Component
public class ElasticSearchIndicesServiceImpl implements ElasticSearchIndicesService {
    private final ElasticsearchClient elasticsearchClient;

    public ElasticSearchIndicesServiceImpl(ElasticSearchClientProvider elasticSearchClientProvider) {
        this.elasticsearchClient = elasticSearchClientProvider.getElasticsearchClient();
    }

    /**
     * Creates an Elasticsearch index with specified settings, mappings, and alias.
     *
     * @param indexName The name of the index to be created.
     * @param alias     The alias to be associated with the index.
     * @param settings  The JSON representation of index settings.
     * @param mappings  The JSON representation of index mappings.
     */
    @SneakyThrows
    public void create(String indexName, String alias, String settings, String mappings, Runnable afterIndexHook) {
        InputStream settingsInputStream = new ByteArrayInputStream(settings.getBytes());
        InputStream mappingsInputStream = new ByteArrayInputStream(mappings.getBytes());

        create(indexName, alias, settingsInputStream, mappingsInputStream, afterIndexHook);
    }

    /**
     * Creates an Elasticsearch index with specified settings, mappings, and alias.
     *
     * @param indexName The name of the index to be created.
     * @param alias     The alias to be associated with the index.
     * @param setting   The input stream containing index settings.
     * @param mapping   The input stream containing index mappings.
     */
    @SneakyThrows
    public void create(String indexName, String alias, InputStream setting, InputStream mapping, Runnable afterIndexHook) {
        ElasticsearchIndicesClient indicesClient = elasticsearchClient.indices();

        List<String> indices = getExistingIndicesThatHasAlias(indicesClient, alias);

        createIndex(indexName, setting, mapping, indicesClient);

        deleteExistingAliasesExceptCreatedIndex(alias, indicesClient, indices);

        putAliasToCreatedIndex(indexName, alias, indicesClient);

        afterIndexHook.run();
    }

    /**
     * Deletes existing aliases associated with the provided alias, except for the newly created index.
     *
     * @param alias         The alias for which existing aliases need to be deleted.
     * @param indicesClient The ElasticsearchIndicesClient instance to interact with Elasticsearch indices.
     * @param indices       The list of existing indices associated with the provided alias.
     * @throws IOException if an I/O error occurs while interacting with Elasticsearch.
     */
    private void deleteExistingAliasesExceptCreatedIndex(String alias, ElasticsearchIndicesClient indicesClient, List<String> indices) throws IOException {
        if (isEmpty(indices)) {
            return;
        }

        DeleteAliasRequest deleteAliasRequest = new DeleteAliasRequest.Builder()
                .index(indices)
                .name(alias)
                .build();

        indicesClient.deleteAlias(deleteAliasRequest);
    }

    /**
     * Associates the provided alias with the newly created index.
     *
     * @param indexName     The name of the newly created index.
     * @param alias         The alias to be associated with the newly created index.
     * @param indicesClient The ElasticsearchIndicesClient instance to interact with Elasticsearch indices.
     * @throws IOException if an I/O error occurs while interacting with Elasticsearch.
     */
    private void putAliasToCreatedIndex(String indexName, String alias, ElasticsearchIndicesClient indicesClient) throws IOException {
        PutAliasRequest putAliasRequest = new PutAliasRequest.Builder()
                .index(indexName)
                .name(alias)
                .build();

        indicesClient.putAlias(putAliasRequest);
    }

    /**
     * Creates an Elasticsearch index with the specified settings, mappings, and name.
     *
     * @param indexName     The name of the index to be created.
     * @param setting       The InputStream containing the settings of the index.
     * @param mapping       The InputStream containing the mappings of the index.
     * @param indicesClient The ElasticsearchIndicesClient instance to interact with Elasticsearch indices.
     * @throws IOException if an I/O error occurs while interacting with Elasticsearch.
     */
    private void createIndex(String indexName, InputStream setting, InputStream mapping, ElasticsearchIndicesClient indicesClient) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                .index(indexName)
                .settings(new IndexSettings.Builder().withJson(setting).build())
                .mappings(new TypeMapping.Builder().withJson(mapping).build())
                .build();

        indicesClient.create(createIndexRequest);
    }

    /**
     * Retrieves a list of existing indices associated with the provided alias.
     *
     * @param indicesClient The ElasticsearchIndicesClient instance to interact with Elasticsearch indices.
     * @param alias         The alias for which existing indices need to be retrieved.
     * @return A list of existing indices associated with the provided alias.
     * @throws IOException if an I/O error occurs while interacting with Elasticsearch.
     */
    @SneakyThrows
    private List<String> getExistingIndicesThatHasAlias(ElasticsearchIndicesClient indicesClient, String alias) {
        ExistsAliasRequest existsAliasRequest = new ExistsAliasRequest.Builder()
                .name(alias)
                .build();

        BooleanResponse exists = indicesClient.existsAlias(existsAliasRequest);

        if (!exists.value()) {
            return List.of();
        }

        GetAliasRequest getAliasRequest = new GetAliasRequest.Builder()
                .name(alias)
                .build();

        return indicesClient.getAlias(getAliasRequest)
                .result()
                .keySet()
                .stream().toList();
    }
}
