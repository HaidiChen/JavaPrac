package javaprac.reflection;

import java.util.*;
import java.lang.reflect.*;

import javaprac.Prac;


public class CopyOfPrac implements Prac {

    @Override
    public void runPrac() {
        int[] a = {1, 2, 3};
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

        String[] b = {"Harry", "Potter", "Larry"};
        // Following line will throw an exception
        //b = (String[]) badCopyOf(b, 10);
        b = (String[]) goodCopyOf(b, 10);
        System.out.println(Arrays.toString(b));
    }

    @Override
    public String getDescription() {
        return "Practice reflection usage - implement copyOf method.";
    }

    private Object goodCopyOf(Object a, int newLength) {
        Class clz = a.getClass();
        if (!clz.isArray()) {
            return null;
        }
        Class componentType = clz.getComponentType();
        /*
         * Following 2 lines use reflection:
         * Array.getLength() & Array.newInstance()
         */
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));

        return newArray;
    }

    private Object[] badCopyOf(Object[] a, int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));

        return newArray;
    }
}
