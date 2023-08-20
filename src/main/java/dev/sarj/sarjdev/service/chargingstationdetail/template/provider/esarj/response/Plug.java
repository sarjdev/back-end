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
public class Plug {
    private int no;
    private String code;
    private String type;
    private String mode;
    private String phase;
    private int nominalPower;
    private int volts;
    private int amps;
    private int isAvailable;
    private int isActive;
    private String stateDate;
    private int status;
}