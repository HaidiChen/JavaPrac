package javaprac.gof.behavioral.mediator;


public class MessageMediator implements Mediator {

    private Friend friend1;
    private Friend friend2;
    private Friend boss;

    public void setFriend1(Friend friend1) {
        this.friend1 = friend1;
    }

    public void setFriend2(Friend friend2) {
        this.friend2 = friend2;
    }

    public void setBoss(Friend boss) {
        this.boss = boss;
    }

    @Override
    public void sendBy(Friend friend, String msg) {
        if (friend == friend1) {
            friend2.receive(msg);
            boss.receive(friend1.getName() + " sends message to " + friend2.getName());
        } else if (friend == friend2) {
            friend1.receive(msg);
            boss.receive(friend2.getName() + " sends message to " + friend1.getName());
        } else {
            friend1.receive(msg);
            friend2.receive(msg);
        }
    }
}
