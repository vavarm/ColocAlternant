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
    public Button notifButton;

    @FXML
    public Button abusesButton;
    public Button profileButton;

    @FXML
    public Button changeProfileButton;

    @FXML
    public Button rentalRequestListButton;

    @FXML
    public Button rentalListButton;

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
     *
     * @param actionEvent the event of the click
     */

    public void notifications(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("listNotification", profileID, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Method called when the abuses button is clicked. Opens the abuses list window.
     *
     * @param actionEvent the event of the click
     */
    public void abusesList(ActionEvent actionEvent) {

        if (!ProfileFacade.getInstance().isAdmin(UserFacade.getInstance().getCurrentUser())) {
            try {
                FXRouter.goTo("abusesList", profileID, false);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    /**
     * Method called when the rental request list button is clicked. Open the rental request list window.
     *
     * @param actionEvent the event of the click
     */
    public void rentalRequestList(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("rentalRequestList", profileID, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method called when the rental list button is clicked. Open the rental list window.
     *
     * @param actionEvent the event of the click
     */
    public void rentalList(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("rentalList", profileID, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
