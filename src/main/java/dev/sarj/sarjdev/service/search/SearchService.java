package dev.sarj.sarjdev.service.search;

import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import dev.sarj.sarjdev.service.search.response.NearestSearchResult;
import dev.sarj.sarjdev.service.search.response.SearchResult;
import dev.sarj.sarjdev.service.search.response.SearchSuggestionResult;

public interface SearchService {

    /**
     * Performs a basic search for all charging stations and returns the search result.
     *
     * @return A search result containing a list of all charging stations.
     */
    SearchResult search();


    /**
     * Performs a basic search for all charging stations and returns the search result with cached.
     *
     * @return A search result containing a list of all charging stations.
     */
    byte[] compressedSearch();

    /**
     * Performs a basic search for all charging stations and returns the search result.
     *
     * @return A search result containing a list of all charging stations.
     */
    byte[] doCompressedSearch();

    /**
     * Searches for the nearest charging stations based on provided coordinates and distance criteria.
     *
     * @param latitude  The latitude coordinate for the search center.
     * @param longitude The longitude coordinate for the search center.
     * @param distance  The maximum distance within which to search for charging stations.
     * @param size      The maximum number of charging stations to return.
     * @return A nearest search result containing the list of nearest charging stations.
     */
    NearestSearchResult nearest(Double latitude, Double longitude, Integer distance, Integer size);

    /**
     * Provides search suggestions based on the given query and size.
     *
     * @param q    The query string for which suggestions are requested.
     * @param size The maximum number of suggestions to be returned.
     * @return A SearchSuggestionResult containing the search suggestions.
     */
    SearchSuggestionResult suggest(String q, Integer size);

    /**
     * Retrieves a ChargingStation document by its ID.
     *
     * @param id The unique ID of the ChargingStation document.
     * @return The retrieved ChargingStation document, or null if not found.
     */
    ChargingStation getById(String id);
}
