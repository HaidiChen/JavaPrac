package javaprac.gof.structural.flyweight;


public class Robot {

    private final String type;
    private String color;

    public Robot(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("Robot type: %s, Robot color: %s", type, color);
    }
}
