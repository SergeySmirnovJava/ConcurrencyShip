package org.concurrency_ship;

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

public class Ship implements Runnable {
    private ShipType shipType;
    private ShipCapacity shipCapacity;
    private Semaphore tunnel;

    public Ship(Semaphore shipTunnel){
        tunnel = shipTunnel;
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


    @Override
    public void run() {
        try {
            tunnel.acquire();
            Thread.sleep(100);
            tunnel.release();
        }
        catch (InterruptedException ie){
            System.out.println("Error");
        }
    }
}
