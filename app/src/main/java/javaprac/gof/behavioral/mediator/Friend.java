package javaprac.gof.behavioral.mediator;


public abstract class Friend {

    protected final Mediator mediator;
    protected final String name;

    public Friend(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public void send(String msg) {
        mediator.sendBy(this, msg);
    }

    public abstract void receive(String msg);

    public String getName() {
        return name;
    }
}
