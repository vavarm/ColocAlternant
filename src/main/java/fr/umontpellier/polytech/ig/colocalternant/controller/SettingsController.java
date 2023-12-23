package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class SettingsController {
    public Label errorLabel;
    public PasswordField newPwd;

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

    private void handlePasswordChange(String newPwd) {
        UserFacade.getInstance().changePassword(newPwd);
    }
}
