package javaprac.concurrency;

import java.util.*;
import java.util.concurrent.locks.*;

import javaprac.Prac;


public class SynchronizationPrac implements Prac {

    private static final double INITIAL_BALANCE = 1000;
    private static final double MAX_AMOUNT = 1000;
    private static final int DELAY = 10;
    private static final int NACCOUNTS = 100;

    @Override
    public void runPrac() {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);

        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            new Thread(() -> {
                try {
                    for (; ;) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();

                        // Use sync mechanism (i.e., locks) to prevent race condition
                        //bank.syncTransfer(fromAccount, toAccount, amount);
                        bank.syncTransfer2(toAccount, fromAccount, amount);

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
            }).start();
        }
    }

    @Override
    public String getDescription() {
        return "Practice concurrent programming - synchronization use case.";
    }
}

class Bank {

    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    /*
     * This method uses explicit locks (i.e., ReentrantLock obj here) to do synchronization.
     */
    public void syncTransfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                sufficientFunds.await();
            }

            unSyncTransfer(from, to, amount);
            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }

    /*
     * This method uses 'synchronized' keyword other than explicit locks to do synchronization.
     */
    public synchronized void syncTransfer2(int from, int to, double amount)
            throws InterruptedException {
        while (accounts[from] < amount) {
            wait();
        }
        unSyncTransfer(from, to, amount);
        notifyAll();
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
