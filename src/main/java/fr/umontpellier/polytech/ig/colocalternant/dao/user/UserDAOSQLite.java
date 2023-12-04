package fr.umontpellier.polytech.ig.colocalternant.dao.user;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;

public class UserDAOSQLite extends UserDAO {

    private UserDAOSQLite() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    private static class UserDAOSQLiteHolder {
        private final static UserDAOSQLite instance = new UserDAOSQLite();
    }

    public static UserDAOSQLite getInstance() {
        return UserDAOSQLiteHolder.instance;
    }
}