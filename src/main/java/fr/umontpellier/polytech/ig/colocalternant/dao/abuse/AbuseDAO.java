package fr.umontpellier.polytech.ig.colocalternant.dao.abuse;

import fr.umontpellier.polytech.ig.colocalternant.abuse.Abuse;
import fr.umontpellier.polytech.ig.colocalternant.abuse.StatusEnum;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;

import java.sql.*;
import java.util.ArrayList;

/**
 * Abstract Data Access Object (DAO) class for handling interactions with the database related to abuse entries.
 */
public abstract class AbuseDAO {
    /**
     * The DAO factory.
     */
    protected DAOFactory daoFactory;

    /**
     * Default constructor for the AbuseDAO class.
     */
    public AbuseDAO() {
    }

    /**
     * Creates a new abuse entry in the database.
     *
     * @param message The message associated with the abuse.
     * @param dest    The destination user for the abuse.
     * @return The created Abuse object.
     */
    public Abuse createAbuse(String message, User dest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        int generatedKey;
        Statement statement = null;

        try {
            connection = daoFactory.getConnection();
            String sql = "INSERT INTO Abuses (message, user_id, status) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message);
            preparedStatement.setInt(2, dest.getId());
            preparedStatement.setString(3, "PENDING");
            preparedStatement.executeUpdate();

            statement = connection.createStatement();
            generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
            if (generatedKeys.next()) {
                int abuseId = generatedKeys.getInt(1);
                return new Abuse(abuseId, message, dest, StatusEnum.PENDING); // Adjust as needed

            } else {
                throw new SQLException("Creating abuse failed, no ID obtained.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly
            return null;
        }
    }

    /**
     * Updates the status of an existing abuse entry in the database.
     *
     * @param abuse  The abuse entry to update.
     * @param status The new status for the abuse entry.
     * @return The updated Abuse object.
     */
    public Abuse updateAbuse(Abuse abuse, StatusEnum status) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            String sql = "UPDATE Abuses SET status = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, abuse.getId());
            preparedStatement.executeUpdate();

            abuse.setStatus(status); // Update the status in the Abuse object
            return abuse;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly
            return null;
        }
    }

    /**
     * Deletes an existing abuse entry from the database.
     *
     * @param abuse The abuse entry to delete.
     * @return The deleted Abuse object.
     */
    public Abuse deleteAbuse(Abuse abuse) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            String sql = "DELETE FROM Abuses WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, abuse.getId());
            preparedStatement.executeUpdate();

            return abuse;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly
            return null;
        }
    }

    /**
     * Retrieves all abuse entries from the database.
     *
     * @return An ArrayList of Abuse objects representing all abuse entries.
     */
    public ArrayList<Abuse> getAllAbuses() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM abuses";
            resultSet = statement.executeQuery(sql);

            ArrayList<Abuse> abuses = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String message = resultSet.getString("message");
                int userId = resultSet.getInt("user_id");
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                StatusEnum status = StatusEnum.valueOf(resultSet.getString("status"));
                User user = daoFactory.getUserDAO().getUserById(userId);
                abuses.add(new Abuse(id, message, user, status));
            }

            return abuses;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly
            return null;
        }
    }
}
