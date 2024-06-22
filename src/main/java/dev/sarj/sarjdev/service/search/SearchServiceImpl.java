package dev.sarj.sarjdev.service.search;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import dev.sarj.sarjdev.core.elasticsearch.service.ElasticSearchService;
import dev.sarj.sarjdev.core.file.ResourceFileContentReader;
import dev.sarj.sarjdev.core.utils.JSONUtils;
import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import dev.sarj.sarjdev.service.search.response.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import static dev.sarj.sarjdev.common.Constant.Elastic.ES_INDEX_ALIAS_NAME;
import static dev.sarj.sarjdev.common.Constant.Elastic.Query.NEAREST_CHARGING_STATIONS_QUERY_PATH;
import static dev.sarj.sarjdev.common.Constant.Elastic.Query.SUGGEST_CHARGING_STATIONS_QUERY_PATH;

@Component
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchServiceImpl self;
    private final ElasticSearchService elasticsearchService;
    private final ResourceFileContentReader resourceFileContentReader;

    @Override
    @Cacheable(value = "charging-stations-search-result")
    public SearchResult search() {
        List<String> fields = List.of("id", "geoLocation", "provider");
        List<ChargingStation> data = elasticsearchService.getAllData(ES_INDEX_ALIAS_NAME, ChargingStation.class, fields);
        return new SearchResult(data);
    }


    @Override
    public byte[] compressedSearch() {
        return self.doCompressedSearch();
    }


    @Override
    @SneakyThrows
    @Cacheable(value = "compressed-charging-stations-search-result")
    public byte[] doCompressedSearch() {
        List<String> fields = List.of("id", "geoLocation", "operator.id");
        List<ChargingStation> data = elasticsearchService.getAllData(ES_INDEX_ALIAS_NAME, ChargingStation.class, fields);

        SearchResult result = new SearchResult(data);

        String json = JSONUtils.serialize(result);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(json.getBytes());
        gzipOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public NearestSearchResult nearest(Double latitude, Double longitude, Integer distance, Integer size) {
        String query = resourceFileContentReader.readResourceFileAsStream(NEAREST_CHARGING_STATIONS_QUERY_PATH)
                .orElseThrow(IllegalArgumentException::new)
                .replaceAll("@SIZE", size.toString())
                .replaceAll("@DISTANCE", distance.toString())
                .replaceAll("@LATITUDE", latitude.toString())
                .replaceAll("@LONGITUDE", longitude.toString());

        SearchResponse<NearestChargingStation> chargingStationSearchResponse = elasticsearchService.runQuery(ES_INDEX_ALIAS_NAME, query, NearestChargingStation.class);

        List<NearestChargingStation> chargingStations = chargingStationSearchResponse.hits()
                .hits()
                .stream()
                .map(hit ->
                {
                    Double measured = hit.fields().get("distance").to(Double[].class)[0];

                    NearestChargingStation chargingStation = hit.source();
                    chargingStation.setDistance(measured);

                    return chargingStation;
                })
                .toList();

        Integer total = (int) chargingStationSearchResponse.hits()
                .total()
                .value();

        return new NearestSearchResult(chargingStations, total, distance);
    }

    @Override
    public SearchSuggestionResult suggest(String q, Integer size) {
        String query = resourceFileContentReader.readResourceFileAsStream(SUGGEST_CHARGING_STATIONS_QUERY_PATH)
                .orElseThrow(IllegalArgumentException::new)
                .replaceAll("@SIZE", size.toString())
                .replaceAll("@FIELD", "searchText")
                .replaceAll("@QUERY", q);

        SearchResponse<ChargingStation> chargingStationSearchResponse = elasticsearchService.runQuery(ES_INDEX_ALIAS_NAME, query, ChargingStation.class);

        List<SuggestedChargingStation> suggestedChargingStations = chargingStationSearchResponse.hits()
                .hits()
                .stream()
                .map(SearchServiceImpl::toSuggestedChargingStation)
                .toList();

        Integer total = (int) chargingStationSearchResponse.hits()
                .total()
                .value();

        return SearchSuggestionResult.of(total, suggestedChargingStations);
    }

    @Override
    public ChargingStation getById(String id) {
        return elasticsearchService.getById(ES_INDEX_ALIAS_NAME, id, ChargingStation.class);
    }

    private static SuggestedChargingStation toSuggestedChargingStation(Hit<ChargingStation> source) {
        ChargingStation chargingStation = source.source();
        String highlight = source.highlight().get("searchText").get(0);

        return SuggestedChargingStation.of(chargingStation, highlight);
    }
}

