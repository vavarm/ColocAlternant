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
        FXRouter.when("deleteAccommodation", "fxml/accommodation/delete.fxml");
        FXRouter.when("accommodationInfo", "fxml/accommodation/info.fxml");
        FXRouter.when("insertAccommodation", "fxml/accommodation/insert.fxml");
        FXRouter.when("accommodationsList", "fxml/accommodation/list.fxml");
        FXRouter.when("updateAccommodation", "fxml/accommodation/update.fxml");
        FXRouter.when("register", "fxml/register-view.fxml");
        FXRouter.when("settings", "fxml/settings-view.fxml");
        FXRouter.when("userList", "fxml/userList-view.fxml");
        FXRouter.when("options", "fxml/options-view.fxml");

        FXRouter.when("chat", "fxml/chat-view.fxml");
        FXRouter.when("chat-message", "fxml/chat-message-view.fxml");

        FXRouter.when("categoryList", "fxml/category-view.fxml");

        FXRouter.when("createAbuse", "fxml/create-abuse-view.fxml");
        FXRouter.when("updateAbuse", "fxml/update-abuse-view.fxml");
        FXRouter.when("deleteAbuse", "fxml/delete-abuse-view.fxml");
        FXRouter.when("abusesList","fxml/abuses-list-view.fxml");


        FXRouter.goTo("hello");
    }
}