package com.machine.coffee.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OutletsConfiguration {
    @JsonProperty("count_n")
    private Integer outletCount;

}
