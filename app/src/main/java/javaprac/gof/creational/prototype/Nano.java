package javaprac.gof.creational.prototype;


public class Nano extends BasicCar {

    public Nano(String modelName) {
        super(modelName);
    }

    @Override
    public BasicCar clone() throws CloneNotSupportedException {
        return (Nano) super.clone();
    }
}
