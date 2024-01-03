package fr.umontpellier.polytech.ig.colocalternant.profile;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.profile.ProfileDAO;

import java.util.ArrayList;

public class ProfileFacade {
    /**
     * The current profile
     */
    private Profile currentProfile;

    /**
     * The DAO factory
     */
    private DAOFactory daoFactory;

    /**
     * The ProfileDAO
     */
    private ProfileDAO profileDAO;

    /**
     * Constructor of the profile facade
     */
    private ProfileFacade() {
        this.currentProfile = null;
        this.daoFactory = DAOSQLiteFactory.getInstance();
        this.profileDAO = daoFactory.getProfileDAO();
    }

    /**
     * Retrieves the unique instance of the user facade.
     * @return The user facade.
     */
    public static ProfileFacade getInstance() {
        return ProfileFacade.ProfileFacadeHolder.instance;
    }

    /**
     * Create a new profile.
     */
    public void createProfile(boolean isPublic, String description, int userID, EnumRole role) {
        //Profile newProfile = profileDAO.createProfile(isPublic, description, userID, role);
        profileDAO.createProfile(isPublic, description, userID, role);
    }

    /**
     * Delete a profile.
     */
    public void deleteProfile(int profileID) {
        //Profile newProfile = profileDAO.createProfile(isPublic, description, userID, role);
        profileDAO.deleteProfile(profileID);
    }

    /**
     * Retrieves all profiles in database.
     * @return An ArrayList of all profiles.
     */
    public ArrayList<Profile> getAllProfiles() {
        return profileDAO.getAllProfiles();
    }

    /**
     * Retrieves all profiles from a user.
     * @param userID The id of the user.
     * @return An arrayList of all profile from a user
     */
    public ArrayList<Profile> getOwnProfiles(int userID) {
        return profileDAO.getOwnProfiles(userID);
    }

    /**
     * Update the profile from a user.
     * @param profileID The id of the profile
     * @param isPublic The visibility of the profile
     * @param description The description of the profile
     * //@param userID The id of the user who has this profile
     */
    public void updateProfile(int profileID, boolean isPublic, String description) {
        profileDAO.updateProfile(profileID, isPublic, description);
    }

    public int getProfileIdWithRole(int userID, EnumRole role) {
        ArrayList<Profile> userProfiles = getOwnProfiles(userID);
        int profileID = -1;
        for (Profile userProfile : userProfiles) {
            if (userProfile.getRole() == role) {
                profileID = userProfile.getId();
            }
        }
        return profileID;
    }

    /**
     * Holder of the unique instance of the user facade
     */
    private static class ProfileFacadeHolder {
        private final static ProfileFacade instance = new ProfileFacade();
    }
}
