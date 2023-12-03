package fr.umontpellier.polytech.ig.colocalternant.dao.user;

import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;

public abstract class UserDAO {
    public abstract User getUser(String email, String password) throws CredentialException;
}