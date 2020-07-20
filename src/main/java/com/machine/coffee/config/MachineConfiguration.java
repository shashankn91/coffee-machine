package com.machine.coffee.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class MachineConfiguration {
    @JsonProperty("beverages")
    private Map<String,Map<String,Integer>> beverages;

    @JsonProperty("total_items_quantity")
    private Map<String,Integer> TotalItemsQuantity;

    @JsonProperty("outlets")
    private OutletsConfiguration outlets;

}
