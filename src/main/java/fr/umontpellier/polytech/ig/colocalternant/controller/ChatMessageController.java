package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.chat.Chat;
import fr.umontpellier.polytech.ig.colocalternant.chat.ChatFacade;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The controller of the list of chats
 */
public class ChatMessageController {

    public static User personToChatWith;

    @FXML
    Label title;

    @FXML
    ScrollPane scrollPane;

    @FXML
    VBox chatBox;

    /**
     * Deletes a chat
     * @param chat
     */
    public void delete(Chat chat) {
        ChatFacade.getInstance().delete(chat);
        updateChatBox();
    }

    /**
     * Retrieves the chats between two users
     * @param current
     * @param second
     */
    public List<Chat> getChatsWith(User current, User second) {
        List<Chat> chats = ChatFacade.getInstance().getChatsWith(current, second);
        return chats;
    }

    /**
     * Sends a message to another user
     * @param message
     * @param current
     * @param dest
     */
    public void send(String message, User current, User dest) {
        ChatFacade.getInstance().send(message, current, dest);
        updateChatBox();
    }

    /**
     * Handles when the user loads the view.
     */
    @FXML
    public void initialize() {
        onCreation();
    }

    /**
     * Create an item for each chat message.
     * Shows the list messages between the current user and the person to chat with.
     */
    private void onCreation() {
        updateChatBox();
    }

    private void updateChatBox(){
        // get all chats
        List<Chat> chats = this.getChatsWith(UserFacade.getInstance().getCurrentUser(), personToChatWith);
        // print all the chats
        System.out.println("Chats:");
        for(Chat chat : chats) {
            System.out.println(chat.getId() + " " + chat.getSender().getFirstName() + " " + chat.getDest().getFirstName() + " " + chat.getMessage() + " " + chat.getTimestamp() + " " + chat.isDeleted());
        }
        System.out.println();
        // remove all the children of the VBox
        chatBox.getChildren().clear();
        // create a HBox for each chat
        for(Chat chat : chats) {
            // add the HBox to the view only if the message is not deleted
            if(!chat.isDeleted()) {
                System.out.println("Create item for: " + chat.getId());
                HBox hBox = this.createItem(chat);
                chatBox.getChildren().add(hBox);
            }
        }
        // scroll to the bottom of the VBox
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }

    private HBox createItem(Chat chat) {
        HBox hBox = new HBox();
        // if the current user is the sender, then the message box is grey
        // if the current user is the dest, then the message box is blue
        if(chat.getSender().getId() == UserFacade.getInstance().getCurrentUser().getId()) {
            hBox.setStyle("-fx-background-color: #d3d3d3");
        }
        else {
            hBox.setStyle("-fx-background-color: #add8e6");
        }
        // add the message and the timestamp
        Label msg = new Label("Message: " + chat.getMessage());
        // create a separator between the message and the timestamp
        Separator separator = new Separator();
        separator.setPrefWidth(10);
        separator.setPrefHeight(10);
        String date = chat.getTimestamp().toString();
        Label timestamp = new Label("Date: " + date);
        // add a button to delete the message
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            delete(chat);
        });
        hBox.getChildren().addAll(msg, separator, timestamp, deleteButton);
        // make the children of the HBox grow to fill the space
        HBox.setHgrow(msg, Priority.ALWAYS);
        // make the HBox take all the width available
        hBox.setMaxWidth(Double.MAX_VALUE);
        return hBox;
    }
}
