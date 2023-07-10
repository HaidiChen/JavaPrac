package javaprac.gof.behavioral.state;


public interface RemoteControl {

    default void pressSwitch(TV tv) {
        throw new UnsupportedOperationException("Needs Implementation.");
    }
}
