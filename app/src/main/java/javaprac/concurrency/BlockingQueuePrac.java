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
    private static final String INPUT_FILE_PATH = "src/main/resources/blocking_queue_prac";

    @Override
    public void runPrac() {
        searchKeywordInFiles();
        //simpleProducerConsumer();
    }

    @Override
    public String getDescription() {
        return "Practice concurrent programming - Blocking queues usage.";
    }

    private void searchKeywordInFiles() {
        try (Scanner in = new Scanner(new File(INPUT_FILE_PATH))) {
            String directory = in.nextLine();
            String keyword = in.nextLine();

            Runnable enumerator = () -> {
                try {
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                } catch (InterruptedException e) {
                }
            };
            // One producer
            new Thread(enumerator).start();

            // Number of 'SEARCH_THREADS' consumers
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void simpleProducerConsumer() {
        // Use blocking queue to store items.
        BlockingQueue<String> itemQueue = new ArrayBlockingQueue<>(10);

        Runnable produce = produceItems(itemQueue);
        Runnable consume = consumeItems(itemQueue);

        /*
         * Create threads for producers & consumers.
         * We could use different ways to create threads.
         *
         * 1. Use new Thread way to create.
         */
        //new Thread(produce).start();
        //for (int threadNumber = 0; threadNumber < 20; threadNumber++) {
        //    new Thread(consume).start();
        //}

        // 2. Use ThreadPool way to create.
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(produce);
        pool.submit(consume);
    }

    private Runnable produceItems(BlockingQueue<String> itemQueue) {
        Runnable producerTask = () -> {
            while (true) {
                for (int i = 0; i < 400; i++) {
                    String item = Thread.currentThread() + " at time: " + System.nanoTime();
                    // add() will throw IllegalStateException if blocking queue is full.
                    //itemQueue.add(item);

                    // offer() will return false if blocking queue is full.
                    System.out.println("offer() succeeded ? " + itemQueue.offer(item));
                    //try {
                    //    Thread.sleep(1000);
                    //} catch (InterruptedException e) {
                    //    e.printStackTrace();
                    //}

                    // put() will block the execution if blocking queue is full.
                    //try {
                    //    itemQueue.put(item);
                    //} catch (InterruptedException e) {
                    //    e.printStackTrace();
                    //}

                    //System.out.println("Sent item " + item + " to blocking queue.");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        return producerTask;

    }

    private Runnable consumeItems(BlockingQueue<String> itemQueue) {
        Runnable consumerTask = () -> {
            for (;;) {
                String item = "placeholder";
                // remove() will throw NoSuchElementException if blocking queue is empty.
                //item = itemQueue.remove();

                // poll() will return null if blocking queue is empty.
                //item = itemQueue.poll();

                // take() will block the execution if blocking queue is empty.
                try {
                    item = itemQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + " got item from queue: " + item);
            }
        };

        return consumerTask;
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
