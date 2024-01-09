package fr.umontpellier.polytech.ig.colocalternant.dao.accommodationAlert;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.accommodationAlert.AccommodationAlert;
import fr.umontpellier.polytech.ig.colocalternant.profile.EnumRole;
import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;
import fr.umontpellier.polytech.ig.colocalternant.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * AccommodationAlertDAO is an abstract class providing methods to interact with the database
 * for managing Accommodation entities.
 */
public abstract class AccommodationAlertDAO {

    /**
     * The DAO factory.
     */
    protected DAOFactory daoFactory;

    /**
     * Inserts an accommodationAlert into the database.
     * @param profileID         The ID of the profile.
     * @param location          The location of the accommodation.
     * @param surface           The surface of the accommodation.
     * @param minPrice          The minimum price of the accommodation.
     * @param maxPrice          The maximum price of the accommodation.
     */
    public void insertAccommodationAlert(int profileID, String location, float surface, float minPrice, float maxPrice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            String query = "INSERT INTO AccomodationAlerts (profileID, location, surface, minPrice, maxPrice) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, profileID);
            preparedStatement.setString(2, location);
            preparedStatement.setFloat(3, surface);
            preparedStatement.setFloat(4, minPrice);
            preparedStatement.setFloat(5, maxPrice);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve all accommodationAlerts from the database.
     * @return List of all accommodationAlerts.
     */
    public ArrayList<AccommodationAlert> getAllAccommodationAlert() {
        ArrayList<AccommodationAlert> accommodationAlerts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM AccomodationAlerts")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        AccommodationAlert accommodationAlert = new AccommodationAlert(
                                resultSet.getInt("id"),
                                resultSet.getInt("profileID"),
                                resultSet.getString("location"),
                                resultSet.getFloat("surface"),
                                resultSet.getFloat("minPrice"),
                                resultSet.getFloat("maxPrice")
                        );
                        accommodationAlerts.add(accommodationAlert);
                    }
                }
            }
        }
         catch (SQLException e) {
            e.printStackTrace();
        }
        return accommodationAlerts;
    }

    /**
     * Retrieves the AccommodationAlert owned by a specific profile from the database.
     * @param profileID The profile for whom to retrieve the AccommodationAlerts.
     * @return List of AccommodationAlerts owned by the profile.
     */
    public ArrayList<AccommodationAlert> getOwnAccommodationAlerts(int profileID) {
        ArrayList<AccommodationAlert> accommodationAlerts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM AccomodationAlerts WHERE profileID = ?")) {
                preparedStatement.setInt(1, profileID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        AccommodationAlert accommodationAlert = new AccommodationAlert(
                                resultSet.getInt("id"),
                                resultSet.getInt("profileID"),
                                resultSet.getString("location"),
                                resultSet.getFloat("surface"),
                                resultSet.getFloat("minPrice"),
                                resultSet.getFloat("maxPrice")
                        );
                        accommodationAlerts.add(accommodationAlert);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accommodationAlerts;
    }

    /**
     * Retrieve a specific accommodationAlert from the database based on its ID.
     * @param id The ID of the accommodationAlert to retrieve.
     * @return The accommodationAlert with the specified ID.
     */
    public AccommodationAlert getAccommodationAlertByID(int id) {
        try {
            Connection connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM AccomodationAlerts WHERE id LIKE ?;")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new AccommodationAlert(
                                resultSet.getInt("id"),
                                resultSet.getInt("profileID"),
                                resultSet.getString("location"),
                                resultSet.getFloat("surface"),
                                resultSet.getFloat("minPrice"),
                                resultSet.getFloat("maxPrice")
                        );
                    }
                }
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update an existing accommodationAlert in the database.
     * @param id                The ID of the accommodation.
     * @param profileID         The ID of the profile.
     * @param location          The location of the accommodation.
     * @param surface           The surface of the accommodation.
     * @param minPrice          The minimum price of the accommodation.
     * @param maxPrice          The maximum price of the accommodation.
     */
    public void updateAccommodationAlert(int id, int profileID, String location, float surface, float minPrice, float maxPrice) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE AccomodationAlerts SET profileID=?, location=?, surface=?, minPrice=?, maxPrice=? WHERE id=?")) {
                preparedStatement.setInt(1, profileID);
                preparedStatement.setString(2, location);
                preparedStatement.setFloat(3, surface);
                preparedStatement.setFloat(4, minPrice);
                preparedStatement.setFloat(5, maxPrice);
                preparedStatement.setInt(6, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete an accommodationAlert from the database.
     * @param id The ID of the accommodationAlert to be deleted.
     */
    public void deleteAccommodationAlert(int id) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM AccomodationAlerts WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Close the database connection, prepared statement, and result set.
     * @param connection        The database connection.
     * @param preparedStatement The prepared statement.
     * @param resultSet         The result set.
     */
    private void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
