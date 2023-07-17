package javaprac.rpc.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import java.util.*;
import java.util.concurrent.*;

import javaprac.Prac;


public class RmiPrac implements Prac {

    private static final int PORT = 8801;
    private static final String SERVICE_ENDPOINT = "rmi://localhost:" + PORT + "/helloService";

    @Override
    public void runPrac() {
        try {
            Thread serviceThread = new Thread(() -> {
                try {
                    startService();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            serviceThread.start();
            serviceThread.join();

            ExecutorService pool = Executors.newCachedThreadPool();
            for (int i = 0; i < 20; i++) {
                pool.execute(() -> {
                    try {
                        String name = "EML" + new Random().nextInt(100);
                        callService(name);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            pool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Learn RPC - RMI";
    }

    private void startService()
            throws RemoteException, AlreadyBoundException, MalformedURLException {
        HelloService helloService = new HelloServiceImpl();

        LocateRegistry.createRegistry(PORT);
        Naming.bind(SERVICE_ENDPOINT, helloService);
        System.out.println("[RPC] helloService is ready.");
    }

    private void callService(String name)
            throws RemoteException, NotBoundException, MalformedURLException {
        HelloService helloService = (HelloService) Naming.lookup(SERVICE_ENDPOINT);
        System.out.println(helloService.sayHi(name));
    }
}
