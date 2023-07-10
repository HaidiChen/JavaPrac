package javaprac.gof.behavioral.iterator;


public interface CusIterator {

    default void first() {
        throw new UnsupportedOperationException("Needs Implementation.");
    }

    default void next() {
        throw new UnsupportedOperationException("Needs Implementation.");
    }

    default boolean isDone() {
        throw new UnsupportedOperationException("Needs Implementation.");
    }

    default String currentItem() {
        throw new UnsupportedOperationException("Needs Implementation.");
    }
}
