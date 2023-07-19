package javaprac.io;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

import javaprac.Prac;


public class NioChannelPrac implements Prac {

    private static final String SRC_PATH = "src/main/resources/nio_channel_prac";
    private static final String DEST_PATH = "src/main/resources/nio_channel_prac_copy";

    @Override
    public void runPrac() {
        //copyFile();
        copyFile2();
    }

    @Override
    public String getDescription() {
        return "Learn I/O - Nonblocking I/O, channel";
    }

    private void copyFile() {
        try (FileInputStream fin = new FileInputStream(SRC_PATH);
                FileChannel finChannel = fin.getChannel();
                FileOutputStream fout = new FileOutputStream(DEST_PATH);
                FileChannel foutChannel = fout.getChannel()) {
            finChannel.transferTo(0, finChannel.size(), foutChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copyFile2() {
        try (FileInputStream fin = new FileInputStream(SRC_PATH);
                FileChannel finChannel = fin.getChannel();
                FileOutputStream fout = new FileOutputStream(DEST_PATH);
                FileChannel foutChannel = fout.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = finChannel.read(buffer);

            while (bytesRead != -1) {
                buffer.flip();

                while (buffer.hasRemaining()) {
                    foutChannel.write(buffer);
                }

                buffer.clear();
                bytesRead = finChannel.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
