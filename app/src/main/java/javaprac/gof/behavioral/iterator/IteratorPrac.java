package javaprac.gof.behavioral.iterator;

import java.util.*;

import javaprac.Prac;


public class IteratorPrac implements Prac {

    @Override
    public void runPrac() {
        String[] subjects = {"Art1", "Art2", "Art3", "Art is art"};

        Arts arts = new Arts(subjects);
        CusIterator iterator = arts.getCusIterator();

        while (!iterator.isDone()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }

        subjects = new String[]{"Science1", "Science2", "Rocket", "Science is everything"};
        Science science = new Science(Arrays.asList(subjects));
        iterator = science.getCusIterator();

        while (!iterator.isDone()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [behavioral] Iterator";
    }
}
