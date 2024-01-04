package fr.umontpellier.polytech.ig.colocalternant.dao.profile;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;
import fr.umontpellier.polytech.ig.colocalternant.profile.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO of the profile
 */
public abstract class ProfileDAO {
    /**
     * The DAO factory
     */
    protected DAOFactory daoFactory;

    /**
     * Retrieves all the users from the database.
     * @return The list of all the users.
     */
    public ArrayList<Profile> getAllProfiles() {
        Connection connection = null;
        try {
            connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profiles;")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    ArrayList<Profile> profiles = new ArrayList<>();
                    while (resultSet.next()) {
                        if (resultSet.getString("role").equals("ADMIN")) {
                            profiles.add(new Profile(resultSet.getInt("user_id"), Role.ADMIN));
                        } else if (resultSet.getString("role").equals("OWNER")) {
                            profiles.add(new Profile(resultSet.getInt("user_id"), Role.OWNER));
                        } else if (resultSet.getString("role").equals("RESIDENT")) {
                            profiles.add(new Profile(resultSet.getInt("user_id"), Role.TENANT));
                        }
                    }
                    return profiles;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }
}