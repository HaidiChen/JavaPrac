package javaprac.interfaces;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import javaprac.Prac;


/*
 * Demonstrates the basic usage of inner classes.
 */
public class InnerClassPrac implements Prac {

    @Override
    public void runPrac() {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();
    }

    @Override
    public String getDescription() {
        return "Practice inner class usage.";
    }
}

class TalkingClock {

    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        ActionListener listener = new TimePrinter();
        Timer timer = new Timer(interval, listener);

        timer.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.stop();
    }

    private class TimePrinter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("At the tone, the time is " + new Date());
            if (beep) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
