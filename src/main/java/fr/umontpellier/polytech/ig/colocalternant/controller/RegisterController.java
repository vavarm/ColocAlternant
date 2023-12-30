package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialExceptionType;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller of the register view.
 */
public class RegisterController {

    /**
     * The first name of the user
     */
    @FXML
    private TextField firstName;
    /**
     * The last name of the user
     */
    @FXML
    private TextField lastName;
    /**
     * The age of the user
     */
    @FXML
    private TextField age;
    /**
     * The email of the user
     */
    @FXML
    private TextField email;
    /**
     * The password of the user
     */
    @FXML
    private PasswordField password;
    /**
     * The photo of the user
     */
    @FXML
    private TextField photo;
    /**
     * The label of the error message
     */
    @FXML
    private Label errorLabel;

    /**
     * Method called when the register button is clicked. Call the register method of the user facade.
     * if the register is successful, go to the login page
     * else display an error message
     * @param firstName of the user
     * @param lastName of the user
     * @param age of the user
     * @param email of the user
     * @param password of the user
     * @param photo of the user
     */
    public void handleRegister(String firstName, String lastName, int age, String email, String password, String photo) {
        try {
            UserFacade.getInstance().register(firstName, lastName, age, email, password, photo);
        } catch(CredentialException credentialException) {
            if (credentialException.getType() == CredentialExceptionType.EMAIL_ALREADY_USED) {
                errorLabel.setText("Email already used");
            }
            errorLabel.setVisible(true);
            return;
        }
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method called when the register button is clicked.
     * if all the fields are filled with good information, call the handleRegister method
     * else display an error message
     * @param actionEvent the event of the click
     */
    public void register(ActionEvent actionEvent) {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || age.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty() || photo.getText().isEmpty()) {
            errorLabel.setText("Please fill all the fields");
            errorLabel.setVisible(true);
            return;
        }
        if (!email.getText().contains("@")) {
            errorLabel.setText("Please enter a valid email");
            errorLabel.setVisible(true);
            return;
        }
        if (Integer.parseInt(age.getText()) < 0) {
            errorLabel.setText("Please enter a valid age");
            errorLabel.setVisible(true);
            return;
        }
        if (password.getText().length() < 8) {
            errorLabel.setText("Please enter a password with at least 8 characters");
            errorLabel.setVisible(true);
            return;
        }
        handleRegister(firstName.getText(), lastName.getText(), Integer.parseInt(age.getText()), email.getText(), password.getText(), photo.getText());
    }
}
