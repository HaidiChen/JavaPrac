package javaprac.concurrency;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.logging.*;

import javaprac.Prac;


public class AnotherThreadPoolPrac implements Prac {

    @Override
    public void runPrac() {
        //threadStarvationDeadlock();

        ExecutorService singleExecutor = new TimingThreadPoolExecutor(
                1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 100; i++) {
            singleExecutor.submit(() -> {
                System.out.println(Thread.currentThread() + ": I'm a custom task");
                System.out.println(Thread.currentThread() + ": " + new Random().nextInt(100));
            });
        }

        singleExecutor.shutdown();
    }

    @Override
    public String getDescription() {
        return "Learn concurrency - thread pool usage.";
    }

    /**
     * Thread starvation deadlock situation.
     *
     * Thread A is executing a task T, and inside T, one sub task is submitted to the executor.
     * Now thread A is waiting for the sub task to complete, which is impossibile as only one
     * thread is allowed to be alive at the same time. To make the sub task run, thread A must end
     * itself first so that a new thread could be started to execute the sub task, which is also
     * impossible as thread A needs the sub task to finish first before ending itself.
     *
     * Main task in Thread A is waiting for the sub task to finish,
     * and the sub task is waiting for the main task in thread A to finish.
     * This starvation (sub task is starving) deadlock (entire program freezes) situation could be
     * solved by increasing the thread pool size but the root cause is the task dependency here.
     */
    private void threadStarvationDeadlock() {
        Callable<Integer> subTask = () -> {
            System.out.println("I'm a sub task running in " + Thread.currentThread().getId());
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 20;
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            try {
                System.out.println("I need result from sub tasks");

                Future<Integer> result = executor.submit(subTask);
                result.get();
                System.out.println("Now I'm good.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private class TimingThreadPoolExecutor extends ThreadPoolExecutor {

        private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
        private final AtomicLong numTasks = new AtomicLong();
        private final AtomicLong totalTime = new AtomicLong();

        public TimingThreadPoolExecutor(int corePoolSize,
                                        int maxPoolSize,
                                        long keepAliveTime,
                                        TimeUnit unit,
                                        BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            System.out.println(String.format("Thread %s: start %s", t, r));
            startTime.set(System.nanoTime());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            try {
                long endTime = System.nanoTime();
                long taskTime = endTime - startTime.get();
                numTasks.incrementAndGet();
                totalTime.addAndGet(taskTime);
                System.out.println(String.format("Thread %s: end %s, time = %dns", t, r, taskTime));
            } finally {
                super.afterExecute(r, t);
            }
        }

        @Override
        protected void terminated() {
            try {
                System.out.println(String.format("Terminated: avg time = %dns",
                            totalTime.get() / numTasks.get()));
            } finally {
                super.terminated();
            }
        }
    }
}
