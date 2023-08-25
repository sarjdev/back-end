package dev.sarj.sarjdev.common;

public class Constant {
    public static class Elastic {
        public static class Query {
            public static final String NEAREST_CHARGING_STATIONS_QUERY_PATH = "elasticsearch/queries/nearest-charging-stations.json";
            public static final String SUGGEST_CHARGING_STATIONS_QUERY_PATH = "elasticsearch/queries/suggest-charging-stations.json";
        }

        public static final String ES_INDEX_ALIAS_NAME = "chargingstations";
        public static final String ES_INDEX_SETTINGS_RESOURCE_PATH = "elasticsearch/settings/chargingstations.settings.json";
        public static final String ES_INDEX_MAPPINGS_RESOURCE_PATH = "elasticsearch/settings/chargingstations.mappings.json";
    }
}
