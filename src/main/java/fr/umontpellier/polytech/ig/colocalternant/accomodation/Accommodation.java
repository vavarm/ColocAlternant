package fr.umontpellier.polytech.ig.colocalternant.accomodation;

import fr.umontpellier.polytech.ig.colocalternant.category.Category;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The `Accommodation` class represents a housing unit available for rent or stay.
 * It encapsulates information such as ID, title, location, description, price,
 * special functionalities, energetic report, and photos.
 */
public class Accommodation {

    /**
     * Default constructor for creating an `Accommodation` object.
     *
     * @param id                     The ID of the accommodation.
     * @param title                  The title of the accommodation.
     * @param location               The location of the accommodation.
     * @param description            The description of the accommodation.
     * @param price                  The price of the accommodation.
     * @param specialFonctionalities The special functionalities of the accommodation.
     * @param energicReport          The energetic report of the accommodation.
     * @param photos                 The photos of the accommodation.
     */
    public Accommodation(int id, String title, String location, String description,
                         float price, String specialFonctionalities, float energicReport, String photos) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.price = price;
        this.specialFonctionalities = specialFonctionalities;
        this.energicReport = energicReport;
        this.photos = photos;
    }

    /**
     * The unique identifier of the accommodation.
     */
    private int id;

    /**
     * The title or name of the accommodation.
     */
    private String title;

    /**
     * The location or address of the accommodation.
     */
    private String location;

    /**
     * A description providing details about the accommodation.
     */
    private String description;

    /**
     * The rental or stay price of the accommodation.
     */
    private float price;

    /**
     * Special functionalities or features offered by the accommodation.
     */
    private String specialFonctionalities;

    /**
     * The energetic report rating of the accommodation.
     */
    public float energicReport;

    /**
     * The file path or reference to photos representing the accommodation.
     */
    public String photos;

    /**
     * The list of all the category.
     */
    private ArrayList<Category> categories = new ArrayList<>();

    /**
     * Get the ID of the accommodation.
     *
     * @return The ID of the accommodation.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the accommodation.
     *
     * @param id The new ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the title of the accommodation.
     *
     * @return The title of the accommodation.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the accommodation.
     *
     * @param title The new title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the location of the accommodation.
     *
     * @return The location of the accommodation.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the location of the accommodation.
     *
     * @param location The new location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the description of the accommodation.
     *
     * @return The description of the accommodation.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the accommodation.
     *
     * @param description The new description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the price of the accommodation.
     *
     * @return The price of the accommodation.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set the price of the accommodation.
     *
     * @param price The new price to set.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Get the special functionalities of the accommodation.
     *
     * @return The special functionalities of the accommodation.
     */
    public String getSpecialFonctionalities() {
        return specialFonctionalities;
    }

    /**
     * Set the special functionalities of the accommodation.
     *
     * @param specialFonctionalities The new special functionalities to set.
     */
    public void setSpecialFonctionalities(String specialFonctionalities) {
        this.specialFonctionalities = specialFonctionalities;
    }

    /**
     * Get the energetic report of the accommodation.
     *
     * @return The energetic report of the accommodation.
     */
    public float getEnergicReport() {
        return energicReport;
    }

    /**
     * Set the energetic report of the accommodation.
     *
     * @param energicReport The new energetic report to set.
     */
    public void setEnergicReport(float energicReport) {
        this.energicReport = energicReport;
    }

    /**
     * Get the photos of the accommodation.
     *
     * @return The photos of the accommodation.
     */
    public String getPhotos() {
        return photos;
    }

    /**
     * Set the photos of the accommodation.
     *
     * @param photos The new photos to set.
     */
    public void setPhotos(String photos) {
        this.photos = photos;
    }

    /**
     * Get the categories of the accommodation.
     *
     * @return The categories of the accommodation.
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /**
     * Add a category to the accommodation.
     *
     * @param category The new category to set.
     */
    public void addCategories(Category category) {
        this.categories.add(category);
    }

}
