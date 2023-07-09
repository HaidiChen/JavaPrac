package javaprac.gof.structural.facade;


public class RobotFacade {

    private final RobotBody rBody;
    private final RobotMetal rMetal;
    private final RobotColor rColor;

    public RobotFacade() {
        this.rBody = new RobotBody();
        this.rMetal = new RobotMetal();
        this.rColor = new RobotColor();
    }

    public void constructRobot(String metal, String color) {
        System.out.println("Creating a robot...");
        rColor.setColor(color);
        rMetal.setMetal(metal);
        rBody.createBody();
        System.out.println("Robot creation done.");
    }
}
