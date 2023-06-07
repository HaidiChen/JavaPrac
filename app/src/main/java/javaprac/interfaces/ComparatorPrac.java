package javaprac.interfaces;

import java.util.*;

import javaprac.Prac;


public class ComparatorPrac implements Prac {

    private static final String[] FRIENDS = {"Peter", "Paul", "Mikery"};

    @Override
    public void runPrac() {
        System.out.println("Original FRIENDS list: ");
        for(String name : FRIENDS) {
            System.out.println(name);
        }

        Comparator<String> comp = new LengthComparator();
        Arrays.sort(FRIENDS, comp);

        System.out.println("After sorting by length, FRIENDS list: ");
        for(String name : FRIENDS) {
            System.out.println(name);
        }
    }

    @Override
    public String getDescription() {
        return "Practice Interface : Comparator<T>";
    }
}

/*
 * Comparator interface is used for providing a custom comparison approach.
 * The Object which implements this interface could be used as an argument in some APIs
 * such as : Arrays.sort(Object[] a, Comparator<T> comp).
 */
class LengthComparator implements Comparator<String> {

    @Override
    public int compare(String first, String second) {
        return first.length() - second.length();
    }
}
