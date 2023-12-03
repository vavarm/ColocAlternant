package fr.umontpellier.polytech.ig.colocalternant.dao;

import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAOSQLite;

public class DAOSQLiteFactory extends DAOFactory {

    private DAOSQLiteFactory() {
    }

    private static class DAOSQliteFactoryHolder {
        private final static DAOSQLiteFactory instance = new DAOSQLiteFactory();
    }

    public static DAOSQLiteFactory getInstance() {
        return DAOSQliteFactoryHolder.instance;
    }

    public UserDAO getUserDAO() {
        return UserDAOSQLite.getInstance();
    }
}