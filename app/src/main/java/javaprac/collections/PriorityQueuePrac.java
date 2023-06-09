package javaprac.collections;

import java.util.*;
import java.time.*;

import javaprac.Prac;


public class PriorityQueuePrac implements Prac {

    @Override
    public void runPrac() {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 22));

        pq.forEach(System.out::println);

        System.out.println("removing element");
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }

    @Override
    public String getDescription() {
        return "Practice PriorityQueue usage.";
    }
}
