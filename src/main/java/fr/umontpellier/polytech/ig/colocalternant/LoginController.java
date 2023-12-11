package fr.umontpellier.polytech.ig.colocalternant;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

/**
 * Controller of the login window
 */
public class LoginController {
    /**
     * The label of the error message
     */
    @FXML
    public Label errorLabel;
    /**
     * The login button
     */
    @FXML
    public Button loginButton;
    /**
     * The password field
     */
    @FXML
    public PasswordField password;
    /**
     * The email field
     */
    // TODO: change the label to email
    @FXML
    public TextField pseudo;

    /**
     * Method called when the login button is clicked. Call the login method of the user facade.
     * if the login is successful, open the main window
     * else display an error message
     * @param actionEvent the event of the click
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        if (pseudo.getText().equals("admin") && password.getText().equals("admin")) {
            errorLabel.setText("Login success");
        } else {
            errorLabel.setText("Login failed");
        }
        errorLabel.setVisible(true);
    }
}
