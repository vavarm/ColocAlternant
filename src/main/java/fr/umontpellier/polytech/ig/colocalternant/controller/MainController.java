package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.profile.ProfileFacade;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainController {

    @FXML
    public Text firstName;

    @FXML
    public Button list;
    public Button chatButton;

    @FXML
    public Button settingsButton;

    @FXML
    public Button userListButton;

    @FXML
    public Button optionsButton;

    @FXML
    public Button abusesButton;

    public void initialize() {
        firstName.setText("Hello " + UserFacade.getInstance().getCurrentUser().getFirstName());
        list.setText("See the accommodations");
        list.setOnAction( event -> {
            try {
                FXRouter.goTo("accommodationsList");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void chats(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("chat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void settings(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("settings");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void userList(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("userList");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method called when the options button is clicked. Open the options window.
     * @param actionEvent the event of the click
     */

    public void options(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("options");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method called when the abuses button is clicked. Opens the abuses list window.
     * @param actionEvent the event of the click
     */

    public void abusesList(ActionEvent actionEvent) {

        try {
            FXRouter.goTo("abusesList");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
