package javaprac.concurrency;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;

import javaprac.Prac;


public class CallableFuturePrac implements Prac {

    private static final String INPUT_FILE_PATH = "./src/main/resources/callable_future_prac";

    @Override
    public void runPrac() {
        Path inputFilePath = Prac.getCwd().resolve(INPUT_FILE_PATH);
        try (Scanner in = new Scanner(new File(inputFilePath.toString()))) {
            String directory = in.nextLine();
            String keyword = in.nextLine();

            MatchCounter counter = new MatchCounter(new File(directory), keyword);
            FutureTask<Integer> task = new FutureTask<>(counter);
            new Thread(task).start();

            try {
                System.out.println(task.get() + " matching files.");
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice concurrent programming - Callable and Future usage.";
    }

    private class MatchCounter implements Callable<Integer> {

        private File directory;
        private String keyword;

        public MatchCounter(File directory, String keyword) {
            this.directory = directory;
            this.keyword = keyword;
        }

        @Override
        public Integer call() {
            int count = 0;
            try {
                File[] files = directory.listFiles();
                List<Future<Integer>> results = new ArrayList<>();

                for (File file : files) {
                    if (file.isDirectory()) {
                        /*
                         * This is how to do a recursive call for Callable's call method
                         * because we can't invoke the call() method directly.
                         */
                        MatchCounter counter = new MatchCounter(file, keyword);
                        FutureTask<Integer> task = new FutureTask<>(counter);
                        results.add(task);
                        new Thread(task).start(); // New thread will invoke the call() method.
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
