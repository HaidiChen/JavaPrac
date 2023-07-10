package javaprac.gof.behavioral.memento;


public class Originator {

    private String state;
    private Memento memento;

    public void setState(String state) {
        this.state = state;
        System.out.println("State at present: " + state);
    }

    public Memento createMemento() {
        memento = new Memento(state);
        return memento;
    }

    public void revert(Memento memento) {
        System.out.println("Reverting to previous state...");
        state = memento.getState();
        System.out.println("State at present: " + state);
    }
}
