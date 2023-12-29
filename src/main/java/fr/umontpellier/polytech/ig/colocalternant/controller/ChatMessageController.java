package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.chat.Chat;
import fr.umontpellier.polytech.ig.colocalternant.chat.ChatFacade;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import java.util.List;

/**
 * The controller of the list of chats
 */
public class ChatMessageController {

    public static User personToChatWith;

    /**
     * Deletes a chat
     * @param chat
     */
    public void delete(Chat chat) {
        ChatFacade.getInstance().delete(chat);
    }

    /**
     * Retrieves the chats between two users
     * @param current
     * @param second
     */
    public void getChatsWith(User current, User second) {
        List<Chat> chats = ChatFacade.getInstance().getChatsWith(current, second);
    }

    /**
     * Sends a message to another user
     * @param message
     * @param current
     * @param dest
     */
    public void send(String message, User current, User dest) {
        ChatFacade.getInstance().send(message, current, dest);
    }
}
