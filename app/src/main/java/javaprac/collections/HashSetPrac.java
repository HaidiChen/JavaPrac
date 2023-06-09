package javaprac.collections;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import javaprac.Prac;


public class HashSetPrac implements Prac {

    @Override
    public void runPrac() {
        Set<String> words = new HashSet<>();
        long[] totalTime = new long[1];

        String filePath = "/home/haidchen/Documents/pg.txt";
        try (SeekableByteChannel sbc = Files.newByteChannel(Paths.get(filePath))) {
            final int BUFFER_CaPACITY = 1024;
            ByteBuffer buf = ByteBuffer.allocate(BUFFER_CaPACITY);

            while (sbc.read(buf) > 0) {
                buf.flip();
                String content = Charset.defaultCharset().decode(buf).toString();
                Stream.of(content.split(" ")).forEach(word -> {
                    long callTime = System.nanoTime();
                    words.add(word);
                    callTime = System.nanoTime() - callTime;
                    totalTime[0] += callTime;
                });
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<String> iter = words.iterator();
        for (int i = 1; i <= 20 && iter.hasNext(); i++) {
            System.out.println(iter.next());
        }
        System.out.println("...");
        System.out.println(words.size() + "distinct words. " + totalTime + " nanoseconds.");
    }

    @Override
    public String getDescription() {
        return "Practice HashSet usage.";
    }
}
