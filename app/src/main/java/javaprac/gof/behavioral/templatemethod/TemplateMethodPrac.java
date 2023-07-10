package javaprac.gof.behavioral.templatemethod;

import javaprac.Prac;


public class TemplateMethodPrac implements Prac {

    @Override
    public void runPrac() {
        Drawer drawer = new CarDrawer();
        drawer.draw();

        drawer = new DeskDrawer();
        drawer.draw();
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [behavioral] Template Method";
    }
}
