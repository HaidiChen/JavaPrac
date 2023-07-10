package javaprac.gof.behavioral.chainofresponsibility;


public class ServerErrorReceiver implements Receiver {

    private final Receiver nextReceiver;

    public ServerErrorReceiver(Receiver nextReceiver) {
        this.nextReceiver = nextReceiver;
    }

    public ServerErrorReceiver() {
        this.nextReceiver = null;
    }

    @Override
    public boolean processMessage(String msg) {
        if (msg.contains("Server")) {
            System.out.println("processed the server error. big save.");
            return true;
        } else {
            if (nextReceiver != null) {
                return nextReceiver.processMessage(msg);
            }
        }

        return false;
    }
}
