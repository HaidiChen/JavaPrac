package javaprac.gof.behavioral.mediator;


public class Boss extends Friend {

    public Boss(Mediator mediator, String name) {
        super(mediator, name);
    }

    public void receive(String msg) {
        System.out.println("Boss " + name + " sees message : " + msg);
    }
}
