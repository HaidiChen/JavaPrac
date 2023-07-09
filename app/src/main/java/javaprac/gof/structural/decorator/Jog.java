package javaprac.gof.structural.decorator;


public class Jog extends AnimationDecorator {

    public Jog(Animation animation) {
        super(animation);
    }

    @Override
    public String act() {
        return String.format("I can jog, and %s", delegate.act());
    }
}
