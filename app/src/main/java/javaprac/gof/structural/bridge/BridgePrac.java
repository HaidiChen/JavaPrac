package javaprac.gof.structural.bridge;

import javaprac.Prac;


public class BridgePrac implements Prac {

    @Override
    public void runPrac() {
        Color red = new RedColor();
        Color green = new GreenColor();
        Shape triangle = new Triangle(red);
        Shape rectangle = new Rectangle(green);

        System.out.println(triangle);
        System.out.println(rectangle);

        triangle = new Triangle(green);
        rectangle = new Rectangle(red);
        System.out.println(triangle);
        System.out.println(rectangle);
    }

    @Override
    public String getDescription() {
        return "Design pattern - [structural] Bridge";
    }
}
