package javaprac.security;

import java.io.*;


/*
 * To run this class in gradle, use command like below, change args if needed:
 *
 * $ ./gradlew encryptClass --args "build/classes/java/main/javaprac/streams/StreamCreationPrac.class build/classes/java/main/javaprac/streams/StreamCreationPrac.caesar 31"
 */

public class Caesar {

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("USAGE: java javaprac.security.Caesar in out key");
            return;
        }

        try (FileInputStream in = new FileInputStream(args[0]);
                FileOutputStream out = new FileOutputStream(args[1])) {
            int key = Integer.parseInt(args[2]);
            int ch;

            while ((ch = in.read()) != -1) {
                byte c = (byte) (ch + key);
                out.write(c);
            }
        }
    }
}
