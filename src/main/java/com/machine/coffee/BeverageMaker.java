package com.machine.coffee;

import lombok.extern.java.Log;

@Log
public class BeverageMaker implements Runnable{
    private final String beverageName;

    public BeverageMaker(String beverageName) {
        this.beverageName = beverageName;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
            System.out.println(String.format("%s is prepared ",beverageName));
        } catch (InterruptedException e) {
            log.severe("Thread Failure");
            e.printStackTrace();
        }

    }
}
