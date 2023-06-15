package javaprac.networks;

import java.io.*;
import java.net.*;
import java.util.*;

import javaprac.Prac;


public class InetAddressPrac implements Prac {

    private static final String INPUT_FILE_PATH = "src/main/resources/inetaddress_prac";

    @Override
    public void runPrac() {
        try (Scanner in = new Scanner(new FileInputStream(INPUT_FILE_PATH), "UTF-8")) {
            String host = in.nextLine();

            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress a : addresses) {
                System.out.println(a);
            }

            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("localhost inetaddress : " + localhost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice networks programming - InetAddress usage.";
    }
}
