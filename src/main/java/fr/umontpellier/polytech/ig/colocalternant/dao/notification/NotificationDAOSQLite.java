package fr.umontpellier.polytech.ig.colocalternant.dao.notification;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.profile.ProfileDAOSQLite;

/**
 * Concrete implementation of the notification DAO for SQLite.
 */
public class NotificationDAOSQLite extends NotificationDAO {
    /**
     * Constructor of the profile DAO
     */
    private NotificationDAOSQLite() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the unique instance of the profile DAO.
     *
     * @return The profile DAO.
     */
    public static NotificationDAOSQLite getInstance() {
        return NotificationDAOSQLite.NotificationDAOSQLiteHolder.instance;
    }

    /**
     * Holder of the unique instance of the user DAO
     */
    private static class NotificationDAOSQLiteHolder {
        private final static NotificationDAOSQLite instance = new NotificationDAOSQLite();
    }
}
