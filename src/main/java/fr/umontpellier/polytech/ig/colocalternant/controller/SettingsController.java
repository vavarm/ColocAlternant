package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;

/**
 * Controller of the settings view.
 */
public class SettingsController {
    /**
     * The label of the error message
     */
    @FXML
    public Label errorLabel;
    /**
     * The new password of the user
     */
    @FXML
    public PasswordField newPwd;

    /**
     * Method called when the logout button is clicked. Logout the user. Open the login window.
     * @param actionEvent the event of the click
     */
    public void passwordChange(ActionEvent actionEvent) {
        if (newPwd.getText().length() < 8) {
            errorLabel.setText("Le mot de passe doit contenir au moins 8 caractères");
            return;
        }
        handlePasswordChange(newPwd.getText());
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method called during the password change process. Call the changePassword method of the user facade.
     * @param newPwd the string of the password
     */
    private void handlePasswordChange(String newPwd) {
        UserFacade.getInstance().changePassword(newPwd);
    }
}
