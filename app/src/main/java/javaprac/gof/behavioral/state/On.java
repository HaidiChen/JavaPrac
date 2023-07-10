package javaprac.gof.behavioral.state;


public class On implements RemoteControl {

    @Override
    public void pressSwitch(TV tv) {
        System.out.println("TV is on, will turn off now.");
        tv.setState(new Off());
    }
}
