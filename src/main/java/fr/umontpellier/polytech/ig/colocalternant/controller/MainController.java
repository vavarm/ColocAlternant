package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainController {
    @FXML
    public Text firstName;

    @FXML
    public Button list;

    public void initialize() {
        firstName.setText("Hello " + UserFacade.getInstance().getCurrentUser().getFirstName());
        list.setText("See the accommodations");
        list.setOnAction( event -> {
            try {
                FXRouter.goTo("accommodationsList");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
