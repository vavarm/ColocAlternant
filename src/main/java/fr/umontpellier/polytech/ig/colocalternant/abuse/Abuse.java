package fr.umontpellier.polytech.ig.colocalternant.abuse;

import fr.umontpellier.polytech.ig.colocalternant.user.User;

/**
 * The Abuse class represents a reported abuse in the system.
 * An abuse includes information such as its unique identifier, a message describing the abuse,
 * the user to whom the abuse is reported, and the status of the abuse.
 */
public class Abuse {

    /**
     * Default constructor for creating an Abuse object.
     *
     * @param id      The unique identifier for the abuse.
     * @param message A String representing the message describing the abuse.
     * @param dest    The User object representing the user to whom the abuse is reported.
     * @param status  The StatusEnum representing the current status of the abuse.
     */
    public Abuse(int id, String message, User dest, StatusEnum status) {
        this.id = id;
        this.message = message;
        this.dest = dest;
        this.status = status;
    }

    /**
     * Retrieves the unique identifier of the abuse.
     *
     * @return The unique identifier of the abuse.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the abuse.
     *
     * @param id The new unique identifier for the abuse.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the message describing the abuse.
     *
     * @return A String representing the message describing the abuse.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message describing the abuse.
     *
     * @param message The new message describing the abuse.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the User object representing the user to whom the abuse is reported.
     *
     * @return The User object representing the user to whom the abuse is reported.
     */
    public User getDest() {
        return dest;
    }

    /**
     * Sets the User object representing the user to whom the abuse is reported.
     *
     * @param dest The new User object representing the user to whom the abuse is reported.
     */
    public void setDest(User dest) {
        this.dest = dest;
    }

    /**
     * Retrieves the current status of the abuse.
     *
     * @return The StatusEnum representing the current status of the abuse.
     */
    public StatusEnum getStatus() {
        return status;
    }

    /**
     * Sets the current status of the abuse.
     *
     * @param status The new StatusEnum representing the current status of the abuse.
     */
    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    // Private members of the Abuse class

    /**
     * The unique identifier of the abuse.
     */
    private int id;

    /**
     * A String representing the message describing the abuse.
     */
    private String message;

    /**
     * The User object representing the user to whom the abuse is reported.
     */
    private User dest;

    /**
     * The StatusEnum representing the current status of the abuse.
     */
    private StatusEnum status;
}
