package javaprac.gof.structural.flyweight;

import java.util.*;

import javaprac.Prac;


public class FlyweightPrac implements Prac {

    @Override
    public void runPrac() {
        RobotFactory robotFactory = new RobotFactory();
        for (int i = 0; i < 3; i++) {
            Robot robot = robotFactory.getRobot("King");
            robot.setColor(getRandomColor());
            System.out.println(robot);
        }

        for (int i = 0; i < 3; i++) {
            Robot robot = robotFactory.getRobot("Queen");
            robot.setColor(getRandomColor());
            System.out.println(robot);
        }
    }

    @Override
    public String getDescription() {
        return "Design pattern - [structural] Flyweight";
    }

    private String getRandomColor() {
        int value = new Random().nextInt(100) % 2;

        return value == 0 ? "red" : "green";
    }
}
