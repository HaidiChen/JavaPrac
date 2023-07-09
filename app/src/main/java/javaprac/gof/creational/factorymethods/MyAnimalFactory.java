package javaprac.gof.creational.factorymethods;


public class MyAnimalFactory implements AnimalFactory {

    @Override
    public Animal getAnimalType(String type) {
        switch(type) {
            case "Tiger":
                return new Tiger();
            case "Duck":
                return new Duck();
            default:
                throw new UnsupportedOperationException("Only support creating Tiger or Duck.");
        }
    }
}
