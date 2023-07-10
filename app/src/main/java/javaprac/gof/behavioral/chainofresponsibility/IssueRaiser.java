package javaprac.gof.behavioral.chainofresponsibility;


public class IssueRaiser {

    private final Receiver firstReceiver;

    public IssueRaiser(Receiver firstReceiver) {
        this.firstReceiver = firstReceiver;
    }

    public IssueRaiser() {
        this.firstReceiver = null;
    }

    public void raiseMessage(String msg) {
        if (firstReceiver != null) {
            if (firstReceiver.processMessage(msg)) {
                System.out.println("Succeeded to process message : " + msg);
            } else {
                System.out.println("Failed to process message : " + msg);
            }
        } else {
            System.out.println("No receiver to process message : " + msg);
        }
    }
}
