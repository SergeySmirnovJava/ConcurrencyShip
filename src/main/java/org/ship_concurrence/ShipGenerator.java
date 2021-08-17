package org.ship_concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ShipGenerator {
    private Ship ship;
    private final ExecutorService shipGenerator;
    private final int shipsNumber;
    private final Semaphore tunnel = new Semaphore(5);
    public ShipGenerator(int shipsNumber){
        this.shipsNumber = shipsNumber;
        shipGenerator = Executors.newFixedThreadPool(this.shipsNumber);
    }

    public void launchShips() throws InterruptedException {
        for (int i = 0; i < shipsNumber; i++){
            shipGenerator.execute(new Ship(tunnel));
        }
        shipGenerator.shutdown();
        shipGenerator.awaitTermination(1, TimeUnit.MINUTES);

    }

}
