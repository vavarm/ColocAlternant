package fr.umontpellier.polytech.ig.colocalternant.dao.accomodation;

import fr.umontpellier.polytech.ig.colocalternant.accomodation.Accommodation;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * AccommodationDAO is an abstract class providing methods to interact with the database
 * for managing Accommodation entities.
 */
public abstract class AccommodationDAO {

    /**
     * The DAO factory.
     */
    protected DAOFactory daoFactory;

    /**
     * Inserts an accommodation into the database.
     *
     * @param accommodation The accommodation to be inserted.
     */
    public void insertAccommodation(Accommodation accommodation) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            String query = "INSERT INTO accomodations (title, location, description, price, specialFonctionalities, energicReport, photos) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, accommodation.getTitle());
            preparedStatement.setString(2, accommodation.getLocation());
            preparedStatement.setString(3, accommodation.getDescription());
            preparedStatement.setFloat(4, accommodation.getPrice());
            preparedStatement.setString(5, accommodation.getSpecialFonctionalities());
            preparedStatement.setFloat(6, accommodation.getEnergicReport());
            preparedStatement.setString(7, accommodation.getPhotos());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //closeResources(connection, preparedStatement, null);
        }
    }

    /**
     * Retrieve all accommodations from the database.
     *
     * @return List of all accommodations.
     */
    public ArrayList<Accommodation> getAllAccommodation() {
        ArrayList<Accommodation> accommodations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT * FROM accomodations";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Accommodation accommodation = new Accommodation(resultSet.getInt("id"),
                        resultSet.getString("title"), resultSet.getString("location"),
                        resultSet.getString("description"), resultSet.getFloat("price"),
                        resultSet.getString("specialFonctionalities"), resultSet.getFloat("energicReport"),
                        resultSet.getString("photos"));
                accommodations.add(accommodation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //closeResources(connection, preparedStatement, resultSet);
        }

        return accommodations;
    }

    /**
     * Retrieve a specific accommodation from the database based on its ID.
     *
     * @param id The ID of the accommodation to retrieve.
     * @return The accommodation with the specified ID.
     */
    public Accommodation getAccommodation(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Accommodation a = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT * FROM accomodations WHERE id  = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                a = new Accommodation(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("location"), resultSet.getString("description"),
                        resultSet.getFloat("price"), resultSet.getString("specialFonctionalities"),
                        resultSet.getFloat("energicReport"), resultSet.getString("photos"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //closeResources(connection, preparedStatement, resultSet);
            return a;
        }
    }

    /**
     * Update an existing accommodation in the database.
     *
     * @param accommodation The accommodation to be updated.
     */
    public void updateAccommodation(Accommodation accommodation) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            String query = "UPDATE accomodations SET title=?, location=?, description=?, price=?, specialFonctionalities=?, energicReport=?, photos=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, accommodation.getTitle());
            preparedStatement.setString(2, accommodation.getLocation());
            preparedStatement.setString(3, accommodation.getDescription());
            preparedStatement.setFloat(4, accommodation.getPrice());
            preparedStatement.setString(5, accommodation.getSpecialFonctionalities());
            preparedStatement.setFloat(6, accommodation.getEnergicReport());
            preparedStatement.setString(7, accommodation.getPhotos());
            preparedStatement.setInt(8, accommodation.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //closeResources(connection, preparedStatement, null);
        }
    }

    /**
     * Delete an accommodation from the database.
     *
     * @param id The ID of the accommodation to be deleted.
     */
    public void deleteAccommodation(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            String query = "DELETE FROM accomodations WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //closeResources(connection, preparedStatement, null);
        }
    }

    /**
     * Retrieve information about whether the current user owns a specific accommodation.
     *
     * @param user_id          The ID of the user.
     * @param accommodation_id The ID of the accommodation.
     * @return ResultSet containing the corresponding line of the owns table if the current accommodation is owned by the current user; null otherwise.
     */
    public ResultSet getOwns(int user_id, int accommodation_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT * FROM owns WHERE user_id=? AND accommodation_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, accommodation_id);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * Close the database connection, prepared statement, and result set.
     *
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
