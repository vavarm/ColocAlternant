package fr.umontpellier.polytech.ig.colocalternant.profile;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Profile class
 */
public class Profile {
    /**
     * The id of the profile
     */
    private int id;

    /**
     * The visibility of the profile
     */
    private boolean isPublic;
    /**
     * The description of the profile
     */
    private String description;
    /**
     * The id of the user
     */
    private int userID;
    /**
     * The role of the profile
     */
    private EnumRole role;

    /**
     * Constructor of the user
     * @param isPublic the visibility of the profile
     * @param description the description of the profile
     * @param userID the id of the profile
     * @param role the role of the profile
     */
    public Profile(int id, boolean isPublic, String description, int userID, EnumRole role) {
        this.id = id;
        this.isPublic = isPublic;
        this.description = description;
        this.userID = userID;
        this.role = role;
    }

    /**
     * Retrieves the id of the profile.
     * @return The profile's role.
     */
    public int getId() { return this.id; }

    /**
     * Defines the id of the profile.
     * @param id The profile's role.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Retrieves the role of the profile.
     * @return The profile's role.
     */
    public EnumRole getRole() { return this.role; }

    /**
     * Defines the role of the profile.
     * @param role The profile's role.
     */
    public void setRole(EnumRole role) { this.role = role; }

    /**
     * Retrieves the description of the profile.
     * @return The profile's description.
     */
    public String getDescription() { return this.description; }

    /**
     * Defines the description of the profile.
     * @param description The profile's description.
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Retrieves the unique id of the profile.
     * @return The profile's identifier.
     */
    public int getUserID() { return this.userID; }

    /**
     * Defines the unique identifier of the user.
     * @param userID The profile's identifier.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Retrieves the visibility of the profile.
     * @return The profile's visibility.
     */
    public boolean getIsPublic() { return this.isPublic; }


    public void setIsPublic(boolean isPublic) { this.isPublic = isPublic; }

    /**
     * Returns a string representation of the profile.
     * @return The profile's string representation.
     */
    public String toString() {
        return "Profile{"
                + "userId=" + userID
                + ", isPublic=" + isPublic
                + ", description=" + description
                + ", role=" + role
                + '}';
    }
}
