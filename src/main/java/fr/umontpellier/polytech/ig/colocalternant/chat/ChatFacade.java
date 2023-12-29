package fr.umontpellier.polytech.ig.colocalternant.chat;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import java.util.List;

/**
 * The facade of the chat management
 */
public class ChatFacade {

    /**
     * The DAO factory
     */
    private DAOFactory daoFactory;

    /**
     * Constructor of the chat facade
     */
    private ChatFacade() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the unique instance of the chat facade.
     * @return The chat facade.
     */
    public static ChatFacade getInstance() {
        return ChatFacadeHolder.instance;
    }

    /**
     * Holder of the unique instance of the chat facade.
     */
    private static class ChatFacadeHolder {
        private final static ChatFacade instance = new ChatFacade();
    }

    /**
     * Retrieves all the chats of the user
     * @param user
     * @return a list of chats
     */
    public List<Chat> getChatsOf(User user) {
        return daoFactory.getChatDAO().getChatsOf(user);
    }

    /**
     * Retrieves the chats between two users
     * @param user1
     * @param user2
     * @return a list of chats
     */
    public List<Chat> getChatsWith(User user1, User user2) {
        return daoFactory.getChatDAO().getChatsWith(user1, user2);
    }

    /**
     * Sends a message to another user
     * @param message
     * @param current
     * @param dest
     * @return
     */
    public void send(String message, User current, User dest) {
        daoFactory.getChatDAO().send(message, current, dest);
    }

    /**
     * Deletes a chat
     * @param chat
     * @return
     */
    public void delete(Chat chat) {
        daoFactory.getChatDAO().delete(chat);
    }
}
