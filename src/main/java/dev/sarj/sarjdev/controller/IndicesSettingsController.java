package dev.sarj.sarjdev.controller;

import dev.sarj.sarjdev.service.elasticsearch.settings.ElasticSearchIndicesSettingsService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/indices-settings")
public class IndicesSettingsController {

    private final ElasticSearchIndicesSettingsService elasticSearchIndicesSettingsService;

    @PostMapping(path = "/rotate-index")
    public ResponseEntity<Boolean> rotateIndex() {
        elasticSearchIndicesSettingsService.rotateChargingStationsIndex();
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
