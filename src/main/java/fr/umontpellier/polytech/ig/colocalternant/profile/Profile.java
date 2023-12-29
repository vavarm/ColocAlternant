package fr.umontpellier.polytech.ig.colocalternant.profile;

public class Profile {
    /**
     * The id of the user
     */
    private int userId;
    /**
     * The role of the profile
     */
    private Role role;

    /**
     * Constructor of the profile
     * @param id the id of the profile
     * @param role the role of the profile
     */
    public Profile(int id, Role role) {
        this.userId = id;
        this.role = role;
    }

    /**
     * Retrieves the unique identifier of the profile.
     * @return The profile's identifier.
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Defines the unique identifier of the profile.
     * @param id The profile's identifier.
     */
    public void setUserId(int id) {
        this.userId = id;
    }

    /**
     * Retrieves the role of the profile.
     * @return The profile's first name.
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Defines the first name of the profile.
     * @param role The profile's first name.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Returns a string representation of the profile.
     * @return The profile's string representation.
     */
    public String toString() {
        return "Profile{" + "id=" + userId + ", role=" + role + '}';
    }
}
