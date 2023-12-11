package fr.umontpellier.polytech.ig.colocalternant;

import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        System.out.println(UserFacade.getInstance().login("john.doe@test.com", "password"));
        System.out.println(UserFacade.getInstance().login("john.doe@test.com", "wrongpassword"));
        System.out.println(UserFacade.getInstance().login("john.doe@doesnotexist.com", "password"));
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}