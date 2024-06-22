package dev.sarj.sarjdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <a href="https://discuss.elastic.co/t/caused-by-java-lang-nosuchmethoderror-void-co-elastic-clients-transport-rest-client-restclienttransport-init/339573/9">...</a>
 */
@EnableJpaRepositories
@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchClientAutoConfiguration.class)
public class SarjdevApplication {

    public static void main(String[] args) {
        SpringApplication.run(SarjdevApplication.class, args);
    }

}
