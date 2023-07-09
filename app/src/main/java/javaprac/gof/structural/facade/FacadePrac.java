package javaprac.gof.structural.facade;

import javaprac.Prac;


public class FacadePrac implements Prac {

    @Override
    public void runPrac() {
        RobotFacade robotFacade = new RobotFacade();

        robotFacade.constructRobot("Bronze", "black");
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [structural] Facade";
    }
}
