package javaprac.gof.behavioral.observer;

import java.util.*;


public class Controller {

    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void changeModelAttribute() {
        model.setAttribute("" + new Random().nextInt(20));
    }
}
