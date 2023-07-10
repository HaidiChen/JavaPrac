package javaprac.gof.behavioral.chainofresponsibility;


public interface Receiver {

    default boolean processMessage(String msg) {
        throw new UnsupportedOperationException("Needs Implementation.");
    }
}
