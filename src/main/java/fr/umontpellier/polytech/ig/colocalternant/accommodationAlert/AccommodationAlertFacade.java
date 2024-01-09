package fr.umontpellier.polytech.ig.colocalternant.accommodationAlert;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.accommodationAlert.AccommodationAlertDAO;

import java.util.ArrayList;

public class AccommodationAlertFacade {
    /**
     * The DAO factory responsible for creating DAO instances.
     */
    private DAOFactory daoFactory;

    /**
     * The AccommodationAlertDAO
     */
    private AccommodationAlertDAO accommodationAlertDAO;

    /**
     * The accommodationAlert that is currently seen by the user.
     */
    private AccommodationAlert currentAccommodationAlert;

    /**
     * Default constructor for creating an `AccommodationAlertFacade` instance.
     * It initializes the DAO factory and sets the current accommodation to null.
     */
    public AccommodationAlertFacade() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
        this.currentAccommodationAlert = null;
        this.accommodationAlertDAO = daoFactory.getAccommodationAlertDAO();
    }

    /**
     * Retrieves the unique instance of the AccommodationAlert facade.
     *
     * @return The AccommodationAlert facade.
     */
    public static AccommodationAlertFacade getInstance() {
        return AccommodationAlertFacade.AccommodationAlertFacadeHolder.instance;
    }

    /**
     * Deletes an accommodationAlert with the given ID.
     *
     * @param id The ID of the accommodation to be deleted.
     */
    public void deleteAccommodationAlert(int id) {
        accommodationAlertDAO.deleteAccommodationAlert(id);
    }

    /**
     * Inserts a new accommodationAlert with the provided details.
     *
     * @param profileID The ID of the profile.
     * @param location  The location of the accommodationAlert.
     * @param surface   The surface of the accommodationAlert.
     * @param minPrice  The minimum price of the accommodationAlert.
     * @param maxPrice  The maximum price of the accommodationAlert.
     */
    public void insertAccommodationAlert(int profileID, String location, float surface, float minPrice, float maxPrice) {
        accommodationAlertDAO.insertAccommodationAlert(profileID, location, surface, minPrice, maxPrice);
    }

    /**
     * Updates an existing accommodationAlert with the provided details.
     *
     * @param id        The ID of the accommodationAlert.
     * @param profileID The ID of the profile
     * @param location  The location of the accommodationAlert.
     * @param surface   The surface of the accommodationAlert.
     * @param minPrice  The minimum price of the accommodationAlert.
     * @param maxPrice  The maximum price of the accommodationAlert.
     */
    public void updateAccommodationAlert(int id, int profileID, String location, float surface, float minPrice, float maxPrice) {
        accommodationAlertDAO.updateAccommodationAlert(id, profileID, location, surface, minPrice, maxPrice);
    }

    /**
     * Retrieves a list of all accommodationAlerts.
     *
     * @return ArrayList containing all accommodationAlerts.
     */
    public ArrayList<AccommodationAlert> getAllAccommodationAlerts() {
        return accommodationAlertDAO.getAllAccommodationAlert();
    }

    /**
     * Retrieves an accommodationAlert with the given profileID.
     *
     * @param profileID The ID of the profile.
     * @return The accommodationAlert with the specified profileID.
     */
    public ArrayList<AccommodationAlert> getOwnAccommodationAlert(int profileID) {
        return accommodationAlertDAO.getOwnAccommodationAlerts(profileID);
    }

    /**
     * Retrieves an accommodationAlert with the given ID.
     *
     * @param id The ID of the accommodationAlert to retrieve.
     * @return The accommodationAlert with the specified ID.
     */
    public AccommodationAlert getAccommodationAlertByID(int id) {
        return accommodationAlertDAO.getAccommodationAlertByID(id);
    }

    /**
     * Gets the currently viewed accommodation.
     *
     * @return The currently viewed accommodation.
     */
    public AccommodationAlert getCurrentAccommodationAlert() {
        return currentAccommodationAlert;
    }

    /**
     * Sets the currently viewed accommodation.
     *
     * @param currentAccommodationAlert The accommodation to set as currently viewed.
     */
    public void setCurrentAccommodationAlert(AccommodationAlert currentAccommodationAlert) {
        this.currentAccommodationAlert = currentAccommodationAlert;
    }

    /**
     * Holder class for implementing the Singleton pattern for `AccommodationFacade`.
     */
    private static class AccommodationAlertFacadeHolder {
        private final static AccommodationAlertFacade instance = new AccommodationAlertFacade();
    }
}
