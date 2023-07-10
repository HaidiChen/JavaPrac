package javaprac.gof.behavioral.memento;

import javaprac.Prac;


public class MementoPrac implements Prac {

    @Override
    public void runPrac() {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        originator.setState("First state");
        careTaker.saveMemento(originator.createMemento());

        originator.setState("Second state");

        originator.revert(careTaker.getMemento());
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [behavioral] Memento";
    }
}
