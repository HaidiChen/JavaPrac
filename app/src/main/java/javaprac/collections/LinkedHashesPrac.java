package javaprac.collections;

import java.util.*;
import java.time.*;

import javaprac.Prac;


public class LinkedHashesPrac implements Prac {

    @Override
    public void runPrac() {
        /*
         * LinkedHashSet & LinkedHashMap remember the insertion order.
         */
        Set<String> lhs = new LinkedHashSet<>();
        lhs.add("hello");
        lhs.add("world");
        lhs.add(null);
        lhs.add("howdy");
        System.out.println("LinkedHashSet output:");
        lhs.forEach(System.out::println);

        Map<Integer, String> lhm = new LinkedHashMap<>();
        lhm.put(1, "one");
        lhm.put(22, "two");
        lhm.put(33, "three");
        lhm.put(4, "four");
        System.out.println("LinkedHashMap output:");
        lhm.forEach((k, v) -> System.out.format("<%d, %s>\n", k, v));

        /*
         * HashSet & HashMap don't remember the insertion order.
         */
        Set<String> hs = new HashSet<>();
        hs.add("hello");
        hs.add("world");
        hs.add(null);
        hs.add("howdy");
        System.out.println("HashSet output:");
        hs.forEach(System.out::println);

        Map<Integer, String> hm = new HashMap<>();
        hm.put(1, "one");
        hm.put(22, "two");
        hm.put(33, "three");
        hm.put(4, "four");
        System.out.println("HashMap output:");
        hm.forEach((k, v) -> System.out.format("<%d, %s>\n", k, v));
    }

    @Override
    public String getDescription() {
        return "Practice LinkedHashSet/Map usage.";
    }
}
