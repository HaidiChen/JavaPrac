package javaprac.gof.structural.adapter;


public class Triangle {

    private final double bottom;
    private final double height;

    public Triangle(double bottom, double height) {
        this.bottom = bottom;
        this.height = height;
    }

    public double area() {
        return bottom * height / 2;
    }
}
