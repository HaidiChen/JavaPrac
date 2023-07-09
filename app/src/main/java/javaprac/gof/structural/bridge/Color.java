package javaprac.gof.structural.bridge;


public interface Color {

    default String code() {
        throw new UnsupportedOperationException("Needs implementation");
    }
}
