package fr.umontpellier.polytech.ig.colocalternant.controller.profile;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;
import fr.umontpellier.polytech.ig.colocalternant.profile.ProfileFacade;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class OwnProfileController {
    private UserFacade userFacade = UserFacade.getInstance();
    private User currentUser = userFacade.getCurrentUser();
    private ProfileFacade profileFacade = ProfileFacade.getInstance();
    private int profileID;
    private Profile myProfile;

    @FXML
    private Label title;
    @FXML
    private ImageView photo;
    @FXML
    private Label firstName;
    @FXML
    private Label name;
    @FXML
    private Label age;
    @FXML
    private Label email;
    @FXML
    private Label description;
    @FXML
    private Label visibility;

    @FXML
    private Button backButton;
    @FXML
    private Button updateButton;

    /**
     * Method called when the update button is clicked.
     * Allow to go on updateProfile-view.
     * @param   actionEvent Obligatory param for ActionEvent
     */
    @FXML
    private void handleUpdateButtonClick(ActionEvent actionEvent) {
        System.out.println("Redirecting to updateProfile-view");
        try { FXRouter.goTo("updateProfile", profileID, false); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    /**
     * Method called when the back button is clicked.
     * Allow to go on main-view.
     * @param   actionEvent Obligatory param for ActionEvent
     */
    @FXML
    private void handleBackButtonClick(ActionEvent actionEvent) {
        System.out.println("Redirecting to main-view");
        try { FXRouter.goTo("main", profileID, false); }
        catch (IOException e) { e.printStackTrace(); throw new RuntimeException(e); }
    }

    /**
     * Method called when the list button is clicked.
     * Allow to go on profilesList-view.
     * @param   actionEvent Obligatory param for ActionEvent
     */
    @FXML
    public void handleListButtonClick(ActionEvent actionEvent) {
        System.out.println("Redirecting to profilesList-view");
        try { FXRouter.goTo("listProfiles", profileID, false); }
        catch (IOException e) { e.printStackTrace(); throw new RuntimeException(e); }
    }

    /**
     * Allow to get the ID of the profile.
     * @return  The ID of the profile.
     */
    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }

    /**
     * Allow to get a profile of the current user by its ID.
     * @param   profileID The profile by its ID.
     */
    private Profile getMyProfile(int profileID) {
        Profile myProfile = null;
        ArrayList<Profile> userProfiles = getOwnProfiles(currentUser.getId());
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
        System.out.println("profileId in OwnProfileController : "+ profileID);

        title.setText("My " + myProfile.getRole().name().toLowerCase() + " profile");

        String imageUrl = currentUser.getPhoto();
        Image image = new Image(imageUrl);
        photo.setImage(image);

        firstName.setText(currentUser.getFirstName());
        name.setText(currentUser.getLastName());
        age.setText(String.valueOf(currentUser.getAge()));
        email.setText(currentUser.getEmail());
        description.setText(myProfile.getDescription());
        visibility.setText(String.valueOf(myProfile.getIsPublic()));

        backButton.setOnAction(this::handleBackButtonClick);
        updateButton.setOnAction(this::handleUpdateButtonClick);
    }

    /**
     * Allow to get all profiles of a user.
     * @param   userID The ID of the user.
     */
    public ArrayList<Profile> getOwnProfiles(int userID) {
        return profileFacade.getOwnProfiles(userID);
    }
}
