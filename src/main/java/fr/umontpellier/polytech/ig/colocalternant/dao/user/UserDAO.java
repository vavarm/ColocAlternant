package fr.umontpellier.polytech.ig.colocalternant.dao.user;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialExceptionType;
import fr.umontpellier.polytech.ig.colocalternant.profile.EnumRole;
import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;
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

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        User user = new User(
                                resultSet.getInt("id"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getInt("age"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("photo")
                        );
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByID(int id) {
        User user = null;
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User(
                                resultSet.getInt("id"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getInt("age"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("photo")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}