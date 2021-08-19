package org.concurrency_ship;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner in = new Scanner(System.in);

        ShipGenerator shipGenerator = new ShipGenerator(in.nextInt());
        shipGenerator.launchShips();
    }
}
