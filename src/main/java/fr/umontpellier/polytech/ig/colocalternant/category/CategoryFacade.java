package fr.umontpellier.polytech.ig.colocalternant.category;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.profile.EnumRole;
import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;
import fr.umontpellier.polytech.ig.colocalternant.profile.ProfileFacade;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;

import java.util.ArrayList;
import java.util.List;

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
     * @return a list of categories
     */
    public ArrayList<Category> getAllCategories() {
        return daoFactory.getCategoryDAO().getAllCategories();
    }

    /**
     * Add a category by its name.
     * @param name The name of the category.
     * @return A message to inform the user of the result of the operation.
     */
    public void addCategory(String name) {
        List<Profile> profiles = ProfileFacade.getInstance().getAllProfiles();
        Profile profile = profiles.stream().filter(p -> p.getId() == getProfileID()).findFirst().orElse(null);
        // Only the admin can delete a category
        if (profile != null && profile.getRole() == (EnumRole.Admin)) {
            daoFactory.getCategoryDAO().insertCategory(new Category(name));
            System.out.println("Category added");
            return;
        }
        System.out.println("You are not allowed to add a category");
    }

    /**
     * Deletes a category by its name.
     * @param name The name of the category.
     * @return A message to inform the user of the result of the operation.
     */
    public void deleteCategory(String name) {
        List<Profile> profiles = ProfileFacade.getInstance().getAllProfiles();
        Profile profile = profiles.stream().filter(p -> p.getId() == getProfileID()).findFirst().orElse(null);
        // Only the admin can delete a category
        if (profile != null && profile.getRole() == (EnumRole.Admin)) {
            daoFactory.getCategoryDAO().deleteCategory(new Category(name));
            System.out.println("Category deleted");
            return;
        }
        System.out.println("You are not allowed to delete a category");
    }

    /**
     * Add category to accommodation.
     * @param accommodationID The id of the accommodation.
     * @param categoryName The name of the category.
     */
    public void addCategoryToAccommodation(int accommodationID, String categoryName) {
        daoFactory.getCategoryDAO().addCategoryToAccommodation(accommodationID, categoryName);
    }

    /**
     * Remove category from accommodation.
     * @param accommodationID The id of the accommodation.
     * @param categoryName The name of the category.
     */
    public void removeCategoryFromAccommodation(int accommodationID, String categoryName) {
        daoFactory.getCategoryDAO().removeCategoryFromAccommodation(accommodationID, categoryName);
    }

    /**
     * Get the profile id from the data passed by FXRouter.
     * @return the user's profile id
     */
    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }

}
