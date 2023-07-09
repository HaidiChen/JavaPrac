package javaprac.gof.creational.abstractfactory;

import javaprac.Prac;


public class AbstractFactoryPrac implements Prac {

    @Override
    public void runPrac() {
        MovieFactory movieFactory = new ComedyMovieFactory();
        BollywoodMovie bm = movieFactory.getBollywoodMovie();
        System.out.println("Bollywood movie name: " + bm.movieName());

        TollywoodMovie tm = movieFactory.getTollywoodMovie();
        System.out.println("Tollywood movie name: " + tm.movieName());

        movieFactory = new ActionMovieFactory();
        bm = movieFactory.getBollywoodMovie();
        System.out.println("Bollywood movie name: " + bm.movieName());

        tm = movieFactory.getTollywoodMovie();
        System.out.println("Tollywood movie name: " + tm.movieName());
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [Creational] Abstract Factory";
    }
}
