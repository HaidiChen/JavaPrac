package javaprac.gof.behavioral.mediator;


public interface Mediator {

    default void sendBy(Friend friend, String msg) {
        throw new UnsupportedOperationException("Needs Implementation.");
    }
}
