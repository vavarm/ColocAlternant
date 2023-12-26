package fr.umontpellier.polytech.ig.colocalternant.chat;

import fr.umontpellier.polytech.ig.colocalternant.user.User;
import java.time.LocalDateTime;

public class Chat {
    /**
     * The id of the chat
     */
    private int id;
    /**
     * The sender of the message
     */
    private User sender;
    /**
     * The destination user of the message
     */
    private User dest;
    /**
     * The content of the message
     */
    private String message;
    /**
     * The timestamp of the creation of the message
     */
    private LocalDateTime timestamp;
    /**
     * The boolean to know if the message was deleted
     */
    private boolean isDeleted;

    /**
     * Constructor of the chat
     * @param id
     * @param sender
     * @param dest
     * @param message
     * @param timestamp
     * @param isDeleted
     */
    public Chat(int id, User sender, User dest, String message, LocalDateTime timestamp, boolean isDeleted) {
        this.sender = sender;
        this.dest = dest;
        this.message = message;
        this.timestamp = timestamp;
        this.isDeleted = isDeleted;
    }

    /**
     * Retrieves the unique identifier of the chat.
     * @return The chat's identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the chat.
     * @param id The chat's identifier.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the sender of the message.
     * @return The sender of the message.
     */
    public User getSender() {
        return sender;
    }

    /**
     * Sets the sender of the message.
     * @param sender The sender of the message.
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * Retrieves the destination user of the message.
     * @return The destination user of the message.
     */
    public User getDest() {
        return dest;
    }

    /**
     * Sets the destination user of the message.
     * @param dest The destination user of the message.
     */
    public void setDest(User dest) {
        this.dest = dest;
    }

    /**
     * Retrieves the content of the message.
     * @return The content of the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the content of the message.
     * @param message The content of the message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the timestamp of the creation of the message.
     * @return The timestamp of the creation of the message.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the creation of the message.
     * @param timestamp The timestamp of the creation of the message.
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Retrieves the boolean to know if the message was deleted.
     * @return The boolean to know if the message was deleted.
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the boolean to know if the message was deleted.
     * @param deleted The boolean to know if the message was deleted.
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
