package javaprac;


public interface Prac {

    default void run() {
        System.out.println("\n===Description Start===");
        System.out.println("    " + getDescription());
        System.out.println("===Description End===\n");

        runPrac();
    }

    String getDescription();

    void runPrac();

}
