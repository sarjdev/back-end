package dev.sarj.sarjdev.controller;

import dev.sarj.sarjdev.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/search")
public class SearchController {

    private final SearchService service;

    @GetMapping
    public ResponseEntity<Object> search() {
        return ResponseEntity.ok(service.search());
    }
}
