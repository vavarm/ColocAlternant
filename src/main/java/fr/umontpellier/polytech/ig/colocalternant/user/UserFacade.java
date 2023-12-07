package fr.umontpellier.polytech.ig.colocalternant.user;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;

public class UserFacade {
    private User currentUser;
    private DAOFactory daoFactory;

    private UserFacade() {
        this.currentUser = null;
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    private static class UserFacadeHolder {
        private final static UserFacade instance = new UserFacade();
    }

    public static UserFacade getInstance() {
        return UserFacadeHolder.instance;
    }

    public User login(String email, String password) {
        try {
            this.currentUser = daoFactory.getUserDAO().getUser(email, password);
        } catch (CredentialException credentialException) {
            //System.err.println(credentialException);
            this.currentUser = null;
            // TODO: handle exception in the view (display error message)
        }
        return this.currentUser;
    }

    public User getCurrentUser() throws NullPointerException {
        if (this.currentUser == null) {
            throw new NullPointerException("No user is currently logged in.");
        }
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void display() {
        // Controller.display(currentUser);
        // in the controller: public void display(Object object){...}
    }
}