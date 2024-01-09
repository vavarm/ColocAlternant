package fr.umontpellier.polytech.ig.colocalternant.accomodation;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.accomodation.AccommodationDAO;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;

import java.util.*;

/**
 * The `AccommodationFacade` class serves as a facade for managing accommodation-related operations.
 * It interacts with the data access layer (DAO) and provides methods for deleting, inserting, updating,
 * and retrieving accommodation information.
 */
public class AccommodationFacade {
    /**
     * The DAO factory responsible for creating DAO instances.
     */
    private DAOFactory daoFactory;

    /**
     * The accommodation that is currently seen by the user.
     */
    private Accommodation currentAccommodation;

    /**
     * Default constructor for creating an `AccommodationFacade` instance.
     * It initializes the DAO factory and sets the current accommodation to null.
     */
    public AccommodationFacade() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
        this.currentAccommodation = null;
    }

    /**
     * Deletes an accommodation with the given ID.
     *
     * @param id The ID of the accommodation to be deleted.
     */
    public void delete(int id) {
        daoFactory.getAccommodationDAO().deleteAccommodation(id);
    }

    /**
     * Inserts a new accommodation with the provided details.
     *
     * @param title                The title of the accommodation.
     * @param location             The location of the accommodation.
     * @param description          The description of the accommodation.
     * @param price                The price of the accommodation.
     * @param specialFonctionalities The special functionalities of the accommodation.
     * @param energicReport        The energetic report of the accommodation.
     * @param photos               The photos of the accommodation.
     */
    public void insertAccommodation(String title, String location, String description,
                                    float price, String specialFonctionalities,
                                    float energicReport, String photos) {
        daoFactory.getAccommodationDAO().insertAccommodation(new Accommodation(-1, title, location, description, price, specialFonctionalities, energicReport, photos));
    }

    /**
     * Updates an existing accommodation with the provided details.
     *
     * @param userId               The ID of the user associated with the accommodation.
     * @param title                The title of the accommodation.
     * @param location             The location of the accommodation.
     * @param description          The description of the accommodation.
     * @param price                The price of the accommodation.
     * @param specialFonctionalities The special functionalities of the accommodation.
     * @param energicReport        The energetic report of the accommodation.
     * @param photos               The photos of the accommodation.
     */
    public void updateAccommodation(int userId, String title, String location,
                                    String description, float price,
                                    String specialFonctionalities, float energicReport, String photos) {
        daoFactory.getAccommodationDAO().updateAccommodation(new Accommodation(userId, title, location, description, price, specialFonctionalities, energicReport, photos));
    }

    /**
     * Retrieves an accommodation with the given ID.
     *
     * @param id The ID of the accommodation to retrieve.
     * @return The accommodation with the specified ID.
     */
    public Accommodation getAccommodation(int id) {
        return daoFactory.getAccommodationDAO().getAccommodation(id);
    }

    /**
     * Retrieves a list of all accommodations.
     *
     * @return ArrayList containing all accommodations.
     */
    public ArrayList<Accommodation> getAllAccommodations() {
        return daoFactory.getAccommodationDAO().getAllAccommodation();
    }

    /**
     * Gets the current instance of `AccommodationFacade` using the Singleton pattern.
     *
     * @return The current instance of `AccommodationFacade`.
     */
    public static AccommodationFacade getInstance() {
        return AccommodationFacadeHolder.instance;
    }

    /**
     * Gets the currently viewed accommodation.
     *
     * @return The currently viewed accommodation.
     */
    public Accommodation getCurrentAccommodation() {
        return currentAccommodation;
    }

    /**
     * Sets the currently viewed accommodation.
     *
     * @param currentAccommodation The accommodation to set as currently viewed.
     */
    public void setCurrentAccommodation(Accommodation currentAccommodation) {
        this.currentAccommodation = currentAccommodation;
    }

    /**
     * Checks if the current user owns the current accommodation.
     *
     * @return True if the current user owns the current accommodation, false otherwise.
     */
    public boolean isOwner() {
        return daoFactory.getAccommodationDAO().getOwns(UserFacade.getInstance().getCurrentUser().getId(), getInstance().getCurrentAccommodation().getId()) != null;
    }

    /**
     * Holder class for implementing the Singleton pattern for `AccommodationFacade`.
     */
    private static class AccommodationFacadeHolder {
        private final static AccommodationFacade instance = new AccommodationFacade();
    }
}
