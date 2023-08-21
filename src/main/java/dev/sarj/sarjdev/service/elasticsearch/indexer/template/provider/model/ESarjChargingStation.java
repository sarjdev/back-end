package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.elasticsearch.indexer.mapper.IndexDocumentMapper;
import dev.sarj.sarjdev.service.elasticsearch.indexer.model.ChargingIndexDocument;
import dev.sarj.sarjdev.service.elasticsearch.indexer.model.ChargingLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
public class ESarjChargingStation implements IndexDocumentMapper {
    @JsonProperty("status")
    private int status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("isInstalled")
    private boolean isInstalled;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("isOnline")
    private boolean isOnline;

    @JsonProperty("warning")
    private String warning;

    @JsonProperty("stationStateDate")
    private String stationStateDate;

    @JsonProperty("plugStateDate")
    private String plugStateDate;

    @JsonProperty("latestStateDate")
    private String latestStateDate;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("floorLevel")
    private String floorLevel;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("email")
    private String email;

    @JsonProperty("isIndoor")
    private boolean isIndoor;

    @JsonProperty("reservable")
    private boolean reservable;

    @JsonProperty("valet")
    private boolean valet;

    @JsonProperty("customIcon")
    private Object customIcon;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("greenE")
    private boolean greenE;

    @JsonProperty("modelCode")
    private String modelCode;

    @JsonProperty("modelDescription")
    private String modelDescription;

    @JsonProperty("modelName")
    private String modelName;

    @JsonProperty("accessTypeId")
    private int accessTypeId;

    @JsonProperty("accessRestriction")
    private String accessRestriction;

    @JsonProperty("payMethods")
    private String payMethods;

    @JsonProperty("emissionPerKWH")
    private double emissionPerKWH;

    @JsonProperty("emissionPerGal")
    private double emissionPerGal;

    @JsonProperty("efficiencyPerKWH")
    private double efficiencyPerKWH;

    @JsonProperty("efficiencyPerGal")
    private double efficiencyPerGal;

    @JsonProperty("updateDate")
    private String updateDate;

    @JsonProperty("updateAction")
    private String updateAction;

    @JsonProperty("insertDate")
    private String insertDate;

    @JsonProperty("storeStationIndex")
    private int storeStationIndex;

    @JsonProperty("storeName")
    private String storeName;

    @JsonProperty("storeAddress")
    private String storeAddress;

    @JsonProperty("storePostalCode")
    private String storePostalCode;

    @JsonProperty("storeCity")
    private String storeCity;

    @JsonProperty("storeState")
    private String storeState;

    @JsonProperty("storeCountryCode")
    private String storeCountryCode;

    @JsonProperty("storeAddressDesc")
    private String storeAddressDesc;

    @JsonProperty("storeTimezone")
    private String storeTimezone;

    @JsonProperty("storeAmenities")
    private String storeAmenities;

    @JsonProperty("storeTotalParking")
    private int storeTotalParking;

    @JsonProperty("storeFreeParkingMinutes")
    private int storeFreeParkingMinutes;

    @JsonProperty("evsesTotal")
    private int evsesTotal;

    @JsonProperty("plugsTotal")
    private int plugsTotal;

    @JsonProperty("plugsAvailable")
    private int plugsAvailable;

    @JsonProperty("plugsOccupied")
    private int plugsOccupied;

    @JsonProperty("plugsUnknown")
    private int plugsUnknown;

    @JsonProperty("hasDC")
    private int hasDC;

    @JsonProperty("plugTypes")
    private String plugTypes;

    @JsonProperty("operatingHours")
    private String operatingHours;

    // Getters and setters for the fields (omitted for brevity)

    public ChargingIndexDocument toIndexDocument() {
        String id = ChargingProvider.ESARJ + "#" + code;
        return ChargingIndexDocument.builder()
                .id(id)
                .city(storeCity)
                .title(storeName)
                .location(ChargingLocation.of(latitude, longitude))
                .address(storeAddress)
                .plugsTotal(plugsTotal)
                .provider(ChargingProvider.ESARJ)
                .pointOfInterests(parsePointOfInterests())
                .build()
                .buildSearchText();
    }

    private List<String> parsePointOfInterests() {
        return Stream.of(storeAmenities.split(","))
                .map(String::trim)
                .toList();
    }

    @Override
    public String toString() {
        return "ESarjChargingStation{" +
                "status=" + status +
                ", code='" + code + '\'' +
                ", isInstalled=" + isInstalled +
                ", isActive=" + isActive +
                ", isOnline=" + isOnline +
                ", warning='" + warning + '\'' +
                ", stationStateDate='" + stationStateDate + '\'' +
                ", plugStateDate='" + plugStateDate + '\'' +
                ", latestStateDate='" + latestStateDate + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", floorLevel='" + floorLevel + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", isIndoor=" + isIndoor +
                ", reservable=" + reservable +
                ", valet=" + valet +
                ", customIcon=" + customIcon +
                ", notes='" + notes + '\'' +
                ", greenE=" + greenE +
                ", modelCode='" + modelCode + '\'' +
                ", modelDescription='" + modelDescription + '\'' +
                ", modelName='" + modelName + '\'' +
                ", accessTypeId=" + accessTypeId +
                ", accessRestriction='" + accessRestriction + '\'' +
                ", payMethods='" + payMethods + '\'' +
                ", emissionPerKWH=" + emissionPerKWH +
                ", emissionPerGal=" + emissionPerGal +
                ", efficiencyPerKWH=" + efficiencyPerKWH +
                ", efficiencyPerGal=" + efficiencyPerGal +
                ", updateDate='" + updateDate + '\'' +
                ", updateAction='" + updateAction + '\'' +
                ", insertDate='" + insertDate + '\'' +
                ", storeStationIndex=" + storeStationIndex +
                ", storeName='" + storeName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", storePostalCode='" + storePostalCode + '\'' +
                ", storeCity='" + storeCity + '\'' +
                ", storeState='" + storeState + '\'' +
                ", storeCountryCode='" + storeCountryCode + '\'' +
                ", storeAddressDesc='" + storeAddressDesc + '\'' +
                ", storeTimezone='" + storeTimezone + '\'' +
                ", storeAmenities='" + storeAmenities + '\'' +
                ", storeTotalParking=" + storeTotalParking +
                ", storeFreeParkingMinutes=" + storeFreeParkingMinutes +
                ", evsesTotal=" + evsesTotal +
                ", plugsTotal=" + plugsTotal +
                ", plugsAvailable=" + plugsAvailable +
                ", plugsOccupied=" + plugsOccupied +
                ", plugsUnknown=" + plugsUnknown +
                ", hasDC=" + hasDC +
                ", plugTypes='" + plugTypes + '\'' +
                ", operatingHours='" + operatingHours + '\'' +
                '}';
    }
}
