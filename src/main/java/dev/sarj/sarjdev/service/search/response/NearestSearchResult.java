package dev.sarj.sarjdev.service.search.response;

import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NearestSearchResult extends SearchResult {
    /**
     * The total number of charging stations in the search result.
     */
    private Integer total;

    /**
     * The distance to the nearest charging station.
     */
    private Integer distance;
    /**
     * The unit of distance measurement (e.g., "km").
     */
    private String distanceUnit = "km";

    /**
     * Creates a new NearestSearchResult instance with the provided charging stations,
     * total count, and distance to the nearest charging station.
     *
     * @param chargingStations The list of charging stations in the search result.
     * @param total            The total number of charging stations.
     * @param distance         The distance to the nearest charging station.
     */
    public NearestSearchResult(List<ChargingStation> chargingStations, Integer total, Integer distance) {
        super(chargingStations);
        this.total = total;
        this.distance = distance;
    }
}
