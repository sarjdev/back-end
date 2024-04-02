package dev.sarj.sarjdev.controller;

import dev.sarj.sarjdev.service.elasticsearch.indexer.template.ElasticSearchIndexerTemplate;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/charging-map-indexer")
public class ChargingStationMapIndexerController {

    private final List<ElasticSearchIndexerTemplate> elasticSearchIndexerTemplates;

    @PostMapping(path = "/index")
    public ResponseEntity<Boolean> index() {
        elasticSearchIndexerTemplates
                .stream()
                .forEach(ElasticSearchIndexerTemplate::index);

        return ResponseEntity.ok(Boolean.TRUE);
    }
}
