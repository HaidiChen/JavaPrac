package javaprac.gof.behavioral.state;


public class TV {

    private RemoteControl remoteControl;

    public TV(RemoteControl remoteControl) {
        this.remoteControl = remoteControl;
    }

    public void setState(RemoteControl remoteControl) {
        this.remoteControl = remoteControl;
    }

    public RemoteControl getState() {
        return remoteControl;
    }

    public void pressButton() {
        remoteControl.pressSwitch(this);
    }
}
