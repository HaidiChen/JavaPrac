package javaprac.gof.behavioral.visitor;

import javaprac.Prac;


public class VisitorPrac implements Prac {

    @Override
    public void runPrac() {
        Phone phone = new Phone();
        phone.addContact("Hello");
        phone.addContact("Kitty");
        phone.addContact("Wondy");

        System.out.println("before visiting:");
        phone.getContacts().forEach(System.out::println);

        Visitor phoneVisitor = new PhoneVisitor();

        System.out.println("after visiting:");
        phone.accept(phoneVisitor);
        phone.getContacts().forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [behavioral] Visitor";
    }
}
