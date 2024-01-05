package fr.umontpellier.polytech.ig.colocalternant.accomodation;

import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class of the accommodation facade
 */
class AccommodationFacadeTest {

    /**
     * Test the singleton pattern of the accommodation facade
     */
    @Test
    void getInstance() {
        AccommodationFacade instance1 = AccommodationFacade.getInstance();
        AccommodationFacade instance2 = AccommodationFacade.getInstance();
        assertSame(instance1, instance2);
    }

    /**
     * Test the getAccommodation method of the accommodation facade
     */
    @Test
    void getAccommodation() {
        AccommodationFacade accommodationFacade = AccommodationFacade.getInstance();
        Accommodation accommodation;

        // Test getting an accommodation with an existing ID
        accommodation = accommodationFacade.getAccommodation(58);
        assertEquals(58, accommodation.getId());
    }

    /**
     * Test the delete method of the accommodation facade
     */
    @Test
    void delete() {
        AccommodationFacade accommodationFacade = AccommodationFacade.getInstance();
        // Test deleting an existing accommodation
        try {
            accommodationFacade.delete(1);
        } catch (Exception exception) {
            fail("AccommodationFacade: delete");
        }
    }

    /**
     * Test the insertAccommodation method of the accommodation facade
     */
    @Test
    void insertAccommodation() {
        AccommodationFacade accommodationFacade = AccommodationFacade.getInstance();
        // Test inserting a new accommodation
        try {
            accommodationFacade.insertAccommodation("Test", "Test", "Test", 0, "Test", 0, "Test");
        } catch (Exception exception) {
            fail("AccommodationFacade: insertAccommodation");
        }
    }

    /**
     * Test the updateAccommodation method of the accommodation facade
     */
    @Test
    void updateAccommodation() {
        AccommodationFacade accommodationFacade = AccommodationFacade.getInstance();
        // Test updating an existing accommodation
        try {
            accommodationFacade.updateAccommodation(1, "Test", "Test", "Test", 0, "Test", 0, "Test");
        } catch (Exception exception) {
            fail("AccommodationFacade: updateAccommodation");
        }
    }

    /**
     * Test the getAllAccommodations method of the accommodation facade
     */
    @Test
    void getAllAccommodations() {
        AccommodationFacade accommodationFacade = AccommodationFacade.getInstance();
        ArrayList<Accommodation> accommodations = null;

        // Test getting all accommodations
        try {
            accommodations = accommodationFacade.getAllAccommodations();
        } catch (Exception exception) {
            fail("AccommodationFacade: getAllAccommodations: Exception thrown.");
        }
        assertEquals(1, accommodations.size());
    }

    /**
     * Test the getCurrentAccommodation method of the accommodation facade
     */
    @Test
    void getCurrentAccommodation() {
        AccommodationFacade accommodationFacade = AccommodationFacade.getInstance();
        Accommodation accommodation;

        // Test getting the current accommodation when no accommodation is set
        accommodation = accommodationFacade.getCurrentAccommodation();
        assertEquals(null, accommodation);

        // Test getting the current accommodation after setting it
        accommodationFacade.setCurrentAccommodation(new Accommodation(1, "Test", "Test", "Test", 0, "Test", 0, "Test"));
        accommodation = accommodationFacade.getCurrentAccommodation();
        assertEquals(1, accommodation.getId());
    }

    /**
     * Test the setCurrentAccommodation method of the accommodation facade
     */
    @Test
    void setCurrentAccommodation() {
        AccommodationFacade accommodationFacade = AccommodationFacade.getInstance();
        Accommodation accommodation;

        // Test setting the current accommodation
        accommodationFacade.setCurrentAccommodation(new Accommodation(1, "Test", "Test", "Test", 0, "Test", 0, "Test"));
        accommodation = accommodationFacade.getCurrentAccommodation();
        assertEquals(1, accommodation.getId());
    }

    /**
     * Test the isOwner method of the accommodation facade
     */
    @Test
    void isOwner() {
        AccommodationFacade accommodationFacade = AccommodationFacade.getInstance();
        UserFacade userFacade = UserFacade.getInstance();

        // Test if the current user is the owner of the current accommodation
        try {
            userFacade.login("john.doe@test.com", "password");
        } catch (CredentialException e) {
            throw new RuntimeException(e);
        }
        accommodationFacade.setCurrentAccommodation(new Accommodation(1, "Test", "Test", "Test", 0, "Test", 0, "Test"));
        Assertions.assertTrue(accommodationFacade.isOwner());
    }
}