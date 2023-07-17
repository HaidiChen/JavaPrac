package javaprac.rpc.simplerpc;

import java.io.*;
import java.net.*;

import java.util.concurrent.*;


public class ServiceProvider {

    private static final ExecutorService executors = Executors.newCachedThreadPool();

    public static void provide(Object service, int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("[RPC] DummyService is up.");

        while (true) {
            final Socket clientSocket = serverSocket.accept();

            executors.execute(() -> {
                runTask(service, clientSocket);
            });

        }

    }

    private static void runTask(Object service, Socket clientSocket) {
        try (ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream())) {
            String methodName = input.readUTF();
            Object[] args = (Object[]) input.readObject();

            try (ObjectOutputStream output = new ObjectOutputStream(
                        clientSocket.getOutputStream())) {
                try {
                    Object result = service.getClass()
                        .getMethod(methodName, String.class)
                        .invoke(service, args);
                    output.writeObject(result);
                } catch (Throwable t) {
                    output.writeObject(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
