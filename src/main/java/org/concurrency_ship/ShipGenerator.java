package org.concurrency_ship;

import java.util.Queue;
import java.util.concurrent.*;

public class ShipGenerator{

    private final int numberOfShips;
    private final ExecutorService shipGenerator;
    private final Semaphore tunnel = new Semaphore(1);
    private final Queue<Ship> queueBanana = new ConcurrentLinkedQueue<>();
    private final Queue<Ship> queueBread = new ConcurrentLinkedQueue<>();
    private final Queue<Ship> queueClothes = new ConcurrentLinkedQueue<>();
    private final QueueManager queueManager = new QueueManager(queueBread, queueBanana, queueClothes);

    public ShipGenerator(int numberOfShips){
        this.numberOfShips = numberOfShips;
        shipGenerator = Executors.newFixedThreadPool(this.numberOfShips);
    }

    public void launchShips() throws ExecutionException, InterruptedException {

        System.out.println("Launch ships...");
        new DockBanana(queueBanana).start();
        new DockBread(queueBread).start();
        new DockClothes(queueClothes).start();
        int i = 0;
            while (i < this.numberOfShips) {
                // shipGenerator.execute(new Ship(tunnel));
                Future<Ship> shipFuture;
                shipFuture = shipGenerator.submit(new Ship(tunnel).call());
                queueManager.pushQueue(shipFuture.get());
                i++;
            }
            //shipGenerator.shutdown();
    }
}
