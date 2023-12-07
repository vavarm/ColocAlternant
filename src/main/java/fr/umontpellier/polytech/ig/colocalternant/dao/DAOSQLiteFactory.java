package fr.umontpellier.polytech.ig.colocalternant.dao;

import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAOSQLite;
import java.sql.*;

public class DAOSQLiteFactory extends DAOFactory {

    private String dbPath = "src/main/resources/db/coloc.db";

    private DAOSQLiteFactory() {
        super();
    }

    protected void setup(Connection connection) {
            this.CreateTables(connection);
            this.SeedTables(connection);
    }

    public Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = java.sql.DriverManager.getConnection("jdbc:sqlite:" + this.dbPath);
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

    private static class DAOSQLiteFactoryHolder {
        private final static DAOSQLiteFactory instance = new DAOSQLiteFactory();
    }

    public static DAOSQLiteFactory getInstance() {
        return DAOSQLiteFactoryHolder.instance;
    }

    public UserDAO getUserDAO() {
        return UserDAOSQLite.getInstance();
    }
}