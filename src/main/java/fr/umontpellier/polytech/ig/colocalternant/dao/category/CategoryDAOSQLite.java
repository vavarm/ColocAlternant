package fr.umontpellier.polytech.ig.colocalternant.dao.category;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;

/**
 * Class of the category DAO SQLite.
 */
public class CategoryDAOSQLite extends CategoryDAO {

    /**
     * Constructor of the category DAO
     */
    private CategoryDAOSQLite() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the unique instance of the category DAO.
     *
     * @return The category DAO.
     */
    public static CategoryDAOSQLite getInstance() {
        return CategoryDAOSQLiteHolder.instance;
    }

    /**
     * Holder of the unique instance of the category DAO
     */
    private static class CategoryDAOSQLiteHolder {
        private final static CategoryDAOSQLite instance = new CategoryDAOSQLite();
    }
}
