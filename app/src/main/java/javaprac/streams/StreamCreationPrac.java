package javaprac.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.List;
import java.util.stream.*;
import java.math.BigInteger;

import javaprac.Prac;


public class StreamCreationPrac implements Prac {

    private static final String INPUT_FILE_PATH = "src/main/resources/stream_creation_prac";

    @Override
    public void runPrac() {
        try {
            String contents = new String(
                    Files.readAllBytes(Paths.get(INPUT_FILE_PATH)), StandardCharsets.UTF_8);

            Stream<String> words = Stream.of(contents.split("\\PL+"));
            show("words", words);

            Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
            show("wordsAnotherWay", wordsAnotherWay);

            try (Stream<String> lines = Files.lines(
                        Paths.get(INPUT_FILE_PATH), StandardCharsets.UTF_8)) {
                show("lines", lines);
            }

            Stream<String> song = Stream.of("Gently", "down", "the", "stream");
            show("song", song);

            Stream<String> silence = Stream.empty();
            show("silence", silence);

            /*
             * The generate() and iterate() methods create inifinite number of elements.
             * So be careful when using them.
             */
            Stream<String> echos = Stream.generate(() -> "Echo");
            show("echos", echos);

            Stream<Double> randoms = Stream.generate(Math::random);
            show("randoms", randoms);

            /*
             * iterate() creates an ordered list of elements.
             * e.g., the following line creates a infinite sequence of 1,2,3,4,5,6,7...
             */
            Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE,
                                                         n -> n.add(BigInteger.ONE));
            show("integers", integers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice streams - various ways to create Stream.";
    }

    private <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream
            .limit(SIZE + 1)
            .collect(Collectors.toList());
        System.out.println(title + ": ");

        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            if (i < SIZE) {
                System.out.print(firstElements.get(i));
            } else {
                System.out.print(" ...");
            }
        }
        System.out.println();

    }
}
