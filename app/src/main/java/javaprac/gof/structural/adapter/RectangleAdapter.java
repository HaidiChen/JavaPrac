package javaprac.gof.structural.adapter;


public class RectangleAdapter extends Rectangle {

    private final Triangle tri;

    public RectangleAdapter(Triangle tri) {
        super(0, 0);
        this.tri = tri;
    }

    @Override
    public double area() {
        return tri.area();
    }
}
