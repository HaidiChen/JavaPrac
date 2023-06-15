package javaprac.networks;

import java.io.*;
import java.net.*;
import java.util.*;

import javaprac.Prac;


public class EchoServerPrac implements Prac {

    @Override
    public void runPrac() {
        try (ServerSocket s = new ServerSocket(8189)) {
            int i = 1;

            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                i++;
                Runnable r = new ThreadedEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice networks programming - A simple Server to echo client's input.";
    }

    private class ThreadedEchoHandler implements Runnable {

        private final Socket client;

        public ThreadedEchoHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try (InputStream inStream = client.getInputStream();
                    OutputStream outStream = client.getOutputStream()) {
                Scanner in = new Scanner(inStream, "UTF-8");
                PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
                out.println("Hello! Enter BYE to exit.");

                boolean done = false;
                while (!done && in.hasNextLine()) {
                    String line = in.nextLine();
                    out.println("Echo : " + line);
                    if (line.trim().equalsIgnoreCase("BYE")) {
                        done = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
