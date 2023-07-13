package javaprac.concurrency;

import java.util.*;

import javaprac.Prac;


public class VisibilityPrac implements Prac {

    private static int number;
    private static boolean ready;

    @Override
    public void runPrac() {
        new Thread(() -> {
            while (!ready) {
                System.out.println("not ready");
                Thread.yield();
            }
            System.out.println(number);
        }).start();

        new Thread(() -> {
            ready = true;
            number = 33 * new Random().nextInt(20);
            //number = 33 * 23;
        }).start();
    }

    @Override
    public String getDescription() {
        return "Learn concurrency - visibility concerns";
    }
}
