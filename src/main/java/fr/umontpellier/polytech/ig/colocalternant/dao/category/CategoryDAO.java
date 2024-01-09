package fr.umontpellier.polytech.ig.colocalternant.dao.category;

import fr.umontpellier.polytech.ig.colocalternant.category.Category;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Abstract class of the category DAO.
 */
public abstract class CategoryDAO {
    /**
     * The DAO factory
     */
    protected DAOFactory daoFactory;

    /**
     * Retrieves all the categories.
     *
     * @return a list of categories
     */
    public ArrayList<Category> getAllCategories() {
        Connection connection = null;
        try {
            connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Categories;")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    ArrayList<Category> categories = new ArrayList<>();
                    while (resultSet.next()) {
                        categories.add(new Category(resultSet.getString("name")));
                    }
                    return categories;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all the categories for a given accommodation.
     *
     * @return a list of categories for a given accommodation
     */
    public ArrayList<Category> getAllCategoriesForAccommodation(int accommodationId) {
        Connection connection = null;
        try {
            connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Categories WHERE id IN (SELECT category_id FROM AccommodationCategories WHERE accommodation_id = ?);")) {
                preparedStatement.setInt(1, accommodationId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    ArrayList<Category> categories = new ArrayList<>();
                    while (resultSet.next()) {
                        categories.add(new Category(resultSet.getString("name")));
                    }
                    return categories;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * insert a category in the database
     *
     * @param category
     */
    public void insertCategory(Category category) {
        Connection connection = null;
        try {
            connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Categories (name) VALUES (?);")) {
                preparedStatement.setString(1, category.getName());
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * delete a category in the database
     *
     * @param category
     */
    public void deleteCategory(Category category) {
        Connection connection = null;
        try {
            connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Categories WHERE name = ?;")) {
                preparedStatement.setString(1, category.getName());
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add category to accommodation.
     *
     * @param accommodationID The id of the accommodation.
     * @param categoryName    The name of the category.
     */
    public void addCategoryToAccommodation(int accommodationID, String categoryName) {
        Connection connection = null;
        try {
            connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO AccommodationCategories (accommodation_id, category_id) VALUES (?, (SELECT id FROM Categories WHERE name = ?));")) {
                preparedStatement.setInt(1, accommodationID);
                preparedStatement.setString(2, categoryName);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove category from accommodation.
     *
     * @param accommodationID The id of the accommodation.
     * @param categoryName    The name of the category.
     */
    public void removeCategoryFromAccommodation(int accommodationID, String categoryName) {
        Connection connection = null;
        try {
            connection = this.daoFactory.getConnection();
            if (this.daoFactory == null) throw new NullPointerException("DAOFactory is null");
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM AccommodationCategories WHERE accommodation_id = ? AND category_id = (SELECT id FROM Categories WHERE name = ?);")) {
                preparedStatement.setInt(1, accommodationID);
                preparedStatement.setString(2, categoryName);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
