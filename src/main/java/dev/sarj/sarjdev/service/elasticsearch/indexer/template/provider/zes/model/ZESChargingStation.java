package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.zes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.elasticsearch.indexer.mapper.IndexDocumentMapper;
import dev.sarj.sarjdev.service.elasticsearch.indexer.model.ChargingIndexDocument;
import dev.sarj.sarjdev.service.elasticsearch.indexer.model.ChargingLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZESChargingStation implements IndexDocumentMapper {
    @JsonProperty("tc")
    private String providerPrefix;

    @JsonProperty("t")
    private String title;

    @JsonProperty("ad")
    private String address;

    @JsonProperty("sc")
    private String stationCode;

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lng")
    private double longitude;

    @JsonProperty("op")
    private boolean onlinePayment;

    @JsonProperty("or")
    private boolean onlineReservation;

    @JsonProperty("add")
    private String addressDirection;

    @JsonProperty("uh")
    private String usageHours;

    @JsonProperty("acc")
    private int acCount;

    @JsonProperty("acp")
    private String acPower;

    @JsonProperty("dcc")
    private int dcCount;

    @JsonProperty("dcp")
    private String dcPower;

    @Override
    public ChargingIndexDocument toIndexDocument() {
        String id = ChargingProvider.ZES + "#" + stationCode.split("\\|")[1];
        return ChargingIndexDocument.builder()
                .id(id)
                .city(null)
                .title(title)
                .location(ChargingLocation.of(latitude, longitude))
                .address(address)
                .plugsTotal(dcCount + acCount)
                .provider(ChargingProvider.ZES)
                .pointOfInterests(List.of())
                .provideLiveStats(false)
                .build()
                .buildSearchText();
    }
}
