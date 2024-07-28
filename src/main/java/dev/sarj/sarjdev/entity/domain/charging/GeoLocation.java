package dev.sarj.sarjdev.entity.domain.charging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocation {

    /**
     * The latitude coordinate of the charging location.
     */
    private double lat;

    /**
     * The longitude coordinate of the charging location.
     */
    private double lon;

    public static GeoLocation of(double lat, double lon) {
        return new GeoLocation(lat, lon);
    }
}
