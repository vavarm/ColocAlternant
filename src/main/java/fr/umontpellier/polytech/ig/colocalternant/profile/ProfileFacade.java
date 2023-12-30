package fr.umontpellier.polytech.ig.colocalternant.profile;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.user.User;

import java.util.ArrayList;

/**
 * Facade of the CRUD operations available on the profile.
 */
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
     * Constructor of the profile facade
     */
    private ProfileFacade() {
        this.currentProfile = null;
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the current profile and check is the user is admin
     * @return true if the user is admin
     */
    public boolean isAdmin(User user){
        for (Profile profile : getAllProfiles()) {
            if (user.getId() ==  currentProfile.getUserId()) {
                if (profile.getRole() == Role.ADMIN) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return all profiles in the database
     */
    public ArrayList<Profile> getAllProfiles() {
        return daoFactory.getProfileDAO().getAllProfiles();
    }

    /**
     * Retrieves the unique instance of the profile facade.
     * @return The profile facade.
     */
    public static ProfileFacade getInstance() {
        return ProfileFacade.ProfileFacadeHolder.instance;
    }

    /**
     * Holder of the unique instance of the profile facade
     */
    private static class ProfileFacadeHolder {
        private final static ProfileFacade instance = new ProfileFacade();
    }
}
