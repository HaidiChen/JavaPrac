package javaprac.gof.creational.abstractfactory;


public class TollywoodActionMovie implements TollywoodMovie {

    private final String name;


    public TollywoodActionMovie(String name) {
        this.name = name;
    }

    @Override
    public String movieName() {
        return name;
    }
}
