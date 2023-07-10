package javaprac.gof.behavioral.command;


public class Invoker {

    public void executeCommand(Command command) {
        command.execute();
    }
}
