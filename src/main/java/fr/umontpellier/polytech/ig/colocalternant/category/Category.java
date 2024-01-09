package fr.umontpellier.polytech.ig.colocalternant.category;

public class Category {
    /**
     * The name of the category
     */
    private String name;

    /**
     * Constructor of the category
     *
     * @param name
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Retrieves the unique name of the category.
     *
     * @return The category's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the unique name of the category.
     *
     * @param newName The category's nae.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Returns a string representation of the category.
     *
     * @return The string representation of the category.
     */
    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
