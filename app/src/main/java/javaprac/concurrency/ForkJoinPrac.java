package javaprac.concurrency;

import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;

import javaprac.Prac;


public class ForkJoinPrac implements Prac {

    @Override
    public void runPrac() {
        final int SIZE = 500;
        double[] numbers = new double[SIZE];

        for (int i = 0; i < SIZE; i++) {
            numbers[i] = Math.random();
        }

        Counter counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }

    @Override
    public String getDescription() {
        return "Practice concurrent programming - Fork Join Framework usage";
    }

    private class Counter extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 251;
        private double[] values;
        private int from;
        private int to;
        private DoublePredicate filter;

        public Counter(double[] values, int from, int to, DoublePredicate filter) {
            this.values = values;
            this.from = from;
            this.to = to;
            this.filter = filter;
        }

        @Override
        protected Integer compute() {
            if (to - from < THRESHOLD) {
                int count = 0;
                for(int i = from; i < to; i++) {
                    if (filter.test(values[i])) {
                        count += 1;
                    }
                }

                return count;
            } else {
                int middle = (to - from) / 2;
                Counter first = new Counter(values, from, middle, filter);
                Counter second = new Counter(values, middle, to, filter);
                invokeAll(first, second);

                return first.join() + second.join();
            }
        }
    }
}
