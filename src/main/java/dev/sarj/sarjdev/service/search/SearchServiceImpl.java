package dev.sarj.sarjdev.service.search;

import dev.sarj.sarjdev.core.elasticsearch.service.ElasticSearchService;
import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import dev.sarj.sarjdev.service.search.response.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final ElasticSearchService elasticsearchService;

    @Override
    public SearchResult search() {
        List<ChargingStation> data = elasticsearchService.getAllData("chargingstations", ChargingStation.class);
        return new SearchResult(data);
    }
}

