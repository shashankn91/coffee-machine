package com.machine.coffee;

import lombok.extern.java.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log
public class CoffeeMachine {
    private final Integer numberOfOutlets;
    private final ConcurrentHashMap<String,Integer> currentState;
    private final Map<String,Map<String,Integer>> beverages;
    private ExecutorService pool;


    public CoffeeMachine(Integer numberOfOutlets, Map<String, Integer> totalItemsQuantity, Map<String, Map<String, Integer>> beverages) {
        this.numberOfOutlets = numberOfOutlets;
        this.beverages = beverages;
        this.currentState = new ConcurrentHashMap<>();
        this.currentState.putAll(totalItemsQuantity);
        this.pool = Executors.newFixedThreadPool(numberOfOutlets);
    }

    /**
     * Check if current stae has enough ingredients to prepare beverage.
     * If it has reduce v and start thread from pool to make beverage.
     * @param beverageName
     */
    public void order(String beverageName){
        if(!beverages.containsKey(beverageName)){
            return;
        }
        Map<String,Integer> ingredients = beverages.get(beverageName);
        for (String ingredient: ingredients.keySet()) {
            int quantityRequired = ingredients.get(ingredient);
            if(!currentState.containsKey(ingredient)){
                System.out.println(String.format("%s cannot be prepared because %s is not available",beverageName,ingredient));
                return;

            }
            int currentQuantity = currentState.getOrDefault(ingredient,0);
            if(currentQuantity < quantityRequired){
                System.out.println(String.format("%s cannot be prepared because %s is 0",beverageName,ingredient));
                return;
            }
        }

        for (String ingredient: ingredients.keySet()) {
            int quantityRequired = ingredients.get(ingredient);
            currentState.put(ingredient,currentState.get(ingredient) - quantityRequired);
        }
        BeverageMaker beverageMaker = new BeverageMaker(beverageName);
        pool.execute(beverageMaker);
        return;

    }

    public void switchOff(){
        this.pool.shutdown();
    }
}
