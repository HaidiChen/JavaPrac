package javaprac.networks;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javaprac.Prac;


public class URLConnectionPrac implements Prac {

    @Override
    public void runPrac() {
        try {
            final String urlName = "http://horstman.com";
            URL url = new URL(urlName);
            URLConnection connection = url.openConnection();
            connection.connect();
            Map<String, List<String>> headers = connection.getHeaderFields();
            headers.forEach((k, v) -> System.out.println(k + " : " + v));

            System.out.println("------");
            System.out.println("getContentType: " + connection.getContentType());
            System.out.println("getContentLength: " + connection.getContentLength());
            System.out.println("getContentEncoding: " + connection.getContentEncoding());
            System.out.println("getDate: " + connection.getDate());
            System.out.println("getExpiration: " + connection.getExpiration());
            System.out.println("getLastModified: " + connection.getLastModified());
            System.out.println("------");

            String encoding = connection.getContentEncoding();
            if (encoding == null) {
                encoding = "UTF-8";
            }

            try (Scanner in = new Scanner(connection.getInputStream(), encoding)) {
                for (int i = 0; i < 10 && in.hasNextLine(); i++) {
                    System.out.println(in.nextLine());
                }
                if (in.hasNextLine()) {
                    System.out.println("...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice networks programming - URLConnection usage.";
    }

}
