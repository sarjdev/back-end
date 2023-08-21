package dev.sarj.sarjdev.service.elasticsearch.indexer.template.provider.aksaenerji.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Devices {
    @JsonProperty("guc")
    public String[] power;

    @JsonProperty("durum")
    public int[] status;

    @JsonProperty("erisim")
    public int[] accessibility;
}