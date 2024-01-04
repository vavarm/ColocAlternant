package fr.umontpellier.polytech.ig.colocalternant.dao.accomodation;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAOSQLite;

public class AccommodationDAOSQLite extends AccommodationDAO{
    /**
     * Constructor of the Accommodation DAO
     */
    private AccommodationDAOSQLite () {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the unique instance of the user DAO.
     * @return The user DAO.
     */
    public static AccommodationDAOSQLite getInstance() {
        return AccommodationDAOSQLite.AccommodationDAOSQLiteHolder.instance;
    }

    /**
     * Holder of the unique instance of the accommodation DAO
     */
    private static class AccommodationDAOSQLiteHolder {
        private final static AccommodationDAOSQLite  instance = new AccommodationDAOSQLite ();
    }
}
