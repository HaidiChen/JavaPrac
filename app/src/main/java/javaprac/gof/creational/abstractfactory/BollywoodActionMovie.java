package javaprac.gof.creational.abstractfactory;


public class BollywoodActionMovie implements BollywoodMovie {

    private final String name;


    public BollywoodActionMovie(String name) {
        this.name = name;
    }

    @Override
    public String movieName() {
        return name;
    }
}
