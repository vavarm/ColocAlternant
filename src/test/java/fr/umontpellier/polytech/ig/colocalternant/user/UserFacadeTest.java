package fr.umontpellier.polytech.ig.colocalternant.user;

import org.junit.Test;

import static org.testng.AssertJUnit.*;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.*;

/**
 * Test class of the user facade
 */
public class UserFacadeTest {
    /**
     * Test the singleton pattern of the user facade
     */
    @Test
    public void getInstance() {
        UserFacade instance1 = UserFacade.getInstance();
        UserFacade instance2 = UserFacade.getInstance();
        assertSame(instance1, instance2);
    }

    /**
     * Test the login method of the user facade
     */
    @Test
    public void succesLogin() {
        UserFacade userFacade = UserFacade.getInstance();
        User user = null;

        // Test successful login
        try {
            user = userFacade.login("john.doe@test.com", "password");
        } catch (CredentialException credentialException) {
            fail("UserFacade: login: CredentialException thrown.");
        }
        assertEquals(user, userFacade.getCurrentUser());
    }
    @Test
    public void failedLogin() {
        UserFacade userFacade = UserFacade.getInstance();
        User user = null;

        // Test login with incorrect credentials
        try {
            user = userFacade.login("wrong@example.com", "wrong_password");
        } catch (CredentialException credentialException) {
            assertTrue("User with wrong credentials",credentialException.getType() == CredentialExceptionType.INVALID_EMAIL || credentialException.getType() == CredentialExceptionType.INVALID_PASSWORD);
        }
        assertEquals(null, user);
    }

    /**
     * Test the getCurrentUser method of the user facade
     */
    @Test
    public void getCurrentUser() {
        UserFacade userFacade = UserFacade.getInstance();

        // Test getting current user when no user is logged in
        //assertThrows(NullPointerException.class, userFacade::getCurrentUser);

        // Test getting current user after successful login
        User user = null;
        try{
            user = userFacade.login("john.doe@test.com", "password");
        } catch (CredentialException credentialException) {
            fail("UserFacade: login: CredentialException thrown.");
        }
        assertEquals(user, userFacade.getCurrentUser());
    }
}