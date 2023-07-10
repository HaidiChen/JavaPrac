package javaprac.gof.behavioral.strategy;


public interface AlgorithmExecutor {

    default void execute() {
        throw new UnsupportedOperationException("Needs Implementation.");
    }
}
