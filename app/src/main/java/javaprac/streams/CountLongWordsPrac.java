package javaprac.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javaprac.Prac;


public class CountLongWordsPrac implements Prac {

    private static final String INPUT_FILE_PATH = "src/main/resources/count_long_words_prac";
    private static final int WORD_MAX_LEN = 12;

    @Override
    public void runPrac() {
        String contents = null;
        try {
            contents = new String(
                    Files.readAllBytes(Paths.get(INPUT_FILE_PATH)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        System.out.println("Calculate number of words in " + INPUT_FILE_PATH + " by iterations.");
        long count = 0;
        for (String w : words) {
            if (w.length() > WORD_MAX_LEN) {
                count++;
            }
        }
        System.out.println("result: " + count);

        System.out.println("Calculate number of words in " + INPUT_FILE_PATH + " by stream.");
        count = words.stream()
                     .filter(w -> w.length() > WORD_MAX_LEN)
                     .count();
        System.out.println("result: " + count);

        System.out.println("Now " + INPUT_FILE_PATH + " by parallel stream.");
        count = words.parallelStream()
                     .filter(w -> w.length() > WORD_MAX_LEN)
                     .count();
        System.out.println("result: " + count);
    }

    @Override
    public String getDescription() {
        return "Practice stream - count long words in a file.";
    }
}
