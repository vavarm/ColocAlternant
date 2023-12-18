package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Controller of the hello window
 */
public class HelloController {
    /**
     * The label of the welcome text
     */
    @FXML
    private Label welcomeText;

    /**
     * Method called when the hello button is clicked
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    /**
     * Method called when the login button is clicked. Open the login window.
     * @param actionEvent the event of the click
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}