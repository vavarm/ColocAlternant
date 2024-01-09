package fr.umontpellier.polytech.ig.colocalternant.accommodationAlert;

import java.util.ArrayList;

public class AccommodationAlert {
    /**
     * The unique identifier of the accommodation.
     */
    private int id;

    /**
     * The unique identifier of the profile.
     */
    private int profileID;

    /**
     * The location or address of the accommodation.
     */
    private String location;

    /**
     * The surface of the accommodation.
     */
    private float surface;

    /**
     * The min price of the accommodation.
     */
    private float minPrice;

    /**
     * The max price of the accommodation.
     */
    private float maxPrice;

    /**
     * Default constructor for creating an `AccommodationAlert` object.
     *
     * @param id        The ID of the accommodation.
     * @param profileID The ID of the profile.
     * @param location  The location of the accommodation.
     * @param surface   The surface of the accommodation.
     * @param minPrice  The minimum price of the accommodation.
     * @param maxPrice  The maximum price of the accommodation.
     */
    public AccommodationAlert(int id, int profileID, String location, float surface, float minPrice, float maxPrice) {
        this.id = id;
        this.profileID = profileID;
        this.location = location;
        this.surface = surface;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

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
     * Get the ID of the profile.
     *
     * @return The ID of the profile.
     */
    public int getProfileID() {
        return profileID;
    }

    /**
     * Set the ID of the profile.
     *
     * @param profileID The new ID to set.
     */
    public void setProfileID(int profileID) {
        this.profileID = profileID;
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
     * Get the surface of the accommodation.
     *
     * @return The surface of the accommodation.
     */
    public float getSurface() {
        return surface;
    }

    /**
     * Set the surface of the accommodation.
     *
     * @param surface The new location to set.
     */
    public void setSurface(float surface) {
        this.surface = surface;
    }

    /**
     * Get the price of the accommodation.
     *
     * @return The min price of the accommodation.
     */
    public float getMinPrice() {
        return minPrice;
    }

    /**
     * Set the price of the accommodation.
     *
     * @param minPrice The new price to set.
     */
    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * Get the price of the accommodation.
     *
     * @return The min price of the accommodation.
     */
    public float getMaxPrice() {
        return maxPrice;
    }

    /**
     * Set the price of the accommodation.
     *
     * @param maxPrice The new price to set.
     */
    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }
}