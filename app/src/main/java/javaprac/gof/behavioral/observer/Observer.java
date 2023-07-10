package javaprac.gof.behavioral.observer;


public interface Observer {

    default void update() {
        throw new UnsupportedOperationException("Needs Implementation.");
    }
}
