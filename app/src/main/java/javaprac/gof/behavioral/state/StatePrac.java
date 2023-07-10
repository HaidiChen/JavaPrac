package javaprac.gof.behavioral.state;

import javaprac.Prac;


public class StatePrac implements Prac {

    @Override
    public void runPrac() {
        RemoteControl off = new Off();
        TV tv = new TV(off);

        for (int i = 0; i < 20; i++) {
            tv.pressButton();
        }
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [behavioral] State";
    }
}
