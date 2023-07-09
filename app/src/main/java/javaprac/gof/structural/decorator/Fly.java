package javaprac.gof.structural.decorator;


public class Fly extends AnimationDecorator {

    public Fly(Animation animation) {
        super(animation);
    }

    @Override
    public String act() {
        return String.format("I can fly, and %s", delegate.act());
    }
}
