package fr.umontpellier.polytech.ig.colocalternant.dao.rental;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;

/**
 * Concrete implementation of the rental DAO for SQLite.
 */
public class RentalDAOSQLite extends RentalDAO {
    /**
     * Constructor of the rental DAO
     */
    private RentalDAOSQLite() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the unique instance of the rental DAO.
     * @return The rental DAO.
     */
    public static RentalDAOSQLite getInstance()  {
        return RentalDAOSQLite.RentalDAOSQLiteHolder.instance;
    }

    /**
     * Holder of the unique instance of the rental DAO
     */
    private static class RentalDAOSQLiteHolder {
        private final static RentalDAOSQLite instance = new RentalDAOSQLite();
    }
}
