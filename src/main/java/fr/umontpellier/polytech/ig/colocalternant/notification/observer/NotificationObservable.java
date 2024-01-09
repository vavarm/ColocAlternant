package fr.umontpellier.polytech.ig.colocalternant.notification.observer;

import java.util.ArrayList;
import java.util.List;
//import java.util.Observer;

public class NotificationObservable {
    private int userID;
    private int newItemID;
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(userID,newItemID);
        }
    }

    public void setNotification(int userID, int newItemID) {
        this.userID = userID;
        this.newItemID = newItemID;
        notifyObservers();
    }
}
