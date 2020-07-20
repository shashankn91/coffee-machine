package com.machine.coffee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machine.coffee.config.Configuration;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Log
public class Driver {

    public static void main(String[] args) {
        try {
            //Test Size
            Integer testSize = 10;
            String content = new String(Driver.class.getResourceAsStream("/config.json").readAllBytes());
            Random rand = new Random();
            ObjectMapper objectMapper = new ObjectMapper();
            Configuration config = objectMapper.readValue(content, Configuration.class);
            /**
             * Load Config to start coffee machine
             */
            CoffeeMachine coffeeMachine = new CoffeeMachine(
                    config.getMachineConfiguration().getOutlets().getOutletCount(),
                    config.getMachineConfiguration().getTotalItemsQuantity(),
                    config.getMachineConfiguration().getBeverages()
            );
            List<String> beverages = config.getMachineConfiguration().getBeverages().keySet().stream().collect(Collectors.toList());
            for (int i = 0; i < testSize ; i++) {
                int index = rand.nextInt(beverages.size());
                String bevName = beverages.get(index);
                coffeeMachine.order(bevName);
            }

            coffeeMachine.switchOff();

        } catch (
                IOException e) {
            e.printStackTrace();
            log.severe("Failed to load config");
        }
    }
}

