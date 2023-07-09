package javaprac.gof.structural.facade;


public class RobotColor {
    private String color;

    public void setColor(String color) {
        this.color = color;
        System.out.println("The robot will be colored with " + color);
    }
}
