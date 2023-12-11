package fr.umontpellier.polytech.ig.colocalternant;

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
    public TextField pseudo;
    public Image primaryLogo;

    public void login(ActionEvent actionEvent) {
        if (pseudo.getText().equals("admin") && password.getText().equals("admin")) {
            errorLabel.setText("Login success");
        } else {
            errorLabel.setText("Login failed");
        }
        errorLabel.setVisible(true);
    }
}
