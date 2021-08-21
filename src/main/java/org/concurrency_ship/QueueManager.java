package org.concurrency_ship;

import java.util.Queue;

public class QueueManager {
    private Queue<Ship> queueBread;
    private Queue<Ship> queueClothes;
    private Queue<Ship> queueBanana;


    public QueueManager(Queue<Ship> queueBread, Queue<Ship> queueBanana, Queue<Ship> queueClothes){
        this.queueBread = queueBread;
        this.queueBanana = queueBanana;
        this.queueClothes = queueClothes;
    }

    public void pushQueue(Ship ship){
        ShipType tempShipType = ship.getShipType();

        switch (tempShipType){
            case BREAD:
                queueBread.add(ship);
                break;
            case BANANAN:
                queueBanana.add(ship);
                break;
            case CLOTHES:
                queueClothes.add(ship);
                break;
        }
    }
}
