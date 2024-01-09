package fr.umontpellier.polytech.ig.colocalternant.controller.profile;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.profile.EnumRole;
import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;
import fr.umontpellier.polytech.ig.colocalternant.profile.ProfileFacade;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;

public class CreateProfileController {
    private UserFacade userFacade = UserFacade.getInstance();
    private User currentUser = userFacade.getCurrentUser();
    private ProfileFacade profileFacade = ProfileFacade.getInstance();

    @FXML
    private Button tenantButton;
    @FXML
    private Button ownerButton;
    @FXML
    private Button adminButton;
    @FXML
    private Button logoutButton;

    @FXML
    private void handleTenantButtonClick(ActionEvent event) {
        redirectToCorrectView(EnumRole.Tenant);
    }

    @FXML
    private void handleOwnerButtonClick(ActionEvent event) {
        redirectToCorrectView(EnumRole.Owner);
    }

    @FXML
    private void handleAdminButtonClick(ActionEvent event) {
        redirectToCorrectView(EnumRole.Admin);
    }

    @FXML
    public void handleLogoutButtonClick(ActionEvent actionEvent) {
        userFacade.logout();
        System.out.println("Redirecting to loginProfile-view");
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        tenantButton.setOnAction(this::handleTenantButtonClick);
        ownerButton.setOnAction(this::handleOwnerButtonClick);
        adminButton.setOnAction(this::handleAdminButtonClick);
        logoutButton.setOnAction(this::handleLogoutButtonClick);
    }

    /**
     * Method called when the Tenant button or the Owner button is clicked. Call the createProfile method of the profile facade.
     *
     * @param isPublic    the visibility of the profile
     * @param description the description of the profile
     * @param userID      the identifer of the user of the profile
     * @param role        the role of the profile
     */
    public void createProfile(boolean isPublic, String description, int userID, EnumRole role) {
        profileFacade.createProfile(isPublic, description, userID, role);
    }

    /**
     * Allow to know if a user has a profile with the role specified.
     *
     * @param selectedRole The role of the specified profile
     * @return true if a user has a profile with the role specified. Else false.
     */
    private boolean userHasProfileWithRole(EnumRole selectedRole) {
        ArrayList<Profile> userProfiles = profileFacade.getOwnProfiles(currentUser.getId());
        boolean hasRole = false;
        for (Profile userProfile : userProfiles) {
            if (userProfile.getRole() == selectedRole) {
                hasRole = true;
            }
        }
        return hasRole;
    }

    /**
     * Method called when a button is clicked.
     * Allow to go on another view.
     *
     * @param selectedRole The role of the specified profile
     */
    private void redirectToCorrectView(EnumRole selectedRole) {
        int profileId = profileFacade.getProfileIdWithRole(currentUser.getId(), selectedRole);
        System.out.println("profileId in CreateProfileController : " + profileId);
        if (userHasProfileWithRole(selectedRole)) {
            System.out.println("Redirecting to ownProfile-view");
            try {
                FXRouter.goTo("main", profileId, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (selectedRole == EnumRole.Admin) {
                System.out.println("You can't create an admin profile");
            } else {
                createProfile(true, "", currentUser.getId(), selectedRole);
                System.out.println("Redirecting to updateProfile-view");
                profileId = profileFacade.getProfileIdWithRole(currentUser.getId(), selectedRole);
                try {
                    FXRouter.goTo("updateProfile", profileId, true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}



