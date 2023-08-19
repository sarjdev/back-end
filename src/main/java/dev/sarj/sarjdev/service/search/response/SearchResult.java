package dev.sarj.sarjdev.service.search.response;

import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchResult {
    private final List<ChargingStation> chargingStations;
}
