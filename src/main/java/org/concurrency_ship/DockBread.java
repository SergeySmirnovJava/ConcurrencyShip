package org.concurrency_ship;

import java.util.Queue;

public class DockBread extends Thread{
    private final Queue<Ship> shipQueue;

    public DockBread(Queue<Ship> shipQueue) {
        this.shipQueue = shipQueue;
        this.setDaemon(true);
    }

    @Override
    public void run() {
            while(true) {
                while (!shipQueue.isEmpty()) {
                    int unloadTime = shipQueue.remove().getShipCapacity().getCapacity();
                    System.out.println("Ship with BREAD will be unload in " + unloadTime * 10/100 + " seconds...");
                    try {
                        Thread.sleep(unloadTime * 10L);
                    } catch (InterruptedException e) {
                        System.out.println("Something went wrong, ship is dead");
                    }
                    System.out.println("Ship with BREAD has been successfully unload");
                }
            }
    }
}
