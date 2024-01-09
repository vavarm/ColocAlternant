package fr.umontpellier.polytech.ig.colocalternant.rental;

import fr.umontpellier.polytech.ig.colocalternant.accomodation.Accommodation;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.AccommodationFacade;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.rental.RentalDAO;
import fr.umontpellier.polytech.ig.colocalternant.dao.rental.RentalDAOSQLite;

import java.util.List;

/**
 * Facade of the CRUD operations available on the rental.
 */
public class RentalFacade {
    /**
     * The DAO factory
     */
    private DAOFactory daoFactory;
    /**
     * The rental DAO
     */
    private RentalDAO rentalDAO;

    /**
     * Constructor of the rental facade
     */
    private RentalFacade() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
        this.rentalDAO =  daoFactory.getRentalDAO();
    }

    /**
     * Retrieves the unique instance of the rental facade.
     * @return The rental facade.
     */
    public static RentalFacade getInstance() {
        return RentalFacadeHolder.instance;
    }

    /**
     * Holder of the unique instance of the rental facade
     */
    private static class RentalFacadeHolder {
        private final static RentalFacade instance = new RentalFacade();
    }

    /**
     * Retrieves the rental with the given id.
     * @param id The id of the rental.
     * @return The rental if it exists, null otherwise.
     */
    public Rental getRental(int id) {
        return rentalDAO.getRentalById(id);
    }

    /**
     * Creates a rental.
     * @param id The id of the rental.
     * @param profileId The id of the profile.
     * @param accommodationId The id of the accommodation.
     * @param period The period of the rental.
     * @param isRequest The state of the rental.
     */
    public void createRental(int profileId, int accommodationId, String period, boolean isRequest) {
        rentalDAO.insertRental(profileId, accommodationId, period, isRequest);
    }

    /**
     * Retrieves all rentals from the database.
     * @return List of all rentals.
     */
    public List<Rental> getAllRentals() {
        return rentalDAO.getAllRentals();
    }

    /**
     * Updates the state of a rental.
     * @param id
     * @param isRequest
     */
    public void updateRentalState(int id, boolean isRequest) {
        rentalDAO.updateRentalState(id, isRequest);
    }

    /**
     * Deletes a rental.
     * @param id The id of the rental.
     */
    public void deleteRental(int id) {
        rentalDAO.deleteRental(id);
    }

    /**
     * Retrieves all rentals of a profile.
     * @param profileId The id of the profile.
     * @return List of all rentals of the profile.
     */
    public List<Rental> getRentalsOf(int profileId) {
        List<Rental> rentals = this.getAllRentals();
        rentals.removeIf(rental -> rental.getProfileId() != profileId);
        return rentals;
    }

    /**
     * Retrieves all rentals of the accomodations of a profile.
     * @param ownerId The id of the owner's profile.
     * @return List of all rentals of the accomodations of the profile.
     */
    public List<Rental> getRentalsOfAccommodationsOf(int ownerId) {
        List<Rental> rentals = this.getAllRentals();
        rentals.removeIf(rental -> {
            Accommodation accommodation = AccommodationFacade.getInstance().getAccommodation(rental.getAccommodationId());
            // TODO return accommodation.getOwnerId() != ownerId;
            return true;
        });
        return rentals;
    }
}
