package fr.umontpellier.polytech.ig.colocalternant.dao;

import fr.umontpellier.polytech.ig.colocalternant.dao.profile.ProfileDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO factory
 */
public abstract class DAOFactory {

    /**
     * The connection to the database
     */
    protected static Connection connection = null;

    /**
     * Constructor of the DAO factory
     */
    protected DAOFactory() {
        try {
            Connection connection = this.getConnection();
            this.setup(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the unique instance of the DAO factory.
     * @return The DAO factory.
     */
    public abstract UserDAO getUserDAO();

    /**
     * Initializes the database. Creates the tables and seeds them.
     * @param connection The connection to the database.
     */
    protected void setup(Connection connection) {
        this.CreateTables(connection);
        this.SeedTables(connection);
    }

    /**
     * Retrieves the connection to the database.
     * @return The connection to the database.
     * @throws SQLException if the connection to the database fails.
     */
    public abstract Connection getConnection() throws SQLException;

    /**
     * Creates the tables related to the user in the database.
     * @param connection The connection to the database.
     */
    private void CreateUserTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, age INTEGER, email TEXT UNIQUE, password TEXT, photo TEXT, isBanned BOOLEAN DEFAULT FALSE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Seeds the tables related to the user in the database.
     * @param connection The connection to the database.
     */
    private void SeedUserTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO Users (firstName, lastName, age, email, password, photo) VALUES ('John', 'Doe', 42, 'john.doe@test.com', 'password', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fpngtree.com%2Fso%2Fprofile&psig=AOvVaw06nRk09YyDMIfh1K51s08j&ust=1701708080137000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCPCzzd7a84IDFQAAAAAdAAAAABAE')");
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("DAOFactory: User already exists");
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates the tables related to the profile in the database.
     * @param connection The connection to the database.
     */
    private void CreateProfileTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Profiles (user_id INTEGER, role TEXT, FOREIGN KEY(user_id) REFERENCES Users(id), PRIMARY KEY(user_id, role))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Seeds the tables related to the profile in the database.
     * @param connection The connection to the database.
     */
    private void SeedProfileTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO Profiles (user_id, role) VALUES (1, 'ADMIN')");
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("DAOFactory: Profile already exists");
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates the tables in the database.
     * @param connection The connection to the database.
     */
    protected void CreateTables(Connection connection) {
        CreateUserTable(connection);
        CreateProfileTable(connection);
    }

    /**
     * Seeds the tables in the database.
     * @param connection The connection to the database.
     */
    protected void SeedTables(Connection connection) {
        SeedUserTable(connection);
        SeedProfileTable(connection);
    }

    /**
     * Retrieves the unique instance of the DAO factory.
     * @return The DAO factory.
     */
    public abstract ProfileDAO getProfileDAO();
}