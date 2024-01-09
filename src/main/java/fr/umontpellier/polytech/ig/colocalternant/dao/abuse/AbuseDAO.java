package fr.umontpellier.polytech.ig.colocalternant.dao.abuse;

import fr.umontpellier.polytech.ig.colocalternant.abuse.Abuse;
import fr.umontpellier.polytech.ig.colocalternant.abuse.StatusEnum;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 */
public abstract class AbuseDAO {
    /**
     * The DAO factory.
     */
    protected DAOFactory daoFactory;

    /**
     * Default constructor
     */
    public AbuseDAO() {
    }

    /**
     * @param message
     * @param dest
     * @return
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
            preparedStatement.setString(3,"Pending");
            preparedStatement.executeUpdate();

            statement = connection.createStatement();
            generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
            if (generatedKeys.next()) {
                int abuseId = generatedKeys.getInt(1);
                return new Abuse(abuseId, message, dest, StatusEnum.PENDING); // Adjust as needed

            }
            else {
                throw new SQLException("Creating abuse failed, no ID obtained.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly
            return null;
        }
    }

    /**
     * @param abuse
     * @param status
     * @return
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
     * @param abuse
     * @return
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
     * @return
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
