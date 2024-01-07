package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.chat.ChatFacade;
import fr.umontpellier.polytech.ig.colocalternant.chat.Chat;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * The controller of the list of users who talk to each other
 */
public class ChatController {

    /**
     * The table that contains all the existent chats with the user
     */
    @FXML
    TableView<User> tableView;

    /**
     * Handles when the user loads the view.
     */
    @FXML
    public void initialize() {
        onCreation();
    }

    /**
     * Create the TableView and the columns.
     * Shows the list of all users who talk with the current user.
     */
    public void onCreation() {
        List<Chat> chats = ChatFacade.getInstance().getChatsOf(UserFacade.getInstance().getCurrentUser());
        List<User> users = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        // get all the users who talk with the current user once
        for(Chat chat : chats) {
            // System.out.println("Sender: " + chat.getSender().getId() + " " + chat.getSender().getFirstName() + " " + chat.getSender().getLastName());
            // System.out.println("Dest: " + chat.getDest().getId() + " " + chat.getDest().getFirstName() + " " + chat.getDest().getLastName());
            User user1 = chat.getSender();
            User user2 = chat.getDest();
            if(user1.getId() == UserFacade.getInstance().getCurrentUser().getId()) {
                // user1 is the current user
                // if the user2 is not already in the list, add it
                if(!ids.contains(user2.getId())){
                    users.add(user2);
                    ids.add(user2.getId());
                }
            }
            else {
                // user2 is the current user
                // if the user1 is not already in the list, add it
                if(!ids.contains(user1.getId())){
                    users.add(user1);
                    ids.add(user1.getId());
                }
            }
        }
        for(User user : users) {
            System.out.println(user.getFirstName());
        }
        // Create TableView
        tableView.setItems(FXCollections.observableArrayList(users));
        // Create columns
        TableColumn<User, String> firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getFirstName()));
        TableColumn<User, String> lastNameColumn = new TableColumn<>("Last name");
        lastNameColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getLastName()));
        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getEmail()));
        TableColumn<User, Button> goToChatColumn = new TableColumn<>("Go to chat");
        goToChatColumn.setCellValueFactory(cell ->
            new SimpleObjectProperty<>(createChatMessageButton(cell.getValue()))
        );

        // Set columns width
        firstNameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        lastNameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        emailColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        goToChatColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));

        // Add columns to the table
        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, emailColumn, goToChatColumn);
    }

    private Button createChatMessageButton(User user) {
        Button button = new Button("Chat");
        button.setOnAction(event -> {
            // open the chat window with the related user
            ChatMessageController.personToChatWith = user;
            try {
                FXRouter.goTo("chat-message");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return button;
    }
}
