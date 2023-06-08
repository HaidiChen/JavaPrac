package javaprac.interfaces;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import javaprac.Prac;


public class CallBackPrac implements Prac {

    @Override
    public void runPrac() {
        ActionListener listener = new TimePrinter();

        /*
         * Constructs a timer that calls the listener once every 2 sec.
         * Note: Timer will start a new thread to do its job.
         */
        Timer timer = new Timer(2000, listener);
        timer.start();

        // Uncomment below to use GUI to block the current thread execution.
        //JOptionPane.showMessageDialog(null, "Quit Program?");

        // Run this method for 6 sec.
        try {
            Thread.sleep(6000);
        } catch (Exception e) {
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
        return "Practice callback usage with interface.";
    }
}

class TimePrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("At the tone, the time is " + new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}
