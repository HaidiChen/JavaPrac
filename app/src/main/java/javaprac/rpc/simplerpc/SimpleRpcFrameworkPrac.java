package javaprac.rpc.simplerpc;

import java.util.*;
import java.util.concurrent.*;

import javaprac.Prac;


public class SimpleRpcFrameworkPrac implements Prac {

    @Override
    public void runPrac() {
        try {
            Thread serviceThread = new Thread(() -> {
                startService();
            });
            serviceThread.start();

            System.out.println("calling service");
            ExecutorService pool = Executors.newCachedThreadPool();

            for (int i = 0; i < 20; i++) {
                pool.execute(() -> {
                    try {
                        callService("EML" + new Random().nextInt(1_000));
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
        return "Learn RPC - Simple RPC framework";
    }

    private void startService() {
        try {
            DummyService service = new DummyServiceImpl();
            ServiceProvider.provide(service, 8880);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callService(String name) throws Exception {
        DummyService dummyService = ConsumerProxy.getService(DummyService.class, "localhost", 8880);
        System.out.format("Lucky Number for %s: %d\n", name, dummyService.luckyNumber(name));
    }
}
