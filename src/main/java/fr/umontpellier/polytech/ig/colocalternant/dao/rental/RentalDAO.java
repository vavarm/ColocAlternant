package fr.umontpellier.polytech.ig.colocalternant.dao.rental;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.rental.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO of the rentals
 */
public class RentalDAO {
    /**
     * The DAO factory
     */
    protected DAOFactory daoFactory;

    /**
     * Creates a new rental in the database.
     * @param id The id of the rental.
     * @param profileId The id of the profile.
     * @param accommodationId The id of the accommodation.
     * @param period The period of the rental.
     * @param isRequest The state of the rental.
     */
    public void insertRental(int profileId, int accommodationId, String period, boolean isRequest){
        try {
            Connection connection = this.daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Rentals (profileId, accommodationId, period, isRequest) VALUES (?, ?, ?, ?)")) {
                preparedStatement.setInt(1, profileId);
                preparedStatement.setInt(2, accommodationId);
                preparedStatement.setString(3, period);
                preparedStatement.setBoolean(4, isRequest);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    /**
     * Retrieves all rentals from the database.
     * @return List of all rentals.
     */
    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        try {
            Connection connection = this.daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Rentals")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Rental rental = new Rental(
                                resultSet.getInt("id"),
                                resultSet.getInt("profileId"),
                                resultSet.getInt("accommodationId"),
                                resultSet.getString("period"),
                                resultSet.getBoolean("isRequest")
                        );
                        rentals.add(rental);
                    }
                }
            }
            return rentals;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves a rental from the database.
     * @param id The id of the rental.
     * @return The rental.
     */
    public Rental getRentalById(int id) {
        try {
            Connection connection = this.daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Rentals WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Rental(
                                resultSet.getInt("id"),
                                resultSet.getInt("profileId"),
                                resultSet.getInt("accommodationId"),
                                resultSet.getString("period"),
                                resultSet.getBoolean("isRequest")
                        );
                    }
                }
            }
            return null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the state of a rental.
     * @param id
     * @param isRequest
     */
    public void updateRentalState(int id, boolean isRequest) {
        try {
            Connection connection = this.daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Rentals SET isRequest = ? WHERE id = ?")) {
                preparedStatement.setBoolean(1, isRequest);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Deletes a rental from the database.
     * @param id The id of the rental.
     */
    public void deleteRental(int id) {
        try {
            Connection connection = this.daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Rentals WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
