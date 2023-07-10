package javaprac.gof.behavioral.strategy;

import javaprac.Prac;


public class StrategyPrac implements Prac {

    @Override
    public void runPrac() {
        SimpleCalculator calculator = new SimpleCalculator();
        AlgorithmExecutor firstAlgoExecutor = new FirstAlgorithmExecutor();
        AlgorithmExecutor secondAlgoExecutor = new SecondAlgorithmExecutor("I have a name");

        calculator.applyAlgo(firstAlgoExecutor);
        calculator.applyAlgo(secondAlgoExecutor);
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [behavioral] Strategy";
    }
}
