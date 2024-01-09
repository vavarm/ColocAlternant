package fr.umontpellier.polytech.ig.colocalternant.controller.rental;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import javafx.event.ActionEvent;

public class RentalRequestListController {
    // TODO display rental request list of profile id. Use the right method in RentalFacade according to the role of the profile (tenant or owner)

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
     * @return the user's profile id
     */
    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }
}
