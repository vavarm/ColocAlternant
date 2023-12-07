package fr.umontpellier.polytech.ig.colocalternant.dao.user;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialExceptionType;
import fr.umontpellier.polytech.ig.colocalternant.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDAO {
    protected DAOFactory daoFactory;

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
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
            return null;
        }
    }
}