package javaprac.gof.behavioral.command;


public interface Command {

    default void execute() {
        throw new UnsupportedOperationException("Needs Implementation.");
    }
}
