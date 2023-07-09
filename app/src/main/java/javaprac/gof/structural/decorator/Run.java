package javaprac.gof.structural.decorator;


public class Run extends AnimationDecorator {

    public Run(Animation animation) {
        super(animation);
    }

    @Override
    public String act() {
        return String.format("I can run, and %s", delegate.act());
    }
}
