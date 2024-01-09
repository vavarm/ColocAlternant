package fr.umontpellier.polytech.ig.colocalternant.dao.abuse;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;

import java.util.*;

/**
 * 
 */
public class AbuseDAOSQLite extends AbuseDAO {

    /**
     * Default constructor
     */
    private AbuseDAOSQLite() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * @return
     */
    public static AbuseDAOSQLite getInstance() {
        return AbuseDAOSQLiteHolder.instance;
    }

    /**
     * @return
     */
    private AbuseDAOSQLite AbuseDAOSQLite() {
        // TODO implement here
        return null;
    }

    private static class AbuseDAOSQLiteHolder{
        private final static AbuseDAOSQLite instance = new AbuseDAOSQLite();
    }

}