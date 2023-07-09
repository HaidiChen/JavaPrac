package javaprac.gof.creational.abstractfactory;


public class TollywoodComedyMovie implements TollywoodMovie {

    private final String name;

    public TollywoodComedyMovie(String name) {
        this.name = name;
    }

    @Override
    public String movieName() {
        return name;
    }
}
