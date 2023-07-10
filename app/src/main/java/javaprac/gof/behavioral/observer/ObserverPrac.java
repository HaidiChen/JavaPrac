package javaprac.gof.behavioral.observer;

import javaprac.Prac;


public class ObserverPrac implements Prac {

    @Override
    public void runPrac() {
        Model model = new Model();
        Controller modelController = new Controller(model);
        View modelView = new View(model);

        for (int i = 0; i < 20; i++) {
            modelController.changeModelAttribute();
        }
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [behavioral] Observer";
    }
}
