package fr.umontpellier.polytech.ig.colocalternant.dao;

import fr.umontpellier.polytech.ig.colocalternant.dao.accomodation.AccommodationDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.profile.ProfileDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAO;

import java.sql.Connection;
import java.sql.ResultSet;
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
     * Retrieves the unique instance of the accommodation DAO.
     * @return The accommodation DAO.
     */
    public abstract AccommodationDAO getAccommodationDAO();

    /**
     * Retrieves the unique instance of the profile DAO.
     * @return The profile DAO.
     */
    public abstract ProfileDAO getProfileDAO();

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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, age INTEGER, email TEXT UNIQUE, password TEXT, photo TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the table related to the accomodation in the database.
     * @param connection The connection to the database.
     *
     */
    private void CreateAccomodationTable(Connection connection){
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Accomodations (id INTEGER PRIMARY KEY AUTOINCREMENT, price FLOAT , location TEXT, title TEXT, surface FLOAT, description TEXT, specialFonctionalities TEXT, energicReport FLOAT, photos TEXT )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the table related to the association between an owner and his accommodation in the database.
     * @param connection The connection to the database.
     *
     */
    private void CreateOwnsTable(Connection connection){
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Owns (user_id INTEGER,  accommodation_id INTEGER, PRIMARY KEY (user_id, accommodation_id),  FOREIGN KEY (user_id) REFERENCES user(id), FOREIGN KEY (accommodation_id) REFERENCES accommodation(id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Seeds the tables related to the user in the database.
     * @param connection The connection to the database.
     */
    private void SeedOwnsTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO owns (user_id, accommodation_id ) VALUES (1,1)");
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("DAOFactory: Owns association already exists");
            } else {
                e.printStackTrace();
            }
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
     * Seeds the tables related to the accommodation in the database.
     * @param connection The connection to the database.
     */
    private void SeedAccommodationTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM Accomodations");
           statement.executeUpdate("INSERT INTO Accomodations (price, location, title, surface, description, specialFonctionalities, energicReport, photos) VALUES (300000000, 'Université de Montpellier, Pl. Eugène Bataillon, 34090 Montpellier', 'Polytech Montpellier', 35, 'school', 'wifi', '0', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS594D5fYC3ogCVbFVVY9zff7tM2z0_cZrf5FA65pLI_y05Bofbcxd4TFfT6NaXnASpRng&usqp=CAU')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Creates the tables in the database.
     * @param connection The connection to the database.
     */
    protected void CreateTables(Connection connection) {

        CreateUserTable(connection);
        CreateAccomodationTable(connection);
        CreateOwnsTable(connection);
    }

    /**
     * Seeds the tables in the database.
     * @param connection The connection to the database.
     */
    protected void SeedTables(Connection connection) {
        SeedUserTable(connection);
        SeedAccommodationTable(connection);
        SeedOwnsTable(connection);
    }
}