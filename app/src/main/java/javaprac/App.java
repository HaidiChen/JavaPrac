package javaprac;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javaprac.aop.IOrder;
import javaprac.aop.ObjectFactory;
import javaprac.aop.Order;
import javaprac.interfaces.*;

public class App {

    public static void main(String[] args) throws Exception {

        List<Prac> ALL_PRACS = List.of(
            new ProxyPrac(),
            //new AnonymousInnerClassPrac(),
            //new InnerClassPrac(),
            //new LambdaPrac(),
            //new CallBackPrac(),
            //new ComparatorPrac(),
            new CloneablePrac()
        );

        ALL_PRACS.forEach(prac -> prac.run());

    }

    private static void streamPrac() throws ExecutionException, InterruptedException {
        var r = new Random();

        var list = IntStream.range(0, 1_000_000)
            .map(x -> r.nextInt(10_000_000))
            .boxed()
            .collect(Collectors.toList());

        var t0 = System.currentTimeMillis();
        System.out.println("max: " + list.stream().max((a, b) -> a - b));
        var time = System.currentTimeMillis() - t0;
        System.out.println("time: " + time);

        var t1 = System.currentTimeMillis();
        var max = list.stream().parallel().max((a, b) -> a - b);
        time = System.currentTimeMillis() - t1;
        System.out.println("time with default pool: " + time + ", max: " + max);

        var pool = new ForkJoinPool(2);
        var t2 = System.currentTimeMillis();
        max = pool.submit(() -> list.parallelStream().max((a, b) -> a - b)).get();
        time = System.currentTimeMillis() - t2;
        System.out.println("time with custom pool: " + time + ", max: " + max);
    }

    private static void aopPrac() throws Exception {
        IOrder order = ObjectFactory.newInstance(Order.class);

        order.pay();
        order.show();
    }

    public String getGreeting() {
        return "Hello World!";
    }

}
