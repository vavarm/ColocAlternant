package fr.umontpellier.polytech.ig.colocalternant.category;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.profile.ProfileFacade;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;

import java.util.ArrayList;

/**
 * Facade for the category management.
 */
public class CategoryFacade {
    /**
     * The DAO factory
     */
    private DAOFactory daoFactory;

    /**
     * Retrieves the unique instance of the category facade.
     *
     * @return The category facade.
     */
    public static CategoryFacade getInstance() {
        return CategoryFacadeHolder.instance;
    }

    /**
     * Holder of the unique instance of the category facade.
     */
    private static class CategoryFacadeHolder {
        private final static CategoryFacade instance = new CategoryFacade();
    }

    /**
     * Constructor of the category facade.
     */
    private CategoryFacade() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves all the categories.
     *
     * @return a list of categories
     */
    public ArrayList<Category> getAllCategories() {
        return daoFactory.getCategoryDAO().getAllCategories();
    }

    /**
     * Add a category by its name.
     *
     * @param name The name of the category.
     * @return A message to inform the user of the result of the operation.
     */
    public String addCategory(String name) {
        // Only the admin can delete a category
        if (ProfileFacade.getInstance().isAdmin(UserFacade.getInstance().getCurrentUser())) {
            daoFactory.getCategoryDAO().insertCategory(new Category(name));
            return "Category added";
        }
        return "You are not allowed to add a category";
    }

    /**
     * Deletes a category by its name.
     *
     * @param name The name of the category.
     * @return A message to inform the user of the result of the operation.
     */
    public String deleteCategory(String name) {
        // Only the admin can delete a category
        if (ProfileFacade.getInstance().isAdmin(UserFacade.getInstance().getCurrentUser())) {
            daoFactory.getCategoryDAO().deleteCategory(new Category(name));
            return "Category deleted";
        }
        return "You are not allowed to delete a category";
    }

    /**
     * Add category to accommodation.
     *
     * @param accommodationID The id of the accommodation.
     * @param categoryName    The name of the category.
     */
    public void addCategoryToAccommodation(int accommodationID, String categoryName) {
        daoFactory.getCategoryDAO().addCategoryToAccommodation(accommodationID, categoryName);
    }

    /**
     * Remove category from accommodation.
     *
     * @param accommodationID The id of the accommodation.
     * @param categoryName    The name of the category.
     */
    public void removeCategoryFromAccommodation(int accommodationID, String categoryName) {
        daoFactory.getCategoryDAO().removeCategoryFromAccommodation(accommodationID, categoryName);
    }

}
