package org.ship_concurrence;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;

enum ShipTypes{
    BREAD,
    BANANA,
    CLOTHES
}

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
public class Ship extends Thread{
    private ShipTypes shipType;
    private ShipCapacity shipCapacity;
    private Semaphore channel;

    //private DockManager dock = new DockManager();
    private Dock docker = new Dock(ShipTypes.BANANA);
    public Ship(Semaphore channel){
        this.shipType = generateShipType();
        this.shipCapacity = generateShipCapacity();
        this.channel = channel;
        docker.start();
    }

    public ShipTypes getShipType() {
        return shipType;
    }

    public void setShipType(ShipTypes shipType) {
        this.shipType = shipType;
    }

    public ShipCapacity getShipCapacity() {
        return shipCapacity;
    }

    public void setShipCapacity(ShipCapacity shipCapacity) {
    }

    public ShipCapacity generateShipCapacity(){
        return ShipCapacity.values()[ThreadLocalRandom.current().nextInt(ShipCapacity.values().length)];
    }

    public ShipTypes generateShipType(){
        return ShipTypes.values()[ThreadLocalRandom.current().nextInt(ShipTypes.values().length)];
    }

    @Override
    public void run() {
        try {

          //  this.channel.acquire();
           // System.out.println(this.channel.getQueueLength() + " are waiting");
            System.out.println(this.getName() + " enter to channel with the type: " + getShipType() + " and capacity: " + getShipCapacity());
            sleep(100);
        //    docker.getShipFromTunnel(this);
           // System.out.println(this.getName() + " ready to release");
          //  this.channel.release();
         //   this.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
