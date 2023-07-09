package javaprac.gof.structural.decorator;


public class Sprint extends AnimationDecorator {

    public Sprint(Animation animation) {
        super(animation);
    }

    @Override
    public String act() {
        return String.format("I can sprint, and %s", delegate.act());
    }
}
