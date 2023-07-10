package javaprac.gof.behavioral.command;

import javaprac.Prac;


public class CommandPrac implements Prac {

    @Override
    public void runPrac() {
        Invoker invoker = new Invoker();
        CommandReceiver receiver = new CommandReceiver();
        Command undoCommand = new UndoCommand(receiver);
        Command redoCommand = new RedoCommand(receiver);

        invoker.executeCommand(undoCommand);
        invoker.executeCommand(redoCommand);
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [behavioral] Command";
    }
}
