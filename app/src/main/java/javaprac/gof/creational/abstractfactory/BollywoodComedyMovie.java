package javaprac.gof.creational.abstractfactory;


public class BollywoodComedyMovie implements BollywoodMovie {

    private final String name;

    public BollywoodComedyMovie(String name) {
        this.name = name;
    }

    @Override
    public String movieName() {
        return name;
    }
}
