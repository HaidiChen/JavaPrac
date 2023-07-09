package javaprac.gof.structural.decorator;


public interface Animation {

    default String act() {
        throw new UnsupportedOperationException("Needs Implementation");
    }
}
