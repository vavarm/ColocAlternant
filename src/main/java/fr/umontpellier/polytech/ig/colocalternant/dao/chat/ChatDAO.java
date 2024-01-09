package fr.umontpellier.polytech.ig.colocalternant.dao.chat;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.chat.Chat;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 * The DAO of the chat
 */
public abstract class ChatDAO {
    /**
     * The DAO factory
     */
    protected DAOFactory daoFactory;

    /**
     * Retrieves all the chats of the given user (as sender or receiver) from the database.
     *
     * @param user
     * @return The list of the chats corresponding to the given user.
     */
    public List<Chat> getChatsOf(User user) {
        try {
            List<Chat> chats = new ArrayList<>();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            Connection connection = this.daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Chats WHERE idSender LIKE ? OR idDest LIKE ?;")) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, user.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // get the user1
                        User user1_ = this.daoFactory.getUserDAO().getUserById(resultSet.getInt("idSender"));
                        // get the user2
                        User user2_ = this.daoFactory.getUserDAO().getUserById(resultSet.getInt("idDest"));
                        // convert the field "date" to LocalDateTime
                        String date = resultSet.getString("timestamp");
                        LocalDateTime localDateTime = LocalDateTime.parse(date);
                        // add the chat to the list
                        chats.add(new Chat(resultSet.getInt("id"), user1_, user2_, resultSet.getString("message"), localDateTime, resultSet.getBoolean("isDeleted")));
                    }
                }
            }
            return chats;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves all the chats between the two given users from the database.
     *
     * @param user1
     * @param user2
     * @return
     */
    public List<Chat> getChatsWith(User user1, User user2) {
        try {
            List<Chat> chats = new ArrayList<>();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            Connection connection = this.daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Chats WHERE (idSender LIKE ? AND idDest LIKE ?) OR (idSender LIKE ? AND idDest LIKE ?);")) {
                preparedStatement.setInt(1, user1.getId());
                preparedStatement.setInt(2, user2.getId());
                preparedStatement.setInt(3, user2.getId());
                preparedStatement.setInt(4, user1.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // get the user1
                        User user1_ = this.daoFactory.getUserDAO().getUserById(resultSet.getInt("idSender"));
                        // get the user2
                        User user2_ = this.daoFactory.getUserDAO().getUserById(resultSet.getInt("idDest"));
                        // convert the field "date" to LocalDateTime
                        String date = resultSet.getString("timestamp");
                        LocalDateTime localDateTime = LocalDateTime.parse(date);
                        // add the chat to the list
                        chats.add(new Chat(resultSet.getInt("id"), user1_, user2_, resultSet.getString("message"), localDateTime, resultSet.getBoolean("isDeleted")));
                    }
                }
            }
            return chats;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Insert the given chat into the database.
     *
     * @param message
     * @param current
     * @param dest
     */
    public void send(String message, User current, User dest) {
        try {
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            Connection connection = this.daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Chats (idSender, idDest, message, timestamp, isDeleted) VALUES (?, ?, ?, ?, ?);")) {
                preparedStatement.setInt(1, current.getId());
                preparedStatement.setInt(2, dest.getId());
                preparedStatement.setString(3, message);
                preparedStatement.setString(4, LocalDateTime.now().toString());
                preparedStatement.setBoolean(5, false);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Deletes the given chat from the database.
     *
     * @param chat
     */
    public void delete(Chat chat) {
        System.out.println("ChatDAO.delete: " + chat.getId());
        try {
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            Connection connection = this.daoFactory.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Chats SET isDeleted = ? WHERE id LIKE ?;")) {
                preparedStatement.setBoolean(1, true);
                preparedStatement.setInt(2, chat.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
