package fr.umontpellier.polytech.ig.colocalternant;

import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class of the application
 */
public class HelloApplication extends Application {
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        // set min size of the window
        stage.setMinHeight(720);
        stage.setMinWidth(1080);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}