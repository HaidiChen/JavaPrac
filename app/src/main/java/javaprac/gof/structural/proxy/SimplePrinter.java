package javaprac.gof.structural.proxy;


public class SimplePrinter implements Printer {

    @Override
    public void start() {
        System.out.println("I'm printing some stuff.");
    }
}
