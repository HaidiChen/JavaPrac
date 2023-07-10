package javaprac.gof.behavioral.mediator;

import javaprac.Prac;


public class MediatorPrac implements Prac {

    @Override
    public void runPrac() {
        MessageMediator mediator = new MessageMediator();
        Friend friend1 = new GoodFriend(mediator, "Ki");
        Friend friend2 = new GoodFriend(mediator, "Ash");
        Friend boss = new Boss(mediator, "Prof. Wu");

        mediator.setFriend1(friend1);
        mediator.setFriend2(friend2);
        mediator.setBoss(boss);

        friend1.send("Hey Ash!");
        friend2.send("Nice chatting with you Ki");
        boss.send("I'm listening to both of you.");
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [behavioral] Mediator";
    }
}
