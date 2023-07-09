package javaprac.gof.structural.proxy;

import javaprac.Prac;


public class StaticProxyPrac implements Prac {

    @Override
    public void runPrac() {
        Printer printer = new ProxiedPrinter();
        for (int i = 0; i < 20; i++) {
            printer.start();
        }
    }

    @Override
    public String getDescription() {
        return "Design pattern - [structural] Proxy";
    }
}
