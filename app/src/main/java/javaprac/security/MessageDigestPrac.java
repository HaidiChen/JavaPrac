package javaprac.security;

import java.io.*;
import java.nio.file.*;
import java.security.*;

import javaprac.Prac;


public class MessageDigestPrac implements Prac {

    private static final String INPUT_FILE_PATH = "src/main/resources/message_digest_prac";

    @Override
    public void runPrac() {
        try {
            MessageDigest sha1Algo = MessageDigest.getInstance("SHA-1");
            MessageDigest md5Algo = MessageDigest.getInstance("MD5");

            byte[] input = Files.readAllBytes(Paths.get(INPUT_FILE_PATH));
            byte[] hash = sha1Algo.digest(input);
            String d = "";

            for (int i = 0; i < hash.length; i++) {
                int v = hash[i] & 0xFF;
                if (v < 16) {
                    d += "0";
                }
                d += Integer.toString(v, 16).toUpperCase() + " ";
            }
            System.out.println("SHA-1 :");
            System.out.println(d);

            hash = md5Algo.digest(input);
            d = "";

            for (int i = 0; i < hash.length; i++) {
                int v = hash[i] & 0xFF;
                if (v < 16) {
                    d += "0";
                }
                d += Integer.toString(v, 16).toUpperCase() + " ";
            }
            System.out.println("MD5 :");
            System.out.println(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Learn security - message digest encrypted with SHA or MD5";
    }
}
