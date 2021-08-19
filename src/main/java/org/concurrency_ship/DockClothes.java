package org.concurrency_ship;

import java.util.Queue;

public class DockClothes extends Thread{
    private final Queue<Ship> shipQueue;

    public DockClothes(Queue<Ship> queue){
        shipQueue = queue;
        this.setDaemon(true);
    }

    @Override
    public void run() {
            while (true) {
                while (!shipQueue.isEmpty()) {
                    int unloadTime = shipQueue.remove().getShipCapacity().getCapacity();
                    System.out.println("Ship with CLOTHES will be unload in " + unloadTime * 10/100 + " seconds...");
                    try {
                        Thread.sleep(unloadTime * 10L);
                    } catch (InterruptedException e) {
                        System.out.println("Something went wrong, ship is dead");
                    }
                    System.out.println("Ship with CLOTHES has been successfully unload");
                }
            }

    }
}
