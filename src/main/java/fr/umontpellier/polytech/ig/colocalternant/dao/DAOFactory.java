package fr.umontpellier.polytech.ig.colocalternant.dao;

import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.chat.ChatDAO;

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
     * Retrieves the unique instance of the DAO factory.
     * @return The User DAO.
     */
    public abstract UserDAO getUserDAO();

    /**
     * Retrieves the unique instance of the DAO factory.
     * @return The Chat DAO.
     */
    public abstract ChatDAO getChatDAO();

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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, age INTEGER, email TEXT UNIQUE, password TEXT, photo TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
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
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM Chats");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
     * Creates the tables in the database.
     * @param connection The connection to the database.
     */
    protected void CreateTables(Connection connection) {
        CreateUserTable(connection);
        CreateChatTable(connection);
    }

    /**
     * Seeds the tables in the database.
     * @param connection The connection to the database.
     */
    protected void SeedTables(Connection connection) {
        SeedUserTable(connection);
        SeedChatTable(connection);
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
    }
}