package org.concurrency_ship;

import java.sql.SQLOutput;
import java.util.Queue;

public class DockBanana extends Thread{
    private final Queue<Ship> shipQueue;

    public DockBanana(Queue<Ship> queue){
        shipQueue = queue;
        this.setDaemon(true);
    }

    @Override
    public void run() {

            while(true) {
                while (!shipQueue.isEmpty()) {
                    int unloadTime = shipQueue.remove().getShipCapacity().getCapacity();
                    System.out.println("Ship with BANANA will be unload in " + unloadTime * 10/100 + " seconds...");
                    try {
                        Thread.sleep(unloadTime * 10L);
                    } catch (InterruptedException e) {
                        System.out.println("Something went wrong, ship is dead");
                    }
                    System.out.println("Ship with BANANA has been successfully unload");
                }
            }

    }
}
