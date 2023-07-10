package javaprac.gof.behavioral.visitor;

import java.util.*;


public class PhoneVisitor implements Visitor {

    @Override
    public void visit(Visitable phone) {
        List<String> contacts = ((Phone) phone).getContacts();
        if (contacts.contains("Kitty")) {
            contacts.remove("Kitty");
            contacts.add("Helly");
        }
    }
}
