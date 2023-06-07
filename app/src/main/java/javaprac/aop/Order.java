package javaprac.aop;

@Aspect(type=TimeUsageAspect.class)
public class Order implements IOrder {

    public void pay() throws InterruptedException {
        System.out.println("Processing payment");
        Thread.sleep(1000);
    }

    public void show() {
        System.out.println("Here's your order details.");
    }
}
