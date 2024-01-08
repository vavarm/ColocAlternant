/**
 * The InsertController class is responsible for controlling the user interface of the accommodation insertion view.
 * It provides functionality to add a new accommodation with specified details such as title, location, description,
 * price, special functionalities, energetic report, and associated photos.
 *
 */
package fr.umontpellier.polytech.ig.colocalternant.controller.accommodation;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.AccommodationFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * The InsertController class handles the insertion of new accommodations.
 */
public class InsertController {

    /**
     * Button used to trigger the insertion of a new accommodation.
     */
    @FXML
    public Button add;

    @FXML
    public TextField newTitle;

    @FXML
    public TextField newLocation;

    @FXML
    public TextField newDescription;

    @FXML
    public TextField newPrice;

    @FXML
    public TextField newSF;

    @FXML
    public TextField newER;

    @FXML
    public TextField newPhotos;


    @FXML
    private Button back;

    /**
     * Initializes the InsertController by setting up the UI components.
     */
    public void initialize() {
        add.setText("Add");
        this.back.setText("Back");
        this.back.setOnAction(event -> {try {
            FXRouter.goTo("accommodationsList", getProfileID(), false);
        }catch (IOException e){
            throw new RuntimeException(e);
        }});
    }

    /**
     * Handles the insertion of a new accommodation when the "Add" button is clicked.
     *
     * @param e The ActionEvent triggered by the button click.
     */
    @FXML
    public void onInsert(ActionEvent e) {
        String title = newTitle.getText();
        String location = newLocation.getText();
        String description = newDescription.getText();
        float price = Float.parseFloat(newPrice.getText());
        String specialFunctionalities = newSF.getText();
        float energeticReport = Float.parseFloat(newER.getText());
        String photos = newPhotos.getText();

        handleInsertAccommodation(title, location, description, price, specialFunctionalities, energeticReport, photos);
    }

    /**
     * Inserts a new accommodation with the provided details.
     *
     * @param title                 The title of the accommodation.
     * @param location              The location of the accommodation.
     * @param description           The description of the accommodation.
     * @param price                 The price of the accommodation.
     * @param specialFunctionalities The special functionalities of the accommodation.
     * @param energeticReport       The energetic report of the accommodation.
     * @param photos                The photos associated with the accommodation.
     */
    public void handleInsertAccommodation(String title, String location, String description, float price,
                                          String specialFunctionalities, float energeticReport, String photos) {
        AccommodationFacade.getInstance().insertAccommodation(title, location, description, price,
                specialFunctionalities, energeticReport, photos);
    }

    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }
}
