package dev.sarj.sarjdev.service.search.response;


import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a suggested charging station along with highlighted text.
 */
@Getter
@Setter
@AllArgsConstructor
public class SuggestedChargingStation {
    /**
     * The highlighted text associated with the suggestion.
     */

    private String highlightedText;
    /**
     * The charging station suggested.
     */
    private ChargingStation chargingStation;

    /**
     * Creates a new SuggestedChargingStation instance using the provided charging station and highlighted text.
     *
     * @param chargingStation The charging station suggested.
     * @param highlightedText The highlighted text associated with the suggestion.
     * @return A new SuggestedChargingStation instance.
     */

    public static SuggestedChargingStation of(ChargingStation chargingStation, String highlightedText) {
        return new SuggestedChargingStation(highlightedText, chargingStation);
    }
}
