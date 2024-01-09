package fr.umontpellier.polytech.ig.colocalternant.controller.profile;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.profile.EnumRole;
import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;
import fr.umontpellier.polytech.ig.colocalternant.profile.ProfileFacade;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ProfilesListController {
    private UserFacade userFacade = UserFacade.getInstance();
    private User currentUser = userFacade.getCurrentUser();
    private ProfileFacade profileFacade = ProfileFacade.getInstance();

    @FXML
    private TableView<CombinedModel> profilesTable;

    @FXML
    private TableColumn<CombinedModel, Integer> idCol;

    @FXML
    private TableColumn<CombinedModel, String> firstNameCol;

    @FXML
    private TableColumn<CombinedModel, String> lastNameCol;

    @FXML
    private TableColumn<CombinedModel, Integer> ageCol;

    @FXML
    private TableColumn<CombinedModel, String> emailCol;

    @FXML
    private TableColumn<CombinedModel, String> descriptionCol;

    @FXML
    private TableColumn<CombinedModel, EnumRole> roleCol;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {

        backButton.setOnAction(this::handleBackButtonClick);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        List<CombinedModel> combinedDataList = IntStream.range(0, parseProfileList().size())
                .mapToObj(i -> new CombinedModel(
                        parseProfileList().get(i).getId(),
                        parseUserList().get(i).getFirstName(),
                        parseUserList().get(i).getLastName(),
                        parseUserList().get(i).getAge(),
                        parseUserList().get(i).getEmail(),
                        parseProfileList().get(i).getDescription(),
                        parseProfileList().get(i).getRole()))
                .toList();

        profilesTable.getItems().setAll(combinedDataList);
    }

    /**
     * Allow to get all users having one or two public profiles.
     *
     * @return a List of users
     */
    private List<User> parseUserList() {
        List<User> users = new ArrayList<>();
        for (Profile profile : getAllPublicProfiles()) {
            for (User user : userFacade.getAllUsers()) {
                if (profile.getUserID() == user.getId()) {
                    users.add(user);
                }
            }
        }
        return users;
    }

    /**
     * Allow to get a List of all public profiles.
     *
     * @return a List of all public profiles
     */
    private List<Profile> parseProfileList() {
        List<Profile> profiles = getAllPublicProfiles();
        return profiles;
    }

    /**
     * Allow to get a ArrayList of all profiles.
     *
     * @return a ArrayList of all profiles
     */
    public ArrayList<Profile> getAllProfiles() {
        return profileFacade.getAllProfiles();
    }

    /**
     * Allow to get a ArrayList of all public profiles.
     *
     * @return a ArrayList of all public profiles
     */
    private ArrayList<Profile> getAllPublicProfiles() {
        ArrayList<Profile> allPublicProfiles = new ArrayList<>();
        for (Profile profile : getAllProfiles()) {
            if (profile.getIsPublic() == true) {
                allPublicProfiles.add(profile);
            }
        }
        return allPublicProfiles;
    }

    /**
     * Method called when the back button is clicked.
     * Allow to go on ownProfile-view.
     *
     * @param actionEvent Obligatory param for ActionEvent
     */
    public void handleBackButtonClick(ActionEvent actionEvent) {
        System.out.println("Redirecting to ownProfile-view");
        Object data = FXRouter.getData();
        int profileID = (int) data;
        try {
            FXRouter.goTo("ownProfile", profileID, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
