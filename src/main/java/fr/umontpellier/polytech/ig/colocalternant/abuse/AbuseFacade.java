package fr.umontpellier.polytech.ig.colocalternant.abuse;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.abuse.AbuseDAO;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;

import java.util.ArrayList;

/**
 * The AbuseFacade class provides a high-level interface for managing abuse-related operations.
 * It serves as a bridge between the application logic and the data access layer for abuse-related functionality.
 */
public class AbuseFacade {

    /**
     * The instance of DAOFactory used for creating data access objects.
     */
    private DAOFactory daoFactory;

    /**
     * The user who is about to get reported (abuser).
     */
    private User abuser;

    /**
     * The abuse that is currently being handled.
     */
    private Abuse currentAbuse;

    /**
     * Private constructor to enforce the singleton pattern.
     * Initializes the DAOFactory to use the DAOSQLiteFactory instance.
     */
    private AbuseFacade() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Gets the singleton instance of the AbuseFacade class.
     *
     * @return The singleton instance of AbuseFacade.
     */
    public static AbuseFacade getInstance() {
        return AbuseFacadeHolder.instance;
    }

    /**
     * Creates a new abuse with the given message and destination user.
     *
     * @param message The message describing the abuse.
     * @param dest    The User object representing the destination user of the abuse report.
     * @return The created Abuse object.
     */
    public Abuse createAbuse(String message, User dest) {
        return daoFactory.getAbuseDAO().createAbuse(message, dest);
    }

    /**
     * Updates the status of the specified abuse.
     *
     * @param abuse  The Abuse object to be updated.
     * @param status The new StatusEnum representing the updated status.
     * @return The updated Abuse object.
     */
    public Abuse updateAbuse(Abuse abuse, StatusEnum status) {
        return daoFactory.getAbuseDAO().updateAbuse(abuse, status);
    }

    /**
     * Deletes the specified abuse.
     *
     * @param abuse The Abuse object to be deleted.
     * @return The deleted Abuse object.
     */
    public Abuse deleteAbuse(Abuse abuse) {
        return daoFactory.getAbuseDAO().deleteAbuse(abuse);
    }

    /**
     * Retrieves a list of all existing abuses.
     *
     * @return An ArrayList containing all existing Abuse objects.
     */
    public ArrayList<Abuse> getAllAbuses() {
        return daoFactory.getAbuseDAO().getAllAbuses();
    }

    /**
     * Gets the current abuser.
     *
     * @return The User object representing the current abuser.
     */
    public User getAbuser() {
        return this.abuser;
    }

    /**
     * Sets the current abuser.
     *
     * @param abuser The User object representing the new abuser.
     */
    public void setAbuser(User abuser) {
        this.abuser = abuser;
    }

    /**
     * Gets the current abuse being handled.
     *
     * @return The current Abuse object.
     */
    public Abuse getCurrentAbuse() {
        return currentAbuse;
    }

    /**
     * Sets the current abuse being handled.
     *
     * @param currentAbuse The Abuse object representing the new current abuse.
     */
    public void setCurrentAbuse(Abuse currentAbuse) {
        this.currentAbuse = currentAbuse;
    }

    /**
     * Holder class for lazy initialization of the singleton instance.
     */
    private static class AbuseFacadeHolder {
        private final static AbuseFacade instance = new AbuseFacade();
    }
}
