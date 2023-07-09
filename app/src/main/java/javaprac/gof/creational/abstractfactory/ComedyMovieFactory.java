package javaprac.gof.creational.abstractfactory;


public class ComedyMovieFactory implements MovieFactory {


    @Override
    public TollywoodMovie getTollywoodMovie() {
        return new TollywoodComedyMovie("tollywood-comedy-movie-1");
    }

    @Override
    public BollywoodMovie getBollywoodMovie() {
        return new BollywoodComedyMovie("bollywood-comedy-movie-1");
    }
}
