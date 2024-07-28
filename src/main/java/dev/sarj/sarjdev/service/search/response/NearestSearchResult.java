package dev.sarj.sarjdev.service.search.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * The {@code NearestSearchResult} class represents the result of a search for the nearest charging stations.
 * It includes a list of {@link NearestChargingStation} objects, the total number of charging stations found,
 * the distance to the nearest charging station, and the unit of distance measurement.
 *
 * <p>This class is typically used to encapsulate the results of a geospatial search query, providing
 * information about charging stations and their proximity to a given location.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * List<NearestChargingStation> stations = new ArrayList<>();
 * stations.add(new NearestChargingStation("Station A", 40.123456, 29.123456, 0.558));
 * NearestSearchResult result = new NearestSearchResult(stations, stations.size(), 0.558);
 * }
 * </pre>
 *
 * @see NearestChargingStation
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-geo-distance-query.html">Elasticsearch Geo Distance Query</a>
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html">Elasticsearch Java API</a>
 */
@Getter
@Setter
public class NearestSearchResult implements Serializable {
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
     * The list of nearest charging stations found in the search result.
     */
    private List<NearestChargingStation> chargingStations;

    /**
     * Creates a new {@code NearestSearchResult} instance with the provided charging stations,
     * total count, and distance to the nearest charging station.
     *
     * @param chargingStations The list of charging stations in the search result.
     * @param total            The total number of charging stations.
     * @param distance         The distance to the nearest charging station.
     */
    public NearestSearchResult(List<NearestChargingStation> chargingStations, Integer total, Integer distance) {
        this.chargingStations = chargingStations;
        this.total = total;
        this.distance = distance;
    }
}
