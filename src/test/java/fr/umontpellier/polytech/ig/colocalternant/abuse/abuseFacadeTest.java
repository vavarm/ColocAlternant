package fr.umontpellier.polytech.ig.colocalternant.abuse;

import fr.umontpellier.polytech.ig.colocalternant.user.User;
import org.junit.Test;

import static org.testng.AssertJUnit.*;

import java.util.ArrayList;

public class abuseFacadeTest {

    @Test
    public void testUpdateAbuse() {
        AbuseFacade abuseFacade = AbuseFacade.getInstance();

        User destUser = new User(1, "John", "Doe", 20, "john@example.com", "password", "");

        String message = "Harassment";
        Abuse createdAbuse = abuseFacade.createAbuse(message, destUser);

        // Update the abuse status
        StatusEnum newStatus = StatusEnum.RESOLVED;
        Abuse updatedAbuse = abuseFacade.updateAbuse(createdAbuse, newStatus);

        assertEquals(newStatus, updatedAbuse.getStatus());
    }

    @Test
    public void testDeleteAbuse() {
        AbuseFacade abuseFacade = AbuseFacade.getInstance();

        User destUser = new User(1, "John", "Doe", 20, "john@example.com", "password", "");

        String message = "Spamming";
        Abuse createdAbuse = abuseFacade.createAbuse(message, destUser);

        // Delete the abuse
        Abuse deletedAbuse = abuseFacade.deleteAbuse(createdAbuse);

        assertNull(abuseFacade.getCurrentAbuse());
        assertFalse(abuseFacade.getAllAbuses().contains(deletedAbuse));
    }

    @Test
    public void testGetAllAbuses() {
        AbuseFacade abuseFacade = AbuseFacade.getInstance();

        // Retrieve all abuses
        ArrayList<Abuse> allAbuses = abuseFacade.getAllAbuses();

        assertNotNull(allAbuses);
    }

    @Test
    public void testSetAbuser() {
        AbuseFacade abuseFacade = AbuseFacade.getInstance();

        User abuser = new User(1, "John", "Doe", 20, "john@example.com", "password", "");

        abuseFacade.setAbuser(abuser);

        assertEquals(abuser, abuseFacade.getAbuser());
    }

    @Test
    public void testSetCurrentAbuse() {
        AbuseFacade abuseFacade = AbuseFacade.getInstance();

        User destUser = new User(1, "John", "Doe", 20, "john@example.com", "password", "");

        String message = "Fraud";
        Abuse createdAbuse = abuseFacade.createAbuse(message, destUser);

        // Set the current abuse
        abuseFacade.setCurrentAbuse(createdAbuse);

        assertEquals(createdAbuse, abuseFacade.getCurrentAbuse());
    }
}
