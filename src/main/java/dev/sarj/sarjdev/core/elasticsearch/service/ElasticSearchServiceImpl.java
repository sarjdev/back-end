package dev.sarj.sarjdev.core.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.SourceConfig;
import dev.sarj.sarjdev.core.elasticsearch.configuration.ElasticSearchClientProvider;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private final ElasticsearchClient elasticsearchClient;

    public ElasticSearchServiceImpl(ElasticSearchClientProvider elasticSearchClientProvider) {
        elasticsearchClient = elasticSearchClientProvider.getElasticsearchClient();
    }

    @Override
    public String indexDocument(String indexName, String id, Object document) {
        try {
            IndexResponse response = elasticsearchClient
                    .index(i -> i.index(indexName).document(document).id(id));

            return response.id();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getAllData(String indexName, Class<T> clazz, List<String> fields) {
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(indexName)
                .from(0)
                .size(10_000)
                .source(new SourceConfig.Builder().filter(q -> q.includes(fields)).build())
                .query(q -> q.matchAll(v -> QueryBuilders.matchAll()))
                .build();

        SearchResponse<T> searchResponse = null;

        try {
            searchResponse = elasticsearchClient.search(searchRequest, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return searchResponse.hits()
                .hits()
                .stream()
                .map(Hit::source).toList();
    }

    @SneakyThrows
    @Override
    public <T> SearchResponse<T> runQuery(String indexName, String queryJson, Class<T> clazz) {
        SearchRequest request = new SearchRequest.Builder()
                .index(indexName)
                .withJson(new ByteArrayInputStream(queryJson.getBytes()))
                .build();

        return elasticsearchClient.search(request, clazz);
    }

    @Override
    public <T> T getById(String indexName, String id, Class<T> clazz) {
        SearchRequest request = new SearchRequest.Builder()
                .index(indexName)
                .query(q -> q.ids(v -> v.values(List.of(id))))
                .build();

        SearchResponse<T> searchResponse = null;

        try {
            searchResponse = elasticsearchClient.search(request, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return searchResponse.hits()
                .hits()
                .get(0)
                .source();
    }
}
