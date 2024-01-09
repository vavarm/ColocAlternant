package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Controller of the options view.
 */
public class OptionsController {
    /**
     * The logout button
     */
    @FXML
    public Button logoutButton;
    /**
     * The label of the error message
     */
    @FXML
    public Label errorLabel;


    /**
     * Method called when the logout button is clicked. Logout the user. Open the login window.
     *
     * @param actionEvent the event of the click
     */
    @FXML
    public void onLogout(ActionEvent actionEvent) {
        UserFacade.getInstance().logout();
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
