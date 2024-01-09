package fr.umontpellier.polytech.ig.colocalternant.dao.accommodationAlert;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;

public class AccommodationAlertDAOSQLite extends  AccommodationAlertDAO {
    /**
     * Constructor of the AccommodationAlert DAO
     */
    private AccommodationAlertDAOSQLite () {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the unique instance of the user DAO.
     * @return The user DAO.
     */
    public static AccommodationAlertDAOSQLite getInstance() {
        return AccommodationAlertDAOSQLite.AccommodationAlertDAOSQLiteHolder.instance;
    }

    /**
     * Holder of the unique instance of the accommodation DAO
     */
    private static class AccommodationAlertDAOSQLiteHolder {
        private final static AccommodationAlertDAOSQLite  instance = new AccommodationAlertDAOSQLite ();
    }
}
