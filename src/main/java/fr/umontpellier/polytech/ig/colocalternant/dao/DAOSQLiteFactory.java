package fr.umontpellier.polytech.ig.colocalternant.dao;

import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAOSQLite;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOSQLiteFactory extends DAOFactory {

    private String dbPath = "src/main/resources/db/coloc.db";

    private DAOSQLiteFactory() {
        super();
    }

    public static DAOSQLiteFactory getInstance() {
        return DAOSQLiteFactoryHolder.instance;
    }

    public Connection getConnection() throws SQLException {
        if (this.connection != null) {
            return this.connection;
        }
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = java.sql.DriverManager.getConnection("jdbc:sqlite:" + this.dbPath);
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        return connection;
    }

    public UserDAO getUserDAO() {
        return UserDAOSQLite.getInstance();
    }

    private static class DAOSQLiteFactoryHolder {
        private final static DAOSQLiteFactory instance = new DAOSQLiteFactory();
    }
}