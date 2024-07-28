package dev.sarj.sarjdev.service.search.response;

import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import lombok.Getter;
import lombok.Setter;

/**
 * The {@code NearestChargingStation} class represents a charging station
 * with an additional field for storing the distance from a specified point.
 * This class extends the {@link ChargingStation} class, inheriting its properties
 * and adding a {@code distance} field to indicate the proximity to a specific location.
 *
 * <p>This class is typically used in applications where the nearest charging stations
 * need to be identified and displayed, providing a convenient way to store and
 * retrieve the distance information along with the station details.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * NearestChargingStation station = new NearestChargingStation();
 * station.setName("Station A");
 * station.setLatitude(40.123456);
 * station.setLongitude(29.123456);
 * station.setDistance(0.558);
 * }
 * </pre>
 *
 * @see ChargingStation
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-geo-distance-query.html">Elasticsearch Geo Distance Query</a>
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html">Elasticsearch Java API</a>
 */
@Getter
@Setter
public class NearestChargingStation extends ChargingStation {

    /**
     * The distance from a specified point to this charging station, measured in kilometers.
     * This value is typically calculated using geospatial queries in Elasticsearch.
     */
    private Double distance;
}
