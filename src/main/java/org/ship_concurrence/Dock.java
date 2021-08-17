package org.ship_concurrence;

public class Dock extends Thread{
    private ShipTypes dockTypes;
    private int timeUnload;
    private ShipTypes currentShip;

    public Dock(ShipTypes dockTypes){
         this.dockTypes = dockTypes;
    }


    public void getShipFromTunnel(Ship ship){
      //  System.out.println("Entered ship");
        synchronized (this) {
            timeUnload = ship.getShipCapacity().getCapacity() * 10;
            currentShip = ship.getShipType();
            notifyAll();
            System.out.println(currentShip);
            System.out.println(timeUnload);
        }

    }

    @Override
    public void run() {
        synchronized (this) {
            while (!this.dockTypes.equals(currentShip)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    sleep(timeUnload);
                    System.out.println("Bread has been unloaded");
                    notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
