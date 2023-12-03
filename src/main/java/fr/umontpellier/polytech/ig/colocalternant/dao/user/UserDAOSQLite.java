package fr.umontpellier.polytech.ig.colocalternant.dao.user;

import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions.CredentialException;

public class UserDAOSQLite extends UserDAO {

    private UserDAOSQLite() {
    }

    private static class UserDAOSQLiteHolder {
        private final static UserDAOSQLite instance = new UserDAOSQLite();
    }

    public static UserDAOSQLite getInstance() {
        return UserDAOSQLiteHolder.instance;
    }

    public User getUser(String email, String password) throws CredentialException {
        return new User(1, "John", "Doe", 21, email, password,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fpngtree.com%2Fso%2Fprofile&psig=AOvVaw06nRk09YyDMIfh1K51s08j&ust=1701708080137000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCPCzzd7a84IDFQAAAAAdAAAAABAE");
        // get user from database using JDBC
    }
}