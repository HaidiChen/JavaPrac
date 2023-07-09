package javaprac.gof.structural.proxy;


public interface Printer {

    default void start() {
        throw new UnsupportedOperationException("Needs implementation");
    }
}
