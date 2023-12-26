package fr.umontpellier.polytech.ig.colocalternant.dao;

import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAOSQLite;
import fr.umontpellier.polytech.ig.colocalternant.dao.chat.ChatDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.chat.ChatDAOSQLite;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Concrete implementation of the DAO factory for SQLite
 */
public class DAOSQLiteFactory extends DAOFactory {

    /**
     * The path to the database
     */
    private String dbPath = "src/main/resources/db/coloc.db";

    /**
     * Constructor of the DAO factory
     */
    private DAOSQLiteFactory() {
        super();
    }

    /**
     * Retrieves the unique instance of the DAO factory.
     * @return The DAO factory.
     */
    public static DAOSQLiteFactory getInstance() {
        return DAOSQLiteFactoryHolder.instance;
    }

    /**
     * Retrieves the connection to the database.
     * @return The connection to the database.
     * @throws SQLException if the connection to the database fails.
     */
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


    /**
     * Retrieves the user DAO.
     * @return The user DAO.
     */
    public UserDAO getUserDAO() {
        return UserDAOSQLite.getInstance();
    }

    /**
     * Retrieves the chat DAO.
     * @return The chat DAO.
     */
    public ChatDAO getChatDAO() {
        return ChatDAOSQLite.getInstance();
    }

    /**
     * Holder of the unique instance of the DAO factory
     */
    private static class DAOSQLiteFactoryHolder {
        private final static DAOSQLiteFactory instance = new DAOSQLiteFactory();
    }
}