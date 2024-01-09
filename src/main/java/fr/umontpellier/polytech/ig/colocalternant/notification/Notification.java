package fr.umontpellier.polytech.ig.colocalternant.notification;

public class Notification {
    private int id;
    private int userID;

    private int newItemID;

    public Notification(int id, int userID, int newItemID) {
        this.id = id;
        this.userID = userID;
        this.newItemID = newItemID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getNewItemID() {
        return newItemID;
    }

    public void setNewItemID(int newItemID) {
        this.newItemID = newItemID;
    }
}

