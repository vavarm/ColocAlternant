package fr.umontpellier.polytech.ig.colocalternant.dao.profile;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.profile.EnumRole;
import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;
import fr.umontpellier.polytech.ig.colocalternant.user.User;

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
     * Creates a new profile in the database.
     *
     * @param isPublic    Whether the profile is public or not.
     * @param description The description of the profile.
     * @param userID      The userID associated with the profile.
     * @param role        The role of the user (Tenant, Owner, Admin).
     * @return The created profile.
     */
    public void createProfile(boolean isPublic, String description, int userID, EnumRole role) {
        //Profile profile = new Profile(-1, isPublic, description, userID, role);
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Profiles (userID, description, role, isPublic) VALUES (?, ?, ?, ?)")) {
                preparedStatement.setInt(1, userID);
                preparedStatement.setString(2, description);
                preparedStatement.setString(3, role.name());
                preparedStatement.setBoolean(4, isPublic);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all profiles from the database.
     *
     * @return List of all profiles.
     */
    public ArrayList<Profile> getAllProfiles() {
        ArrayList<Profile> profiles = new ArrayList<>();
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profiles")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Profile profile = new Profile(
                                resultSet.getInt("id"),
                                resultSet.getBoolean("isPublic"),
                                resultSet.getString("description"),
                                resultSet.getInt("userID"),
                                EnumRole.valueOf(resultSet.getString("role"))
                        );
                        profiles.add(profile);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profiles;
    }

    /**
     * Retrieves the profiles owned by a specific user from the database.
     *
     * @param userID The user for whom to retrieve the profiles.
     * @return List of profiles owned by the user.
     */
    public ArrayList<Profile> getOwnProfiles(int userID) {
        ArrayList<Profile> profiles = new ArrayList<>();
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profiles WHERE userID = ?")) {
                preparedStatement.setInt(1, userID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Profile profile = new Profile(
                                resultSet.getInt("id"),
                                resultSet.getBoolean("isPublic"),
                                resultSet.getString("description"),
                                resultSet.getInt("userID"),
                                EnumRole.valueOf(resultSet.getString("role"))
                        );
                        profiles.add(profile);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profiles;
    }

    /**
     * Update the information of a profile in the database.
     *
     * @param isPublic    Whether the profile is public or not.
     * @param description The description of the profile.
     * @param profileID   The ID of the profile.
     */
    public void updateProfile(int profileID, boolean isPublic, String description) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Profiles SET isPublic = ?, description = ? WHERE id = ?")) {
                preparedStatement.setBoolean(1, isPublic);
                preparedStatement.setString(2, description);
                preparedStatement.setInt(3, profileID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a profile from the database.
     *
     * @param profileID The ID of the profile.
     */
    public void deleteProfile(int profileID) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Profiles WHERE id = ?")) {
                preparedStatement.setInt(1, profileID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

