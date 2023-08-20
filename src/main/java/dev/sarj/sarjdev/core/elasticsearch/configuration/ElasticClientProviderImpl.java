package dev.sarj.sarjdev.core.elasticsearch.configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ElasticClientProviderImpl implements ElasticSearchClientProvider {

    @Value("${elastic.server}")
    String serverUrl;
    @Value("${elastic.username:#{null}}")
    String username;
    @Value("${elastic.password:#{null}}")
    String password;

    @Override
    public ElasticsearchClient getElasticsearchClient() {
        RestClientBuilder restClientBuilder = RestClient
                .builder(HttpHost.create(serverUrl));

        Optional.ofNullable(username)
                .ifPresent(uname -> appendCredentials(restClientBuilder));

        RestClient restClient = restClientBuilder
                .build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // And create the API client
        return new ElasticsearchClient(transport);
    }

    /**
     * Appends the provided credentials to the RestClientBuilder.
     *
     * @param restClientBuilder The RestClientBuilder to which credentials will be appended.
     */
    private void appendCredentials(RestClientBuilder restClientBuilder) {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        restClientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
    }
}
