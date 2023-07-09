package javaprac.gof.structural.decorator;

import javaprac.Prac;


public class DecoratorPrac implements Prac {

    @Override
    public void runPrac() {
        Animation animation = new Fly(
                new Sprint(
                    new Run(
                        new Jog(
                            new Walk()))));

        System.out.println(animation.act());

        animation = new Sprint(new Walk());
        System.out.println(animation.act());
    }

    @Override
    public String getDescription() {
        return "Design pattern - [structural] Decorator";
    }
}
