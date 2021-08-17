package org.ship_concurrence;

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private int n;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();
    private int arr = 0;
    private int i = 0;
    public Main(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(int printNumber) throws InterruptedException {
        lock.lock();
        for(i = 0; i < n; i++){
            System.out.println(0);
            arr = i;
            cond.signalAll();
        }
        lock.unlock();
    }

    //четное
    public void even(int printNumber) throws InterruptedException {
        lock.lock();
        while(arr % 2 != 0 && arr > 1)
            cond.await();
        printNumber.accept(arr);
        //cond.signalAll();
        lock.unlock();
    }

    //нечетное
    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        while(arr % 2 != 1)
            cond.await();
        printNumber.accept(arr);
        //cond.signalAll();
        lock.unlock();
    }
    public static void main(String[] args) throws InterruptedException {
       // ShipGenerator shipGenerator = new ShipGenerator(100);
       // shipGenerator.launchShips();
        int n = 10;
        n /=2;
        System.out.println("end");
    }
}
