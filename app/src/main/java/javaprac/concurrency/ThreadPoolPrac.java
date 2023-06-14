package javaprac.concurrency;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import javaprac.Prac;


/*
 * This is pretty much the same as the CallableFuturePrac use case, except that we use
 * ThreadPool here to create threads instead of manually doing so with 'new Thread()'.
 */
public class ThreadPoolPrac implements Prac {

    private static final String INPUT_FILE_PATH = "src/main/resources/thread_pool_prac";

    @Override
    public void runPrac() {
        try (Scanner in = new Scanner(new File(INPUT_FILE_PATH))) {
            String directory = in.nextLine();
            String keyword = in.nextLine();

            /*
             * Executors provides multiple methods for creating thread pools.
             * e.g.,
             * - Executors.newCachedThreadPool()
             * - Executors.newFixedThreadPool()
             * - Executors.newSingleThreadExecutor() etc.
             */
            ExecutorService pool = Executors.newCachedThreadPool();

            MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
            Future<Integer> result = pool.submit(counter);

            try {
                System.out.println(result.get() + " matching files.");
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*
             * If no tasks to be submitted, shut down the thread pool,
             * otherwise it'll wait for the next task and the program won't exit.
             */
            pool.shutdown();

            int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
            System.out.println("largest pool size = " + largestPoolSize);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice concurrent programming - ThreadPool usage";
    }

    private class MatchCounter implements Callable<Integer> {

        private File directory;
        private String keyword;
        private ExecutorService pool;

        public MatchCounter(File directory, String keyword, ExecutorService pool) {
            this.directory = directory;
            this.keyword = keyword;
            this.pool = pool;
        }

        @Override
        public Integer call() {
            int count = 0;
            try {
                File[] files = directory.listFiles();
                List<Future<Integer>> results = new ArrayList<>();

                for (File file : files) {
                    if (file.isDirectory()) {
                        MatchCounter counter = new MatchCounter(file, keyword, pool);
                        Future<Integer> result = pool.submit(counter);
                        results.add(result);
                    } else {
                        if (search(file)) {
                            count += 1;
                        }
                    }
                }

                for (Future<Integer> result : results) {
                    try {
                        count += result.get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return count;
        }

        private boolean search(File file) {
            try {
                try (Scanner in = new Scanner(file, "utf-8")) {
                    boolean found = false;

                    while (!found && in.hasNextLine()) {
                        String line = in.nextLine();
                        if (line.contains(keyword)) {
                            found = true;
                        }
                    }

                    return found;
                }
            } catch (IOException e) {
                return false;
            }
        }
    }
}
