package org.concurrency_ship;

import java.util.Queue;
import java.util.concurrent.*;

public class ShipGenerator extends Thread{

    private final int numberOfShips;
    private final ExecutorService shipGenerator;
    private final Semaphore tunnel = new Semaphore(5);
    private final Queue<Ship> queueBanana = new ConcurrentLinkedQueue<>();
    private final Queue<Ship> queueBread = new ConcurrentLinkedQueue<>();
    private final Queue<Ship> queueClothes = new ConcurrentLinkedQueue<>();
    private final Queue<Ship> queue = new ArrayBlockingQueue<>(100);

    public ShipGenerator(int numberOfShips){
        this.numberOfShips = numberOfShips;
        shipGenerator = Executors.newFixedThreadPool(this.numberOfShips);
    }

    public void launchShips() throws ExecutionException, InterruptedException {

        System.out.println("Launch ships...");
        this.start();
        System.out.println(this.getId() + " is running");
        new DockBanana(queueBanana).start();
        new DockBread(queueBread).start();
        new DockClothes(queueClothes).start();
        int i = 0;
            while (i < this.numberOfShips) {
                // shipGenerator.execute(new Ship(tunnel));
                Future<Ship> shipFuture;
                shipFuture = shipGenerator.submit(new Ship(tunnel).call());
                i++;
                queue.add(shipFuture.get());
            }

    }

    @Override
    public void run() {
        synchronized (this) {
            while (true){
                while (!queue.isEmpty()) {
                    System.out.println("Starting parse common queue...");
                    Ship testShip = queue.remove();
                    System.out.println(testShip.getShipType());
                    if (testShip.getShipType().toString().equals(ShipType.BREAD.toString())) {
                        System.out.println("Queue ship bread ");
                        queueBread.add(testShip);
                    } else if (testShip.getShipType().toString().equals(ShipType.BANANAN.toString())) {
                        System.out.println("Queue ship banana");
                        queueBanana.add(testShip);
                    } else if (testShip.getShipType().toString().equals(ShipType.CLOTHES.toString())) {
                        System.out.println("Queue ship clothes");
                        queueClothes.add(testShip);
                    } else {
                        System.out.println("Unrecognized error");
                    }
                }
            }
        }
    }


}
