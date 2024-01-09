package fr.umontpellier.polytech.ig.colocalternant.controller.accommodationAlert;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.accommodationAlert.AccommodationAlertFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class listNotificationController {
    private AccommodationAlertFacade alertFacade = AccommodationAlertFacade.getInstance();

    @FXML
    private Button backButton;
    @FXML
    private Button alertButton;

    @FXML
    private void handleBackButtonClick(ActionEvent actionEvent) {
        System.out.println("Redirecting to main-view");
        try { FXRouter.goTo("main", getProfileID(), false); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    @FXML
    private void handleListAccommodationAlterButtonClick(ActionEvent actionEvent) {
        System.out.println("Redirecting to listAccommodationAlert-view");
        try { FXRouter.goTo("listAlert", getProfileID(), false); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    public void initialize() {
        backButton.setOnAction(this::handleBackButtonClick);
        alertButton.setOnAction(this::handleListAccommodationAlterButtonClick);
    }

    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }
}
