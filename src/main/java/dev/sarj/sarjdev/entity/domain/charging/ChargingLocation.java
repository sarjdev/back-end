package dev.sarj.sarjdev.entity.domain.charging;


import lombok.*;

import java.io.Serializable;

/**
 * Represents a charging location with latitude and longitude coordinates.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargingLocation implements Serializable {
    /**
     * The unique identifier of the city.
     */
    private Long cityId;

    /**
     * The name of the city.
     */
    private String cityName;

    /**
     * The unique identifier of the district.
     */
    private Long districtId;

    /**
     * The name of the district.
     */
    private String districtName;

    /**
     * The address associated with the location.
     */
    private String address;

    /**
     * The latitude coordinate of the charging location.
     */
    private double lat;

    /**
     * The longitude coordinate of the charging location.
     */
    private double lon;
}
