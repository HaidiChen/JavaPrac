package javaprac.gof.creational.prototype;

import javaprac.Prac;


public class PrototypePrac implements Prac {

    @Override
    public void runPrac() {
        try {
            BasicCar basicNano = new Nano("Green Nano");
            BasicCar basicFord = new Ford("Yellow Ford");
            BasicCar anotherCar = basicNano.clone();
            anotherCar.setPrice(1_000_000);
            System.out.println(anotherCar);

            anotherCar = basicFord.clone();
            anotherCar.setPrice(4_000_000);
            System.out.println(anotherCar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Design pattern - [creational] Prototype";
    }
}
