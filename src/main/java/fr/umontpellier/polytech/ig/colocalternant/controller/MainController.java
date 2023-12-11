package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class MainController {
    @FXML
    public Text firstName;

    public void initialize() {
        firstName.setText("Hello " + UserFacade.getInstance().getCurrentUser().getFirstName());
    }
}
