package javaprac.gof.creational.builder;


public class Vehicle {

    private final String brand;
    private final String color;
    private final String type;
    private final int wheels;

    private Vehicle(String brand, String color, String type, int wheels) {
        this.brand = brand;
        this.color = color;
        this.type = type;
        this.wheels = wheels;
    }

    @Override
    public String toString() {
        return String.format("Vehicle info:\nBrand: %s\nColor: %s\nType: %s\nWheels: %d\n",
                brand, color, type, wheels);
    }

    public static class Builder {
        private String brand = "default-brand";
        private String color = "default-color";
        private String type = "default-type";
        private int wheels = 1;

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder wheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(brand, color, type, wheels);
        }
    }
}
