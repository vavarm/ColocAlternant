package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainController {
    @FXML
    public Text firstName;

    @FXML
    public Button settingsButton;

    @FXML
    public Button userListButton;

    public void initialize() {
        firstName.setText("Hello " + UserFacade.getInstance().getCurrentUser().getFirstName());
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
}
