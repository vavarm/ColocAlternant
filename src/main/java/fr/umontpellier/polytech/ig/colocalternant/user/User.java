package fr.umontpellier.polytech.ig.colocalternant.user;

import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;

import java.util.ArrayList;

/**
 * User class
 */
public class User {
    /**
     * The id of the user
     */
    private int id;
    /**
     * The first name of the user
     */
    private String firstName;
    /**
     * The last name of the user
     */
    private String lastName;
    /**
     * The age of the user
     */
    private int age;
    /**
     * The email of the user
     */
    private String email;
    /**
     * The password of the user
     */
    private String password;
    /**
     * The photo of the user
     */
    private String photo;
    /**
     * The ban status of the user
     */
    private boolean isBanned;

    /**
     * Constructor of the user
     * @param id the id of the user
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param age the age of the user
     * @param email the email of the user
     * @param password the password of the user
     * @param photo the path to the photo of the user
     */
    public User(int id, String firstName, String lastName, int age, String email, String password, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    /**
     * Retrieves the unique identifier of the user.
     * @return The user's identifier.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Defines the unique identifier of the user.
     * @param id The user's identifier.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the first name of the user.
     * @return The user's first name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Defines the first name of the user.
     * @param firstName The user's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the user.
     * @return The user's last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Defines the last name of the user.
     * @param lastName The user's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the age of the user.
     * @return The user's age.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Defines the age of the user.
     * @param age The user's age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Retrieves the email of the user.
     * @return The user's email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Defines the email of the user.
     * @param email The user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password of the user.
     * @return The user's password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Defines the password of the user.
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the photo of the user.
     * @return The user's photo path.
     */
    public String getPhoto() {
        return this.photo;
    }

    /**
     * Defines the photo of the user.
     * @param photo The user's photo path.
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Retrieves the ban status of the user.
     * @return The user's ban status.
     */
    public boolean getIsBanned() {
        return this.isBanned;
    }

    /**
     * Defines the ban status of the user.
     * @param isBanned The user's ban status.
     */
    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    /**
     * Returns a string representation of the user.
     * @return The user's string representation.
     */
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
                + ", email=" + email + ", password=" + password + ", photo=" + photo + '}';
    }
}