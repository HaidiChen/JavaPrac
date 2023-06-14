package javaprac.streams;

import static java.util.stream.Collectors.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import javaprac.Prac;


public class ParallelStreamPrac implements Prac {

    private static final String INPUT_FILE_PATH = "src/main/resources/parallel_stream_prac";

    @Override
    public void runPrac() {
        try {
            String contents = new String(
                    Files.readAllBytes(Paths.get(INPUT_FILE_PATH)), StandardCharsets.UTF_8);
            List<String> wordList = Arrays.asList(contents.split("\\PL+"));

            // very bad code ahead, output will be different from each time the code is run.
            for (int i = 0; i < 3; i++) {
                int[] shortWords = new int[10];
                wordList.parallelStream().forEach(s -> {
                    if (s.length() < 10) {
                        shortWords[s.length()]++;
                    }
                });
                System.out.println(Arrays.toString(shortWords));
            }

            // Remedy - group and count
            for (int i = 0; i < 3; i++) {
                Map<Integer, Long> shortWordCounts = wordList
                    .parallelStream()
                    .filter(s -> s.length() < 10)
                    .collect(groupingBy(String::length, counting()));
                System.out.println(shortWordCounts);
            }

            // Downstream order not deterministic
            for (int i = 0; i < 3; i++) {
                Map<Integer, List<String>> result = wordList
                    .parallelStream()
                    .collect(groupingByConcurrent(String::length));

                System.out.println((i + 1) + " : " + result.get(14));
            }

            for(int i = 0; i < 3; i++) {
                Map<Integer, Long> wordCounts = wordList
                    .parallelStream()
                    .collect(groupingByConcurrent(String::length, counting()));

                System.out.println(wordCounts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice streams - parallel stream usage.";
    }
}
