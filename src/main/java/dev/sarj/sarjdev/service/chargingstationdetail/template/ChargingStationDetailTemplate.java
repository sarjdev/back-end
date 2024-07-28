package dev.sarj.sarjdev.service.chargingstationdetail.template;

import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

/**
 * An abstract class representing a template for retrieving charging station details.
 * This class provides methods to fetch details about charging stations based on their unique identifiers.
 *
 * @param <T> The type representing the charging station status or details.
 */
@Slf4j
@RequiredArgsConstructor
public abstract class ChargingStationDetailTemplate {

    // Mapping of charging provider prefixes to ChargingProvider enum values
    private final Map<String, ChargingProvider> chargingStationMap = Map.of("EPDK", ChargingProvider.EPDK);

    private final SearchService service;

    /**
     * Retrieves the details of a charging station based on its unique identifier.
     *
     * @param documentId The unique identifier of the charging station.
     * @return An Optional containing the charging station details, or empty if not found.
     */
    public Optional<ChargingStation> getDetail(String documentId) {

        isProviderRelated(documentId);

        String chargingStationId = resolveProviderChargingStationId(documentId);

        try {
            ChargingStation chargingStationStatus = getChargingStationStatus(documentId);
            return Optional.ofNullable(chargingStationStatus);

        } catch (Exception ex) {
            log.error("There is an error while accessing charging station detail. provider: {}, charging station id: {}", getChargingProvider(), chargingStationId);
            return Optional.empty();
        }
    }

    /**
     * Retrieves the ChargingProvider associated with the implementation.
     *
     * @return The ChargingProvider enum representing the associated charging provider.
     */
    protected abstract ChargingProvider getChargingProvider();


    /**
     * Retrieves the status or details of a charging station based on its identifier.
     *
     * @param chargingStationId The identifier of the charging station.
     * @return The charging station status or details.
     */
    protected ChargingStation getChargingStationStatus(String chargingStationId) {
        return service.getById(chargingStationId);
    }

    /**
     * Checks if the provided charging station identifier is related to the provider of the implementation.
     *
     * @param chargingStationId The charging station identifier.
     * @return True if the identifier is related to the provider, false otherwise.
     * @throws IllegalArgumentException if the charging provider is not found in the map.
     */
    public boolean isProviderRelated(String chargingStationId) {
        if (requireProviderRelated()) {
            String chargingProviderPrefix = resolveChargingProviderPrefix(chargingStationId);

            ChargingProvider chargingProvider = Optional.ofNullable(chargingStationMap.get(chargingProviderPrefix))
                    .orElseThrow(() -> new IllegalArgumentException("There is no any provider in current service, prefix: " + chargingProviderPrefix));

            return getChargingProvider() == chargingProvider;
        }

        return true;
    }

    protected abstract boolean requireProviderRelated();

    /**
     * Resolves the charging station identifier after removing the provider prefix.
     *
     * @param chargingStationId The charging station identifier.
     * @return The resolved charging station identifier without the provider prefix.
     */
    protected String resolveProviderChargingStationId(String chargingStationId) {
        return chargingStationId.split("#")[1];
    }

    /**
     * Resolves the charging provider prefix from the charging station identifier.
     *
     * @param chargingStationId The charging station identifier.
     * @return The charging provider prefix.
     */
    protected String resolveChargingProviderPrefix(String chargingStationId) {
        return chargingStationId.split("#")[0];
    }

}
