package javaprac.concurrency;

import java.util.concurrent.*;

import javaprac.Prac;


public class JmmPrac implements Prac {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    @Override
    public void runPrac() {
        possibleReordering();
    }

    @Override
    public String getDescription() {
        return "Learn concurrency - Java Memory Model";
    }

    /**
     * Possible Reordering of instructions.
     *
     * If JVM reordered the instructions in task1, which runs the `x = b` first and then switches to
     * task2, the final result could be (0, 0) instead of (0, 1), (1, 0) or (1, 1).
     */
    private void possibleReordering() {
        CyclicBarrier barrier = new CyclicBarrier(3);

        Runnable task1 = () -> {
            try {
                barrier.await();
                a = 1;
                x = b;
            } catch (Exception e) {}
        };

        Runnable task2 = () -> {
            try {
                barrier.await();
                b = 1;
                y = a;
            } catch (Exception e) {}
        };

        try {
            Thread first = new Thread(task1);
            Thread second = new Thread(task2);

            first.start();
            second.start();

            barrier.await();

            first.join();
            second.join();

            System.out.format("(%d, %d)\n", x, y);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
