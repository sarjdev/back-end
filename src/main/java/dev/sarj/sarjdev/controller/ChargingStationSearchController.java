package dev.sarj.sarjdev.controller;

import dev.sarj.sarjdev.service.search.SearchService;
import dev.sarj.sarjdev.service.search.response.NearestSearchResult;
import dev.sarj.sarjdev.service.search.response.SearchResult;
import dev.sarj.sarjdev.service.search.response.SearchSuggestionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/search")
public class ChargingStationSearchController {

    private final SearchService service;

    @GetMapping
    public ResponseEntity<SearchResult> search() {
        return ResponseEntity.ok(service.search());
    }

    @GetMapping("/nearest")
    public ResponseEntity<NearestSearchResult> nearest(
            @RequestParam(name = "latitude") Double latitude,
            @RequestParam(name = "longitude") Double longitude,
            @RequestParam(name = "distance", defaultValue = "10", required = false) Integer distance,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        return ResponseEntity.ok(service.nearest(latitude, longitude, distance, size));
    }

    @GetMapping("/suggest")
    public ResponseEntity<SearchSuggestionResult> suggest(
            @RequestParam(name = "q") String q,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        return ResponseEntity.ok(service.suggest(q, size));
    }

}
