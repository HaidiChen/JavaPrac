package javaprac.interfaces;

import java.lang.reflect.*;
import java.util.*;

import javaprac.Prac;


/*
 * Demonstrates the basic usage of proxies.
 */
public class ProxyPrac implements Prac {

    @Override
    public void runPrac() {
        Object[] elements = new Object[1000];

        for (int i = 0; i < 1000; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[] { Comparable.class }, handler);

            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length) + 1;

        int indexOfKey = Arrays.binarySearch(elements, key);

        if (indexOfKey >= 0) {
            System.out.println(elements[indexOfKey]);
        }
    }

    @Override
    public String getDescription() {
        return "Practice Proxies";
    }
}

class TraceHandler implements InvocationHandler {

    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("." + method.getName() + "(");

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");

        return method.invoke(target, args);
    }
}
