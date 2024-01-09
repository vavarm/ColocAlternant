package fr.umontpellier.polytech.ig.colocalternant.controller.accommodation;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.Accommodation;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.AccommodationFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller class for updating accommodation information.
 */
public class UpdateController {

    /**
     * Button used to trigger the update of the accommodation.
     */
    @FXML
    public Button update;

    @FXML
    public TextField newTitle = new TextField(AccommodationFacade.getInstance().getCurrentAccommodation().getTitle());

    @FXML
    public TextField newLocation = new TextField(AccommodationFacade.getInstance().getCurrentAccommodation().getLocation());

    @FXML
    public TextField newDescription = new TextField(AccommodationFacade.getInstance().getCurrentAccommodation().getDescription());

    @FXML
    public TextField newPrice = new TextField(String.valueOf(AccommodationFacade.getInstance().getCurrentAccommodation().getPrice()));

    @FXML
    public TextField newSF = new TextField(AccommodationFacade.getInstance().getCurrentAccommodation().getSpecialFonctionalities());

    @FXML
    public TextField newER = new TextField(String.valueOf(AccommodationFacade.getInstance().getCurrentAccommodation().getEnergicReport()));

    @FXML
    public TextField newPhotos = new TextField(AccommodationFacade.getInstance().getCurrentAccommodation().getPhotos());

    @FXML
    private Button back;

    /**
     * Default constructor
     */
    public UpdateController() {
    }

    /**
     * Initializes the text fields with the current accommodation information.
     */
    public void initialize() {
        newTitle.setText(AccommodationFacade.getInstance().getCurrentAccommodation().getTitle());
        newLocation.setText(AccommodationFacade.getInstance().getCurrentAccommodation().getLocation());
        newDescription.setText(AccommodationFacade.getInstance().getCurrentAccommodation().getDescription());
        newPrice.setText(String.valueOf(AccommodationFacade.getInstance().getCurrentAccommodation().getPrice()));
        newSF.setText(AccommodationFacade.getInstance().getCurrentAccommodation().getSpecialFonctionalities());
        newER.setText(String.valueOf(AccommodationFacade.getInstance().getCurrentAccommodation().getEnergicReport()));
        newPhotos.setText(AccommodationFacade.getInstance().getCurrentAccommodation().getPhotos());
        this.back.setText("Back");
        this.back.setOnAction(event -> {
            try {
                FXRouter.goTo("accommodationInfo", getProfileID(), false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Handles the update button action and calls the updateAccommodation method.
     *
     * @param e The ActionEvent triggered by the update button.
     */
    @FXML
    void updateAccommodation(ActionEvent e) {
        String title = newTitle.getText();
        String location = newLocation.getText();
        String description = newDescription.getText();
        float price = Float.parseFloat(newPrice.getText());
        String specialFonctionalities = newSF.getText();
        float energicReport = Float.parseFloat(newER.getText());
        String photos = newPhotos.getText();

        handleUpdateAccommodation(AccommodationFacade.getInstance().getCurrentAccommodation().getId(), title,
                location, description, price, specialFonctionalities, energicReport, photos);
    }

    /**
     * Updates the accommodation information using the provided parameters.
     *
     * @param id                     The ID of the accommodation to be updated.
     * @param title                  The new title of the accommodation.
     * @param location               The new location of the accommodation.
     * @param description            The new description of the accommodation.
     * @param price                  The new price of the accommodation.
     * @param specialFonctionalities The new special functionalities of the accommodation.
     * @param energicReport          The new energetic report of the accommodation.
     * @param photos                 The new photos of the accommodation.
     */
    public void handleUpdateAccommodation(int id, String title, String location, String description, float price,
                                          String specialFonctionalities, float energicReport, String photos) {
        AccommodationFacade.getInstance().updateAccommodation(id, title, location, description, price,
                specialFonctionalities, energicReport, photos);
    }

    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }
}
