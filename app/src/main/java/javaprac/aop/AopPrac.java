package javaprac.aop;

import javaprac.Prac;


public class AopPrac implements Prac {

    @Override
    public void runPrac() {
        try {
            IOrder order = ObjectFactory.newInstance(Order.class);
            order.pay();
            order.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "AOP practice";
    }
}
