package javaprac.gof.behavioral.observer;


public class View implements Observer {

    private final Model model;

    public View(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public void unsubscribe() {
        model.removeObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Model Attribute Changed to : " + model.getAttribute());
    }
}
