package javaprac.gof.behavioral.observer;


public class Model extends Observable {

    private String attribute;

    public void setAttribute(String attribute) {
        this.attribute = attribute;
        publishToAll();
    }

    public String getAttribute() {
        return attribute;
    }
}
