package fr.umontpellier.polytech.ig.colocalternant.dao;

import fr.umontpellier.polytech.ig.colocalternant.dao.accommodationAlert.AccommodationAlertDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.accommodationAlert.AccommodationAlertDAOSQLite;
import fr.umontpellier.polytech.ig.colocalternant.dao.notification.NotificationDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.notification.NotificationDAOSQLite;
import fr.umontpellier.polytech.ig.colocalternant.dao.profile.ProfileDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.profile.ProfileDAOSQLite;

import fr.umontpellier.polytech.ig.colocalternant.dao.accomodation.AccommodationDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.accomodation.AccommodationDAOSQLite;

import fr.umontpellier.polytech.ig.colocalternant.dao.category.CategoryDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.category.CategoryDAOSQLite;
import fr.umontpellier.polytech.ig.colocalternant.dao.profile.ProfileDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.profile.ProfileDAOSQLite;
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
     * Retrieves the profile DAO.
     * @return The profile DAO.
     */
    public ProfileDAO getProfileDAO() {
        return ProfileDAOSQLite.getInstance();
    }


    /**
     * Retrieves the accommodation DAO.
     * @return The accommodation DAO.
     */
    public AccommodationDAO getAccommodationDAO() {
        return AccommodationDAOSQLite.getInstance();
    }


    /**
     * Retrieves the accommodationAlert DAO.
     * @return The accommodationAlert DAO.
     */
    public AccommodationAlertDAO getAccommodationAlertDAO() {
        return AccommodationAlertDAOSQLite.getInstance();
    }


    /**
     * Retrieves the chat DAO.
     * @return The chat DAO.
     */
    public ChatDAO getChatDAO() {
        return ChatDAOSQLite.getInstance();
    }

    
    /**
     * Retrieves the category DAO.
     * @return The category DAO.
     */
    public CategoryDAO getCategoryDAO() {
        return CategoryDAOSQLite.getInstance();
    }


    /**
     * Retrieves the category DAO.
     * @return The category DAO.
     */
    public NotificationDAO getNotificationDAO() {
        return NotificationDAOSQLite.getInstance();
    }


    /**
     * Holder of the unique instance of the DAO factory
     */
    private static class DAOSQLiteFactoryHolder {
        private final static DAOSQLiteFactory instance = new DAOSQLiteFactory();
    }
}