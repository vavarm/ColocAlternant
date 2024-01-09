package fr.umontpellier.polytech.ig.colocalternant.rental;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

class RentalFacadeTest {

    @Test
    void testCreateRental() {
        RentalFacade rentalFacade = RentalFacade.getInstance();
        rentalFacade.createRental(10, 10, "2020-01-01 - 2020-01-02", true);
        // get the rental created by the request
        try {
            Connection connection = DAOSQLiteFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Rentals WHERE profileId = 10 AND accommodationId = 10 AND period = '2020-01-01 - 2020-01-02'");
            Rental rental2 = new Rental(resultSet.getInt("id"), resultSet.getInt("profileId"), resultSet.getInt("accommodationId"), resultSet.getString("period"), resultSet.getBoolean("isRequest"));
            assertEquals(10, rental2.getProfileId());
            assertEquals(10, rental2.getAccommodationId());
            assertEquals("2020-01-01 - 2020-01-02", rental2.getPeriod());
            assertEquals(true, rental2.getIsRequest());
            // delete the rental created by the request
            rentalFacade.deleteRental(rental2.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}