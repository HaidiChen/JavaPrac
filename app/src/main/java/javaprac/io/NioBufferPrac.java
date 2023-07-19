package javaprac.io;

import java.nio.*;

import javaprac.Prac;


public class NioBufferPrac implements Prac {

    @Override
    public void runPrac() {
        String content = "你好！Java Non-Blocking I/O.";
        CharBuffer buffer = CharBuffer.allocate(50);

        for (int i = 0, n = content.length(); i < n; i++) {
            buffer.put(content.charAt(i));
        }

        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }

        buffer.rewind();
        System.out.println();

        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println();

        buffer.clear();
        buffer.put('你')
              .put('好')
              .put('！');
        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println();
    }

    @Override
    public String getDescription() {
        return "Learn I/O - Nonblocking I/O, buffer";
    }
}
