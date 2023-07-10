package javaprac.gof.behavioral.strategy;


public class SecondAlgorithmExecutor implements AlgorithmExecutor {

    private final String name;

    public SecondAlgorithmExecutor(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("Executing the second algorithm : " + name);
    }
}
