package javaprac.reflection;

import java.util.*;
import java.lang.reflect.*;

import javaprac.Prac;


public class MethodPrac implements Prac {

    public static void sayMyName(String name) {
        System.out.println("I AM " + name + "!");
    }

    public static void gotcha() {
        System.out.println("Aha, gotcha!");
    }

    @Override
    public void runPrac() {
        try {
            Method helloMethod = MethodPrac.class.getMethod("sayHello");
            helloMethod.invoke(this);

            Method staticMethod = MethodPrac.class.getMethod("sayMyName", String.class);
            staticMethod.invoke(null, "Master Yi");

            Method gotchaMethod = MethodPrac.class.getMethod("gotcha");
            gotchaMethod.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice reflection usage - use Method obj to call any functions.";
    }

    public void sayHello() {
        System.out.println("Hello, World");
    }
}
