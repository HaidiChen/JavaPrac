package javaprac.gof.creational.singleton;

import java.util.concurrent.*;

import javaprac.Prac;


public class SingletonPrac implements Prac {

    @Override
    public void runPrac() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        Runnable task = () -> {
            Captain captain = Captain.create();
            System.out.println(Thread.currentThread() + ": " + captain);
        };

        for (int i = 0; i < 20; i++) {
            fixedThreadPool.submit(task);
        }

        fixedThreadPool.shutdown();
    }

    @Override
    public String getDescription() {
        return "Design pattern - [creational] Singleton";
    }
}
