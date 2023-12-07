package fr.umontpellier.polytech.ig.colocalternant.dao;

import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAOFactory {

    protected static Connection connection = null;

    DAOFactory() {
        try {
            Connection connection = this.getConnection();
            this.setup(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract UserDAO getUserDAO();

    protected void setup(Connection connection) {
        this.CreateTables(connection);
        this.SeedTables(connection);
    }

    public abstract Connection getConnection() throws SQLException;

    private void CreateUserTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, age INTEGER, email TEXT UNIQUE, password TEXT, photo TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void SeedUserTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO Users (firstName, lastName, age, email, password, photo) VALUES ('John', 'Doe', 42, 'john.doe@test.com', 'password', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fpngtree.com%2Fso%2Fprofile&psig=AOvVaw06nRk09YyDMIfh1K51s08j&ust=1701708080137000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCPCzzd7a84IDFQAAAAAdAAAAABAE')");
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                System.err.println("User already exists");
            } else {
                e.printStackTrace();
            }
        }
    }

    protected void CreateTables(Connection connection) {
        CreateUserTable(connection);
    }

    protected void SeedTables(Connection connection) {
        SeedUserTable(connection);
    }
}