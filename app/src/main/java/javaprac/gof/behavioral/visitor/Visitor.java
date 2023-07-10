package javaprac.gof.behavioral.visitor;


public interface Visitor {

    default void visit(Visitable visitable) {
        throw new UnsupportedOperationException("Needs Implementation.");
    }
}
