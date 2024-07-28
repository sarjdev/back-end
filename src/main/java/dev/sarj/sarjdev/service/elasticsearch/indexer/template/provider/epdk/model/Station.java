package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.epdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sarj.sarjdev.entity.domain.charging.*;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.elasticsearch.indexer.mapper.IndexDocumentMapper;
import dev.sarj.sarjdev.service.elasticsearch.indexer.model.ChargingIndexDocument;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Station implements IndexDocumentMapper {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("address")
    private String address;

    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lng")
    private double lng;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("reportUrl")
    private String reportUrl;

    @JsonProperty("reservationUrl")
    private String reservationUrl;

    @JsonProperty("operatorid")
    private Long operatorId;

    @JsonProperty("operatortitle")
    private String operatorTitle;

    @JsonProperty("licenceActive")
    private boolean licenceActive;

    @JsonProperty("licenceStatus")
    private int licenceStatus;

    @JsonProperty("stationActive")
    private boolean stationActive;

    @JsonProperty("serviceType")
    private String serviceType;

    @JsonProperty("green")
    private String green;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("cityid")
    private String cityId;

    @JsonProperty("districtid")
    private String districtId;

    @JsonProperty("sockets")
    private List<Socket> sockets;

    @JsonProperty("paymentTypes")
    private List<PaymentType> paymentTypes;

    @Override
    public ChargingIndexDocument toIndexDocument() {

        ChargingLocation chargingLocation = new ChargingLocation();
        chargingLocation.setCityId(Long.valueOf(getCityId()));
        chargingLocation.setDistrictId(Long.valueOf(getDistrictId()));
        chargingLocation.setAddress(getAddress());
        chargingLocation.setLat(getLat());
        chargingLocation.setLon(getLng());

        Operator operator = new Operator();
        operator.setId(getOperatorId());
        operator.setTitle(getOperatorTitle());
        operator.setBrand(getBrand());

        List<Plug> plugs = new ArrayList<>();

        for (Socket socket : getSockets()) {

            if (socket == null) {
                continue;
            }

            Plug plug = new Plug();
            plug.setCount(1);
            plug.setId(socket.getId());
            plug.setPower(socket.getPower());
            plug.setPrice(socket.getPrice());
            plug.setType(PlugType.valueOf(socket.getType()));
            plug.setSocketNumber(socket.getSocketNumber());
            plug.setSubType(PlugSubType.valueOf(socket.getSubType()));

            BigDecimal currentPrice = socket.getPrices()
                    .stream().filter(price -> price.getEndTime() == null)
                    .map(Price::getPrice)
                    .findAny()
                    .orElseGet(socket::getPrice);

            plug.setPrice(currentPrice);

            plugs.add(plug);
        }

        ChargingIndexDocument indexDocument = new ChargingIndexDocument();

        indexDocument.setId(getId());
        indexDocument.setTitle(getTitle());
        indexDocument.setPhone(getPhone());
        indexDocument.setReservationUrl(getReservationUrl());
        indexDocument.setLocation(chargingLocation);
        indexDocument.setGeoLocation(GeoLocation.of(getLat(), getLng()));
        indexDocument.setOperator(operator);
        indexDocument.setPlugs(plugs);
        indexDocument.setPlugsTotal(plugs.size());
        indexDocument.setStationActive(isStationActive());
        indexDocument.setProvider(ChargingProvider.EPDK);
        indexDocument.setPaymentTypes(getPaymentTypes());
        indexDocument.setProvideLiveStats(false);
        indexDocument.buildSearchText();

        return indexDocument;
    }
}
