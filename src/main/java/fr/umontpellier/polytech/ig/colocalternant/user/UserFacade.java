package fr.umontpellier.polytech.ig.colocalternant.user;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;

/**
 * Facade of the CRUD operations available on the user.
 */
public class UserFacade {
    /**
     * The current user
     */
    private User currentUser;
    /**
     * The DAO factory
     */
    private DAOFactory daoFactory;

    /**
     * Constructor of the user facade
     */
    private UserFacade() {
        this.currentUser = null;
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the unique instance of the user facade.
     * @return The user facade.
     */
    public static UserFacade getInstance() {
        return UserFacadeHolder.instance;
    }

    /**
     * Logs in the user with the given email and password.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The user if the login is successful, null otherwise.
     */
    public User login(String email, String password) throws CredentialException {
        try {
            this.currentUser = daoFactory.getUserDAO().getUser(email, password);
            return this.currentUser;
        } catch (CredentialException credentialException) {
            this.currentUser = null;
            throw new CredentialException(credentialException.getType());
        }
    }

    /**
     * Retrieves the current user.
     * @return The current user.
     * @throws NullPointerException if no user is currently logged in.
     */
    public User getCurrentUser() throws NullPointerException {
        if (this.currentUser == null) {
            throw new NullPointerException("UserFacade: No user is currently logged in.");
        }
        return this.currentUser;
    }

    /**
     * Holder of the unique instance of the user facade
     */
    private static class UserFacadeHolder {
        private final static UserFacade instance = new UserFacade();
    }
}