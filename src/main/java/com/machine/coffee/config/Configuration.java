package com.machine.coffee.config;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Configuration {
    @JsonProperty("machine")
    private MachineConfiguration machineConfiguration;
}
