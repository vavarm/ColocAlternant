package fr.umontpellier.polytech.ig.colocalternant.chat;

import fr.umontpellier.polytech.ig.colocalternant.user.User;
import org.junit.Test;

import java.util.List;

import static org.testng.AssertJUnit.*;


/**
 * Test class of the chat facade
 */
public class ChatFacadeTest {

    @Test
    public void testGetChatsOf() {
        // Create a user for testing
        User user = new User(1, "Prenom_1", "Nom_1", 18, "a", "a", "photo1");

        // Create an instance of ChatFacade
        ChatFacade chatFacade = ChatFacade.getInstance();

        // Call the method and check if the returned list is not null
        List<Chat> chats = chatFacade.getChatsOf(user);
        assertNotNull(chats);
    }

    @Test
    public void testGetChatsWith() {
        // Create two users for testing
        User user1 = new User(1, "Prenom_1", "Nom_1", 18, "a", "a", "photo1");
        User user2 = new User(2, "Prenom_2", "Nom_2", 18, "a", "a", "photo1");

        // Create an instance of ChatFacade
        ChatFacade chatFacade = ChatFacade.getInstance();

        // Call the method and check if the returned list is not null
        List<Chat> chats = chatFacade.getChatsWith(user1, user2);
        assertNotNull(chats);
    }

    @Test
    public void testSend() {
        // Create users for testing
        User sender = new User(1, "Prenom_1", "Nom_1", 18, "a", "a", "photo1");
        User receiver = new User(2, "Prenom_2", "Nom_2", 18, "a", "a", "photo1");

        // Create an instance of ChatFacade
        ChatFacade chatFacade = ChatFacade.getInstance();

        // Send a message and check for any exceptions
        try {
            chatFacade.send("Test message", sender, receiver);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }
}
