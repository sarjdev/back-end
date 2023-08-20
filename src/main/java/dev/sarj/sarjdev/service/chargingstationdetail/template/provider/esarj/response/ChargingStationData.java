package dev.sarj.sarjdev.service.chargingstationdetail.template.provider.esarj.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargingStationData {
    private int status;
    private String code;
    private boolean isInstalled;
    private boolean isActive;
    private boolean isOnline;
    private String warning;
    private String stateDate;
    private int evsesTotal;
    private int plugsTotal;
    private int plugsAvailable;
    private int plugsOccupied;
    private int plugsUnknown;
    private String evseIds;
    private LatestStationState latestStationState;
    private List<Plug> plugs;
}