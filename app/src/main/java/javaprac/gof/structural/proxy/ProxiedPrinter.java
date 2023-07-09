package javaprac.gof.structural.proxy;

import java.util.*;


public class ProxiedPrinter implements Printer {

    private Printer printer;

    @Override
    public void start() {
        int num = new Random().nextInt(14) % 2;

        if (num % 2 == 0) {
            if (printer == null) {
                printer = new SimplePrinter();
            }
            printer.start();
        } else {
            System.out.println("permission denied.");
        }
    }
}
