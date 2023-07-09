package javaprac.gof.structural.flyweight;

import java.util.*;


public class RobotFactory {

    private static final Map<String, Robot> cachedRobots = new HashMap<>();

    public Robot getRobot(String type) {
        Robot robot = null;

        if (cachedRobots.containsKey(type)) {
            robot = cachedRobots.get(type);
        } else {
            switch (type) {
                case "King":
                    System.out.println("Creating a King robot now.");
                    robot = new Robot(type);
                    break;
                case "Queen":
                    System.out.println("Creating a Queen robot now.");
                    robot = new Robot(type);
                    break;
                default:
                    throw new UnsupportedOperationException("We don't have other types of robot.");
            }
        }

        cachedRobots.putIfAbsent(type, robot);

        return robot;
    }
}
