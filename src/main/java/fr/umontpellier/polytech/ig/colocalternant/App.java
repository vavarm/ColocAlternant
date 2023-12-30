package fr.umontpellier.polytech.ig.colocalternant;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.*;

/**
 * Main class of the application
 */
public class App extends Application {
    /**
     * Main method of the application. Launches the javafx application.
     * @param args arguments of the application
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Starts the javafx application
     * @param stage the stage of the application
     * @throws IOException if the fxml file is not found
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage);
        FXRouter.when("hello", "fxml/hello-view.fxml");
        FXRouter.when("login", "fxml/login-view.fxml");
        FXRouter.when("main", "fxml/main-view.fxml");
        FXRouter.when("register", "fxml/register-view.fxml");
        FXRouter.when("settings", "fxml/settings-view.fxml");
        FXRouter.when("userList", "fxml/userList-view.fxml");
        FXRouter.when("options", "fxml/options-view.fxml");

        FXRouter.goTo("hello");
    }
}