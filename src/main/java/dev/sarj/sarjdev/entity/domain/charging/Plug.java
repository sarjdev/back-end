package dev.sarj.sarjdev.entity.domain.charging;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Plug implements Serializable {
    private PlugType type;
    private int count;
    private String power;
}
