package javaprac.gof.behavioral.state;


public class Off implements RemoteControl {

    @Override
    public void pressSwitch(TV tv) {
        System.out.println("TV is off, will turn on now.");
        tv.setState(new On());
    }
}
