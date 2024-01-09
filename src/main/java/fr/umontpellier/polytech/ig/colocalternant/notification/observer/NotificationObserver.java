package fr.umontpellier.polytech.ig.colocalternant.notification.observer;

import fr.umontpellier.polytech.ig.colocalternant.notification.NotificationFacade;

public class NotificationObserver implements Observer {
    private int userID;
    private int newItemID;

    private NotificationFacade nf = NotificationFacade.getInstance();

    public NotificationObserver() {}

    @Override
    public void update(int userID2, int newItemID2) {
        System.out.println("User " + userID + " notified about: " + newItemID2);
        setUserID((int) userID2);
        setNewItem((int) newItemID2);
        nf.insertNotif(userID2, newItemID2);
    }



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getNewItem() {
        return newItemID;
    }

    public void setNewItem(int newItemID) {
        this.newItemID = newItemID;
    }
}