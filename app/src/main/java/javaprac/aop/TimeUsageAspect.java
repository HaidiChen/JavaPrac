package javaprac.aop;

public class TimeUsageAspect implements IAspect {

    private long startTime;

    public void before() {
        startTime = System.nanoTime();
        System.out.println("Task started");
    }

    public void after() {
        long endTime = System.nanoTime();
        System.out.println("Task ended, total time: " + (endTime -startTime) + " nano seconds");
    }
}
