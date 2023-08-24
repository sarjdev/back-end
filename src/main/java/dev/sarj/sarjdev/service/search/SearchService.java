package dev.sarj.sarjdev.service.search;

import dev.sarj.sarjdev.service.search.response.NearestSearchResult;
import dev.sarj.sarjdev.service.search.response.SearchResult;

public interface SearchService {

    /**
     * Performs a basic search for all charging stations and returns the search result.
     *
     * @return A search result containing a list of all charging stations.
     */
    SearchResult search();

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
}
