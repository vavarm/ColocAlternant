package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.chat.Chat;
import fr.umontpellier.polytech.ig.colocalternant.chat.ChatFacade;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
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

    @FXML
    TextField messageField;

    @FXML
    Button sendButton;

    @FXML
    Button refreshButton;

    @FXML
    Button backButton;

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
        // set the title
        title.setText("Chat with " + personToChatWith.getFirstName() + " " + personToChatWith.getLastName());
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
        Label msg = new Label(chat.getMessage());
        String date = chat.getTimestamp().toString();
        // convert the date to dd/MM/yyyy HH:mm:ss
        date = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4) + " " + date.substring(11, 19);
        Label timestamp = new Label(date);
        // put the date in italic
        timestamp.setStyle("-fx-font-style: italic");
        // add a button to delete the message
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            delete(chat);
        });
        // create a new HBox with the timestamp and the delete button
        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(timestamp, deleteButton);
        // add the children to the HBox
        hBox.getChildren().addAll(msg, hBox2);
        // make the HBox take all the width available
        hBox.setMaxWidth(Double.MAX_VALUE);
        // convert the hBox to a flexbox with a space between the elements
        HBox.setHgrow(msg, Priority.ALWAYS);
        HBox.setHgrow(hBox2, Priority.ALWAYS);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox2.setAlignment(Pos.CENTER_RIGHT);
        return hBox;
    }

    /**
     * Handles when the user clicks on the send button.
     */
    public void onSend() {
        // get the message
        String message = messageField.getText();
        if (message.isEmpty()) return;
        // send the message
        this.send(message, UserFacade.getInstance().getCurrentUser(), personToChatWith);
        // clear the message field
        messageField.setText("");
        // update the chat box
        updateChatBox();
    }

    /**
     * Handles when the user clicks on the refresh button.
     */
        public void onRefresh(ActionEvent actionEvent) {
        // update the chat box
        updateChatBox();
    }

    /**
     * Handles when the user clicks on the back button.
     */
    public void onBack(ActionEvent actionEvent) {
        // go back to the list of chats
        try{
            FXRouter.goTo("chat", getProfileID(), false);
        } catch (IOException e){
            System.out.println("Error while going back to the list of chats");
        }
    }

    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }



}
