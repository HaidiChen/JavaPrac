package javaprac.generics;

import java.time.*;

import javaprac.Prac;

public class GenericClassPrac implements Prac {

    public static class Pair<T> {

        private T first;
        private T second;

        public Pair() {
            this.first = null;
            this.second = null;
        }

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }

        public void setFirst(T newValue) {
            this.first = newValue;
        }

        public void setSecond(T newValue) {
            this.second = newValue;
        }
    }

    @Override
    public void runPrac() {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("minimum: " + mm.getFirst());
        System.out.println("maximum: " + mm.getSecond());

        LocalDate[] birthdays = {
            LocalDate.of(1906, 12, 9),
            LocalDate.of(1815, 12, 10),
            LocalDate.of(1903, 12, 3),
            LocalDate.of(1910, 6, 22),
        };

        Pair<LocalDate> mmDates = ArrayAlg.minmax(birthdays);
        System.out.println("min Date = " + mmDates.getFirst());
        System.out.println("max Date = " + mmDates.getSecond());
    }

    @Override
    public String getDescription() {
        return "Practice Generic Classes and methods.";
    }
}

class ArrayAlg {

//    public static GenericClassPrac.Pair<String> minmax(String[] a) {
//        if (a == null || a.length == 0) {
//            return null;
//        }
//
//        String min = a[0];
//        String max = a[0];
//
//        for (String ele : a) {
//            if (min.compareTo(ele) > 0) {
//                min = ele;
//            }
//            if (max.compareTo(ele) < 0) {
//                max = ele;
//            }
//        }
//
//        return new GenericClassPrac.Pair<>(min, max);
//    }

    /*
     * Converts the above method to a generic method.
     */
    public static <T extends Comparable> GenericClassPrac.Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }

        T min = a[0];
        T max = a[0];

        for (T ele : a) {
            if (min.compareTo(ele) > 0) {
                min = ele;
            }
            if (max.compareTo(ele) < 0) {
                max = ele;
            }
        }

        return new GenericClassPrac.Pair<>(min, max);
    }
}
