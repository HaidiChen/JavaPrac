package javaprac.gof.behavioral.strategy;


public class SimpleCalculator {

    public void applyAlgo(AlgorithmExecutor algoExecutor) {
        algoExecutor.execute();
    }
}
