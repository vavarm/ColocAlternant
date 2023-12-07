package fr.umontpellier.polytech.ig.colocalternant.user;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.*;

public class UserFacadeTest {


    @Test
    public void getInstance() {
        UserFacade instance1 = UserFacade.getInstance();
        UserFacade instance2 = UserFacade.getInstance();
        assertSame(instance1, instance2);
    }



    @Test
    public void login() {
        UserFacade userFacade = UserFacade.getInstance();

        // Test successful login
        User user = userFacade.login("john.doe@test.com", "password");
        assertNotNull(user);
        assertEquals(user, userFacade.getCurrentUser());

        // Test login with incorrect credentials
        user = userFacade.login("wrong@example.com", "wrong_password");
        assertNull(user);
    }


    @Test
    public void getCurrentUser() {
        UserFacade userFacade = UserFacade.getInstance();

        // Test getting current user when no user is logged in
        //assertThrows(NullPointerException.class, userFacade::getCurrentUser);

        // Test getting current user after successful login
        User user = userFacade.login("john.doe@test.com", "password");
        assertEquals(user, userFacade.getCurrentUser());
    }


    @Test
    public void setCurrentUser() {
        UserFacade userFacade = UserFacade.getInstance();

        // Test setting and getting current user
        User user = new User(1, "John", "Doe", 42,  "john.doe@test.com", "password", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fpngtree.com%2Fso%2Fprofile&psig=AOvVaw06nRk09YyDMIfh1K51s08j&ust=1701708080137000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCPCzzd7a84IDFQAAAAAdAAAAABAE");
        userFacade.setCurrentUser(user);
        assertEquals(user, userFacade.getCurrentUser());
    }
}
