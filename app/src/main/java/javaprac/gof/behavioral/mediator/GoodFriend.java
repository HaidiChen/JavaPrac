package javaprac.gof.behavioral.mediator;


public class GoodFriend extends Friend {

    public GoodFriend(Mediator mediator, String name) {
        super(mediator, name);
    }

    public void receive(String msg) {
        System.out.println(name + " got message : " + msg);
    }
}
