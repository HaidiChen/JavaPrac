package javaprac.gof.behavioral.chainofresponsibility;


public class EmailErrorReceiver implements Receiver {

    private final Receiver nextReceiver;

    public EmailErrorReceiver(Receiver nextReceiver) {
        this.nextReceiver = nextReceiver;
    }

    public EmailErrorReceiver() {
        this.nextReceiver = null;
    }

    @Override
    public boolean processMessage(String msg) {
        if (msg.contains("Email")) {
            System.out.println("processed email error.");
            return true;
        } else {
            if (nextReceiver != null) {
                return nextReceiver.processMessage(msg);
            }
        }

        return false;
    }
}
