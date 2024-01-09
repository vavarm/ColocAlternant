package fr.umontpellier.polytech.ig.colocalternant.controller.accommodationAlert;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.accommodationAlert.AccommodationAlert;
import fr.umontpellier.polytech.ig.colocalternant.accommodationAlert.AccommodationAlertFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class insertUpdateAccommodationAlertController {

    private AccommodationAlertFacade alertFacade = AccommodationAlertFacade.getInstance();

    @FXML
    private TextArea locationField;
    @FXML
    private TextArea surfaceField;
    @FXML
    private TextArea minPriceField;
    @FXML
    private TextArea maxPriceField;
    @FXML
    private Button backButton;
    @FXML
    private Button submitButton;


    @FXML
    public void handleBackButtonClick(ActionEvent actionEvent) {
        System.out.println("Redirecting to listAccommodationAlert-view");
        try { FXRouter.goTo("listAlert", getProfileID(), false); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    @FXML
    public void handleSubmitButtonClick(ActionEvent actionEvent) {
        String location = locationField.getText();
        float surface = Float.parseFloat(surfaceField.getText());
        float minPrice = Float.parseFloat(minPriceField.getText());
        float maxPrice = Float.parseFloat(maxPriceField.getText());

        if (alertFacade.getCurrentAccommodationAlert() != null) {
            alertFacade.updateAccommodationAlert(alertFacade.getCurrentAccommodationAlert().getId(), getProfileID(), location, surface, minPrice, maxPrice);
        }
        else {
            alertFacade.insertAccommodationAlert(getProfileID(), location, surface, minPrice, maxPrice);
        }
        alertFacade.setCurrentAccommodationAlert(null);

        System.out.println("Redirecting to listAccommodationAlert-view");
        try { FXRouter.goTo("listAlert", getProfileID(), false); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    public void initialize() {
        backButton.setOnAction(this::handleBackButtonClick);
        submitButton.setOnAction(this::handleSubmitButtonClick);
        submitButton.setText("Create an alert");

        if (getCond() == true) {
            AccommodationAlert currentAlert = alertFacade.getCurrentAccommodationAlert();
            locationField.setText(currentAlert.getLocation());
            surfaceField.setText(String.valueOf(currentAlert.getSurface()));
            minPriceField.setText(String.valueOf(currentAlert.getMinPrice()));
            maxPriceField.setText(String.valueOf(currentAlert.getMaxPrice()));

            submitButton.setText("Update the Alert");
        }
    }

    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }

    private boolean getCond() {
        Object data2 = FXRouter.getData2();
        boolean cond = (boolean) data2;
        return cond;
    }
}
