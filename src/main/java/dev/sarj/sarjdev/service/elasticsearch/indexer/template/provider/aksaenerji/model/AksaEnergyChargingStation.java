package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.aksaenerji.model;

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
public class AksaEnergyChargingStation implements IndexDocumentMapper {
    @JsonProperty("id")
    public int id;

    @JsonProperty("istasyonNoktasi")
    public String stationPoint;

    @JsonProperty("cihazSayisi")
    public int deviceCount;

    @JsonProperty("acikAdres")
    public String openAddress;

    @JsonProperty("erisimTipi")
    public String accessibilityType;

    @JsonProperty("il")
    public String city;

    @JsonProperty("ilce")
    public String district;

    @JsonProperty("koordinat")
    public double[] coordinates;

    @JsonProperty("cihazlar")
    public Devices devices;

    @JsonProperty("index")
    public int index;

    @JsonProperty("renk")
    public String color;

    @Override
    public ChargingIndexDocument toIndexDocument() {
        String id = ChargingProvider.AKSAENERGY + "#" + this.id;
        return ChargingIndexDocument.builder()
                .id(id)
                .city(city)
                .title(stationPoint)
                .location(ChargingLocation.of(coordinates[0], coordinates[1]))
                .address(openAddress)
                .plugsTotal(deviceCount)
                .provider(ChargingProvider.AKSAENERGY)
                .pointOfInterests(List.of())
                .provideLiveStats(false)
                .build()
                .buildSearchText();
    }
}
