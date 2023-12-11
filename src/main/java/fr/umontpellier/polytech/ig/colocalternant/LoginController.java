package fr.umontpellier.polytech.ig.colocalternant;

import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialExceptionType;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

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
    @FXML
    public TextField email;

    /**
     * The stage of the window
     */
    private Stage stage;
    /**
     * The scene of the window
     */
    private Scene scene;
    /**
     * The root of the window
     */
    private Parent root;



    /**
     * Method called when the login button is clicked. Call the login method of the user facade.
     * if the login is successful, open the main window
     * else display an error message
     * @param actionEvent the event of the click
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        if (email.getText().isEmpty() || password.getText().isEmpty()) {
            errorLabel.setText("Please fill all the fields");
            errorLabel.setVisible(true);
            return;
        }
        try {
            UserFacade.getInstance().login(email.getText(), password.getText());
        } catch(CredentialException credentialException) {
            if (credentialException.getType() == CredentialExceptionType.INVALID_EMAIL) {
                errorLabel.setText("Login failed : Invalid email");
            } else if (credentialException.getType() == CredentialExceptionType.INVALID_PASSWORD) {
                errorLabel.setText("Login failed : Invalid password");
            }
            errorLabel.setVisible(true);
            System.out.println("CredentialException is : " + credentialException.getMessage());
        }
        // redirect to main page
        try {
            MainApplication mainApplication = new MainApplication();
            mainApplication.start(new Stage());
        } catch (Exception e) {
            System.out.println("Exception is : " + e.getMessage());
        }
    }
}
