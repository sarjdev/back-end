package dev.sarj.sarjdev.service.chargingstationdetail.template.provider.zes;

import dev.sarj.sarjdev.entity.domain.charging.ChargingStation;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.chargingstationdetail.template.ChargingStationDetailTemplate;
import dev.sarj.sarjdev.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZESChargingStationDetailService extends ChargingStationDetailTemplate<ChargingStation> {

    private final SearchService service;

    @Override
    protected ChargingProvider getChargingProvider() {
        return ChargingProvider.ZES;
    }

    @Override
    protected ChargingStation getChargingStationStatus(String chargingStationId) {
        String documentId = ChargingProvider.ZES + "#" + chargingStationId;
        return service.getById(documentId);
    }

}
