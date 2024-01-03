package fr.umontpellier.polytech.ig.colocalternant.dao.profile;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;

import java.util.ArrayList;

public class ProfileDAOSQLite extends ProfileDAO {
    /**
     * Constructor of the user DAO
     */
    private ProfileDAOSQLite() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the unique instance of the profile DAO.
     * @return The profile DAO.
     */
    public static ProfileDAOSQLite getInstance()  {
        return ProfileDAOSQLiteHolder.instance;
    }

    /**
     * Holder of the unique instance of the user DAO
     */
    private static class ProfileDAOSQLiteHolder {
        private final static ProfileDAOSQLite instance = new ProfileDAOSQLite();
    }
}