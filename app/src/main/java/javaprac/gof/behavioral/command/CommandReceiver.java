package javaprac.gof.behavioral.command;


public class CommandReceiver {

    public void performUndo() {
        System.out.println("Undoing last operation.");
    }

    public void performRedo() {
        System.out.println("Redoing operation.");
    }
}
