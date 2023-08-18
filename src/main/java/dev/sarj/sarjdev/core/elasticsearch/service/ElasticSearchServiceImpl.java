package dev.sarj.sarjdev.core.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import dev.sarj.sarjdev.core.elasticsearch.configuration.ElasticSearchClientProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private final ElasticsearchClient elasticsearchClient;

    public ElasticSearchServiceImpl(ElasticSearchClientProvider elasticSearchClientProvider) {
        elasticsearchClient = elasticSearchClientProvider.getElasticsearchClient();
    }

    @Override
    public String indexDocument(String indexName, Object document) {
        try {
            IndexResponse response = elasticsearchClient
                    .index(i -> i.index(indexName).document(document));

            return response.id();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
