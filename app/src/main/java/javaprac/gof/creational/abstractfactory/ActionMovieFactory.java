package javaprac.gof.creational.abstractfactory;


public class ActionMovieFactory implements MovieFactory {


    @Override
    public TollywoodMovie getTollywoodMovie() {
        return new TollywoodActionMovie("tollywood-action-movie-1");
    }

    @Override
    public BollywoodMovie getBollywoodMovie() {
        return new BollywoodActionMovie("bollywood-action-movie-1");
    }
}
