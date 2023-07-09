package javaprac.gof.creational.factorymethods;


public interface Animal {

    default String speak() {
        throw new UnsupportedOperationException("Needs Implementation. What does your animal say?");
    }
}
