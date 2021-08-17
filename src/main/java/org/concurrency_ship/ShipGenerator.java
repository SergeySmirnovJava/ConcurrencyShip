package org.concurrency_ship;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ShipGenerator {
    private Ship ship;
    private int numberOfShips;
    private final ExecutorService shipGenerator;
    private Semaphore tunnel;

    public ShipGenerator(int numberOfShips){
        this.numberOfShips = numberOfShips;
        shipGenerator = Executors.newFixedThreadPool(this.numberOfShips);
    }

    public void launchShips(){
        for(int i = 0; i < this.numberOfShips; i++){
            shipGenerator.execute(new Ship(tunnel));
        }
    }
}
