package fr.umontpellier.polytech.ig.colocalternant;

import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class LoginController {
    public Label errorLabel;
    public Button loginButton;
    public PasswordField password;
    public TextField email;


    public void login(ActionEvent actionEvent) {
        if ( UserFacade.getInstance().login(email.getText(), password.getText()) != null) {
            errorLabel.setText("Login success");
            //TODO: redirect to main page
        } else {
            errorLabel.setText("Login failed");
        }
        errorLabel.setVisible(true);
    }
}
