package fr.umontpellier.polytech.ig.colocalternant.dao.notification;

import fr.umontpellier.polytech.ig.colocalternant.accomodation.Accommodation;
import fr.umontpellier.polytech.ig.colocalternant.chat.Chat;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.accommodationAlert.AccommodationAlert;
import fr.umontpellier.polytech.ig.colocalternant.notification.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class NotificationDAO {
    /**
     * The DAO factory
     */
    protected DAOFactory daoFactory;

    public void delete(int id) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Notifs WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertNotif(int userID, int newItemID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            String query = "INSERT INTO Notifs (userID, newItemID) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, newItemID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Notification> getAllNotifs() {
        ArrayList<Notification> notifs = new ArrayList<>();
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Notifs")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Notification notif = new Notification(
                                resultSet.getInt("id"),
                                resultSet.getInt("userID"),
                                resultSet.getInt("newItemID")
                        );
                        notifs.add(notif);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifs;
    }

}
