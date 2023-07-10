package javaprac.gof.behavioral.iterator;


public interface CusIterable {

    default CusIterator getCusIterator() {
        throw new UnsupportedOperationException("Needs Implementation.");
    }
}
