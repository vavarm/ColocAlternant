package fr.umontpellier.polytech.ig.colocalternant;

import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("fxml/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);

        Text firstName = (Text) scene.lookup("#firstName");
        firstName.setText("Welcome " + UserFacade.getInstance().getCurrentUser().getFirstName());
        // set min size of the window
        stage.setMinHeight(720);
        stage.setMinWidth(1080);

        stage.setTitle("ColocAlternant");
        stage.setScene(scene);
        stage.show();
    }
}
