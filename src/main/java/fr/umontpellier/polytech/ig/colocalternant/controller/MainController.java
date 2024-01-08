package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainController {

    private int profileID;

    @FXML
    public Label firstName;

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
    public Button profileButton;

    @FXML
    public Button changeProfileButton;

    public void initialize() {
        profileID = getProfileID();
        firstName.setText("Hello " + UserFacade.getInstance().getCurrentUser().getFirstName());
    }

    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }

    public void chats(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("chat", profileID, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accommodations(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("accommodationsList", profileID, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void settings(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("settings", profileID, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void userList(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("userList", profileID, false);
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

    public void profile(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("ownProfile", profileID, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeProfile(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("createProfile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
