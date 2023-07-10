package javaprac.gof.behavioral.observer;

import java.util.*;


public abstract class Observable {

    private final List<Observer> observers;

    public Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void publishToAll() {
        observers.forEach(observer -> observer.update());
    }
}
