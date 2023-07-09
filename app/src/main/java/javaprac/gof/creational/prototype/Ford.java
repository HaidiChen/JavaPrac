package javaprac.gof.creational.prototype;


public class Ford extends BasicCar {

    public Ford(String modelName) {
        super(modelName);
    }

    @Override
    public BasicCar clone() throws CloneNotSupportedException {
        return (Ford) super.clone();
    }
}
