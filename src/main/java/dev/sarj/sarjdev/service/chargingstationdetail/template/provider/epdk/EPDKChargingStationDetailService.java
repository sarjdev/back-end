package dev.sarj.sarjdev.service.chargingstationdetail.template.provider.epdk;

import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.chargingstationdetail.template.ChargingStationDetailTemplate;
import dev.sarj.sarjdev.service.search.SearchService;
import org.springframework.stereotype.Component;

@Component
public class EPDKChargingStationDetailService extends ChargingStationDetailTemplate {
    public EPDKChargingStationDetailService(SearchService service) {
        super(service);
    }

    @Override
    protected ChargingProvider getChargingProvider() {
        return ChargingProvider.EPDK;
    }

    @Override
    protected boolean requireProviderRelated() {
        return false;
    }

    @Override
    protected String resolveProviderChargingStationId(String chargingStationId) {
        return chargingStationId;
    }
}
