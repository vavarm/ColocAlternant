package fr.umontpellier.polytech.ig.colocalternant.dao.user;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialExceptionType;
import fr.umontpellier.polytech.ig.colocalternant.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO of the user
 */
public abstract class UserDAO {
    /**
     * The DAO factory
     */
    protected DAOFactory daoFactory;

    /**
     * Retrieves the user with the given email and password from the database.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The user if the login is successful, null otherwise.
     * @throws CredentialException if the email or the password is incorrect.
     */
    public User getUser(String email, String password) throws CredentialException {
        try {
            Connection connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE email LIKE ?;")) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        if (resultSet.getString("password").equals(password)) {
                            return new User(resultSet.getInt("id"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getInt("age"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("photo"));
                        } else {
                            CredentialException credentialException = new CredentialException(CredentialExceptionType.INVALID_PASSWORD);
                            throw new CredentialException(CredentialExceptionType.INVALID_PASSWORD);
                        }
                    } else {
                        throw new CredentialException(CredentialExceptionType.INVALID_EMAIL);
                    }
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    /**
     * Inserts the given user into the database.
     * @param newUser The user to insert.
     * @throws CredentialException if the email is already used.
     */
    public void insertUser(User newUser) throws CredentialException {
        Connection connection = null;
        try {
            connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (firstname, lastname, age, email, password, photo) VALUES (?, ?, ?, ?, ?, ?);")) {
                preparedStatement.setString(1, newUser.getFirstName());
                preparedStatement.setString(2, newUser.getLastName());
                preparedStatement.setInt(3, newUser.getAge());
                preparedStatement.setString(4, newUser.getEmail());
                preparedStatement.setString(5, newUser.getPassword());
                preparedStatement.setString(6, newUser.getPhoto());
                System.out.println("SQL Query: " + preparedStatement.toString());
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 0) {
                    // No rows were affected, indicating a problem
                    throw new CredentialException(CredentialExceptionType.EMAIL_ALREADY_USED);
                }
                connection.commit();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Updates the password's given user in the database.
     * @param currentUser The user to update.
     * @param newPwd The new password of the user.
     */
    public void changePassword(User currentUser, String newPwd) {
        Connection connection = null;
        try {
            connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Users SET password = ? WHERE id = ?;")) {
                preparedStatement.setString(1, newPwd);
                preparedStatement.setInt(2, currentUser.getId());
                System.out.println("SQL Query: " + preparedStatement.toString());
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 0) {
                    // No rows were affected, indicating a problem
                    throw new Exception("No rows were affected, indicating a problem");
                }
                connection.commit();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all the users from the database.
     * @return The list of all the users.
     */
    public ArrayList<User> getAllUsers() {
        Connection connection = null;
        try {
            connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users;")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    ArrayList<User> users = new ArrayList<>();
                    while (resultSet.next()) {
                        users.add(new User(resultSet.getInt("id"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getInt("age"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("photo")));
                    }
                    return users;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }
}