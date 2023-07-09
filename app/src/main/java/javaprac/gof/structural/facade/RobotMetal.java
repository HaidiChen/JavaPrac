package javaprac.gof.structural.facade;


public class RobotMetal {
    private String metal;

    public void setMetal(String metal) {
        this.metal = metal;
        System.out.println("The robot will be made of " + metal);
    }
}
