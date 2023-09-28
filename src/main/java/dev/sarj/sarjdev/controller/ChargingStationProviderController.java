package dev.sarj.sarjdev.controller;

import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/charging-station-providers")
public class ChargingStationProviderController {

    @GetMapping
    public ChargingProvider[] getChargingProviders() {
        return ChargingProvider.values();
    }
}
