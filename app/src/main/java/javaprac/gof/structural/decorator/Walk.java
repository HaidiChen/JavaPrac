package javaprac.gof.structural.decorator;


public class Walk implements Animation {

    @Override
    public String act() {
        return "I can walk.";
    }
}
