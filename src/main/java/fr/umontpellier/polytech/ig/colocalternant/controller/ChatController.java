package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.chat.ChatFacade;
import fr.umontpellier.polytech.ig.colocalternant.chat.Chat;
import java.util.List;

/**
 * The controller of the list of users who talk to each other
 */
public class ChatController {
    /**
     * Retrieves all the chats of the user
     * @param user
     */
    public void getChatsOf(User user) {
        List<Chat> chats = ChatFacade.getInstance().getChatsOf(user);
    }
}
