package fr.umontpellier.polytech.ig.colocalternant.dao.abuse;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;

import java.util.*;

/**
 * Concrete implementation of the AbuseDAO for SQLite database.
 */
public class AbuseDAOSQLite extends AbuseDAO {

    /**
     * Private constructor to ensure singleton pattern and set the DAO factory.
     */
    private AbuseDAOSQLite() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Gets the instance of AbuseDAOSQLite using the singleton pattern.
     *
     * @return The instance of AbuseDAOSQLite.
     */
    public static AbuseDAOSQLite getInstance() {
        return AbuseDAOSQLiteHolder.instance;
    }

    /**
     * Private constructor to enforce the singleton pattern using the Bill Pugh Singleton Implementation.
     */
    private static class AbuseDAOSQLiteHolder {
        private final static AbuseDAOSQLite instance = new AbuseDAOSQLite();
    }
}
