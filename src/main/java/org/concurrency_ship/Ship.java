package org.concurrency_ship;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

enum ShipCapacity{
    LITTLE(10),
    MIDDLE(50),
    GREAT(100);

    private final int capacity;
    ShipCapacity(int capacity){
        this.capacity = capacity;
    }
    public int getCapacity(){
        return capacity;
    }
}

enum ShipType{
    BREAD,
    BANANAN,
    CLOTHES
}

public class Ship implements Callable<Ship> {
    private ShipType shipType;
    private ShipCapacity shipCapacity;
    private Semaphore tunnel;

    public Ship(Semaphore shipTunnel){
        tunnel = shipTunnel;
        shipCapacity = generateRandomCapacity();
        shipType = generateRandomType();
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public ShipCapacity getShipCapacity() {
        return shipCapacity;
    }

    public void setShipCapacity(ShipCapacity shipCapacity) {
        this.shipCapacity = shipCapacity;
    }

    public ShipCapacity generateRandomCapacity(){
        return ShipCapacity.values()[ThreadLocalRandom.current().nextInt(ShipCapacity.values().length)];
    }

    public ShipType generateRandomType(){
        return ShipType.values()[ThreadLocalRandom.current().nextInt(ShipType.values().length)];
    }

   /* @Override
    public Ship call() throws Exception {
        tunnel.acquire();
        Thread.sleep(100);
        tunnel.release();
        return this;
    }*/


    @Override
    public Ship call() {
        try {
            if (tunnel.hasQueuedThreads()){
                System.out.println("Waiting for free tunnel " + tunnel.getQueueLength());
            }
            tunnel.acquire();
            System.out.println("Ships in tunnel " + tunnel.availablePermits());
            System.out.println(Thread.currentThread().getId() +": " + this.getShipType() + " "
                                                                    + this.getShipCapacity() + " in tunnel");
          //  Thread.sleep(1);
            tunnel.release();
            System.out.println(Thread.currentThread().getId() + " ready for unload");
            return this;
        }
        catch (InterruptedException ie){
            System.out.println("Error");
            return this;
        }
    }
}
