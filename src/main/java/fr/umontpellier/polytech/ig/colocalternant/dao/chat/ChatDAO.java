package fr.umontpellier.polytech.ig.colocalternant.dao.chat;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.chat.Chat;
import java.util.List;

/**
 * The DAO of the chat
 */
public abstract class ChatDAO {
    /**
     * The DAO factory
     */
    protected DAOFactory daoFactory;

    public List<Chat> getChatsOf(User user) {
        // TODO implement here
        return null;
    }

    public List<Chat> getChatsWith(User user1, User user2) {
        // TODO implement here
        return null;
    }

    public void send(String message, User current, User dest) {
        // TODO implement here
    }

    public void delete(Chat chat) {
        // TODO implement here
    }
}
