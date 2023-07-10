package javaprac.gof.behavioral.command;


public class UndoCommand implements Command {

    private final CommandReceiver receiver;

    public UndoCommand(CommandReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.performUndo();
    }
}
