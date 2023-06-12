package javaprac;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface Prac {

    static Path getCwd() {
        return Paths.get("").toAbsolutePath();
    }

    default void run() {
        System.out.println("\n===Description Start===");
        System.out.println("    " + getDescription());
        System.out.println("===Description End===\n");

        runPrac();
    }

    String getDescription();

    void runPrac();

}
