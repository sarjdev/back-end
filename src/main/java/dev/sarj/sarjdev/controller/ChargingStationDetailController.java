package dev.sarj.sarjdev.controller;

import dev.sarj.sarjdev.service.chargingstationdetail.template.ChargingStationDetailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/charging-stations")
public class ChargingStationDetailController {

    private final List<ChargingStationDetailTemplate> chargingStationDetailServices;

    @GetMapping(path = "/{chargingStationId}/detail")
    public ResponseEntity<?> detail(@PathVariable String chargingStationId) {
        ChargingStationDetailTemplate chargingStationDetailService = chargingStationDetailServices.stream()
                .filter(chargingStationDetailServices -> chargingStationDetailServices.isProviderRelated(chargingStationId))
                .findFirst()
                .orElseThrow();

        return ResponseEntity.ok(chargingStationDetailService.getDetail(chargingStationId).orElse(null));
    }
}
