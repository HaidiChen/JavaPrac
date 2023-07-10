package javaprac.gof.behavioral.visitor;


public interface Visitable {

    default void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
