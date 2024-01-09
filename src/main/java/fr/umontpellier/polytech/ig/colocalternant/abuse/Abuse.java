package fr.umontpellier.polytech.ig.colocalternant.abuse;

import fr.umontpellier.polytech.ig.colocalternant.user.User;

/**
 * 
 */
public class Abuse {

    /**
     * Default constructor
     */
    public Abuse(int id, String message, User dest, StatusEnum status) {
        this.id = id;
        this.message = message;
        this.dest = dest;
        this.status = status;
    }

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    private String message;

    /**
     * 
     */
    private User dest;

    /**
     * 
     */
    private StatusEnum status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getDest() {
        return dest;
    }

    public void setDest(User dest) {
        this.dest = dest;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}