package javaprac.gof.structural.decorator;


public abstract class AnimationDecorator implements Animation {

    protected final Animation delegate;

    public AnimationDecorator(Animation delegate) {
        this.delegate = delegate;
    }
}
