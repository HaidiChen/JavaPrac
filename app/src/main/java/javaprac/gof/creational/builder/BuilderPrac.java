package javaprac.gof.creational.builder;

import javaprac.Prac;


public class BuilderPrac implements Prac {

    @Override
    public void runPrac() {
        Vehicle vehicle = new Vehicle.Builder()
            .brand("Moto")
            .color("red")
            .type("motorcycle")
            .wheels(2)
            .build();

        System.out.println(vehicle);

        vehicle = new Vehicle.Builder()
            .type("motorcycle")
            .wheels(2)
            .build();
        System.out.println(vehicle);
    }

    @Override
    public String getDescription() {
        return "Design pattern - [creational] Builder";
    }
}
