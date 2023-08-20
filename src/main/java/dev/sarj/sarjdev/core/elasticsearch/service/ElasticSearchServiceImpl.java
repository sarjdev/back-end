package dev.sarj.sarjdev.core.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import dev.sarj.sarjdev.core.elasticsearch.configuration.ElasticSearchClientProvider;
import org.springframework.stereotype.Component;

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
    public <T> List<T> getAllData(String indexName, Class<T> clazz) {
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(indexName)
                .from(0)
                .size(1000)
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
}
