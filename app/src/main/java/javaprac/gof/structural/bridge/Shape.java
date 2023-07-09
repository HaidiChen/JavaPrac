package javaprac.gof.structural.bridge;


public abstract class Shape {

    private final Color color;

    protected Shape(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s is filled with color %s",
                this.getClass().getSimpleName(), color.code());
    }
}
