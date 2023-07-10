package javaprac.gof.behavioral.templatemethod;


public class DeskDrawer implements Drawer {

    @Override
    public void stepOne() {
        System.out.println("Draw the desk surface.");
    }

    @Override
    public void stepTwo() {
        System.out.println("Draw four desk legs.");
    }

    @Override
    public void finalStep() {
        System.out.println("Draw some books if you want.");
    }

    @Override
    public void describe() {
        System.out.println("An extremely simple desk drawing.");
    }
}
