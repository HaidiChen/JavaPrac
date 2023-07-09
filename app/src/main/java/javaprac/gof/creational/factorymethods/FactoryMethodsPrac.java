package javaprac.gof.creational.factorymethods;

import javaprac.Prac;


public class FactoryMethodsPrac implements Prac {

    @Override
    public void runPrac() {
        AnimalFactory animalFactory = new MyAnimalFactory();
        Animal animal = animalFactory.getAnimalType("Tiger");
        System.out.println(animal.speak());

        animal = animalFactory.getAnimalType("Duck");
        System.out.println(animal.speak());
    }

    @Override
    public String getDescription() {
        return "Design pattern - [creational] Factory Methods";
    }
}
