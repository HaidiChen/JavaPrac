package javaprac.gof.creational.prototype;


public abstract class BasicCar implements Cloneable {

    protected final String modelName;
    protected long price;

    protected BasicCar(String modelName) {
        this.modelName = modelName;
        this.price = 1_000;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public BasicCar clone() throws CloneNotSupportedException {
        return (BasicCar) super.clone();
    }

    @Override
    public String toString() {
        return String.format("Model: %s, Price: %d", modelName, price);
    }
}
