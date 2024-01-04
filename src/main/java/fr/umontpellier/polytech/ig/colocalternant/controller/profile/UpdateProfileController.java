package fr.umontpellier.polytech.ig.colocalternant.controller.profile;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;
import fr.umontpellier.polytech.ig.colocalternant.profile.ProfileFacade;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateProfileController {
    private UserFacade userFacade = UserFacade.getInstance();
    private User currentUser = userFacade.getCurrentUser();
    private ProfileFacade profileFacade = ProfileFacade.getInstance();
    private Profile myProfile;
    private int profileID;
    private boolean cond;


    @FXML
    private TextArea descriptionField;
    @FXML
    private ComboBox<String> publicProfileComboBox;
    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;


    /**
     * Method called when the delete button is clicked.
     * Allow to go on createProfile-view.
     * @param actionEvent Obligatory param for ActionEvent
     */
    @FXML
    private void handleDeleteButtonClick(ActionEvent actionEvent) {
        profileFacade.deleteProfile(profileID);
        System.out.println("Redirecting to createProfile-view");
        try { FXRouter.goTo("createProfile"); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    /**
     * Method called when the cancel button is clicked.
     * Allow to go on ownProfile-view.
     * @param   actionEvent Obligatory param for ActionEvent
     */
    @FXML
    private void handleCancelButtonClick(ActionEvent actionEvent) {
        System.out.println("Redirecting to ownProfile-view");
        try { FXRouter.goTo("ownProfile", profileID, false); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    /**
     * Method called when the submit button is clicked.
     * Allow to go on ownProfile-view.
     * @param   actionEvent Obligatory param for ActionEvent
     */
    @FXML
    private void handleSubmitButtonClick(ActionEvent actionEvent) {
        String description = descriptionField.getText();
        String publicProfileValue = publicProfileComboBox.getValue();
        boolean choice = true;
        publicProfileComboBox.setValue("Yes");

        if (publicProfileValue.equals("Yes")) { choice = true; }
        else { choice = false; }
        updateProfile(profileID, choice, description);

        if (getCond() == false) {
            System.out.println("Redirecting to ownProfile-view");
            try { FXRouter.goTo("ownProfile", profileID, false); }
            catch (IOException e) { throw new RuntimeException(e); }
        }
        else {
            System.out.println("Redirecting to ownProfile-view");
            try { FXRouter.goTo("main"); }
            catch (IOException e) { throw new RuntimeException(e); }
        }
    }

    /**
     * Allow to get the id of the profile by getting the data from the router.
     * @return The id of the profile.
     */
    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }

    /**
     * Allow to get the cond of the profile by getting the data from the router.
     * @return The cond of the profile.
     */
    private boolean getCond() {
        Object data2 = FXRouter.getData2();
        boolean cond = (boolean) data2;
        return cond;
    }

    /**
     * Allow to get a profile of the current user by its ID.
     * @param   profileID The profile by its ID.
     */
    private Profile getMyProfile(int profileID) {
        Profile myProfile = null;
        ArrayList<Profile> userProfiles = profileFacade.getOwnProfiles(currentUser.getId());
        for (Profile userProfile : userProfiles) {
            if (userProfile.getId() == profileID) {
                myProfile = userProfile;
            }
        }
        return myProfile;
    }

    public void initialize() {
        profileID = getProfileID();
        myProfile = getMyProfile(profileID);
        cond = getCond();
        System.out.println("profileId in UpdateProfileController : "+ profileID);

        descriptionField.setText(myProfile.getDescription());
        publicProfileComboBox.setValue("Yes");

        if (cond == true) {
            deleteButton.setVisible(false);
            cancelButton.setVisible(false);
        }
        else {
            deleteButton.setVisible(true);
            cancelButton.setVisible(true);
        }
        deleteButton.setOnAction(this::handleDeleteButtonClick);
        cancelButton.setOnAction(this::handleCancelButtonClick);
        submitButton.setOnAction(this::handleSubmitButtonClick);
    }

    /**
     * Method called when the submit button is clicked. Call the updateProfile method of the profile facade.
     * @param profileID     the ID of the profile
     * @param isPublic      the visibility of the profile
     * @param description   the description of the profile
     */
    public void updateProfile(int profileID, boolean isPublic, String description) {
        profileFacade.updateProfile(profileID, isPublic, description);
    }
}
