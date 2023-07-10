package javaprac.gof.behavioral.templatemethod;


public interface Drawer {

    default void draw() {
        stepOne();
        stepTwo();
        finalStep();

        System.out.println("Here's what I drew:");
        describe();
    }

    void stepOne();
    void stepTwo();
    void finalStep();
    void describe();
}
