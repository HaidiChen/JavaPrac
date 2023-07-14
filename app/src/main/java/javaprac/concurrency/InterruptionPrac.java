package javaprac.concurrency;

import java.math.*;
import java.util.*;
import java.util.concurrent.*;

import javaprac.Prac;


public class InterruptionPrac implements Prac {

    @Override
    public void runPrac() {
        try {
            PrimeGenerator primeGen = new PrimeGenerator();
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(primeGen);
            try {
                Thread.sleep(3);
            } finally {
                primeGen.cancel();
            }
            primeGen.get().forEach(System.out::println);

            executor.shutdown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String getDescription() {
        return "Learn concurrency - interrupt thread executions";
    }

    private class PrimeGenerator implements Runnable {

        private final List<BigInteger> primes = new CopyOnWriteArrayList<>();
        private volatile boolean cancelled;

        @Override
        public void run() {
            BigInteger p = BigInteger.ONE;

            while(!cancelled) {
                p = p.nextProbablePrime();
                primes.add(p);
            }
        }

        public void cancel() {
            cancelled = true;
        }

        public List<BigInteger> get() {
            return new CopyOnWriteArrayList<BigInteger>(primes);
        }
    }
}
