package fr.umontpellier.polytech.ig.colocalternant.dao.user;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;

/**
 * Concrete implementation of the user DAO for SQLite.
 */
public class UserDAOSQLite extends UserDAO {

    /**
     * Constructor of the user DAO
     */
    private UserDAOSQLite() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the unique instance of the user DAO.
     *
     * @return The user DAO.
     */
    public static UserDAOSQLite getInstance() {
        return UserDAOSQLiteHolder.instance;
    }

    /**
     * Holder of the unique instance of the user DAO
     */
    private static class UserDAOSQLiteHolder {
        private final static UserDAOSQLite instance = new UserDAOSQLite();
    }
}