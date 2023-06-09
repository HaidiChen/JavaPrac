package javaprac.interfaces;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import javaprac.Prac;


/*
 * Demonstrates the basic usage of anonymous inner classes.
 * This is how programmers do callbacks before Java Lambda exists.
 * But now it's recommended to use Lambda instead.
 */
public class AnonymousInnerClassPrac implements Prac {

    @Override
    public void runPrac() {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();
    }

    @Override
    public String getDescription() {
        return "Practice anonymous inner class usage.";
    }

    private class TalkingClock {

        private int interval;
        private boolean beep;

        public TalkingClock(int interval, boolean beep) {
            this.interval = interval;
            this.beep = beep;
        }

        public void start() {
            Timer timer = new Timer(interval, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("At the tone, the time is " + new Date());
                    if (beep) {
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            });

            timer.start();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timer.stop();
        }

    }
}
