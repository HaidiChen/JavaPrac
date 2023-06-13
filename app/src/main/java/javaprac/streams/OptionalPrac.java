package javaprac.streams;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

import javaprac.Prac;


public class OptionalPrac implements Prac {

    private static final String INPUT_FILE_PATH = "./src/main/resources/optional_prac";

    @Override
    public void runPrac() {
        try {
            Path inputFilePath = Prac.getCwd().resolve(INPUT_FILE_PATH);
            String contents = new String(Files.readAllBytes(inputFilePath), StandardCharsets.UTF_8);

            List<String> wordList = Arrays.asList(contents.split("\\PL+"));

            Optional<String> optionalValue = wordList.stream()
                .filter(s -> s.contains("fred"))
                .findFirst();
            System.out.println(optionalValue.orElse("No word") + " contains fred");

            optionalValue = wordList.stream()
                .filter(s -> s.contains("red"))
                .findFirst();
            optionalValue.ifPresent(s -> System.out.println(s + " contains red"));

            Set<String> results = new HashSet<>();
            optionalValue.ifPresent(results::add);
            Optional<Boolean> added = optionalValue.map(results::add);
            System.out.println(added + ", " + results);

            Optional<String> optionalString = Optional.empty();
            String result = optionalString.orElse("N/A");
            System.out.println("result: " + result);
            result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
            System.out.println("result: " + result);

            try {
                result = optionalString.orElseThrow(IllegalStateException::new);
                System.out.println("result: " + result);
            } catch (Throwable t) {
                t.printStackTrace();
            }

            System.out.println(inverse(4.0).flatMap(this::squareRoot));
            System.out.println(inverse(-1.0).flatMap(this::squareRoot));
            System.out.println(inverse(0.0).flatMap(this::squareRoot));
            Optional<Double> result2 = Optional.of(-4.0)
                .flatMap(this::inverse)
                .flatMap(this::squareRoot);
            System.out.println(result2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice streams - Optional usage.";
    }

    private Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    private Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
