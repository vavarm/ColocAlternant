package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class MainController {

    @FXML
    public Text firstName;

    @FXML
    public Button chatButton;

    public void initialize() {
        firstName.setText("Hello " + UserFacade.getInstance().getCurrentUser().getFirstName());
    }

    public void chats(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("chat");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
