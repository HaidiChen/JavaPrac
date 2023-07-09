package javaprac.gof.creational.factorymethods;


public interface AnimalFactory {

    default Animal getAnimalType(String type) {
        throw new UnsupportedOperationException("Needs implementation.");
    }
}
