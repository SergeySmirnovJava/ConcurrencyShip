package org.ship_concurrence;

import javax.print.Doc;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DockManager {

    private ShipCapacity shipCapacity;
    private boolean isDockRunning = false;

    public DockManager(){
        ExecutorService dock = Executors.newFixedThreadPool(3);
        dock.execute(new Dock(ShipTypes.BANANA));
        dock.execute(new Dock(ShipTypes.BREAD));
        dock.execute(new Dock(ShipTypes.CLOTHES));
    }



    public void setShipCapacity(ShipCapacity shipCapacity){

    }

}
