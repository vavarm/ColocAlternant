package fr.umontpellier.polytech.ig.colocalternant.dao.user;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.*;
import java.sql.*;

public class UserDAOSQLite extends UserDAO {

    private UserDAOSQLite() {
    }

    private static class UserDAOSQLiteHolder {
        private final static UserDAOSQLite instance = new UserDAOSQLite();
    }

    public static UserDAOSQLite getInstance() {
        return UserDAOSQLiteHolder.instance;
    }

    public User getUser(String email, String password) throws CredentialException {
        try {
            Connection connection = DAOSQLiteFactory.getInstance().connect();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE email LIKE ?;");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        } catch (CredentialException credentialException) {
            throw credentialException;
        }
    }
}