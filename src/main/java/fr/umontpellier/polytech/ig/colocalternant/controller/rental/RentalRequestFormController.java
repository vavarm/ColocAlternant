package fr.umontpellier.polytech.ig.colocalternant.controller.rental;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.AccommodationFacade;
import fr.umontpellier.polytech.ig.colocalternant.rental.RentalFacade;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class RentalRequestFormController {
    // TODO period
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker endDatePicker;
    @FXML
    GridPane gridPane;

    /**
     * Handles on load.
     */
    public void initialize() {
        // column constraints
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(70);
        gridPane.getColumnConstraints().addAll(column1, column2);
    }

    /**
     * Handles when the user clicks on the "Submit" button.
     */
    public void handleSubmitButtonClick(ActionEvent actionEvent) {
        String period = startDatePicker.getValue().toString() + " - " + endDatePicker.getValue().toString();
        RentalFacade rentalFacade = RentalFacade.getInstance();
        rentalFacade.createRental(getProfileID(), AccommodationFacade.getInstance().getCurrentAccommodation().getId(), period, true);
        try {
            FXRouter.goTo("accommodationsList", getProfileID(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles when the user clicks on the "Back" button.
     */
    public void handleBackButtonClick(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("accommodationsList", getProfileID(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the profile id from the data passed by FXRouter.
     * @return the user's profile id
     */
    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }
}