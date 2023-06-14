package javaprac.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

import javaprac.Prac;


public class MemoryMapPrac implements Prac {

    private static final String INPUT_FILE_PATH = "src/main/resources/memory_map_prac";

    @Override
    public void runPrac() {
        try {
            long startTime = System.currentTimeMillis();
            Path filename = Paths.get(INPUT_FILE_PATH);

            long crcValue = checksumInputStream(filename);
            long endTime = System.currentTimeMillis();
            System.out.println("checksum by InputStream : " + Long.toHexString(crcValue));
            System.out.println("Time used : \t" + (endTime - startTime) + " milliseconds");

            startTime = System.currentTimeMillis();
            crcValue = checksumBufferedInputStream(filename);
            endTime = System.currentTimeMillis();
            System.out.println("checksum by BufferedInputStream : " + Long.toHexString(crcValue));
            System.out.println("Time used : \t" + (endTime - startTime) + " milliseconds");

            startTime = System.currentTimeMillis();
            crcValue = checksumRandomAccessFile(filename);
            endTime = System.currentTimeMillis();
            System.out.println("checksum by RandomAccessFile : " + Long.toHexString(crcValue));
            System.out.println("Time used : \t" + (endTime - startTime) + " milliseconds");

            startTime = System.currentTimeMillis();
            crcValue = checksumMappedFile(filename);
            endTime = System.currentTimeMillis();
            System.out.println("checksum by FileChannel : " + Long.toHexString(crcValue));
            System.out.println("Time used : \t" + (endTime - startTime) + " milliseconds");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice I/O - use memory map to operate files.";
    }

    private long checksumInputStream(Path filename) throws IOException {
        try (InputStream in = Files.newInputStream(filename)) {
            CRC32 crc = new CRC32();

            int c;
            while ((c = in.read()) != -1) {
                crc.update(c);
            }

            return crc.getValue();
        }
    }

    private long checksumBufferedInputStream(Path filename) throws IOException {
        try (InputStream in = new BufferedInputStream(Files.newInputStream(filename))) {
            CRC32 crc = new CRC32();

            int c;
            while ((c = in.read()) != -1) {
                crc.update(c);
            }

            return crc.getValue();
        }
    }

    private long checksumRandomAccessFile(Path filename) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename.toFile(), "r")) {
            long length = file.length();
            CRC32 crc = new CRC32();

            for (int p = 0; p < length; p++) {
                file.seek(p);
                int c = file.readByte();
                crc.update(c);
            }

            return crc.getValue();
        }
    }

    private long checksumMappedFile(Path filename) throws IOException {
        try (FileChannel channel = FileChannel.open(filename)) {
            CRC32 crc = new CRC32();
            int length = (int) channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);

            for (int p = 0; p < length; p++) {
                int c = buffer.get(p);
                crc.update(c);
            }

            return crc.getValue();
        }
    }
}
