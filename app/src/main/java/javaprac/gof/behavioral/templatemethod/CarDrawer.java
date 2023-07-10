package javaprac.gof.behavioral.templatemethod;


public class CarDrawer implements Drawer {

    @Override
    public void stepOne() {
        System.out.println("Step 1: draw the car body.");
    }

    @Override
    public void stepTwo() {
        System.out.println("Step 2: draw the four wheels.");
    }

    @Override
    public void finalStep() {
        System.out.println("Last step: draw the driver if you can.");
    }

    @Override
    public void describe() {
        System.out.println("A car with a driver in it.");
    }
}
