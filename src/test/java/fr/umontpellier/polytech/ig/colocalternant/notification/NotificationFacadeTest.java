package fr.umontpellier.polytech.ig.colocalternant.notification;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.notification.NotificationDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import org.junit.Test;

import static org.testng.AssertJUnit.*;

import java.util.ArrayList;

public class NotificationFacadeTest {
    @Test
    public void testInsertAndGetOwnNotifs() {
        UserFacade userFacade = UserFacade.getInstance();
        User user = null;

        // Test successful login
        try {
            user = userFacade.login("john.doe@test.com", "password");

            // Création d'une instance de NotificationFacade
            NotificationFacade notificationFacade = NotificationFacade.getInstance();

            // Insertion d'une nouvelle notification
            int userID = 1;
            int newItemID = 123;
            notificationFacade.insertNotif(userID, newItemID);

            // Récupération des notifications propres de l'utilisateur
            ArrayList<Notification> ownNotifs = DAOSQLiteFactory.getInstance().getNotificationDAO().getAllNotifs();

            // Vérification si la notification insérée est présente dans la liste des notifications propres
            boolean found = false;
            for (Notification notif : ownNotifs) {
                if (notif.getUserID() == userID && notif.getNewItemID() == newItemID) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);

        } catch (CredentialException credentialException) {
            fail("UserFacade: login: CredentialException thrown.");
        }

    }
}
