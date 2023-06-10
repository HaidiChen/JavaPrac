package javaprac.concurrency;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import javaprac.Prac;


public class BlockingQueuePrac implements Prac {

    private static final int FILE_QUEUE_SIZE = 3;
    private static final int SEARCH_THREADS = 100;
    private static final File DUMMY = new File("");
    private static final BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    @Override
    public void runPrac() {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory (e.g. /opt/jdkxx/src):");
            String directory = in.nextLine();
            System.out.println("Enter keyword (e.g., volatile):");
            String keyword = in.nextLine();

            Runnable enumerator = () -> {
                try {
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                } catch (InterruptedException e) {
                }
            };
            new Thread(enumerator).start();

            for (int i = 1; i <= SEARCH_THREADS; i++) {
                Runnable searcher = () -> {
                    try {
                        boolean done = false;
                        while (!done) {
                            File file = queue.take();
                            if (file == DUMMY) {
                                queue.put(file);
                                done = true;
                            } else {
                                search(file, keyword);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
                new Thread(searcher).start();
            }
        }
    }

    @Override
    public String getDescription() {
        return "Practice concurrent programming - Blocking queues usage.";
    }

    private void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                enumerate(file);
            } else {
                queue.put(file);
            }
        }
    }

    private void search(File file, String keyword) throws IOException {
        try (Scanner in = new Scanner(file, "utf-8")) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    System.out.printf("%s/:%d:%s%n", file.getPath(), lineNumber, line);
                }
            }
        }
    }
}
