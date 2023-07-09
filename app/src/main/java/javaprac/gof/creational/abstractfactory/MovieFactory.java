package javaprac.gof.creational.abstractfactory;


public interface MovieFactory {
    default TollywoodMovie getTollywoodMovie() {
        throw new UnsupportedOperationException("Needs implementation");
    }

    default BollywoodMovie getBollywoodMovie() {
        throw new UnsupportedOperationException("Needs implementation");
    }
}
