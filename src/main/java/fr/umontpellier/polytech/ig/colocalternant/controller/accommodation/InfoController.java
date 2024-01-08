/**
 * The InfoController class is responsible for controlling the user interface of the accommodation information view.
 * It displays details about a specific accommodation, such as title, location, description, price,
 * special functionalities, energetic report, and associated photos. Additionally, it provides functionality
 * for the user to delete or update the accommodation, but these options are only available if the user is
 * the owner of the accommodation.
 *
 */
package fr.umontpellier.polytech.ig.colocalternant.controller.accommodation;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.Accommodation;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.AccommodationFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class InfoController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label specialFunctionalitiesLabel;

    @FXML
    private Label energeticReportLabel;

    @FXML
    private ImageView photosImageView;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button back;

    /**
     * Initializes the InfoController by setting up the UI components and displaying
     * information about the current accommodation. It also configures the delete and
     * update buttons based on the ownership status of the current user.
     */
    public void initialize() {

        // Display accommodation details
        titleLabel.setText(AccommodationFacade.getInstance().getCurrentAccommodation().getTitle());
        locationLabel.setText("Location: " + AccommodationFacade.getInstance().getCurrentAccommodation().getLocation());
        descriptionLabel.setText("Description: " + AccommodationFacade.getInstance().getCurrentAccommodation().getDescription());
        priceLabel.setText("Price: " + AccommodationFacade.getInstance().getCurrentAccommodation().getPrice());
        specialFunctionalitiesLabel.setText("Special Functionalities: " + AccommodationFacade.getInstance().getCurrentAccommodation().getSpecialFonctionalities());
        energeticReportLabel.setText("Energetic Report: " + AccommodationFacade.getInstance().getCurrentAccommodation().getEnergicReport());

        // Load image from file or URL and set it to the ImageView
        Image image = new Image(AccommodationFacade.getInstance().getCurrentAccommodation().getPhotos());
        photosImageView.setImage(image);

        // Configure delete and update buttons for the accommodation owner
        if (AccommodationFacade.getInstance().isOwner()){
            deleteButton.setText("Delete");
            deleteButton.setOnAction(event -> {
                try {
                    FXRouter.goTo("deleteAccommodation", getProfileID(), false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            updateButton.setText("Update");
            updateButton.setOnAction(event -> {
                try {
                    FXRouter.goTo("updateAccommodation", getProfileID(), false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        this.back.setText("Back");
        this.back.setOnAction(event -> {try {
            FXRouter.goTo("accommodationsList", getProfileID(), false);
        }catch (IOException e){
            throw new RuntimeException(e);
        }});
    }

    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }
}
