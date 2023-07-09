package javaprac.gof.structural.adapter;


public class Rectangle {

    private final int length;
    private final int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public double area() {
        return length * width;
    }
}
