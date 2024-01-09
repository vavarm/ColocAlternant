package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Controller of the user list view.
 */
public class UserListController {

    /**
     * The VBox of the view
     */
    @FXML
    VBox box;

    @FXML
    public Button backButton;

    /**
     * Handles when the user loads the view.
     */
    @FXML
    public void initialize() {
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

        TableColumn<User, Boolean> isBannedColumn = new TableColumn<>("IsBanned");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("isBanned"));

        TableColumn<User, VBox> actionsButtonColumn = new TableColumn<>("Actions");
        actionsButtonColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(createButtonContainer(cellData.getValue())));

        // Set column widths
        firstNameColumn.setMinWidth(200);
        lastNameColumn.setMinWidth(200);
        ageColumn.setMinWidth(50);
        emailColumn.setMinWidth(150);
        photoColumn.setMinWidth(150);
        isBannedColumn.setMinWidth(50);

        actionsButtonColumn.setMinWidth(100);

        // Add columns to TableView
        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn, emailColumn, photoColumn, isBannedColumn, actionsButtonColumn);

        box.getChildren().add(tableView);
    }


    /**
     * Create a VBox to list the actions for the given user.
     *
     * @param user The user.
     * @return The VBox.
     */
    private VBox createButtonContainer(User user) {
        Button banButton = createBanButton(user);
        Button unbanButton = createUnBanButton(user);

        VBox buttonContainer = new VBox(banButton, unbanButton);
        buttonContainer.setSpacing(5); // Optional: Set the spacing between buttons

        return buttonContainer;
    }

    /**
     * Create a button to ban the given user.
     *
     * @param user The user.
     * @return The button.
     */
    private Button createBanButton(User user) {
        Button button = new Button("Ban");

        button.setOnAction(event -> {
            UserFacade.getInstance().banUser(user);
        });

        return button;
    }

    /**
     * Create a button to unban the given user.
     *
     * @param user The user.
     * @return The button.
     */
    private Button createUnBanButton(User user) {
        Button button = new Button("UnBan");

        button.setOnAction(event -> {
            UserFacade.getInstance().unBanUser(user);
        });

        return button;
    }

    public void handleBackButtonClick(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("main", getProfileID(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }
}
