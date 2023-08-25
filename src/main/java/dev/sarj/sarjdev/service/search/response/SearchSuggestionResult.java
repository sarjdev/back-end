package dev.sarj.sarjdev.service.search.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents the result of a search suggestion query, containing total suggestion count and a list of suggested charging stations.
 */
@Getter
@Setter
@AllArgsConstructor
public class SearchSuggestionResult {
    /**
     * The total number of search suggestions.
     */
    private Integer total;

    /**
     * The list of suggested charging stations along with highlighted text.
     */
    private List<SuggestedChargingStation> suggestions;

    /**
     * Creates a new SearchSuggestionResult instance using the provided total count and suggestions list.
     *
     * @param total       The total number of search suggestions.
     * @param suggestions The list of suggested charging stations.
     * @return A new SearchSuggestionResult instance.
     */

    public static SearchSuggestionResult of(Integer total, List<SuggestedChargingStation> suggestions) {
        return new SearchSuggestionResult(total, suggestions);
    }
}
