package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class UserListController {

    @FXML
    VBox box;

    /**
     * Handles when the user loads the view.
     */
    @FXML
    public void initialize() {
        // Initialiser votre TableView ici
        onCreation();
    }

    /**
     * Create the TableView and add it to the VBox.
     * Shows the list of all users.
     */
    public void onCreation() {
        ArrayList<User> users = UserFacade.getInstance().getAllUsers();

        // Create TableView
        TableView<User> tableView = new TableView<>();
        tableView.setItems(FXCollections.observableArrayList(users));

        // Create columns
        TableColumn<User, String> firstNameColumn = new TableColumn<>("FirstName");
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getFirstName() + " " + cellData.getValue().getLastName()));

        TableColumn<User, String> lastNameColumn = new TableColumn<>("LastName");
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getLastName()));

        TableColumn<User, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, ImageView> photoColumn = new TableColumn<>("Photo");
        photoColumn.setCellValueFactory(cellData -> {
            return new TableCell<User, ImageView>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(ImageView item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        setGraphic(item);
                    }
                }
            }.itemProperty();
        });
        photoColumn.setCellValueFactory(cellData -> {
            ImageView imageView = new ImageView();
            try {
                imageView.setImage(new Image(cellData.getValue().getPhoto()));
            } catch (IllegalArgumentException e) {
                imageView.setImage(new Image("https://www.freeiconspng.com/uploads/no-image-icon-4.png"));
            }
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            return new SimpleObjectProperty<>(imageView);
        });

        // Set column widths
        firstNameColumn.setMinWidth(200);
        lastNameColumn.setMinWidth(200);
        ageColumn.setMinWidth(50);
        emailColumn.setMinWidth(150);
        photoColumn.setMinWidth(150);

        // Add columns to TableView
        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn, emailColumn, photoColumn);

        box.getChildren().add(tableView);
    }
}
