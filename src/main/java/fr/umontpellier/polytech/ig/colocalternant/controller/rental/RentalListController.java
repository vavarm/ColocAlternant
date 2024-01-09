package fr.umontpellier.polytech.ig.colocalternant.controller.rental;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.Accommodation;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.AccommodationFacade;
import fr.umontpellier.polytech.ig.colocalternant.profile.EnumRole;
import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;
import fr.umontpellier.polytech.ig.colocalternant.profile.ProfileFacade;
import fr.umontpellier.polytech.ig.colocalternant.rental.Rental;
import fr.umontpellier.polytech.ig.colocalternant.rental.RentalFacade;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class RentalListController {
    @FXML
    TableView tableView;

    public void initialize() {
        // get the current user's profile
        List<Profile> profiles = ProfileFacade.getInstance().getAllProfiles();
        Profile profile = profiles.stream().filter(p -> p.getId() == getProfileID()).findFirst().orElse(null);
        if (profile == null) {
            System.out.println("Profile not found");
            return;
        }
        if (profile.getRole() == EnumRole.Tenant) {
            // display rental list of the tenant
            List<Rental> rentals = RentalFacade.getInstance().getRentalsOf(this.getProfileID());
            // remove the requests
            rentals.removeIf(r -> r.getIsRequest());
            tableView.setItems(FXCollections.observableArrayList(rentals));
        } else if (profile.getRole() == EnumRole.Owner) {
            // TODO display rental list of the owner by listing the rentals of all of his accommodations
        }

        TableColumn<Rental, String> periodColumn = new TableColumn<>("Period");
        periodColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getPeriod()));

        TableColumn<Rental, VBox> accommodationColumn = new TableColumn<>("Accommodation");
        accommodationColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(createButtonContainer(AccommodationFacade.getInstance().getAccommodation(cell.getValue().getAccommodationId()))));

        tableView.getColumns().addAll(periodColumn, accommodationColumn);
    }

    /**
     * Creates a VBox to list the actions for the given accommodation.
     *
     * @param accommodation The accommodation.
     * @return The VBox.
     */
    private VBox createButtonContainer(Accommodation accommodation) {
        Button showButton = createShowButton(accommodation);

        VBox buttonContainer = new VBox(showButton);
        buttonContainer.setSpacing(5); // Optional: Set the spacing between buttons

        return buttonContainer;
    }

    /**
     * Creates a button that redirects to the page displaying detailed information about the given accommodation.
     *
     * @param accommodation The accommodation.
     * @return The button.
     */
    private Button createShowButton(Accommodation accommodation) {
        Button button = new Button("Show");

        button.setOnAction(event -> {
            try {
                AccommodationFacade.getInstance().setCurrentAccommodation(accommodation);
                FXRouter.goTo("accommodationInfo", getProfileID(), false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }

    /**
     * Handles when the user clicks on the "Back" button.
     */
    public void handleBackButtonClick(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("main", getProfileID(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the profile id from the data passed by FXRouter.
     *
     * @return the user's profile id
     */
    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }
}
