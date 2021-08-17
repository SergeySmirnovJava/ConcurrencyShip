import java.util.concurrent.locks.Lock;

public class OrderPrint  implements  Runnable{



    @Override
    public void run() {

    }
}

class DiningPhilosophers {

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        pickLeftFork.run();
        Thread thread = new Thread(pickLeftFork);
        thread.getId();
        if (thread.isAlive()){}

    }
}