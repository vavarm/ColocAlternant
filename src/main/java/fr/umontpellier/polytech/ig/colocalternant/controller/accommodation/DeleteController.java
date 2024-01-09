package fr.umontpellier.polytech.ig.colocalternant.controller.accommodation;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.AccommodationFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.*;

/**
 * The `DeleteController` class is responsible for handling the deletion of accommodations
 * in the user interface. It interacts with the `AccommodationFacade` to perform the deletion
 * operation and updates the UI accordingly.
 */
public class DeleteController {

    @FXML
    public Button delete;

    @FXML
    public Label accommodation;

    @FXML
    public Button back;

    /**
     * Default constructor for creating a `DeleteController` instance.
     */
    public DeleteController() {
    }

    /**
     * Initializes the controller. Sets the text for the delete button and the accommodation label.
     */
    public void initialize() {
        this.delete.setText("Delete");
        this.accommodation.setText("Do you want to delete " + AccommodationFacade.getInstance().getCurrentAccommodation().getTitle() + "?");
        this.back.setText("Back");
        this.back.setOnAction(event -> {try {
            FXRouter.goTo("accommodationInfo", getProfileID(), false);
        }catch (IOException e){
            throw new RuntimeException(e);
        }});
    }

    /**
     * Method called when the delete button is pressed.
     *
     * @param e The ActionEvent triggered by the delete button press.
     */
    @FXML
    public void onDelete(ActionEvent e) {
        handleDelete(AccommodationFacade.getInstance().getCurrentAccommodation().getId());
        try {
            FXRouter.goTo("accommodationsList", getProfileID(), false);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Handles the deletion of an accommodation with the given ID.
     *
     * @param id The ID of the accommodation to be deleted.
     */
    public void handleDelete(int id) {
        AccommodationFacade.getInstance().delete(id);
    }

    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }
}
