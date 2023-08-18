package dev.sarj.sarjdev.service.elasticsearch.indexer.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a charging location with latitude and longitude coordinates.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChargingLocation {
    /**
     * The latitude coordinate of the charging location.
     */
    private double lat;
    /**
     * The longitude coordinate of the charging location.
     */
    private double lon;

    /**
     * Creates a new ChargingLocation instance with the given latitude and longitude coordinates.
     *
     * @param lat The latitude coordinate.
     * @param lon The longitude coordinate.
     * @return A new ChargingLocation instance with the specified coordinates.
     */

    public static ChargingLocation of(double lat, double lon) {
        return new ChargingLocation(lat, lon);
    }
}
