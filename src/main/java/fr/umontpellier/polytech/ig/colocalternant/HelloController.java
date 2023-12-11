package fr.umontpellier.polytech.ig.colocalternant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        LoginApplication loginApplication = new LoginApplication();
        try {
            loginApplication.start(new Stage());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}