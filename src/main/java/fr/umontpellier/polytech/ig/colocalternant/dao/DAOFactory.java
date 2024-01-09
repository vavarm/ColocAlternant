package fr.umontpellier.polytech.ig.colocalternant.dao;

import fr.umontpellier.polytech.ig.colocalternant.category.CategoryFacade;
import fr.umontpellier.polytech.ig.colocalternant.dao.accomodation.AccommodationDAO;

import fr.umontpellier.polytech.ig.colocalternant.dao.category.CategoryDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.profile.ProfileDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.rental.RentalDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.profile.ProfileDAO;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.dao.chat.ChatDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.time.LocalDateTime;

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
     * Retrieves the unique instance of the user DAO.
     * @return The User DAO.
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
     * Retrieves the unique instance of the chat DAO
     * @return The Chat DAO.
     */
    public abstract ChatDAO getChatDAO();

    /**
     * Retrieves the unique instance of the category DAO
     * @return The Category DAO.
     */
    public abstract CategoryDAO getCategoryDAO();

    /**
     * Retrieves the unique instance of the rental DAO.
     * @return The rental DAO.
     */
    public abstract RentalDAO getRentalDAO();

    /**
     * Initializes the database. Creates the tables and seeds them.
     * @param connection The connection to the database.
     */
    protected void setup(Connection connection) {
        this.CreateTables(connection);
        this.SeedTables(connection);
        this.printAllInfo(connection);
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
     * Creates the tables related to the profile in the database.
     * @param connection The connection to the database.
     */
    private void CreateProfileTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Profiles (id INTEGER PRIMARY KEY AUTOINCREMENT, userID INTEGER, description TEXT, role TEXT, isPublic BOOLEAN)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void DeleteProfileTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Profiles");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void DeleteUserTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*        
     * Creates the tables related to the chat in the database.
     * @param connection The connection to the database.
     */
    private void CreateChatTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Chats (id INTEGER PRIMARY KEY AUTOINCREMENT, idSender INTEGER, idDest INTEGER, message TEXT, timestamp TEXT, isDeleted INTEGER)");
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
            // drop table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Accomodations (id INTEGER PRIMARY KEY AUTOINCREMENT, price FLOAT , location TEXT, title TEXT, surface FLOAT, description TEXT, specialFonctionalities TEXT, energicReport FLOAT, photos TEXT, UNIQUE (location, title))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the table related to the category in the database.
     * @param connection The connection to the database.
     */
    private void CreateCategoryTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Categories (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE)");
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
     * Creates the table related to the rentals in the database.
     * @param connection The connection to the database.
     */
    private void CreateRentalsTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Rentals (id INTEGER PRIMARY KEY AUTOINCREMENT, profileId INTEGER, accommodationId INTEGER, period TEXT, isRequest INTEGER, FOREIGN KEY (profileId) REFERENCES Profiles(id), FOREIGN KEY (accommodationId) REFERENCES Accomodations(id), UNIQUE (profileId, accommodationId, period))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Seeds the tables related to the rentals in the database.
     * @param connection The connection to the database.
     */
    private void SeedRentalsTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO Rentals (profileId, accommodationId, period, isRequest) VALUES (2, 1, '2020-01-01 2020-08-01', 0)");
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("DAOFactory: Rental already exists");
            } else {
                e.printStackTrace();
            }
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
            statement.executeUpdate("INSERT INTO Users (firstName, lastName, age, email, password, photo) VALUES ('Prenom_1', 'Nom_1', 18, 'a', 'a', 'https://res.cloudinary.com/editions-tissot/image/upload/t_wp_lynx_featured_post/v1585896810/Article_lumio_impact_crnvrs_apprentis.jpg')");
            statement.executeUpdate("INSERT INTO Users (firstName, lastName, age, email, password, photo) VALUES ('Prenom_2', 'Nom_2', 20, 'b', 'b', 'https://res.cloudinary.com/editions-tissot/image/upload/t_wp_lynx_featured_post/v1585896810/Article_lumio_impact_crnvrs_apprentis.jpg')");
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("DAOFactory: User already exists");
            } else {
                e.printStackTrace();
            }
        }
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO Users (firstName, lastName, age, email, password, photo) VALUES ('Jane', 'Doe', 42, 'jane.doe@test.com', 'password', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fpngtree.com%2Fso%2Fprofile&psig=AOvVaw06nRk09YyDMIfh1K51s08j&ust=1701708080137000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCPCzzd7a84IDFQAAAAAdAAAAABAE')");
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("DAOFactory: User already exists");
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * Seeds the tables related to the chat in the database.
     * @param connection The connection to the database.
     */
    private void SeedChatTable(Connection connection) {
        // remove all chats
        /*
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM Chats");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Chats (idSender, idDest, message, timestamp, isDeleted) VALUES (?, ?, ?, ?, ?);")) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 2);
            preparedStatement.setString(3, "Hello");
            preparedStatement.setString(4, LocalDateTime.now().toString());
            preparedStatement.setBoolean(5, false);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("DAOFactory: Chat already exists");
            } else {
                e.printStackTrace();
            }
        }
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Chats (idSender, idDest, message, timestamp, isDeleted) VALUES (?, ?, ?, ?, ?);")) {
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, 1);
            preparedStatement.setString(3, "Hi");
            preparedStatement.setString(4, LocalDateTime.now().toString());
            preparedStatement.setBoolean(5, false);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("DAOFactory: Chat already exists");
            } else {
                e.printStackTrace();
            }
        }
    }


    /**
     * Seeds the tables related to the profile in the database.
     * @param connection The connection to the database.
     */
    private void SeedProfileTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO Profiles (userID, description, role, isPublic) VALUES (1, 'The first description', 'Owner', 'TRUE')");
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("DAOFactory: Profile already exists");
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
           statement.executeUpdate("INSERT INTO Accomodations (price, location, title, surface, description, specialFonctionalities, energicReport, photos) VALUES (300000000, 'Université de Montpellier, Pl. Eugène Bataillon, 34090 Montpellier', 'Polytech Montpellier', 35, 'school', 'wifi', '0', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS594D5fYC3ogCVbFVVY9zff7tM2z0_cZrf5FA65pLI_y05Bofbcxd4TFfT6NaXnASpRng&usqp=CAU')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Seeds the tables related to the category in the database.
     * @param connection The connection to the database.
     */
    private void SeedCategoryTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO Categories (name) VALUES ('bathroom')");
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("DAOFactory: Category already exists");
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates the tables related to the category and the accommodation in the database.
     * @param connection The connection to the database.
     */
    private void CreateCategoryAccommodationTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS CategoryAccommodation (category_id INTEGER, accommodation_id INTEGER, PRIMARY KEY (category_id, accommodation_id), FOREIGN KEY (category_id) REFERENCES Categories(id), FOREIGN KEY (accommodation_id) REFERENCES Accomodations(id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Seeds the tables related to the category and the accommodation in the database.
     * @param connection The connection to the database.
     */
    private void SeedCategoryAccommodationTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO AccommodationCategories (category_id, accommodation_id) VALUES (1, 1)");
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("DAOFactory: CategoryAccommodation already exists");
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
        CreateAccomodationTable(connection);
        CreateOwnsTable(connection);
        CreateChatTable(connection);
        DeleteProfileTable(connection);
        CreateProfileTable(connection);
        CreateCategoryTable(connection);
        CreateCategoryAccommodationTable(connection);
        CreateRentalsTable(connection);
    }


    /**
     * Seeds the tables in the database.
     * @param connection The connection to the database.
     */
    protected void SeedTables(Connection connection) {
        SeedUserTable(connection);
        SeedAccommodationTable(connection);
        SeedOwnsTable(connection);
        SeedChatTable(connection);
        SeedProfileTable(connection);
        SeedCategoryTable(connection);
        SeedCategoryAccommodationTable(connection);
        SeedRentalsTable(connection);
    }

    protected void printAllInfo(Connection connection){
        //print the ids and email of all users
        System.out.println("Users:");
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT id, email FROM Users");
            while(resultSet.next()){
                System.out.println("id: " + resultSet.getInt("id") + " email: " + resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
        //print the ids and messages of all chats
        System.out.println("Chats:");
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT id, message FROM Chats");
            while(resultSet.next()){
                System.out.println("id: " + resultSet.getInt("id") + " message: " + resultSet.getString("message"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Accommodations:");
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT id, title FROM Accomodations");
            while(resultSet.next()){
                System.out.println("id: " + resultSet.getInt("id") + " title: " + resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}