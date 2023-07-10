package javaprac.gof.behavioral.command;


public class RedoCommand implements Command {

    private final CommandReceiver receiver;

    public RedoCommand(CommandReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.performRedo();
    }
}
