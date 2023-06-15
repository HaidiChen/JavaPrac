package javaprac.networks;

import java.io.*;
import java.net.*;
import java.util.*;

import javaprac.Prac;


public class SocketPrac implements Prac {

    @Override
    public void runPrac() {
        try (Socket s = new Socket("time-a.nist.gov", 13);
                Scanner in = new Scanner(s.getInputStream(), "UTF-8")) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice networks programming - Socket usage.";
    }
}
