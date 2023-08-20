package dev.sarj.sarjdev.service.chargingstationdetail.template.provider.esarj.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargingStationResponse {
    private String key;
    private String description;
    private int status;
    private ChargingStationData data;

}