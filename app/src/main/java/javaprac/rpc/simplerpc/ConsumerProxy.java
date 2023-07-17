package javaprac.rpc.simplerpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.*;
import java.net.Socket;


public class ConsumerProxy {

    public static <T> T getService(
            final Class<T> interfaceClass, final String host, final int port) throws Exception {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                                          new Class<?>[]{interfaceClass},
                                          new ServiceHandler(host, port));
    }

    private static final class ServiceHandler implements InvocationHandler {

        private final String host;
        private final int port;

        public ServiceHandler(String host, int port) {
            this.host = host;
            this.port = port;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
            try (Socket socket = new Socket(host, port)) {
                try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
                    output.writeUTF(method.getName());
                    output.writeObject(args);

                    try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                        Object result = input.readObject();
                        if (result instanceof Throwable) {
                            throw (Throwable) result;
                        }

                        return result;
                    }
                }
            }
        }
    }
}
