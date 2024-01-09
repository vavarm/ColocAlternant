package fr.umontpellier.polytech.ig.colocalternant.accommodationAlert;

import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import org.junit.Test;

import static org.testng.AssertJUnit.*;

import java.util.ArrayList;

public class AccommodationAlertFacadeTest {

    @Test
    public void testInsertAndGetAllAccommodationAlerts() {
        AccommodationAlertFacade facade = AccommodationAlertFacade.getInstance();

        // Insert a new accommodation alert
        int profileID = 1;
        String location = "Test Location";
        float surface = 50.0f;
        float minPrice = 100.0f;
        float maxPrice = 200.0f;

        facade.insertAccommodationAlert(profileID, location, surface, minPrice, maxPrice);

        // Retrieve all accommodation alerts and check if the inserted one is present
        ArrayList<AccommodationAlert> allAccommodationAlerts = facade.getAllAccommodationAlerts();

        boolean found = false;
        for (AccommodationAlert alert : allAccommodationAlerts) {
            if (alert.getLocation().equals(location) &&
                    alert.getSurface() == surface &&
                    alert.getMinPrice() == minPrice &&
                    alert.getMaxPrice() == maxPrice) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }


    @Test
    public void testGetAllAccommodationAlerts() {
        AccommodationAlertFacade facade = AccommodationAlertFacade.getInstance();

        // Insert some sample accommodation alerts
        int profileID1 = 1;
        int profileID2 = 2;
        String location1 = "Test Location 1";
        String location2 = "Test Location 2";
        float surface1 = 50.0f;
        float surface2 = 60.0f;
        float minPrice1 = 100.0f;
        float minPrice2 = 120.0f;
        float maxPrice1 = 200.0f;
        float maxPrice2 = 240.0f;

        facade.insertAccommodationAlert(profileID1, location1, surface1, minPrice1, maxPrice1);
        facade.insertAccommodationAlert(profileID2, location2, surface2, minPrice2, maxPrice2);

        // Retrieve all accommodation alerts
        ArrayList<AccommodationAlert> allAccommodationAlerts = facade.getAllAccommodationAlerts();

        // Check if the retrieved list is not null
        assertNotNull(allAccommodationAlerts);

        // Check if the retrieved list contains the inserted accommodation alerts
        assertTrue(allAccommodationAlerts.stream().anyMatch(alert ->
                alert.getLocation().equals(location1) &&
                        alert.getSurface() == surface1 &&
                        alert.getMinPrice() == minPrice1 &&
                        alert.getMaxPrice() == maxPrice1));

        assertTrue(allAccommodationAlerts.stream().anyMatch(alert ->
                alert.getLocation().equals(location2) &&
                        alert.getSurface() == surface2 &&
                        alert.getMinPrice() == minPrice2 &&
                        alert.getMaxPrice() == maxPrice2));
    }

}
