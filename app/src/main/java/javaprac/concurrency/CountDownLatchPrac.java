package javaprac.concurrency;

import java.util.*;
import java.util.concurrent.*;

import javaprac.Prac;


public class CountDownLatchPrac implements Prac {

    @Override
    public void runPrac() {
        int nThreads = 100;
        Runnable task = () -> {
            synchronized(this) {
                long threadId = Thread.currentThread().getId();
                System.out.println(threadId + " is working.");
                System.out.println(threadId + " generated number: " + new Random().nextInt(1000));
            }
        };

        try {
            long executionTime = timeTasks(nThreads, task);
            System.out.format("%d threads finished their tasks in %d milli seconds.\n",
                    nThreads, executionTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Learn concurrency - CountDownLatch usage.";
    }

    private long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        ExecutorService pool = Executors.newFixedThreadPool(nThreads);

        for (int i = 0; i < nThreads; i++) {
            pool.submit(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        long starTime = System.currentTimeMillis();

        System.out.println(nThreads + " threads will start at the same time.");
        startGate.countDown();

        endGate.await();
        long endTime = System.currentTimeMillis();
        pool.shutdown();

        return endTime - starTime;
    }
}
