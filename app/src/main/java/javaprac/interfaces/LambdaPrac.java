package javaprac.interfaces;

import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import javaprac.Prac;


/*
 * Demonstrates the basic usage of Java Lambda.
 */
public class LambdaPrac implements Prac {

    @Override
    public void runPrac() {
        String[] planets = {
            "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"
        };

        System.out.println("Original list:");
        System.out.println(Arrays.toString(planets));
        System.out.println("After sorting by dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));

        System.out.println("After sorting by length:");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        System.out.println(Arrays.toString(planets));

        Timer timer = new Timer(1000, event -> System.out.println("The time is " + new Date()));
        timer.start();

        // Run this method for 3 sec.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
         * Need this to stop the timer, otherwise the program wouldn't exit
         * if the time interval set in the Timer obj is <= 1 sec.
         */
        timer.stop();
    }

    @Override
    public String getDescription() {
        return "Practice Lambda usage.";
    }
}
