package javaprac.gof.behavioral.visitor;

import java.util.*;


public class Phone implements Visitable {

    private final List<String> contacts;

    public Phone() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(String contact) {
        contacts.add(contact);
    }

    public List<String> getContacts() {
        return contacts;
    }
}
