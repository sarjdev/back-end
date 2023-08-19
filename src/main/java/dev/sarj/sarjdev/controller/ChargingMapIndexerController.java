package dev.sarj.sarjdev.controller;

import dev.sarj.sarjdev.service.elasticsearch.indexer.template.ElasticSearchIndexerTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/charging-map-indexer")
public class ChargingMapIndexerController {

    private final List<ElasticSearchIndexerTemplate> elasticSearchIndexerTemplates;

    @PostMapping(path = "/index")
    public ResponseEntity<Boolean> index() {
        elasticSearchIndexerTemplates
                .parallelStream()
                .forEach(ElasticSearchIndexerTemplate::index);

        return ResponseEntity.ok(Boolean.TRUE);
    }
}
