package fr.umontpellier.polytech.ig.colocalternant.dao.profile;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;

/**
 * Concrete implementation of the profile DAO for SQLite.
 */
public class ProfileDAOSQLite extends ProfileDAO {

    /**
     * Constructor of the profile DAO
     */
    private ProfileDAOSQLite() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the unique instance of the profile DAO.
     *
     * @return The profile DAO.
     */
    public static ProfileDAOSQLite getInstance() {
        return ProfileDAOSQLite.ProfileDAOSQLiteHolder.instance;
    }

    /**
     * Holder of the unique instance of the profile DAO
     */
    private static class ProfileDAOSQLiteHolder {
        private final static ProfileDAOSQLite instance = new ProfileDAOSQLite();
    }
}