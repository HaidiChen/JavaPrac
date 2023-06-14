package javaprac.streams;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import javaprac.Prac;


public class CollectResultPrac implements Prac {

    private static final String INPUT_FILE_PATH = "src/main/resources/stream_creation_prac";

    @Override
    public void runPrac() {
        try {
            Iterator iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
            while (iter.hasNext()) {
                System.out.println(iter.next());
            }

            Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
            System.out.println("Object array: " + numbers);

            try {
                Integer number = (Integer) numbers[0];
                System.out.println("number: " + number);
                // The following line throws exception
                Integer[] numbers2 = (Integer[]) numbers;
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

            Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
            System.out.println("Integer array: " + numbers3);

            Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
            show("noVowelSet", noVowelSet);

            TreeSet<String> noVowelTreeSet = noVowels()
                .collect(Collectors.toCollection(TreeSet::new));
            show("noVowelTreeSet", noVowelTreeSet);

            String result = noVowels().limit(10).collect(Collectors.joining());
            System.out.println("Joining: " + result);

            result = noVowels().limit(10).collect(Collectors.joining(", "));
            System.out.println("Joining with comma: " + result);

            IntSummaryStatistics summary = noVowels()
                .collect(Collectors.summarizingInt(String::length));
            double averageWordLength = summary.getAverage();
            double maxWordLength = summary.getMax();
            System.out.println("Average word length: " + averageWordLength);
            System.out.println("Max word length: " + maxWordLength);
            System.out.println("forEach: ");
            noVowels().limit(10).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice streams - various ways to collect stream result.";
    }

    private Stream<String> noVowels() throws IOException {
        String contents = new String(
                Files.readAllBytes(Paths.get(INPUT_FILE_PATH)), StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+"));

        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    private <T> void show(String label, Set<T> set) {
        System.out.println(label + ": " + set.getClass().getName());
        System.out.println("[" + set.stream()
                                    .limit(10)
                                    .map(Object::toString)
                                    .collect(Collectors.joining(", "))
                            + "]");
    }
}
