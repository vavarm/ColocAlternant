package fr.umontpellier.polytech.ig.colocalternant.rental;

/**
 * Rental class
 */
public class Rental {
    /**
     * The id of the rental
     */
    private int id;

    /**
     * The id of the tenant profile who created the rental
     */
    private int profileId;

    /**
     * The id of the accommodation concerned by the rental
     */
    private int accommodationId;

    /**
     * The period of the rental
     */
    private String period;

    /**
     * The state of the rental
     */
    private boolean isRequest;

    /**
     * The constructor of the rental
     *
     * @param profileId       the id of the tenant profile who created the rental
     * @param accommodationId the id of the accommodation concerned by the rental
     * @param period          the period of the rental
     * @param isRequest       the state of the rental
     */
    public Rental(int id, int profileId, int accommodationId, String period, boolean isRequest) {
        this.id = id;
        this.profileId = profileId;
        this.accommodationId = accommodationId;
        this.period = period;
        this.isRequest = isRequest;
    }

    /**
     * Retrieves the id of the rental.
     *
     * @return The rental's id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Defines the id of the rental.
     *
     * @param id The rental's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the id of the tenant profile who created the rental.
     *
     * @return The rental's tenant profile id.
     */
    public int getProfileId() {
        return this.profileId;
    }

    /**
     * Defines the id of the tenant profile who created the rental.
     *
     * @param profileId The rental's tenant profile id.
     */
    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    /**
     * Retrieves the id of the accommodation concerned by the rental.
     *
     * @return The rental's accommodation id.
     */
    public int getAccommodationId() {
        return this.accommodationId;
    }

    /**
     * Defines the id of the accommodation concerned by the rental.
     *
     * @param accommodationId The rental's accommodation id.
     */
    public void setAccommodationId(int accommodationId) {
        this.accommodationId = accommodationId;
    }

    /**
     * Retrieves the period of the rental.
     *
     * @return The rental's period.
     */
    public String getPeriod() {
        return this.period;
    }

    /**
     * Defines the period of the rental.
     *
     * @param period The rental's period.
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Retrieves the state of the rental.
     *
     * @return The rental's state.
     */
    public boolean getIsRequest() {
        return this.isRequest;
    }

    /**
     * Defines the state of the rental.
     *
     * @param isRequest The rental's state.
     */
    public void setIsRequest(boolean isRequest) {
        this.isRequest = isRequest;
    }
}
