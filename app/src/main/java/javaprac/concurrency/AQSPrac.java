package javaprac.concurrency;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

import javaprac.Prac;


public class AQSPrac implements Prac {

    private static final double INITIAL_BALANCE = 1000;
    private static final double MAX_AMOUNT = 1000;
    private static final int DELAY = 10;
    private static final int NACCOUNTS = 100;

    @Override
    public void runPrac() {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);

        ExecutorService pool = Executors.newFixedThreadPool(50);

        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            pool.submit(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();

                        // Use sync mechanism (i.e., locks) to prevent race condition
                        bank.syncTransfer(fromAccount, toAccount, amount);

                        /*
                         * Following line doesn't use sync to lock resources, which could
                         * lead to race condition and unexpected result.
                         */
                        //bank.unSyncTransfer(fromAccount, toAccount, amount);

                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        pool.shutdown();
    }

    @Override
    public String getDescription() {
        return "Practice concurrent programming - AQS usage.";
    }

    /*
     * Implement a mutex using AQS framework.
     */
    private class Mutex extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return compareAndSetState(1, 0);
        }

        public void lock() {
            this.acquire(0);
        }

        public void unlock() {
            this.release(0);
        }
    }

    private class Bank {

        private final double[] accounts;
        private final Mutex mutex;

        public Bank(int n, double initialBalance) {
            accounts = new double[n];
            Arrays.fill(accounts, initialBalance);
            mutex = new Mutex();
        }

        /*
         * Use Customized lock (Mutex obj) to do the synchronization.
         */
        public void syncTransfer(int from, int to, double amount) {
            mutex.lock();
            try {
                if (accounts[from] < amount) {
                    return;
                }
                unSyncTransfer(from, to, amount);
            } finally {
                mutex.unlock();
            }
        }

        public void unSyncTransfer(int from, int to, double amount) {
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total balance: %10.2f%n", getTotalBalance());
        }

        public double getTotalBalance() {
            return Arrays.stream(accounts).sum();
        }

        public int size() {
            return accounts.length;
        }
    }
}
