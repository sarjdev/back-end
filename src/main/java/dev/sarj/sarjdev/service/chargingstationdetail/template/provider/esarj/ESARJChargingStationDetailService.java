package dev.sarj.sarjdev.service.chargingstationdetail.template.provider.esarj;

import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.chargingstationdetail.template.ChargingStationDetailTemplate;
import dev.sarj.sarjdev.service.search.SearchService;
import org.springframework.stereotype.Component;

@Component
public class ESARJChargingStationDetailService extends ChargingStationDetailTemplate {
    public ESARJChargingStationDetailService(SearchService service) {
        super(service);
    }

    @Override
    protected ChargingProvider getChargingProvider() {
        return ChargingProvider.ESARJ;
    }
}
